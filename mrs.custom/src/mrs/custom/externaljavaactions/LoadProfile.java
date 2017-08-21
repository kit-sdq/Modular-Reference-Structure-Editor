package mrs.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

import mrs.Layer;
import mrs.Metamodel;
import mrs.custom.util.Util;

public class LoadProfile implements IExternalJavaAction {
    public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        Layer layer = (Layer) selections.iterator().next();
       
        String uriText = Util.openLoadResourceFromWorkspaceDialog(SHELL);
        if (uriText == null || uriText.isEmpty()) //e.g. on cancel
            return;


        URI uri = URI.createURI(uriText);
        
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);

        Profile profile = getProfileByURI(uri, editingDomain);
        
        if (!layer.getModularReferenceStructure().getLoadedProfiles().contains(profile)) { 
            layer.getModularReferenceStructure().getLoadedProfiles().add(profile);
        }
        addMetamodelsToLayer(profile, layer);
              
       
    }

    private Profile getProfileByURI(URI uri, TransactionalEditingDomain editingDomain) {
        ResourceSet resourceSet = editingDomain.getResourceSet();
        
        Resource profileResource = resourceSet.getResource(uri, true);
        
        Profile profile = (Profile) profileResource.getContents().get(0);
        
        return profile;
    }
    
    private void addMetamodelsToLayer(Profile profile, Layer layer) {
        Collection<Metamodel> loadedMetamodels = Util.getAllMetamodels(layer.getModularReferenceStructure());
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
        for(Stereotype stereotype : profile.getStereotypes()) {
            for (EReference reference : stereotype.getEReferences()) {
                EPackage mainGenPackage = Util.getTopMostPackage(reference.getEType().getEPackage());
                addMetamodelToLayer(mainGenPackage, editingDomain, loadedMetamodels, layer);
            }
            for (Extension extension: stereotype.getExtensions()) {
                EPackage mainGenPackage = Util.getTopMostPackage(extension.getTarget().getEPackage());
                addMetamodelToLayer(mainGenPackage, editingDomain, loadedMetamodels, layer);
            }
        }
    }
    
    private void addMetamodelToLayer(EPackage mainGenPackage, TransactionalEditingDomain editingDomain, Collection<Metamodel> loadedMetamodels, Layer layer) {
        EPackage mainEcorePackage = Util.getEcorePackageFromRegisteredPackage(mainGenPackage, editingDomain);
        
        Metamodel correspondingMetamodel = Util.getCorrespondingMetamodel(mainEcorePackage, loadedMetamodels);
        if (correspondingMetamodel == null) { //If no metamodel found, load it
            correspondingMetamodel = Util.createMetamodel(mainEcorePackage, layer);
        }
    }
    
    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}

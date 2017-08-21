package mrs.custom.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Shell;

import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.MrsFactory;

public class Util {

    /**
     * Returns the main {@link EPackage} at the specified {@link URI} and loads it in the {@link ResourceSet} of the
     * {@link TransactionalEditingDomain} if is not already loaded
     * 
     * @param uri the uri where the resource can be found
     * @param editingDomain the current transactional editing domain, in which the resource should be loaded
     * @return the main EPackage of the metamodel at the URI
     */
    public static EPackage getMainPackageByURI(URI uri, TransactionalEditingDomain editingDomain) {
        ResourceSet resourceSet = editingDomain.getResourceSet();

        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",
                new EcoreResourceFactoryImpl());

        Resource metamodelResource = resourceSet.getResource(uri, true);

        if (metamodelResource == null) {
            System.out.println("Could not load resource " + uri.toString());
            return null;
        }

        EPackage metamodel = (EPackage) metamodelResource.getContents().get(0);

        return metamodel;
    }
    
    /**
     * Creates a {@link Metamodel} in the given layer and with the given main package 
     * @param mainPackage the main package of the metamodel
     * @param layer the layer of the metamodel inside the {@link ModularReferenceStructure}
     * @return the just created metamodel
     */
    public static Metamodel createMetamodel(EPackage mainPackage, Layer layer) {
        Metamodel metamodel = MrsFactory.eINSTANCE.createMetamodel();
        metamodel.setMainPackage(mainPackage);
        metamodel.setLayer(layer);
        metamodel.setName(mainPackage.getName());
        return metamodel;
        
    }
    
    /**
     * Opens a {@link LoadResourceFromWorkspaceDialog} and returns the resulting URI text
     * @param shell The {@link Shell} in which the dialog will be created
     * @return a String containing the URI selected by the user
     */
    public static String openLoadResourceFromWorkspaceDialog(Shell shell) {
        LoadResourceFromWorkspaceDialog dialog = new LoadResourceFromWorkspaceDialog(shell);
        dialog.open();
        String uriText = dialog.getURIText();
        return uriText;
    }
    
    /**
     * Returns the first {@link Metamodel}  found in the metamodels collection, whose main package is mainPackage
     * @param mainPackage the main package
     * @param metamodels the collection of metamodels to be looked into
     * @return the metamodel if found, null if not
     */
    public static Metamodel getCorrespondingMetamodel(EPackage mainPackage, Collection<Metamodel> metamodels) {
        for (Metamodel m : metamodels) {
            if (m.getMainPackage() == mainPackage)
                return m;
        }
        return null;
    }
    
    /**
     * Returns all metamodels present in the ModularReferenceStructure
     * 
     * @param mrs
     *            the ModularReferenceStructure
     * @return a Collection containing the metamodels
     */
    public static Collection<Metamodel> getAllMetamodels(ModularReferenceStructure mrs) {
        Collection<Metamodel> result = new ArrayList<Metamodel>();
        for (Layer l : mrs.getLayers()) {
            result.addAll(l.getMetamodels());
        }
        return result;
    }
    
    /**
     * Returns the main EPackage of the metamodel containing ePackage
     * 
     * @param ePackage
     * @return ePackage if it has no super package, otherwise the top most package of the super
     *         package of ePackage
     */
    public static EPackage getTopMostPackage(EPackage ePackage) {
        if (ePackage.getESuperPackage() == null)
            return ePackage;
        else
            return getTopMostPackage(ePackage.getESuperPackage());
    }
    
    public static EPackage getEcorePackageFromRegisteredPackage(EPackage registeredPackage, TransactionalEditingDomain editingDomain) {
        ResourceSet resourceSet = editingDomain.getResourceSet();
        String nsURI = registeredPackage.getNsURI();
        resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));

        Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
        URI location = ePackageNsURItoGenModelLocationMap.get(nsURI);
        Resource resource = resourceSet.getResource(location, true);
        EcoreUtil.resolveAll(resource);
        EPackage ecorePackage = ((GenModel) resource.getContents().get(0)).getGenPackages().get(0).getEcorePackage();
        return ecorePackage;
    }

}

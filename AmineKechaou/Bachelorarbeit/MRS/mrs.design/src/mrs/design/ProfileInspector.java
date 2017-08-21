package mrs.design;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

import mrs.Metamodel;
import mrs.custom.util.Util;

public class ProfileInspector {
    Metamodel metamodel;

    public ProfileInspector(Metamodel metamodel) {
        this.metamodel = metamodel;
    }

    public Map<Metamodel, Set<Stereotype>> getExtensions() {
        Map<Metamodel, Set<Stereotype>> extensions = new HashMap<Metamodel, Set<Stereotype>>();
        EList<Profile> loadedProfiles = metamodel.getLayer().getModularReferenceStructure().getLoadedProfiles();
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(metamodel);
        
        for (Profile profile : loadedProfiles) {
            for (Stereotype stereotype : profile.getStereotypes()) {                
                for (EReference reference : stereotype.getEReferences()) {
                    EPackage mainReferenceGenPackage = Util.getTopMostPackage(reference.getEType().getEPackage());
                    EPackage mainReferenceEcorePackage = Util.getEcorePackageFromRegisteredPackage(mainReferenceGenPackage, editingDomain);

                    if (mainReferenceEcorePackage == metamodel.getMainPackage()) {
                        
                        for (Extension extension : stereotype.getAllExtensions()) {
                            EPackage mainExtensionGenPackage = Util.getTopMostPackage(extension.getTarget().getEPackage());
                            EPackage mainExtensionEcorePackage = Util.getEcorePackageFromRegisteredPackage(mainExtensionGenPackage, editingDomain);
                            
                            Collection<Metamodel> loadedMetamodels = Util.getAllMetamodels(metamodel.getLayer().getModularReferenceStructure());
                            Metamodel correspondingMetamodel = Util.getCorrespondingMetamodel(mainExtensionEcorePackage, loadedMetamodels);
                            if (correspondingMetamodel == null) { //If no metamodel found, load it
                                correspondingMetamodel = Util.createMetamodel(mainExtensionEcorePackage, metamodel.getLayer());
                            }
                            
                            if (extensions.containsKey(correspondingMetamodel)) {
                                extensions.get(correspondingMetamodel).add(stereotype);
                            }
                            else {
                                Set<Stereotype> stereotypes = new HashSet<Stereotype>();
                                stereotypes.add(stereotype);
                                extensions.put(correspondingMetamodel, stereotypes);                                
                            }
                        }
                        
                        break; // go to next stereotype
                    }
                }
            }
        }
        return extensions;
    }
    
}

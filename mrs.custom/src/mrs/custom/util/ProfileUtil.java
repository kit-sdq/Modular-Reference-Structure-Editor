package mrs.custom.util;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

import mrs.Layer;
import mrs.Metamodel;

public class ProfileUtil {

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

	public static void addMetamodelsToLayer(Profile profile, Layer layer) {
	    Collection<Metamodel> loadedMetamodels = MRSUtil.getAllMetamodels(layer.getModularReferenceStructure());
	    TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
	    for(Stereotype stereotype : profile.getStereotypes()) {
	        for (EReference reference : stereotype.getEReferences()) {
	            EPackage mainGenPackage = MRSUtil.getTopMostPackage(reference.getEType().getEPackage());
	            addMetamodelToLayer(mainGenPackage, editingDomain, loadedMetamodels, layer);
	        }
	        for (Extension extension: stereotype.getExtensions()) {
	            EPackage mainGenPackage = MRSUtil.getTopMostPackage(extension.getTarget().getEPackage());
	            addMetamodelToLayer(mainGenPackage, editingDomain, loadedMetamodels, layer);
	        }
	    }
	}

	private static void addMetamodelToLayer(EPackage mainGenPackage, TransactionalEditingDomain editingDomain, Collection<Metamodel> loadedMetamodels, Layer layer) {
	    EPackage mainEcorePackage = ProfileUtil.getEcorePackageFromRegisteredPackage(mainGenPackage, editingDomain);
	    
	    Metamodel correspondingMetamodel = MRSUtil.getCorrespondingMetamodel(mainEcorePackage, loadedMetamodels);
	    if (correspondingMetamodel == null) { //If no metamodel found, load it
	        correspondingMetamodel = MRSUtil.createMetamodel(mainEcorePackage, layer);
	    }
	}

	public static Profile getProfileByURI(URI uri, TransactionalEditingDomain editingDomain) {
	    ResourceSet resourceSet = editingDomain.getResourceSet();
	    
	    Resource profileResource = resourceSet.getResource(uri, true);
	    
	    Profile profile = (Profile) profileResource.getContents().get(0);
	    
	    return profile;
	}

}

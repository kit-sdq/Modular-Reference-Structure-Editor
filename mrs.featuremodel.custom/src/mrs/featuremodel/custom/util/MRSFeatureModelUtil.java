package mrs.featuremodel.custom.util;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

import featuremodel.Feature;
import featuremodel.FeatureDiagram;
import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;

public class MRSFeatureModelUtil {
	public static final String MRS_FEATURE_PROFILE = "MRSFeatureModel";
	public static final String MRS_FEATURE_DIAGRAM_STEREOTYPE = "FeatureModelSupport";
	public static final String LAYER_FEATURE_STEREOTYPE = "LinkedFeatures";
	public static final String FEATURE_METAMODEL_STEREOTYPE = "IncludesRelation";
	
	public static void applyMRSFeatureModelProfile(Resource resource) {
		ProfileAPI.applyProfile(resource, MRS_FEATURE_PROFILE);
	}
	
	public static boolean isProfileApplied(Resource resource) {
		return ProfileAPI.isProfileApplied(resource, MRS_FEATURE_PROFILE);
	}
	
	public static void applyStereotypeMRSFeatureDiagram(ModularReferenceStructure mrs) {
		StereotypeAPI.applyStereotype(mrs, MRS_FEATURE_DIAGRAM_STEREOTYPE);
	}
	
	public static void applyStereotypeLayerFeature(Layer layer) {
		StereotypeAPI.applyStereotype(layer, LAYER_FEATURE_STEREOTYPE);
	}	
	
	public static void applyStereotypeFeatureMetamodel(Feature feature) {
		StereotypeAPI.applyStereotype(feature, FEATURE_METAMODEL_STEREOTYPE);
	}
		
	public static boolean isStereotypeMRSFeatureDiagramApplied(ModularReferenceStructure mrs) {
		return StereotypeAPI.isStereotypeApplied(mrs, MRS_FEATURE_DIAGRAM_STEREOTYPE);
	}
	
	public static boolean isStereotypeLayerFeatureApplied(Layer layer) {
		return StereotypeAPI.isStereotypeApplied(layer, LAYER_FEATURE_STEREOTYPE);
	}
	
	public static boolean isStereotypeFeatureMetamodelApplied(Feature feature) {
		return StereotypeAPI.isStereotypeApplied(feature, FEATURE_METAMODEL_STEREOTYPE);
	}
	
	public static StereotypeApplication getStereotypeApplicationMRSFeatureDiagram(ModularReferenceStructure mrs) {
		Optional<StereotypeApplication> stereotypeApplicationOptional = StereotypeAPI
				.getStereotypeApplications(mrs, MRS_FEATURE_DIAGRAM_STEREOTYPE).stream().findFirst();
		return stereotypeApplicationOptional.orElse(null);
	}
	
	public static StereotypeApplication getStereotypeApplicationLayerFeature(Layer layer) {
		Optional<StereotypeApplication> stereotypeApplicationOptional = StereotypeAPI
				.getStereotypeApplications(layer, LAYER_FEATURE_STEREOTYPE).stream().findFirst();
		return stereotypeApplicationOptional.orElse(null);
	}
	
	public static StereotypeApplication getStereotypeApplicationFeatureMetamodel(Feature feature) {
		Optional<StereotypeApplication> stereotypeApplicationOptional = StereotypeAPI
				.getStereotypeApplications(feature, FEATURE_METAMODEL_STEREOTYPE).stream().findFirst();
		return stereotypeApplicationOptional.orElse(null);
	}
	
	public static EList<FeatureDiagram> getLoadedFeatureDiagrams(ModularReferenceStructure mrs) {
		EList<FeatureDiagram> result = new BasicEList<FeatureDiagram>();
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMRSFeatureDiagram(mrs);
		if (stereotypeApplication != null) {
			
			List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(FeatureDiagram.class)).collect(Collectors.toList());
			
			if (refs != null && !refs.isEmpty())
				result.addAll((EList<? extends FeatureDiagram>) stereotypeApplication.eGet(refs.get(0)));
					
		}
		return result;
	}
	
	public static void loadFeatureDiagram(ModularReferenceStructure mrs, FeatureDiagram featureDiagram) {
		if (!isProfileApplied(mrs.eResource()))
			applyMRSFeatureModelProfile(mrs.eResource());
		
		if (!isStereotypeMRSFeatureDiagramApplied(mrs))
			applyStereotypeMRSFeatureDiagram(mrs);
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMRSFeatureDiagram(mrs);

		List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(FeatureDiagram.class)).collect(Collectors.toList());
		EReference FeatureDiagramsReference = refs.get(0);
		
		EList<FeatureDiagram> loadedFeatureDiagrams = getLoadedFeatureDiagrams(mrs);
		if (!loadedFeatureDiagrams.contains(featureDiagram))
			loadedFeatureDiagrams.add(featureDiagram);
		
		stereotypeApplication.eSet(FeatureDiagramsReference, loadedFeatureDiagrams);
	}
	
	public static void unloadFeatureDiagrams(ModularReferenceStructure mrs, Collection<FeatureDiagram> featureDiagrams) {
		StereotypeApplication stereotypeApplication = getStereotypeApplicationMRSFeatureDiagram(mrs);

		List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(FeatureDiagram.class)).collect(Collectors.toList());
		EReference FeatureDiagramReference = refs.get(0);
		
		EList<FeatureDiagram> loadedFeatureDiagrams = getLoadedFeatureDiagrams(mrs);
		
		loadedFeatureDiagrams.removeAll(featureDiagrams);
		
		stereotypeApplication.eSet(FeatureDiagramReference, loadedFeatureDiagrams);
	}
	
	public static EList<Feature> getLoadedFeatures(Layer layer) {
		EList<Feature> result = new BasicEList<Feature>();
		StereotypeApplication stereotypeApplication = getStereotypeApplicationLayerFeature(layer);
		if (stereotypeApplication != null) {
			
			List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(Feature.class)).collect(Collectors.toList());
			
			if (refs != null && !refs.isEmpty())
				result.addAll((EList<? extends Feature>) stereotypeApplication.eGet(refs.get(0)));
					
		}
		return result;
	}
	
	public static void loadFeatures(Layer layer, Collection<Feature> features) {
		ModularReferenceStructure mrs = layer.getModularReferenceStructure();
		if (!isProfileApplied(mrs.eResource()))
			applyMRSFeatureModelProfile(mrs.eResource());
		
		if (!isStereotypeLayerFeatureApplied(layer))
			applyStereotypeLayerFeature(layer);
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationLayerFeature(layer);

		List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(Feature.class)).collect(Collectors.toList());
		EReference FeatureReference = refs.get(0);
		
		EList<Feature> loadedFeatures = getLoadedFeatures(layer);
		
		for (Feature feature : features) {
			if (!loadedFeatures.contains(feature))
				loadedFeatures.add(feature);
		}
		
		stereotypeApplication.eSet(FeatureReference, loadedFeatures);
	}
	public static void unloadFeatures(Layer layer, Collection<Feature> features) {
		StereotypeApplication stereotypeApplication = getStereotypeApplicationLayerFeature(layer);

		List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(Feature.class)).collect(Collectors.toList());
		EReference FeatureReference = refs.get(0);
		
		EList<Feature> loadedFeatures = getLoadedFeatures(layer);
		
		loadedFeatures.removeAll(features);
		
		stereotypeApplication.eSet(FeatureReference, loadedFeatures);
	}
		
	public static EList<Metamodel> getIncludedMetamodels(Feature feature) {
		EList<Metamodel> result = new BasicEList<Metamodel>();
		StereotypeApplication stereotypeApplication = getStereotypeApplicationFeatureMetamodel(feature);
		if (stereotypeApplication != null) {
			List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(Metamodel.class)).collect(Collectors.toList());
			
			if (refs != null && !refs.isEmpty())
				result.addAll((EList<? extends Metamodel>) stereotypeApplication.eGet(refs.get(0)));
					
		}
		return result;
	}
	
	public static void includeMetamodelIntoFeature(Metamodel metamodel, Feature feature) {
		FeatureDiagram featureDiagram = (FeatureDiagram) feature.eContainer();
		if (!isProfileApplied(featureDiagram.eResource()))
			applyMRSFeatureModelProfile(featureDiagram.eResource());
		
		if (!isStereotypeFeatureMetamodelApplied(feature))
			applyStereotypeFeatureMetamodel(feature);
		
		StereotypeApplication stereotypeApplication = getStereotypeApplicationFeatureMetamodel(feature);

		List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(Metamodel.class)).collect(Collectors.toList());
		EReference metamodelReference = refs.get(0);
		
		EList<Metamodel> includedMetamodels = getIncludedMetamodels(feature);
		
		if (!includedMetamodels.contains(metamodel))
			includedMetamodels.add(metamodel);
		
		stereotypeApplication.eSet(metamodelReference, includedMetamodels);
	}
}

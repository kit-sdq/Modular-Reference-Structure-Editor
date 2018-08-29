package mrs.featuremodel.design;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import featuremodel.Feature;
import featuremodel.FeatureDiagram;
import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.featuremodel.custom.util.MRSFeatureModelUtil;
import mrs.featuremodel.custom.util.MetamodelURLUtil;

public class Services {
		
	public Collection<FeatureDiagram> getFeatureDiagrams(ModularReferenceStructure mrs) {
		return MRSFeatureModelUtil.getLoadedFeatureDiagrams(mrs);
	}
	
	public Collection<FeatureDiagram> getFeatureDiagramsWithNoRootFeature(ModularReferenceStructure mrs) {
		return getFeatureDiagrams(mrs).stream().filter(f -> f.getRootFeature() == null).collect(Collectors.toList());
	}
	
	public Collection<Feature> getFeatures(Layer layer) {
		Collection<Feature> loadedFeatures = MRSFeatureModelUtil.getLoadedFeatures(layer);
		
		ModularReferenceStructure mrs = layer.getModularReferenceStructure();
		Collection<FeatureDiagram> loadedFeatureDiagrams = getFeatureDiagrams(mrs);
		
		return loadedFeatures.stream().filter(f -> loadedFeatureDiagrams.contains(f.eContainer())).collect(Collectors.toList());
	}
	
	public Collection<Metamodel> getIncludedMetamodels(Feature feature) {
		return MRSFeatureModelUtil.getIncludedMetamodels(feature);
	}
	
	public void includeMetamodelIntoFeature(Metamodel metamodel, Feature feature) {
		MRSFeatureModelUtil.includeMetamodelIntoFeature(metamodel, feature);
	}
	
	public void removeMetamodelFromFeature(Metamodel metamodel, Feature feature) {
		MRSFeatureModelUtil.removeMetamodelFromFeature(metamodel, feature);
	}
	
	public void addFeatureToLayer(Layer layer, EObject feature) {
		Collection<Feature> features = new ArrayList<Feature>();
		features.add((Feature) feature);
		MRSFeatureModelUtil.loadFeatures(layer, features);
	}
	
	public void removeFeatureFromLayer(Layer layer, Feature feature) {
		Collection<Feature> features = new ArrayList<Feature>();
		features.add(feature);
		MRSFeatureModelUtil.unloadFeatures(layer, features);
	}
	
	public ModularReferenceStructure getModularReferenceStructure(EObject eObject) {
		if (eObject instanceof ModularReferenceStructure)
			return (ModularReferenceStructure) eObject;
		else if (eObject instanceof Layer)
			return ((Layer) eObject).getModularReferenceStructure();
		else
			return null;
	}
	
	public void unloadFeatureDiagram(FeatureDiagram featureDiagram, ModularReferenceStructure mrs) {
		Collection<FeatureDiagram> featureDiagrams = new ArrayList<FeatureDiagram>();
		featureDiagrams.add(featureDiagram);
		MRSFeatureModelUtil.unloadFeatureDiagrams(mrs, featureDiagrams);
		for (Layer layer : mrs.getLayers()) {
			Collection<Feature> loadedFeatures = MRSFeatureModelUtil.getLoadedFeatures(layer).stream().filter(f -> f.eContainer() == featureDiagram).collect(Collectors.toList());
			MRSFeatureModelUtil.unloadFeatures(layer, loadedFeatures);
		}
	}
	
	public Collection<EObject> getListOfFeatureDiagramsAndTheirFeatures(ModularReferenceStructure mrs) {
		Collection<EObject> result = new ArrayList<EObject>();
		
		Collection<FeatureDiagram> featureDiagrams = getFeatureDiagrams(mrs);
		result.addAll(featureDiagrams);
		
		for (FeatureDiagram featureDiagram : featureDiagrams) {
			result.addAll(featureDiagram.getFeatures());
		}
		return result;
	}
	
	public String getMetamodelName(Metamodel metamodel) {
		String url = MetamodelURLUtil.getURL(metamodel);
		if (url.isEmpty()) {
			return metamodel.getName();
		}
		else {
			return metamodel.getName() + "\nplugin:" + url;
		}
	}
	
	public EObject print(EObject o) {
		System.out.println(o);
		return o;
	}
}

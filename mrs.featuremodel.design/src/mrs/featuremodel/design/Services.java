package mrs.featuremodel.design;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

import featuremodel.Feature;
import featuremodel.FeatureDiagram;
import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.featuremodel.custom.util.MRSFeatureModelUtil;
import mrs.featuremodel.custom.util.MetamodelURIUtil;

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

	
	public String getMetamodelURI(Metamodel metamodel) {
		return MetamodelURIUtil.getURI(metamodel);
	}
	
	public Collection<Feature> getReadyToInstallFeatures(DDiagram diagram) {
		DAnnotation annotation = getMarkedToInstallDAnnotation(diagram);
		return annotation.getReferences().stream().map(o -> ((DDiagramElement) o).getTarget()).filter(o -> o instanceof Feature).map(o -> (Feature) o).collect(Collectors.toList());
	}
	
	public void markOrUnmarkAsReadyToInstall(DDiagramElement element) {
		if (!(element.getTarget() instanceof Feature))
			return;
		DDiagram diagram = element.getParentDiagram();
		DAnnotation annotation = getMarkedToInstallDAnnotation(diagram);
		if (annotation.getReferences().contains(element)) {
			annotation.getReferences().remove(element);
		} else {
			annotation.getReferences().add(element);			
		}
		for (EObject o : annotation.getReferences()) {
			System.out.println(o);
		}
	}
	public boolean isMarked(DDiagramElement element) {
		DAnnotation annotation = getMarkedToInstallDAnnotation(element.getParentDiagram());
		return annotation.getReferences().contains(element);
	}
	
	public DAnnotation getMarkedToInstallDAnnotation(DDiagram diagram) {
		DAnnotation annotation = diagram.getDAnnotation("marked");
		if (annotation == null) {
			annotation = DescriptionFactory.eINSTANCE.createDAnnotation();
			annotation.setSource("marked");
			diagram.getEAnnotations().add(annotation);
		}
		return annotation;
	}
	
	
	public EObject print(EObject o) {
		System.out.println(o);
		System.out.println(o.getClass());
		return o;
	}
}

package mrs.featuremodel.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import featuremodel.Feature;
import mrs.Layer;
import mrs.ModularReferenceStructure;
import mrs.featuremodel.custom.util.MRSFeatureModelUtil;

public class SetLoadedFeatures implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Layer layer = (Layer) parameters.get("layer");
		Collection<Feature> features = (Collection<Feature>) parameters.get("features");
		
		ModularReferenceStructure mrs = layer.getModularReferenceStructure();
		if (!MRSFeatureModelUtil.isProfileApplied(mrs.eResource()))
			MRSFeatureModelUtil.applyMRSFeatureModelProfile(mrs.eResource());
		
		if (!MRSFeatureModelUtil.isStereotypeLayerFeatureApplied(layer))
			MRSFeatureModelUtil.applyStereotypeLayerFeature(layer);
		
		StereotypeApplication stereotypeApplication = MRSFeatureModelUtil.getStereotypeApplicationLayerFeature(layer);

		List<EReference> refs = stereotypeApplication.getStereotype().getEReferences().stream().filter(r -> r.getEReferenceType().getInstanceClass().equals(Feature.class)).collect(Collectors.toList());
		EReference FeatureReference = refs.get(0);
		stereotypeApplication.eSet(FeatureReference, features);

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}

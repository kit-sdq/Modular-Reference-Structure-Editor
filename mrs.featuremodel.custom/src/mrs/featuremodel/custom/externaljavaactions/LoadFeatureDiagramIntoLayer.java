package mrs.featuremodel.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import featuremodel.FeatureDiagram;
import mrs.Layer;
import mrs.ModularReferenceStructure;
import mrs.featuremodel.custom.util.MRSFeatureModelUtil;

public class LoadFeatureDiagramIntoLayer extends LoadFeatureDiagram {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Layer layer = (Layer) selections.iterator().next();
		ModularReferenceStructure mrs = layer.getModularReferenceStructure();
		FeatureDiagram featureDiagram = getFeatureDiagram(mrs);
		if (featureDiagram != null) {
			MRSFeatureModelUtil.loadFeatureDiagram(mrs, featureDiagram);
			MRSFeatureModelUtil.loadFeatures(layer, featureDiagram.getFeatures());
		}
		
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}

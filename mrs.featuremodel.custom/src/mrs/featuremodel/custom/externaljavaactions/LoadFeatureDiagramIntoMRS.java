package mrs.featuremodel.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;

import featuremodel.FeatureDiagram;
import mrs.ModularReferenceStructure;
import mrs.featuremodel.custom.util.MRSFeatureModelUtil;

public class LoadFeatureDiagramIntoMRS extends LoadFeatureDiagram {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ModularReferenceStructure mrs = (ModularReferenceStructure) selections.iterator().next();
		FeatureDiagram featureDiagram = getFeatureDiagram(mrs);

		if (featureDiagram != null) {
			MRSFeatureModelUtil.loadFeatureDiagram(mrs, featureDiagram);

			MessageDialog.openInformation(SHELL, "Load FeatureDiagram",
					"The FeatureDiagram " + featureDiagram.getName() + " has been loaded into the MRS!");
		}

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}

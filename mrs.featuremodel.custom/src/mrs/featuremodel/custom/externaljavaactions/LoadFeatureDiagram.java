package mrs.featuremodel.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;

import featuremodel.FeatureDiagram;
import mrs.ModularReferenceStructure;

public abstract class LoadFeatureDiagram implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	
	protected FeatureDiagram getFeatureDiagram(ModularReferenceStructure mrs) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(FeatureDiagram.class);
		
		
		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				mrs.eResource().getResourceSet());
		
		dialog.setProvidedService(FeatureDiagram.class);
		
		dialog.open();
		
		return (FeatureDiagram) dialog.getResult();
	}


}

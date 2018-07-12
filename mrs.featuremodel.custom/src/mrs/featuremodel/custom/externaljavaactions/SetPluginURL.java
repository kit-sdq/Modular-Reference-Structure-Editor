package mrs.featuremodel.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import mrs.Metamodel;
import mrs.featuremodel.custom.util.MetamodelURLUtil;

public class SetPluginURL implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Metamodel metamodel = (Metamodel) selections.iterator().next();
		String initialValue = MetamodelURLUtil.getURL(metamodel);
		InputDialog inputDialog = new InputDialog(SHELL, "Set Plug-in URL", "Enter the URL of the plug-in containing this metamodel", initialValue, null);
		inputDialog.open();
		String url = inputDialog.getValue();
		MetamodelURLUtil.setURL(metamodel, url);
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}

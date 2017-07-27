package mrs.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.custom.util.LoadResourceFromWorkspaceDialog;
import mrs.custom.util.Util;


public class LoadMetamodel implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Layer layer = (Layer) selections.iterator().next();
		
		Metamodel metamodel = (Metamodel) parameters.get("metamodel");
		String uriText = getURIText();
		if (uriText == null || uriText.isEmpty()) //e.g. on cancel
			return;

		
        URI uri = URI.createURI(uriText);


		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
        EPackage mainPackage = Util.getMetamodel(uri, editingDomain);
        
        // if the main EPackage was retrieved successfully and and there still isn't a metamodel with this mainPackage in the MRS
		if (mainPackage != null && !metamodelAlreadyExists(mainPackage, (ModularReferenceStructure) layer.eContainer())) {
	        metamodel.setMainPackage(mainPackage);
	        metamodel.setName(mainPackage.getName());
		}
		
		
		
		
	}
	
	private String getURIText() {
		LoadResourceFromWorkspaceDialog dialog = new LoadResourceFromWorkspaceDialog(SHELL);
		dialog.open();
		String uriText = dialog.getURIText();
		return uriText;
	}
	
	private boolean metamodelAlreadyExists(EPackage mainPackage, ModularReferenceStructure mrs) {
		for (Layer layer : mrs.getLayers()) {
			for (Metamodel metamodel : layer.getMetamodels()) {
				if (mainPackage.equals(metamodel.getMainPackage()))
					return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}
	
	

}

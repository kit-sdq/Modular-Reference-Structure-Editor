package edu.kit.kastel.sdq.mrs.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecoretools.design.ui.wizard.EcoreModelerWizard;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import edu.kit.kastel.sdq.mrs.Layer;
import edu.kit.kastel.sdq.mrs.Metamodel;
import edu.kit.kastel.sdq.mrs.custom.util.MRSUtil;

public class CreateMetamodel implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Layer layer = (Layer) selections.iterator().next();
		
		Metamodel metamodel = (Metamodel) parameters.get("metamodel");
		
		EcoreModelerWizard wizard = new EcoreModelerWizard();
		IWorkbench workbench = PlatformUI.getWorkbench();
		IStructuredSelection selection = (IStructuredSelection) workbench.getActiveWorkbenchWindow().getSelectionService().getSelection();
		wizard.init(workbench, selection);
		WizardDialog dialog = new WizardDialog(SHELL, wizard);

		dialog.open();
		
		IProject project = wizard.getNewProject();
		
		if (project == null) //e.g. on cancel
			return;
		
		IFolder modelFolder = project.getFolder("model");
		IResource modelResource = getModelResource(modelFolder);
		
		URI uri = URI.createPlatformResourceURI(modelResource.getFullPath().makeAbsolute().toString(), true);

		
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
		
        EPackage mainPackage = MRSUtil.getMainPackageByURI(uri, editingDomain);
        
        metamodel.setMainPackage(mainPackage);
        metamodel.setName(mainPackage.getName());

	}

	private IResource getModelResource(IFolder modelFolder) {
		try {
			for (IResource r : modelFolder.members()) {
				if (r.getFileExtension().equals("ecore"))
					return r;
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}

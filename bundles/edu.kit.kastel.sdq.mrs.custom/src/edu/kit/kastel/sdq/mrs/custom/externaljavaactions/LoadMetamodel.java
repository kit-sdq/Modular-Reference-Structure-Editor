package edu.kit.kastel.sdq.mrs.custom.externaljavaactions;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import edu.kit.kastel.sdq.mrs.Layer;
import edu.kit.kastel.sdq.mrs.custom.util.MRSUtil;


public class LoadMetamodel implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Layer layer = (Layer) selections.iterator().next();
		
		List<URI> uris = MRSUtil.openLoadResourceFromWorkspaceDialog(SHELL);
		if (uris == null || uris.isEmpty()) //e.g. on cancel
			return;

		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
		
		for(URI uri : uris) {
			EPackage mainPackage = MRSUtil.getMainPackageByURI(uri, editingDomain);
        
	        // if the main EPackage was retrieved successfully and there still isn't a metamodel with this mainPackage in the MRS
			if (mainPackage != null && !MRSUtil.metamodelAlreadyExists(mainPackage, layer.getModularReferenceStructure())) {
				MRSUtil.createMetamodel(mainPackage, layer);
			}			
		}
		
	}
	
	
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}
	
	

}

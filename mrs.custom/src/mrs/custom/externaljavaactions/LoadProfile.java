package mrs.custom.externaljavaactions;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;

import mrs.Layer;
import mrs.custom.util.MRSUtil;
import mrs.custom.util.ProfileUtil;

public class LoadProfile implements IExternalJavaAction {
    public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        Layer layer = (Layer) selections.iterator().next();
       
        List<URI> uris = MRSUtil.openLoadResourceFromWorkspaceDialog(SHELL);
		if (uris == null || uris.isEmpty()) //e.g. on cancel
			return;

		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
		
		for (URI uri : uris) {			
	        Profile profile = ProfileUtil.getProfileByURI(uri, editingDomain);
	        if (!layer.getModularReferenceStructure().getLoadedProfiles().contains(profile)) { 
	            layer.getModularReferenceStructure().getLoadedProfiles().add(profile);
	        }
	        ProfileUtil.addMetamodelsToLayer(profile, layer);
		}
    }

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}

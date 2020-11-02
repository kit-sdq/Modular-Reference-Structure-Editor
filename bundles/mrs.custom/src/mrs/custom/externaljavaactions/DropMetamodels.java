package mrs.custom.externaljavaactions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DResource;
import org.modelversioning.emfprofile.Profile;

import mrs.Layer;
import mrs.custom.util.MRSUtil;
import mrs.custom.util.ProfileUtil;

public class DropMetamodels implements IExternalJavaAction {
	private final String ecoreExtension = "ecore";
	private final String profileExtension = "emfprofile_diagram";
	private final String[] extensions = {ecoreExtension, profileExtension};
	
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		Object element = parameters.get("element");
		if (! (element instanceof DResource))
			return;
		
		Layer layer = (Layer) selections.iterator().next();
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(layer);
		
		File folder = new File(((DResource) element).getPath());
		Collection<File> files = getAllEcoreFiles(folder);
		for (File file : files) {
			IContainer container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(new Path(file.getAbsolutePath()));
			URI uri = URI.createPlatformResourceURI(container.getFullPath().toString(), true);
			
			if (uri.fileExtension().equals(ecoreExtension)) {
				EPackage mainPackage = MRSUtil.getMainPackageByURI(uri, editingDomain);
		        
		        // if the main EPackage was retrieved successfully and there still isn't a metamodel with this mainPackage in the MRS
				if (mainPackage != null && !MRSUtil.metamodelAlreadyExists(mainPackage, layer.getModularReferenceStructure())) {
					MRSUtil.createMetamodel(mainPackage, layer);
				}	
			} else if (uri.fileExtension().equals(profileExtension)) {
				Profile profile = ProfileUtil.getProfileByURI(uri, editingDomain);
		        if (!layer.getModularReferenceStructure().getLoadedProfiles().contains(profile)) { 
		            layer.getModularReferenceStructure().getLoadedProfiles().add(profile);
		        }
		        ProfileUtil.addMetamodelsToLayer(profile, layer);
			}	
		}
			
	}

	private Collection<File> getAllEcoreFiles(File file) {
		Collection<File> files = new ArrayList<File>();
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.isFile() && Arrays.asList(extensions).contains((new Path(f.getPath())).getFileExtension())) {
					files.add(f);
				}
				else if (f.isDirectory())
					files.addAll(getAllEcoreFiles(f));
			}
		}
		return files;
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}
	

}

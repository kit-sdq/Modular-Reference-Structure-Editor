package edu.kit.kastel.sdq.mrs.newmrsprojectfromecorewizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import edu.kit.kastel.sdq.case4lang.newprojectwizard.FileUtils;
import edu.kit.kastel.sdq.case4lang.newprojectwizard.SelectProjectsPage;
import edu.kit.kastel.sdq.mrs.Layer;
import edu.kit.kastel.sdq.mrs.Metamodel;
import edu.kit.kastel.sdq.mrs.ModularReferenceStructure;
import edu.kit.kastel.sdq.mrs.MrsFactory;
import edu.kit.kastel.sdq.mrs.MrsPackage;
import edu.kit.kastel.sdq.mrs.custom.util.MRSUtil;
import edu.kit.kastel.sdq.mrs.newmrsprojectwizard.NewMrsProjectWizard;

public class NewMrsProjectFromEcoreWizard extends NewMrsProjectWizard implements INewWizard {

	protected SelectProjectsPage selectProjectsPage;
	
	public NewMrsProjectFromEcoreWizard() {
		super();
	}

	@Override
	protected void setRootObject() {
		EClass eClassMrs = MrsPackage.eINSTANCE.getModularReferenceStructure();
		EClass eClassLayer = MrsPackage.eINSTANCE.getLayer();
		EClass eClassMetamodel = MrsPackage.eINSTANCE.getMetamodel();
		rootObject = MrsFactory.eINSTANCE.create(eClassMrs);
		EObject eObjectLayer = MrsFactory.eINSTANCE.create(eClassLayer);
		
		ModularReferenceStructure mrs = (ModularReferenceStructure) rootObject;
		Layer layer = (Layer) eObjectLayer;
		
		layer.setModularReferenceStructure(mrs);
		layer.setName("Layer 1");
		mrs.getLayers().add(layer);
		
		IStructuredSelection selectedModels = selectProjectsPage.getSelection();
		for(Object object : selectedModels) {
			try {
				IProject project = (IProject) object;
				IFolder folder = project.getFolder("model");
				folder.getFile("*.ecore");
				
				ResourceSet resourceSet = new ResourceSetImpl();
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",
			                new EcoreResourceFactoryImpl());
				
				for(IResource fileResource : folder.members()) {
					if(fileResource.getFileExtension().equals("ecore")) {
				        URI uri = URI.createPlatformResourceURI(fileResource.getFullPath().makeAbsolute().toString(), true);
				        Resource metamodelResource = resourceSet.getResource(uri, true);
				        if (metamodelResource != null && metamodelResource.getContents().size() == 1) {
				        	EObject eObjectMetamodel = MrsFactory.eINSTANCE.create(eClassMetamodel);
							Metamodel metamodel = (Metamodel) eObjectMetamodel;
							metamodel.setLayer(layer);
							metamodel.setName(fileResource.getName());
				        	metamodel.setMainPackage((EPackage) metamodelResource.getContents().get(0));
				        }
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void addPages() {
		selectProjectsPage = new SelectProjectsPage("selectProjectsPage");
		addPage(selectProjectsPage);
		newProjectCreationPage = new WizardNewProjectCreationPage("newProjectCreationPage");
		addPage(newProjectCreationPage);
	}

}

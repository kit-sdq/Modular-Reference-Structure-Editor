package edu.kit.kastel.sdq.mrs.newmrsprojectwizard;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.INewWizard;

import edu.kit.kastel.sdq.case4lang.newprojectwizard.NewProjectWizard;
import edu.kit.kastel.sdq.mrs.Layer;
import edu.kit.kastel.sdq.mrs.ModularReferenceStructure;
import edu.kit.kastel.sdq.mrs.MrsFactory;
import edu.kit.kastel.sdq.mrs.MrsPackage;
import edu.kit.kastel.sdq.mrs.presentation.MrsEditorPlugin;

public class NewMrsProjectWizard extends NewProjectWizard implements INewWizard {

	public NewMrsProjectWizard() {
		super();
	}

	@Override
	protected void setRootObject() {
		EClass eClassMrs = MrsPackage.eINSTANCE.getModularReferenceStructure();
		EClass eClassLayer = MrsPackage.eINSTANCE.getLayer();
		rootObject = MrsFactory.eINSTANCE.create(eClassMrs);
		EObject eObjectLayer = MrsFactory.eINSTANCE.create(eClassLayer);
		
		ModularReferenceStructure mrs = (ModularReferenceStructure) rootObject;
		Layer layer = (Layer) eObjectLayer;
		
		layer.setModularReferenceStructure(mrs);
		layer.setName("Layer 1");
		mrs.getLayers().add(layer);
	}

	@Override
	protected String getDefaultModelFileNameWithExtension() {
		return "My.mrs";
	}

	@Override
	protected String getEncoding() {
		return "UTF-8";
	}

	@Override
	protected EMFPlugin getEditorPlugin() {
		return MrsEditorPlugin.INSTANCE;
		
	}

	@Override
	protected String getDefaultRepresentationName() {
		return "MRS Diagram";
	}

	@Override
	protected String getDefaultRepresentationsFileNameWithExtension() {
		return "My.aird";
	}

	@Override
	protected String getRepresentationViewpoint() {
		return "mrs.viewpoint";
	}

	@Override
	protected String getDefaultPageImageDescriptorName() {
		return "NewMrs";
	}

}

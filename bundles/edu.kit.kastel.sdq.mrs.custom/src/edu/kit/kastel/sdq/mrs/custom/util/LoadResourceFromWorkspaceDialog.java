package edu.kit.kastel.sdq.mrs.custom.util;


import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class LoadResourceFromWorkspaceDialog extends LoadResourceDialog {

	public LoadResourceFromWorkspaceDialog(Shell parent) {
		super(parent);
	}

	public LoadResourceFromWorkspaceDialog(Shell parent, EditingDomain domain) {
		super(parent, domain);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
        Composite buttonComposite = (Composite)composite.getChildren()[0];
        buttonComposite.getChildren()[0].dispose();
        return composite;
		
	}

}

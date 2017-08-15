package mrs.custom.externaljavaactions;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import mrs.Metamodel;

public class SelectVisibleItems implements IExternalJavaAction {

    public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    
    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        Metamodel metamodel = (Metamodel) parameters.get("metamodel");
        List<?> choiceOfValues = (List<?>) parameters.get("choiceOfValues");
        int referenceId = (int) parameters.get("referenceId");
        
        AdapterFactoryLabelProvider adapterFactoryLabelProvider = new  AdapterFactoryLabelProvider(new EcoreItemProviderAdapterFactory());
        LabelProvider labelProvider = new LabelProvider() {
            @Override
            public String getText(Object object) {
                return adapterFactoryLabelProvider.getText(object);
            }

            @Override
            public Image getImage(Object object) {
                return ExtendedImageRegistry.getInstance().getImage(adapterFactoryLabelProvider.getImage(object));
            }   
        };
        EReference eReference = metamodel.eClass().getEReferences().get(referenceId);
        FeatureEditorDialog dialog = new FeatureEditorDialog(SHELL, labelProvider, metamodel,
                eReference, metamodel.getName(), choiceOfValues);
        dialog.open();
        
        EList<?> result = dialog.getResult();
        if (result != null) {
            metamodel.eSet(eReference, result);
        }
    }

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}

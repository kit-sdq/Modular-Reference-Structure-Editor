package mrs.featuremodel.custom.externaljavaactions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.InstallOperation;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import featuremodel.Feature;
import mrs.Metamodel;
import mrs.featuremodel.custom.util.MRSFeatureModelUtil;
import mrs.featuremodel.custom.util.MetamodelURIUtil;

public class OpenInstallationWizard implements IExternalJavaAction {
	private ProvisioningUI provisioningUI;
	private Set<URI> repositoryLocations;
	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		provisioningUI = ProvisioningUI.getDefaultUI();
		repositoryLocations = new HashSet<URI>();
		Collection<Feature> features = (Collection<Feature>) parameters.get("features");
		Set<Metamodel> metamodels = MRSFeatureModelUtil.getIncludedMetamodels(features);
		Collection<String> uriStrings = new ArrayList<String>();
		metamodels.stream().forEach(m -> uriStrings.add(MetamodelURIUtil.getURI(m)));
		Collection<URI> uriList = new ArrayList<URI>();
		uriStrings.forEach(s -> {
			try {
				uriList.add(new URI(s));
			} catch (URISyntaxException e) {

				MessageDialog.openError(SHELL, "Parsing error", "An error occured while trying to parse " + s + " into a URI: \n" + e.getMessage());
				e.printStackTrace();
				return;
			}
		});
		URI[] uris = new URI[uriList.size()];
		uris = uriList.toArray(uris);
		repositoryLocations.addAll(uriList);
		
		try {
			Collection<IInstallableUnit> ius = computeInstallableUnits();
			final InstallOperation installOperation = provisioningUI.getInstallOperation(ius, uris);
			installOperation.resolveModal(new NullProgressMonitor());
			provisioningUI.openInstallWizard(ius, installOperation, null);
		} catch (ProvisionException | OperationCanceledException e) {
			MessageDialog.openError(SHELL, "Error while opening the installation wizard", "An error occured while trying to open the installation wizard: \n" + e.getMessage());
			e.printStackTrace();
		}
	}


	private Collection<IInstallableUnit> computeInstallableUnits() throws ProvisionException, OperationCanceledException {
		List<IMetadataRepository> repositories = addRepositories();
		final List<IInstallableUnit> installableUnits = queryInstallableUnits(repositories);
		return installableUnits;
	}

	private List<IInstallableUnit> queryInstallableUnits(List<IMetadataRepository> repositories) {
		final List<IInstallableUnit> installableUnits = new ArrayList<IInstallableUnit>();

		for (final IMetadataRepository repository : repositories) {
			//final Set<String> installableUnitIdsThisRepository = getDescriptorIds(repository);
			IQuery<IInstallableUnit> query = QueryUtil.createMatchQuery( //
					"id ~= /*.feature.group/ && " + //$NON-NLS-1$
							"properties['org.eclipse.equinox.p2.type.group'] == true ");//$NON-NLS-1$
			IQueryResult<IInstallableUnit> result = repository.query(query,
					new NullProgressMonitor());
			for (Iterator<IInstallableUnit> iter = result.iterator(); iter
					.hasNext();) {
				IInstallableUnit iu = iter.next();
				String id = iu.getId();
//				if (installableUnitIdsThisRepository.contains(id.substring(0,
//						id.indexOf(P2_FEATURE_GROUP_SUFFIX)))) {
//					installableUnits.add(iu);
//				}
				installableUnits.add(iu);
				
			}
		}
		return installableUnits;
	}

	private List<IMetadataRepository> addRepositories() throws ProvisionException, OperationCanceledException {
		ProvisioningSession session = provisioningUI.getSession();
		
		ArrayList<IMetadataRepository> repositories = new ArrayList<IMetadataRepository>();
		IMetadataRepositoryManager manager = (IMetadataRepositoryManager) session.getProvisioningAgent().getService(IMetadataRepositoryManager.SERVICE_NAME);
		for (URI uri : repositoryLocations) {
			IMetadataRepository repository = manager.loadRepository(uri, new NullProgressMonitor());
			repositories.add(repository);
		}
		return repositories;
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return true;
	}

	
}

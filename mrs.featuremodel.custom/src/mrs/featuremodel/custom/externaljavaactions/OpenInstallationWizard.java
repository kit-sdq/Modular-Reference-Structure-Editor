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
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

public class OpenInstallationWizard implements IExternalJavaAction {
	private ProvisioningUI provisioningUI;
	private Set<URI> repositoryLocations;
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		provisioningUI = ProvisioningUI.getDefaultUI();
		repositoryLocations = new HashSet<URI>();
		URI uri = null;
		try {
			uri = new URI("https://sdqweb.ipd.kit.edu/eclipse/emf-profiles-sirius-editor/nightly/");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		URI[] uris = {uri};
		repositoryLocations.add(uri);
		try {
			Collection<IInstallableUnit> ius = computeInstallableUnits();
			final InstallOperation installOperation = provisioningUI.getInstallOperation(ius, uris);
			installOperation.resolveModal(new NullProgressMonitor());
			provisioningUI.openInstallWizard(ius, installOperation, null);
		} catch (ProvisionException | OperationCanceledException e) {
			// TODO Auto-generated catch block
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

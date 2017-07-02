package mrs.custom.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class Util {

	public static EPackage getMetamodel(URI uri, TransactionalEditingDomain editingDomain) {
		ResourceSet resourceSet = editingDomain.getResourceSet();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		
		Resource metamodelResource = resourceSet.getResource(uri, true);
		
        if (metamodelResource == null) {
        	System.out.println("Could not load resource " + uri.toString());
        	return null;
        }
        
        EPackage metamodel = (EPackage) metamodelResource.getContents().get(0);
        
        return metamodel;
	}
	
}

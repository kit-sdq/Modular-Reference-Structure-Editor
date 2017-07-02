package mrs.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;

import mrs.Metamodel;

public class MetamodelInvestigator {
	private Metamodel metamodel;
	private Map<Metamodel, Set<EClassifier>> dependencies;
	private Collection<Metamodel> metamodels;
	
	public MetamodelInvestigator(Metamodel metamodel) {
		this.metamodel = metamodel;
		this.metamodels = Services.getAllMetamodels(metamodel.getLayer().getModularReferenceStructure());
		this.dependencies = new HashMap<Metamodel, Set<EClassifier>>();
	}
	
	public void computeDependencies() {
		for (EClassifier c : getEAllClassifiers(metamodel.getMainPackage())) {
			if (!(c instanceof EClass)) //e.g. c is EDataType or EEnum... Only EClass can depend on other elements
				continue;
			
			EClass eClass = (EClass) c;
			
			eClass.getESuperTypes().forEach(x -> addDependency(x));
			
			eClass.getEReferences().forEach(x -> {
				addDependency(x.getEType());
				visitGenericRef(x.getEGenericType());
			});
			
			eClass.getETypeParameters().forEach(x -> visitTypeParam(x));
			
			eClass.getEGenericSuperTypes().forEach(x -> visitGenericSuperType(x));
			
			eClass.getEOperations().forEach(x -> {
				addDependency(x.getEType());
				
				x.getEParameters().forEach(y -> {
					addDependency(y.getEType());
					visitGenericType(y.getEGenericType());
				});

				visitGenericType(x.getEGenericType());
				
				x.getETypeParameters().forEach(y -> visitTypeParam(y));
				
			});
		}
	}
	
	public Set<Metamodel> getReferencedMetamodels() {
		return dependencies.keySet();
	}
	public Set<EClassifier> getReferencedEClassifiers(Metamodel metamodel) {
		return dependencies.get(metamodel);
	}
	
	private void addDependency(EClassifier eClassifier) {
		
		EPackage mainPackage = Services.getTopMostPackage(eClassifier.getEPackage());
		
		//if the referenced metamodel is the current metamodel itself, do nothing
		if (mainPackage == metamodel.getMainPackage())
			return;
		
		Metamodel dependency = getCorrespondingMetamodel(mainPackage);
		
		if (dependencies.containsKey(dependency)) {
			dependencies.get(dependency).add(eClassifier);
		}
		else {
			Set<EClassifier> eClassifiers = new HashSet<EClassifier>();
			eClassifiers.add(eClassifier);
			dependencies.put(dependency, eClassifiers);
			
		}
	}
	
	private Metamodel getCorrespondingMetamodel (EPackage mainPackage) {
		for (Metamodel m : metamodels) {
			if (m.getMainPackage() == mainPackage)
				return m;
		}
		return null;
	}
    private void visitTypeParam(ETypeParameter typeParam) {
        typeParam.getEBounds().forEach(bound -> visitGenericType(bound));
    }

    private void visitGenericSuperType(EGenericType genericSuperType) {
        if (genericSuperType.getETypeArguments().size() > 0)
            visitGenericType(genericSuperType);
    }

    private void visitGenericRef(EGenericType genericTypeOfRef) {
        if (genericTypeOfRef.getETypeArguments().size() > 0)
            visitGenericType(genericTypeOfRef);
    }
    
    private void visitGenericType(EGenericType genericType) {
        if (genericType == null)
            return;

        EClassifier eClassifier = genericType.getEClassifier();
        if (eClassifier != null) {
            addDependency(eClassifier);
        }

        visitGenericType(genericType.getEUpperBound());
        visitGenericType(genericType.getELowerBound());
        genericType.getETypeArguments().forEach(t -> visitGenericType(t));

        ETypeParameter typeParam = genericType.getETypeParameter();
        if (typeParam != null) {
            visitTypeParam(typeParam);
        }
    }
	
	private Collection<EClassifier> getEAllClassifiers(EPackage ePackage) {
		Collection<EClassifier> result = new ArrayList<EClassifier>();
		result.addAll(ePackage.getEClassifiers());
		ePackage.getESubpackages().forEach(x -> result.addAll(getEAllClassifiers(x)));
		return result;
	}
}

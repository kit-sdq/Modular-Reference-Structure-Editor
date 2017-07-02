package mrs.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;

import mrs.Metamodel;

public class MetamodelInvestigator {
	private Metamodel metamodel;
	private Set<Metamodel> referencedMetamodels;
	private Collection<Metamodel> metamodels;
	
	public MetamodelInvestigator(Metamodel metamodel) {
		this.metamodel = metamodel;
		this.referencedMetamodels = new HashSet<Metamodel>();
		this.metamodels = Services.getAllMetamodels(metamodel.getLayer().getModularReferenceStructure());
	}
	
	public Set<Metamodel> getReferencedMetamodels() {
		for (EClassifier c : getEAllClassifiers(metamodel.getMainPackage())) {
			if (!(c instanceof EClass)) //e.g. c is EDataType or EEnum... Only EClass can depend on other elements
				continue;
			
			EClass eClass = (EClass) c;
			
			eClass.getESuperTypes().forEach(x -> markAsReferenced(x.getEPackage()));
			
			eClass.getEReferences().forEach(x -> {
				markAsReferenced(x.getEType().getEPackage());
				visitGenericRef(x.getEGenericType());
			});
			
			eClass.getETypeParameters().forEach(x -> visitTypeParam(x));
			
			eClass.getEGenericSuperTypes().forEach(x -> visitGenericSuperType(x));
			
			eClass.getEOperations().forEach(x -> {
				markAsReferenced(x.getEType().getEPackage());
				
				x.getEParameters().forEach(y -> {
					markAsReferenced(y.getEType().getEPackage());
					visitGenericType(y.getEGenericType());
				});

				visitGenericType(x.getEGenericType());
				
				x.getETypeParameters().forEach(y -> visitTypeParam(y));
				
			});
		}
		return referencedMetamodels;
	}
	
	/**
	 * Adds the metamodel containing <code>mainPackage</code> to <code>refrencedMetamodels</code>
	 * @param ePackage the referenced package
	 * @param currentMetamodel metamodel being currently visited
	 * @param metamodels list of all metamodels in the modular reference structure
	 * @param referencedMetamodels list of metamodels that are already referenced 
	 */
	private void markAsReferenced(EPackage ePackage) {
		//System.out.println("Parent of " + ePackage.getName() + ": " + ePackage.getESuperPackage());
		EPackage mainPackage = Services.getTopMostPackage(ePackage);
		
		//if the referenced metamodel is the current metamodel itself, do nothing
		if (mainPackage == metamodel.getMainPackage())
			return;
		
		//if the metamodel containing the mainPackage hasn't already been marked 
		if (referencedMetamodels.stream().noneMatch(x -> x.getMainPackage().equals(mainPackage))) {
			Optional<Metamodel> refrencedMetamodel = metamodels.stream().filter(x -> x.getMainPackage().equals(mainPackage)).findAny();
			//Get the metamodel containing the mainPackage from the list of all metamodels and add it to the result
			if (refrencedMetamodel.isPresent())
				referencedMetamodels.add(refrencedMetamodel.get());
		}
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
            markAsReferenced(Services.getTopMostPackage(eClassifier.getEPackage()));
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

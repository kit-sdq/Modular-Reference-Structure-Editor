package mrs.design;

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
import mrs.custom.util.Util;

public class MetamodelInspector {
    private Metamodel metamodel;
    private Map<Metamodel, Set<EClassifier>> dependencies;

    public MetamodelInspector(Metamodel metamodel) {
        this.metamodel = metamodel;
        this.dependencies = new HashMap<Metamodel, Set<EClassifier>>();
    }

    /**
     * Inspects each EClass in metamodel on the basis of their dependencies (ESuperType, EReference,
     * EOperations, ETypeParameters...)
     * 
     * @return the value of the field <code>dependencies</code>
     */
    private Map<Metamodel, Set<EClassifier>> computeDependencies() {
        for (EClassifier c : Services.getEAllClassifiers(metamodel.getMainPackage())) {
            if (!(c instanceof EClass)) // e.g. c is EDataType or EEnum... Only EClass can depend on
                                        // other elements
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
        return dependencies;
    }

    /**
     * Computes the dependencies of metamodel and returns the metamodels, on which metamodel
     * depends.
     * 
     * @return a set of all metamodels in the current MRS, on which metamodel depends
     */
    public Set<Metamodel> getReferencedMetamodels() {
        return computeDependencies().keySet();
    }

    /**
     * Computes the dependencies of the base metamodel and returns the EClassifiers in the parameter
     * metamodel on which the base metamodel depends.
     * 
     * @param metamodel
     * @return the set of the EClassfiers
     */
    public Set<EClassifier> getReferencedEClassifiers(Metamodel metamodel) {
        return computeDependencies().get(metamodel);
    }

    /**
     * Adds the eClassifier to the dependencies hashmap. If its containing metamodel isn't already
     * in the hashmap, a new entry is created
     * 
     * @param eClassifier
     */
    private void addDependency(EClassifier eClassifier) {

        EPackage mainPackage = Util.getTopMostPackage(eClassifier.getEPackage());

        // if the referenced metamodel is the current metamodel itself, do nothing
        if (mainPackage == metamodel.getMainPackage())
            return;

        Metamodel referencedMetamodel = getCorrespondingMetamodel(mainPackage);

        if (dependencies.containsKey(referencedMetamodel)) {
            dependencies.get(referencedMetamodel).add(eClassifier);
        } else {
            Set<EClassifier> eClassifiers = new HashSet<EClassifier>();
            eClassifiers.add(eClassifier);
            dependencies.put(referencedMetamodel, eClassifiers);

        }
    }

    /**
     * Gets the metamodel whose main package is mainPackage.
     * 
     * @param mainPackage
     * @return the found metamodel
     */
    private Metamodel getCorrespondingMetamodel(EPackage mainPackage) {
        Collection<Metamodel> metamodels = Util.getAllMetamodels(metamodel.getLayer().getModularReferenceStructure());
        Metamodel correspondingMetamodel = Util.getCorrespondingMetamodel(mainPackage, metamodels);
        if (correspondingMetamodel == null) // If no such metamodel is loaded, import it
            correspondingMetamodel = Util.createMetamodel(mainPackage, metamodel.getLayer());

        return correspondingMetamodel;
    }

    /**
     * visits all bounds of the typeParam
     * 
     * @param typeParam
     */
    private void visitTypeParam(ETypeParameter typeParam) {
        typeParam.getEBounds().forEach(bound -> visitGenericType(bound));
    }

    /**
     * visits genericSuperType if it has type arguments
     * 
     * @param genericSuperType
     */
    private void visitGenericSuperType(EGenericType genericSuperType) {
        if (genericSuperType.getETypeArguments().size() > 0)
            visitGenericType(genericSuperType);
    }

    /**
     * visits genericTypeOfRef if it has type arguments
     * 
     * @param genericTypeOfRef
     */
    private void visitGenericRef(EGenericType genericTypeOfRef) {
        if (genericTypeOfRef.getETypeArguments().size() > 0)
            visitGenericType(genericTypeOfRef);
    }

    /**
     * Makes a call to addDependency on the EClassifier of genericType and visits the upper and
     * lower bounds as well as the type arguments and the type parameter
     * 
     * @param genericType
     */
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

}

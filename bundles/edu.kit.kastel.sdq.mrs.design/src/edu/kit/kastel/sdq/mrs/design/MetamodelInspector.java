package edu.kit.kastel.sdq.mrs.design;

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

import edu.kit.kastel.sdq.mrs.design.Dependency.DependencyType;
import edu.kit.kastel.sdq.mrs.Metamodel;
import edu.kit.kastel.sdq.mrs.custom.util.MRSUtil;

public class MetamodelInspector {
    private Metamodel metamodel;
    private Map<Metamodel, Set<Dependency>>dependencies;

    public MetamodelInspector(Metamodel metamodel) {
        this.metamodel = metamodel;
        this.dependencies = new HashMap<Metamodel, Set<Dependency>>();
    }

    /**
     * Inspects each EClass in metamodel on the basis of their dependencies (ESuperType, EReference,
     * EOperations, ETypeParameters...)
     * 
     * @return the value of the field <code>dependencies</code>
     */
    private Map<Metamodel, Set<Dependency>> computeDependencies() {
        for (EClassifier c : Services.getEAllClassifiers(metamodel.getMainPackage())) {
            if (!(c instanceof EClass)) // e.g. c is EDataType or EEnum... Only EClass can depend on
                                        // other elements
                continue;

            EClass eClass = (EClass) c;

            eClass.getESuperTypes().forEach(x -> addDependency(new Dependency(eClass, x, DependencyType.E_SUPER_TYPE)));

            eClass.getEReferences().forEach(x -> {
                addDependency(new Dependency(eClass, x.getEType(), DependencyType.E_REFERENCE));
                visitGenericRef(x.getEGenericType(), eClass);
            });
            
            eClass.getEAttributes().forEach(x -> {
                addDependency(new Dependency(eClass, x.getEType(), DependencyType.E_ATTRIBUTE));
                visitGenericRef(x.getEGenericType(), eClass);
            });

            eClass.getETypeParameters().forEach(x -> visitTypeParam(x, eClass));

            eClass.getEGenericSuperTypes().forEach(x -> visitGenericSuperType(x, eClass));

            eClass.getEOperations().forEach(x -> {
                addDependency(new Dependency(eClass, x.getEType(), DependencyType.E_OPERATION_RETURN_TYPE));

                x.getEParameters().forEach(y -> {
                    addDependency(new Dependency(eClass, y.getEType(), DependencyType.E_OPERATION_PARAMETER));
                    visitGenericType(y.getEGenericType(), eClass);
                });

                visitGenericType(x.getEGenericType(), eClass);

                x.getETypeParameters().forEach(y -> visitTypeParam(y, eClass));

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
    public Set<Dependency> getReferencedEClassifiers(Metamodel metamodel) {
        return computeDependencies().get(metamodel);
    }

    /**
     * Adds the eClassifier to the dependencies hashmap. If its containing metamodel isn't already
     * in the hashmap, a new entry is created
     * 
     * @param eClassifier
     */
    private void addDependency(Dependency dependency) {
        EClassifier eClassifier = dependency.getTarget();
        EPackage mainPackage = MRSUtil.getTopMostPackage(eClassifier.getEPackage());

        // if the referenced metamodel is the current metamodel itself, do nothing
        if (mainPackage == metamodel.getMainPackage())
            return;

        Metamodel referencedMetamodel = getCorrespondingMetamodel(mainPackage);

        if (dependencies.containsKey(referencedMetamodel)) {
            dependencies.get(referencedMetamodel).add(dependency);
        } else {
            Set<Dependency> metamodelDependencies = new HashSet<Dependency>();
            metamodelDependencies.add(dependency);
            dependencies.put(referencedMetamodel, metamodelDependencies);

        }
    }

    /**
     * Gets the metamodel whose main package is mainPackage.
     * 
     * @param mainPackage
     * @return the found metamodel
     */
    private Metamodel getCorrespondingMetamodel(EPackage mainPackage) {
        Collection<Metamodel> metamodels = MRSUtil.getAllMetamodels(metamodel.getLayer().getModularReferenceStructure());
        Metamodel correspondingMetamodel = MRSUtil.getCorrespondingMetamodel(mainPackage, metamodels);
        if (correspondingMetamodel == null) // If no such metamodel is loaded, import it
            correspondingMetamodel = MRSUtil.createMetamodel(mainPackage, metamodel.getLayer());

        return correspondingMetamodel;
    }

    /**
     * visits all bounds of the typeParam
     * 
     * @param typeParam
     * @param source dependency's origin
     */
    private void visitTypeParam(ETypeParameter typeParam, EClassifier source) {
        typeParam.getEBounds().forEach(bound -> visitGenericType(bound, source));
    }

    /**
     * visits genericSuperType if it has type arguments
     * 
     * @param genericSuperType
     * @param source dependency's origin
     */
    private void visitGenericSuperType(EGenericType genericSuperType, EClassifier source) {
        if (genericSuperType.getETypeArguments().size() > 0)
            visitGenericType(genericSuperType, source);
    }

    /**
     * visits genericTypeOfRef if it has type arguments
     * 
     * @param genericTypeOfRef
     * @param source dependency's origin
     */
    private void visitGenericRef(EGenericType genericTypeOfRef, EClassifier source) {
        if (genericTypeOfRef.getETypeArguments().size() > 0)
            visitGenericType(genericTypeOfRef, source);
    }

    /**
     * Makes a call to addDependency on the EClassifier of genericType and visits the upper and
     * lower bounds as well as the type arguments and the type parameter
     * 
     * @param genericType
     * @param source dependency's origin
     */
    private void visitGenericType(EGenericType genericType, EClassifier source) {
        if (genericType == null)
            return;

        EClassifier eClassifier = genericType.getEClassifier();
        if (eClassifier != null) {
            addDependency(new Dependency(source, eClassifier, DependencyType.E_GENERIC_TYPE));
        }

        visitGenericType(genericType.getEUpperBound(), source);
        visitGenericType(genericType.getELowerBound(), source);
        genericType.getETypeArguments().forEach(t -> visitGenericType(t, source));

        ETypeParameter typeParam = genericType.getETypeParameter();
        if (typeParam != null) {
            visitTypeParam(typeParam, source);
        }
    }

}

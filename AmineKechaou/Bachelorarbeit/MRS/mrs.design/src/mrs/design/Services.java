package mrs.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.modelversioning.emfprofile.Stereotype;

import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.MrsPackage;
import mrs.custom.util.Util;

public class Services {
    
    /**
     * Gets all metamodels in the modular reference structure, on which the metamodel passed as
     * parameter depend.
     * 
     * @param metamodel
     *            the metamodel being inspected
     * @return the set of the metamodels
     */
    public Set<Metamodel> getReferencedMetamodels(Metamodel metamodel) {
        MetamodelInspector inspector = new MetamodelInspector(metamodel);
        return inspector.getReferencedMetamodels();
    }

    /**
     * Gets all dependencies from sourceMetamodel to targetMetamodel
     * 
     * @param sourceMetamodel
     * @param targetMetamodel
     * @return the set of the dependencies
     */
    private Set<Dependency> getDependencies(Metamodel sourceMetamodel, Metamodel targetMetamodel) {
        MetamodelInspector inspector = new MetamodelInspector(sourceMetamodel);
        return inspector.getReferencedEClassifiers(targetMetamodel);
    }

    
    public Set<Metamodel> getExtendedMetamodels(Metamodel metamodel) {
        ProfileInspector inspector = new ProfileInspector(metamodel);
        return inspector.getExtensions().keySet();
    }
    
    public Set<Stereotype> getExtendingStereotypes(Metamodel extendingMetamodel, Metamodel extendedMetamodel) {
        ProfileInspector inspector = new ProfileInspector(extendingMetamodel);
        return inspector.getExtensions().get(extendedMetamodel);
    }
    
    public String printStereotypes(DDiagramElement view) {
        DEdge edge = (DEdge) view;
        Metamodel source = (Metamodel) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
        Metamodel target = (Metamodel) ((DSemanticDecorator) edge.getTargetNode()).getTarget();
        
        Set<Stereotype> stereotypes = getExtendingStereotypes(source, target);
        
        String result = "";
        
        if (stereotypes != null) {
            for (Stereotype stereotype : stereotypes) {
                result += "<<" + stereotype.getProfile().getName() + "." + stereotype.getName() + ">>\n";
            }  
        }
        return result;
    }

    /**
     * Returns all metamodels present in the ModularReferenceStructure
     * 
     * @param mrs
     *            the ModularReferenceStructure
     * @return a Collection containing the metamodels
     */
    public static Collection<Metamodel> getAllMetamodels(ModularReferenceStructure mrs) {
        return Util.getAllMetamodels(mrs);
    }

    /**
     * Returns the main EPackage of the metamodel containing ePackage
     * 
     * @param ePackage
     * @return ePackage if it has no super package, otherwise the top most package of the super
     *         package of ePackage
     */
    public static EPackage getTopMostPackage(EPackage ePackage) {
        return Util.getTopMostPackage(ePackage);
    }

    /**
     * Checks whether the edge is pointing from a layer to a lower one
     * 
     * @param edge
     * @return true if edge points to a metamodel in a lower layer, false otherwise
     */
    public boolean edgeIsPointingDownwards(DEdge edge) {
        Metamodel source = (Metamodel) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
        Metamodel target = (Metamodel) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

        Layer sourceLayer = source.getLayer();
        Layer targetLayer = target.getLayer();

        ModularReferenceStructure mrs = sourceLayer.getModularReferenceStructure();

        if (sourceLayer == targetLayer)
            return false;

        EList<Layer> layers = mrs.getLayers();

        return layers.indexOf(targetLayer) > layers.indexOf(sourceLayer);

    }

    /**
     * Checks whether there is a cycle containing the edge by running a variant of Breadth First
     * Search starting from the target of the edge and checking if the edge's souurce can be reached
     * 
     * @param edge
     * @return true if a cycle containing edge is discovered
     */
    public boolean edgeIsPartOfCycle(DEdge edge) {
        EdgeTarget sourceNode = edge.getSourceNode();
        EdgeTarget targetNode = edge.getTargetNode();

        // Initialize queue with the target node
        Queue<EdgeTarget> queue = new LinkedList<EdgeTarget>();
        queue.add(targetNode);

        // Mark the target node
        Collection<EdgeTarget> markedNodes = new ArrayList<EdgeTarget>();
        markedNodes.add(targetNode);

        // Run BFS. If the sourceNode is reachable from the targetNode, then the edge source->target
        // is part of a cycle
        while (!queue.isEmpty()) {
            EdgeTarget current = queue.poll();
            Collection<EdgeTarget> adjacentNodes = current.getOutgoingEdges().stream().map(x -> x.getTargetNode())
                    .collect(Collectors.toList());

            if (adjacentNodes.contains(sourceNode))
                return true;

            Collection<EdgeTarget> unmarkedAdjacentNodes = adjacentNodes.stream().filter(x -> !markedNodes.contains(x))
                    .collect(Collectors.toList());
            queue.addAll(unmarkedAdjacentNodes);
            markedNodes.addAll(unmarkedAdjacentNodes);
        }
        return false;
    }

    /**
     * This method returns the metamodel containing the semantic elemenet of the container view
     * 
     * @param containerView
     *            a graphical element in the diagram. Must be inside a Metamodel
     * @return the metamodel
     */
    public Metamodel getContainingMetamodel(DNodeContainer containerView) {
        if (containerView.getTarget() instanceof Metamodel)
            return (Metamodel) containerView.getTarget();
        else
            return getContainingMetamodel((DNodeContainer) containerView.eContainer());
    }

    /**
     * Gets all subpackages of ePackage recursively
     * 
     * @param ePackage
     * @return a Collection of all subpackages
     */
    public Collection<EPackage> getEAllSubPackages(EPackage ePackage) {
        Collection<EPackage> result = new ArrayList<EPackage>();
        result.addAll(ePackage.getESubpackages());
        ePackage.getESubpackages().forEach(x -> result.addAll(getEAllSubPackages(x)));
        return result;
    }

    /**
     * Prints the EClass of eObject and eObject.toString(). Exclusively for debugging purposes
     * 
     * @param eObject
     * @return the eObject parameter
     */
    public EObject print(EObject eObject) {
        System.out.println(eObject.getClass() + " " + eObject);
        return eObject;
    }

    /**
     * Returns a String of all EClassifiers in the target metamodel, on which some EClass in the
     * source metamodel depends. EClassifiers are separated by a comma
     * 
     * @param edge
     * @return String representation of the dependencies
     */
    public String printDependencies(DEdge edge) {
        Metamodel source = (Metamodel) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
        Metamodel target = (Metamodel) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

        Set<Dependency> dependencies = getDependencies(source, target);

        String result = "";
        if (dependencies != null) {
            for (Dependency dependency : dependencies) {
                result = result + dependency.getTarget().getName() + " (" + dependency.getType() + " in " + dependency.getSource().getName() + ") \n";
            } 
        }
        
        return result.isEmpty() ? result : result.substring(0, result.length() - 2);

    }
    
    /**
     * Computes the EClassifiers that should be visible and removes all invalid references in the visibleEClassifiers list
     * @param ePackage the packaged being inspected
     * @param metamodel the metamodel containing the EClassifiers
     * @return a list of the EClassifiers that should be visible inside the metamodel
     */
    public Collection<EClassifier> getVisibleEClassifiers(EPackage ePackage, Metamodel metamodel) {
        Collection<EClassifier> eClassifiers = ePackage.getEClassifiers();
        Collection<EClassifier> eAllClassifiers = getEAllClassifiers(metamodel.getMainPackage());
        Collection<EClassifier> visibleEClassifiers = metamodel.getVisibleEClassifiers();
        
        Collection<EClassifier> result = new ArrayList<EClassifier>();
        Collection<EClassifier> ghostEClassifiers = new ArrayList<EClassifier>();
        
        for (EClassifier visibleEClassifier : visibleEClassifiers) {
            if(!eAllClassifiers.contains(visibleEClassifier))
                ghostEClassifiers.add(visibleEClassifier);
            if(eClassifiers.contains(visibleEClassifier))
                result.add(visibleEClassifier);
        }
        
        visibleEClassifiers.removeAll(ghostEClassifiers);
        return result;
    }
    
    
    /**
     * This method returns the currently visible subpackages inside the ePackage
     * 
     * @param ePackage
     *            the EPackage, whose visible subpackages are going to be retrieved
     * @param containerView
     *            the graphical element corresponding to ePackage
     * @return a Collection containing all visible subpackages of ePackage
     */
    public Collection<EPackage> getVisibleSubPackages(EPackage ePackage, DNodeContainer containerView) {
        Metamodel metamodel = getContainingMetamodel(containerView);
        
        Collection<EPackage> visibleEPackages = metamodel.getVisibleEPackages();
        Collection<EPackage> eAllSubPackages = getEAllSubPackages(metamodel.getMainPackage());
        Collection<EPackage> eSubPackages = ePackage.getESubpackages();
        
        Collection<EPackage> result = new ArrayList<EPackage>();
        Collection<EPackage> ghostEClassifiers = new ArrayList<EPackage>();
        
        for (EPackage visibleEPackage : visibleEPackages) {
            if(!eAllSubPackages.contains(visibleEPackage))
                ghostEClassifiers.add(visibleEPackage);
            if(eSubPackages.contains(visibleEPackage))
                result.add(visibleEPackage);
        }
        
        visibleEPackages.removeAll(ghostEClassifiers);
        
        return result;
    }
    
    
    /**
     * Gets all EClassifiers inside an EPackage recursively. 
     * @param ePackage
     * @return a List containing the EClassfiers
     */
    public static  List<EClassifier> getEAllClassifiers(EPackage ePackage) {
        List<EClassifier> result = new ArrayList<EClassifier>();
        result.addAll(ePackage.getEClassifiers());
        ePackage.getESubpackages().forEach(x -> result.addAll(getEAllClassifiers(x)));
        return result;
    }
    
    /**
     * Returns the reference id of the EReference visibleEClassfiers
     * @param eObject
     * @return the reference id
     */
    public int getVisibleEClassifiersReferenceId(EObject eObject) {
        return MrsPackage.METAMODEL__VISIBLE_ECLASSIFIERS;
    }
    
    /**
     * Returns the reference id of the EReference visibleEPackages
     * @param eObject
     * @return the reference id
     */
    public int getVisibleEPackagesReferenceId(EObject eObject) {
        return MrsPackage.METAMODEL__VISIBLE_EPACKAGES;
    }
}

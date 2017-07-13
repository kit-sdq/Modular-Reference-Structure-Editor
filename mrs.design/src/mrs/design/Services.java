package mrs.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;

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
     * Gets all EClassfiers in the targetMetamodel, on which some EClassifier in the sourceMetamodel
     * depend.
     * 
     * @param sourceMetamodel
     * @param targetMetamodel
     * @return the set of the EClassfiers
     */
    public Set<EClassifier> getReferencedEClassifiers(Metamodel sourceMetamodel, Metamodel targetMetamodel) {
        MetamodelInspector inspector = new MetamodelInspector(sourceMetamodel);
        return inspector.getReferencedEClassifiers(targetMetamodel);
    }

    /**
     * Returns all metamodels present in the ModularReferenceStructure
     * 
     * @param mrs
     *            the ModularReferenceStructure
     * @return a Collection containing the metamodels
     */
    public static Collection<Metamodel> getAllMetamodels(ModularReferenceStructure mrs) {
        Collection<Metamodel> result = new ArrayList<Metamodel>();
        for (Layer l : mrs.getLayers()) {
            result.addAll(l.getMetamodels());
        }
        return result;
    }

    /**
     * Returns the main EPackage of the metamodel containing ePackage
     * 
     * @param ePackage
     * @return ePackage if it has no super package, otherwise the top most package of the super
     *         package of ePackage
     */
    public static EPackage getTopMostPackage(EPackage ePackage) {
        if (ePackage.getESuperPackage() == null)
            return ePackage;
        else
            return getTopMostPackage(ePackage.getESuperPackage());
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
        return ePackage.getESubpackages().stream().filter(x -> visibleEPackages.contains(x))
                .collect(Collectors.toList());
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
    public String getDependencies(DEdge edge) {
        Metamodel source = (Metamodel) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
        Metamodel target = (Metamodel) ((DSemanticDecorator) edge.getTargetNode()).getTarget();

        Set<EClassifier> eClassifiers = getReferencedEClassifiers(source, target);

        String result = "";

        for (EClassifier eClassifier : eClassifiers) {
            result = result + eClassifier.getName() + ", ";
        }
        return result.isEmpty() ? result : result.substring(0, result.length() - 2);

    }
}

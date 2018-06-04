package mrs.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.modelversioning.emfprofile.Stereotype;

import mrs.Layer;
import mrs.Metamodel;
import mrs.ModularReferenceStructure;
import mrs.custom.util.MRSUtil;

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
        return MRSUtil.getAllMetamodels(mrs);
    }

    /**
     * Returns the main EPackage of the metamodel containing ePackage
     * 
     * @param ePackage
     * @return ePackage if it has no super package, otherwise the top most package of the super
     *         package of ePackage
     */
    public static EPackage getTopMostPackage(EPackage ePackage) {
        return MRSUtil.getTopMostPackage(ePackage);
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
     * Search starting from the target of the edge and checking if the edge's source can be reached
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
     * This method returns the metamodel containing the semantic element of the container view
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
    
    public Collection<EPackage> getAllPackages(EPackage ePackage) {
        Collection<EPackage> result = new ArrayList<EPackage>();
        result.add(ePackage);
        result.addAll(getEAllSubPackages(ePackage));
        return result;
    }
    
    /**
     * Computes the containment chain of the given EObject
     * @param eObject The EObject whose containment chain is to be returned 
     * @param topMostContainer the container on which the search should stop
     * @return a stack containing all the containers of eObject recursively up to topMostContainer (not included) 
     */
    public List<EObject> getChainOfContainers(EObject eObject, EObject topMostContainer) {
    	List<EObject> acc = new ArrayList<EObject>();
    	List<EObject> result = getChainOfContainers(eObject, topMostContainer, acc);
    	Collections.reverse(result);
    	return result;
    }
    private List<EObject> getChainOfContainers(EObject eObject, EObject topMostContainer, List<EObject> acc) {
    	if (eObject.eContainer() == topMostContainer)
    		return acc;
    	else {
    		acc.add(eObject.eContainer());
    		return getChainOfContainers(eObject.eContainer(), topMostContainer, acc);
    	}
    			
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
    
    public Metamodel getCorrespondingMetamodel(EPackage mainPackage, ModularReferenceStructure mrs) {
    	return MRSUtil.getCorrespondingMetamodel(mainPackage, MRSUtil.getAllMetamodels(mrs));
    }
    
    public boolean metamodelAlreadyExists(EPackage mainPackage, ModularReferenceStructure mrs) {
    	return MRSUtil.metamodelAlreadyExists(mainPackage, mrs);
    }
    
    /**
     * Hides all edges in the diagram that are transitive. An edge is considered transitive if there exists another path from its source to its target 
     * @param diagram the MRS diagram
     * @param excludeCyclicalDependencies if true, then edges that are part of cycles will not be hidden regardless of their being transitive or not
     */
    public void hideTransitiveEdges(DSemanticDiagram diagram, boolean excludeCyclicalDependencies) {
    	EList<DEdge> edges = diagram.getEdges();
    	Collection<DEdge> transitiveEdges = new ArrayList<DEdge>();
    	for (DEdge edge : edges) {
    		if (excludeCyclicalDependencies && edgeIsPartOfCycle(edge))
    			continue;
    		if (edgeIsTransitive(edge, transitiveEdges)) {
    			transitiveEdges.add(edge);
    		}
    	}
    	for (DEdge edge : transitiveEdges) {
    		HideFilterHelper.INSTANCE.hide(edge);
    	}
    }
    
    private boolean edgeIsTransitive(DEdge edge, Collection<DEdge> transitiveEdges) {
    	EdgeTarget sourceNode = edge.getSourceNode();
        EdgeTarget targetNode = edge.getTargetNode();
        
        
        // Initialize queue with the target node
        Queue<EdgeTarget> queue = new LinkedList<EdgeTarget>();
        Collection<EdgeTarget> sourceAdjacentNodes = sourceNode.getOutgoingEdges().stream()
        		.filter(e -> !transitiveEdges.contains(e)) // only consider edges that are not already marked as transitive
        		.map(x -> x.getTargetNode()).collect(Collectors.toList());
        sourceAdjacentNodes.remove(targetNode);
        
        queue.addAll(sourceAdjacentNodes); //add all nodes adjacent to the source to the queue, except for the target node
        
        
        Collection<EdgeTarget> markedNodes = new ArrayList<EdgeTarget>();
        markedNodes.addAll(queue); //mark all nodes already in the queue, i.e. all nodes adjacent to the source to the queue, except for the target node
        markedNodes.add(sourceNode); // mark the source node to avoid it being revisited
        
        // Run BFS. If the targetNode is reachable from the sourceNode via another path that does not contain an edge that is already marked as transitive, 
        //then the edge source->target is transitive
        while (!queue.isEmpty()) {
            EdgeTarget current = queue.poll();
            Collection<EdgeTarget> adjacentNodes = current.getOutgoingEdges().stream()
            		.filter(e -> !transitiveEdges.contains(e)) // only consider edges that are not already marked as transitive
            		.map(x -> x.getTargetNode()).collect(Collectors.toList());

            // if the target node is adjacent to the node currently being investigated, 
            // then the considered edge is transitive
            if (adjacentNodes.contains(targetNode)) {
                return true; 
            }

            Collection<EdgeTarget> unmarkedAdjacentNodes = adjacentNodes.stream().filter(x -> !markedNodes.contains(x))
                    .collect(Collectors.toList()); //get all unmarked adjacent nodes
            queue.addAll(unmarkedAdjacentNodes); // add them to the queue
            markedNodes.addAll(unmarkedAdjacentNodes); // mark them
        }
        
    	return false;
    }
    
    public Collection<Metamodel> getMetamodels(Layer layer) {
    	return MRSUtil.getMetamodels(layer);
    }
    
    /*public EObject print(EObject o) {
    	System.out.println(o);
    	return o;
    }*/
    
    
}
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
	public Set<Metamodel> getReferencedMetamodels(Metamodel metamodel) {
		MetamodelInvestigator investigator = new MetamodelInvestigator(metamodel);
		investigator.computeDependencies();
		return investigator.getReferencedMetamodels();
	}
	
	public Set<EClassifier> getReferencedEClassifiers(Metamodel sourceMetamodel, Metamodel targetMetamodel) {
		MetamodelInvestigator investigator = new MetamodelInvestigator(sourceMetamodel);
		investigator.computeDependencies();
		return investigator.getReferencedEClassifiers(targetMetamodel);
	}
	
	public static Collection<Metamodel> getAllMetamodels(ModularReferenceStructure mrs) {
		Collection<Metamodel> result = new ArrayList<Metamodel>();
		for (Layer l : mrs.getLayers()) {
			result.addAll(l.getMetamodels());
		}
		return result;
	}
	
	public static EPackage getTopMostPackage(EPackage ePackage) {
		if (ePackage.getESuperPackage() == null)
			return ePackage;
		else
			return getTopMostPackage(ePackage.getESuperPackage());
	}

	
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
	
	public boolean edgeIsPartOfCycle(DEdge edge) {
		EdgeTarget sourceNode = edge.getSourceNode();
		EdgeTarget targetNode = edge.getTargetNode();
				
		//Initialize queue with the target node
		Queue<EdgeTarget> queue = new LinkedList<EdgeTarget>();
		queue.add(targetNode);
		
		//Mark the target node
		Collection<EdgeTarget> markedNodes = new ArrayList<EdgeTarget>();
		markedNodes.add(targetNode);
		
		//Run BFS. If the sourceNode is reachable from the targetNode, then the edge source->target is part of a cycle
		while(!queue.isEmpty()) {
			EdgeTarget current = queue.poll();
			Collection<EdgeTarget> adjacentNodes = current.getOutgoingEdges().stream().map(x -> x.getTargetNode()).collect(Collectors.toList());
			
			if (adjacentNodes.contains(sourceNode))
				return true;
			
			Collection<EdgeTarget> unmarkedAdjacentNodes = adjacentNodes.stream().filter(x -> !markedNodes.contains(x)).collect(Collectors.toList());
			queue.addAll(unmarkedAdjacentNodes);
			markedNodes.addAll(unmarkedAdjacentNodes);
		}
		return false;
	}
	   
    public Metamodel getContainingMetamodel(DNodeContainer containerView) {
    	if (containerView.getTarget() instanceof Metamodel)
    		return (Metamodel) containerView.getTarget();
    	else
    		return getContainingMetamodel((DNodeContainer) containerView.eContainer());
    }
    
    public Collection<EPackage> getVisibleSubPackages(EPackage ePackage, DNodeContainer containerView) {
    	Metamodel metamodel = getContainingMetamodel(containerView);
    	Collection<EPackage> visibleEPackages = metamodel.getVisibleEPackages();
    	return ePackage.getESubpackages().stream().filter(x -> visibleEPackages.contains(x)).collect(Collectors.toList());
    }
    
    public Collection<EPackage> getEAllSubPackages(EPackage ePackage) {
    	Collection<EPackage> result = new ArrayList<EPackage>();
    	result.addAll(ePackage.getESubpackages());
    	ePackage.getESubpackages().forEach(x -> result.addAll(getEAllSubPackages(x)));
    	return result;
    }
    public EObject print(EObject eObject) {
    	System.out.println(eObject.getClass() + " " + eObject);
    	return eObject;
    }
    
    public String getEdgeLabel(DEdge edge) {
		Metamodel source = (Metamodel) ((DSemanticDecorator) edge.getSourceNode()).getTarget();
		Metamodel target = (Metamodel) ((DSemanticDecorator) edge.getTargetNode()).getTarget();
		System.out.println("Source: " + source + " ; Target: " + target);
		Set<EClassifier> eClassifiers = getReferencedEClassifiers(source, target);
		
		String result = "";
		
		for (EClassifier eClassifier : eClassifiers) {
			result = result + eClassifier.getName() + ", ";
		}		
		return result.substring(0, result.length() - 2);
    	
    }
}

package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Graph <N extends Node>
{
	//For every node, there will be a hash containing
	//the nodes it's connected to and its corresponding edges
	private HashMap<N, HashMap<N, Edge>> graph;
	private HashMap<Edge, Pair<N, N>> edgesToNodes;
	
	public Graph()
	{
		edgesToNodes = new HashMap<Edge, Pair<N, N>>();
		graph = new HashMap<N, HashMap<N, Edge>>();
	}

	///// NODES STUFF /////////////////////////////////////////////////////
	/**
	 * Add a new disconnected node (without edges to any node). The node mustn't exist in the graph before adding it.
	 * No node with the same id as the parameter node must exist in the graph.
	 * @param node The node to be added
	 */
	public void addNode(N node)
	{
		graph.put(node, new HashMap<N, Edge>());
	}

	/**
	 * Returns a set of nodes the given node is connected(adjacent) to. 
	 */
	public Set<N> getAdjacentNodesTo(N node)
	{
		if(!graph.containsKey(node)) return null;
		return graph.get(node).keySet();
	}

	/**
	 * Returns the two nodes connected by the edge e
	 */
	public Pair<N, N> getNodesConnectedBy(Edge e)
	{
		return edgesToNodes.get(e);
	}

	/**
	 * Returns a set of all the nodes in the graph
	 */
	public Set<N> getAllNodes()
	{
		return graph.keySet();
	}
	
	/**
	 * Remove a node and all its connections
	 * @param node The node to be removed
	 */
	public void removeNode(N node)
	{	
		if(!graph.containsKey(node)) return;
		
		//Get the nodes it was connected to
	    for(N adjNode : graph.get(node).keySet()) //Remove every edge connected to the removed node
	    {
	    	//Remove the removed node from others nodes' edge list
	    	graph.get(adjNode).remove(node);
	    }
	    graph.remove(node); //Remove the node itself
	}
	
	//////////////////////////////////////////////////////////////////////
	

	
	
	///// EDGE STUFF /////////////////////////////////////////////////////
	/**
	 * Add a new edge between two nodes. The edge passed as parameter mustn't exist in the graph before adding it.
	 * @param node1 The first node to be connected
	 * @param node2 The second node to be connected
	 * @param edge The edge that will connect node1 and node2
	 */
	public void addEdge(N node1, N node2, Edge edge)
	{
		graph.get(node1).put(node2, edge);
		graph.get(node2).put(node1, edge);
		edgesToNodes.put(edge, new Pair<N,N>(node1, node2));
	}

	/**
	 * Returns the edge between node1 and node2. 
	 * Returns null in case it doesn't exist.
	 */
	public Edge getEdge(N node1, N node2)
	{
		Set<N> allEdges = getAllNodes();
		if(!allEdges.contains(node1) || !allEdges.contains(node2)) return null;
		
		return graph.get(node1).get(node2);
	}

	/*
	 * Returns a set of all the edges. (Consequently, there aren't repeated edges)
	 */
	public Set<Edge> getAllEdges()
	{
		return edgesToNodes.keySet();
	}
	
	/**
	 * Removes a  given edge.
	 * @param edge The edge that will be removed
	 */
	public void removeEdge(Edge edge)
	{
		//We need to use iterators because we are removing while iterating items
		Pair<N,N> nodes = edgesToNodes.get(edge);
		N n1 = nodes.getFirst(); N n2 = nodes.getSecond();
		graph.get(n1).remove(n2);
		graph.get(n2).remove(n1);
		edgesToNodes.remove(edge);
	}
	/**
	 * Removes all the edges.
	 */
	public void removeAllEdges()
	{
		edgesToNodes = new HashMap<Edge, Pair<N,N>>();
		for(N n : graph.keySet()) graph.put(n, new HashMap<N, Edge>());
	}
	
	public Set<Edge> getAdjacentEdgesTo(Node n)
	{
		Set<Edge> edges = new HashSet<Edge>(graph.get(n).values());
		return edges;
	}
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	///// COMMUNITIES STUFF //////////////////////////////////////////////
	/**
	 * Returns the connected components in the graph
	 */
	public ArrayList<Community> getConnectedComponents()
	{
		ArrayList< Set<N> > connectedComponents = new ArrayList< Set<N> >();
		Set<N> visitedNodes = new HashSet<N>();
		for(N origin : graph.keySet())
		{
			if(visitedNodes.contains(origin)) continue;
			
			LinkedList<N> nextNodes = new LinkedList<N>();
			N currentNode = origin; nextNodes.push(origin);
			Set<N> cc = new HashSet<N>();
			connectedComponents.add(cc); cc.add(origin);
			while(nextNodes.size() > 0)
			{
				currentNode = nextNodes.get(0); nextNodes.remove(0);
				for(N n : graph.get(currentNode).keySet())
				{
					if(!visitedNodes.contains(n))
					{
						cc.add(n);
						visitedNodes.add(n);
						nextNodes.add(nextNodes.size(), n);
					}
				}
			}
		}
		
		ArrayList<Community> comunities = new ArrayList<Community>();
		for(Set<N> setNodes : connectedComponents)
		{
			Community com = new Community();
			for(N node : setNodes) com.addNode(node);
			
			comunities.add(com);
		}
		
		return comunities;
	}
	//////////////////////////////////////////////////////////////////////
	
	public boolean equals(Object o)
	{
		Graph<N> g = (Graph<N>) o;
		
		//S'ha de passar a ArrayList perque si no, al comparar sets amb set.equals(set2), tambe compara
		//els hashcodes, i nomes volem que compari amb la funcio (Node/Edge).equals, 
		//per aixo aquesta conversio rara.
		ArrayList<N> nsG = new ArrayList<N>(g.graph.keySet());
		ArrayList<N> nsThis = new ArrayList<N>(this.graph.keySet());
		
		//Los nodos han de ser los mismos
		//Per comparar si son els mateixos sets, miro si es contenen l'un al altre :3
		if(!nsG.containsAll(nsThis) || !nsThis.containsAll(nsG))  return false;
		
		for(N nG : nsG)
		{
			for(N nThis : nsThis)
			{
				if(nG.equals(nThis))
				{
					ArrayList<N> nsAdjG = new ArrayList<N>(g.graph.get(nG).keySet());
					ArrayList<N> nsAdjThis =  new ArrayList<N>(this.graph.get(nThis).keySet());
					
					//Els nodes adjacents a nG han de ser els mateixos (mateixa id)
					if(!nsAdjG.containsAll(nsAdjThis) || !nsAdjThis.containsAll(nsAdjG)) return false;
					for(N nAdjG : nsAdjG)
					{
						for(N nAdjThis : nsAdjThis)
						{
							if(nAdjThis.equals(nAdjG))
							{
								Edge eG = g.getEdge(nG, nAdjG);
								Edge eThis = this.getEdge(nThis, nAdjThis);
								if(eG == null && eThis == null) break;
								if(eG == null && eThis != null) return false;
								if(eG != null && eThis == null) return false;
								if(eG.getWeight() != eThis.getWeight()) return false;
								break;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	////////////////////////////////////////////////////////////////////////////
}

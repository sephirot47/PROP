package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Graph <N extends Node, E extends Edge>
{
	//For every node, there will be a hash containing
	//the nodes it's connected to and its corresponding edges
	private HashMap<N, HashMap<N, E>> graph; 
	
	public Graph()
	{
		graph = new HashMap<N, HashMap<N, E>>();
	}


	///// NODES STUFF /////////////////////////////////////////////////////
	/**
	 * Add a new disconnected node (without edges to any node). The node mustn't exist in the graph before adding it.
	 * @param node The node to be added
	 */
	public void addNode(N node)
	{
		if(graph.containsKey(node)) //Si son la misma instancia
		{
			System.err.println("Can't add repeated nodes, create a new Node instead. Ignoring the AddNode");
			return;
		}      
		else if(new ArrayList<Node>(getAllNodes()).contains(node)) //Si tienen la misma id
		{
			System.err.println("Can't add two nodes with the same id.");
			return;
		}
		
		graph.put(node, new HashMap<N, E>()); //Tot correcte, es pot afegir el node
	}

	/**
	 * Returns a set of nodes the given node is connected(adjacent) to. 
	 */
	public Set<N> getAdjacentNodesTo(N node)
	{
		if(!graph.containsKey(node)) { System.err.println("The graph doesn't contain the node."); return null; }
		return graph.get(node).keySet();
	}

	/**
	 * Returns the two nodes connected by the edge e
	 */
	public Pair<N, N> getNodesConnectedBy(E e)
	{
		for(N n1 : graph.keySet())
			for(N n2 : graph.get(n1).keySet())
				if(getEdge(n1, n2) == e) 
					return new Pair<N, N>(n1, n2);
		return null;
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
	public void addEdge(N node1, N node2, E edge)
	{
		if(!graph.containsKey(node1) || !graph.containsKey(node2))
		{
			System.err.println("node1 or node2 don't exist in the graph.");
			return;
		}
		else if(getAllEdges().contains(edge))
		{
			System.err.println("Can't add repeated edges. Use a new instance of Edge instead.");
			return;
		}

		graph.get(node1).put(node2, edge);
		graph.get(node2).put(node1, edge);
	}

	/**
	 * Returns the edge between node1 and node2. 
	 * Returns null in case it doesn't exist.
	 */
	public E getEdge(N node1, N node2)
	{
		Set<N> allEdges = getAllNodes();
		if(!allEdges.contains(node1) || !allEdges.contains(node2)) return null;
		
		return graph.get(node1).get(node2);
	}

	/*
	 * Returns a set of all the edges. (Consequently, there aren't repeated edges)
	 */
	public Set<E> getAllEdges()
	{
		Set<E> edges = new HashSet<E>();
		Set<N> nodes =  getAllNodes();
		for(N n : nodes)
		{
			for(N n2 : nodes)
			{
				E edge = getEdge(n, n2);
				if(edge != null && !edges.contains(edge)) edges.add(edge);
			}
		}
		return edges;
	}
	
	/**
	 * Removes a  given edge.
	 * @param edge The edge that will be removed
	 */
	public void removeEdge(E edge)
	{
		//We need to use iterators because we are removing while iterating items
		Iterator it1 = graph.values().iterator();
		while(it1.hasNext())
		{
			HashMap<N,E> adjList = (HashMap<N,E>) it1.next();
			Iterator it2 = adjList.keySet().iterator();
			while(it2.hasNext())
			{
				Node adjNode = (Node)it2.next();
				if(adjList.get(adjNode) == edge) it2.remove();
			}
		}
	}
	/**
	 * Removes all the edges.
	 */
	public void removeAllEdges()
	{
		Set<E> edges = getAllEdges();
		for(E e : edges) removeEdge(e);
	}
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	///// COMMUNITIES STUFF //////////////////////////////////////////////
	/**
	 * Returns the connected components in the graph
	 */
	public ArrayList< Set<N> > getConnectedComponents()
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
		
		return connectedComponents;
	}
	//////////////////////////////////////////////////////////////////////
	
	public boolean equals(Object o)
	{
		Graph<N,E> g = (Graph<N,E>) o;
		
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
								E eG = g.getEdge(nG, nAdjG);
								E eThis = this.getEdge(nThis, nAdjThis);
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
	
	//// UTILS ////////////
	public void print()
	{
		for(N n1 : getAllNodes())
		{
			System.out.print(n1.getId() + ": ");
			for(N n2 : getAdjacentNodesTo(n1))
			{
				E e = getEdge(n1, n2);
				System.out.print("(" + n2.getId() + ", " + e.getWeight() + "), ");
			}
			System.out.println(""); // :3
		}
	}
	////////////////////////////////////////////////////////////////////////////
}

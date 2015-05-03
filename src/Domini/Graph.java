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
	 * Add a new disconnected node (without edges to any node). 
	 * PRE: The node mustn't exist in the graph before adding it(always add a new Node), 
	 * 	    neither a node with the same id.
	 * 		If you add a node and don't take this into account, the graph may not work properly.
	 * @param node The node to be added
	 */
	public void AddNode(N node)
	{
		graph.put(node, new HashMap<N, E>());
	}

	/**
	 * Returns a set of nodes the given node is connected(adjacent) to. 
	 */
	public Set<N> GetAdjacentNodesTo(N node)
	{
		if(!graph.containsKey(node)) { System.err.println("The graph doesn't contain the node."); return null; }
		return graph.get(node).keySet();
	}

	/**
	 * Returns the two nodes connected by the edge e
	 */
	public Pair<N, N> GetNodesConnectedBy(E e)
	{
		for(N n1 : graph.keySet())
			for(N n2 : graph.get(n1).keySet())
				if(GetEdge(n1, n2) == e) 
					return new Pair<N, N>(n1, n2);
		return null;
	}

	/**
	 * Returns a set of all the nodes in the graph
	 */
	public Set<N> GetAllNodes()
	{
		return graph.keySet();
	}
	
	/**
	 * Remove a node and all its connections
	 * @param node The node to be removed
	 */
	public void RemoveNode(N node)
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
	 * Add a new edge between two nodes. 
	 * PRE: The edge passed as parameter mustn't exist in the graph before adding it.
	 *      Always add a new Edge.
	 * @param node1 The first node to be connected
	 * @param node2 The second node to be connected
	 * @param edge The edge that will connect node1 and node2
	 */
	public void AddEdge(N node1, N node2, E edge)
	{
		if(!graph.containsKey(node1) || !graph.containsKey(node2))
		{
			System.err.println("node1 or node2 don't exist in the graph.");
			return;
		}

		graph.get(node1).put(node2, edge);
		graph.get(node2).put(node1, edge);
	}

	/**
	 * Returns the edge between node1 and node2. 
	 * Returns null in case it doesn't exist.
	 */
	public E GetEdge(N node1, N node2)
	{
		Set<N> allEdges = GetAllNodes();
		if(!allEdges.contains(node1) || !allEdges.contains(node2)) return null;
		
		return graph.get(node1).get(node2);
	}

	/*
	 * Returns a set of all the edges. (Consequently, there aren't repeated edges)
	 */
	public Set<E> GetAllEdges()
	{
		Set<E> edges = new HashSet<E>();
		Set<N> nodes =  GetAllNodes();
		for(N n : nodes)
		{
			for(N n2 : nodes)
			{
				E edge = GetEdge(n, n2);
				if(edge != null && !edges.contains(edge)) edges.add(edge);
			}
		}
		return edges;
	}
	
	/**
	 * Removes a  given edge.
	 * @param edge The edge that will be removed
	 */
	public void RemoveEdge(E edge)
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
	public void RemoveAllEdges()
	{
		Set<E> edges = GetAllEdges();
		for(E e : edges) RemoveEdge(e);
	}
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	///// COMMUNITIES STUFF //////////////////////////////////////////////
	/**
	 * Returns the connected components in the graph
	 */
	public ArrayList< Set<N> > GetConnectedComponents()
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
					ArrayList<N> nsAdjG =  new ArrayList<N>(g.graph.get(nG).keySet());
					ArrayList<N> nsAdjThis =  new ArrayList<N>(this.graph.get(nThis).keySet());
					
					//Els nodes adjacents a nG han de ser els mateixos (mateixa id)
					if(!nsAdjG.containsAll(nsAdjThis) || !nsAdjThis.containsAll(nsAdjG)) return false;
					for(N nAdjG : nsAdjG)
					{
						for(N nAdjThis : nsAdjThis)
						{
							if(nAdjThis.equals(nAdjG))
							{
								E eG = g.GetEdge(nG, nAdjG);
								E eThis = this.GetEdge(nThis, nAdjThis);
								if(eG == null && eThis == null) break;
								if(eG == null && eThis != null) return false;
								if(eG != null && eThis == null) return false;
								if(eG.GetWeight() != eThis.GetWeight()) return false;
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
	public void Print()
	{
		for(N n1 : GetAllNodes())
		{
			System.out.print(n1.GetId() + ": ");
			for(N n2 : GetAdjacentNodesTo(n1))
			{
				E e = GetEdge(n1, n2);
				System.out.print("(" + n2.GetId() + ", " + e.GetWeight() + "), ");
			}
			System.out.println(""); // :3
		}
	}
	////////////////////////////////////////////////////////////////////////////
}

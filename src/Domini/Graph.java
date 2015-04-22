package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	public void AddNode(N node)
	{
		graph.put(node, new HashMap<N, E>()); //Init its edge list
	}

	/**
	 * Returns a set of nodes the given node is connected(adjacent) to. 
	 */
	public Set<N> GetAdjacentNodesTo(N node)
	{
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
	public void AddEdge(N node1, N node2, E edge)
	{
		graph.get(node1).put(node2, edge);
		graph.get(node2).put(node1, edge);
	}

	/**
	 * Returns the edge between node1 and node2. 
	 * Returns null in case it doesn't exist.
	 */
	public E GetEdge(N node1, N node2)
	{
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
		for(HashMap<N,E> adjList : graph.values())
		{
			for(E nodeEdge : adjList.values())
			{
		    	if(edge.equals(nodeEdge))
		    	{
		    		adjList.remove(nodeEdge);
		    	}
		    }
	    }
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

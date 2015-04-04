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


	/**
	 * Add a new disconnected node (without edges to any node)
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
	 * Returns a set of all the nodes in the graph
	 */
	public Set<N> GetNodes()
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

	/**
	 * Sets the weight of all edges to 0
	 */
	private void ClearEdgeBetweenness()
	{
		for(N n : graph.keySet()) 
			for(E e : graph.get(n).values()) e.SetWeight(0);
	}

	/**
	 * Returns communities in the graph
	 * @param comNumber The number of communities that you want
	 */
	public ArrayList< HashSet<N> > GetCommunities(int comNumber)
	{
		if(comNumber > GetNodes().size())
		{
			System.err.println("The number of communities can't be greater than the number of nodes.");
			return new ArrayList< HashSet<N> >();
		}
		
		return GetConnectedComponents();
	}

	/**
	 * Returns the connected components in the graph
	 */
	public ArrayList< HashSet<N> > GetConnectedComponents()
	{
		ArrayList< HashSet<N> > connectedComponents = new ArrayList< HashSet<N> >();
		HashSet<N> visitedNodes = new HashSet<N>();
		
		for(N origin : graph.keySet())
		{
			if(visitedNodes.contains(origin)) continue;
			
			LinkedList<N> nextNodes = new LinkedList<N>();
			
			N currentNode = origin;
			nextNodes.push(origin);
			
			HashSet<N> cc = new HashSet<N>();
			connectedComponents.add(cc);
			cc.add(origin);
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
	
	public void UpdateEdgeBetweenness()
	{
		ClearEdgeBetweenness(); //All edges to zero
		
		//For every node, get the shortest path to every other node
		//For every edge every path passes by, add 1 to its betweenness
		for(N origin : graph.keySet())
		{
			HashSet<N> visitedNodes = new HashSet<N>();
			LinkedList<N> nextNodes = new LinkedList<N>();
			
			N currentNode = origin;
			nextNodes.push(origin);
			visitedNodes.add(origin);
			while(nextNodes.size() > 0)
			{
				currentNode = nextNodes.get(0); nextNodes.remove(0);
			    for(N n : graph.get(currentNode).keySet())
			    {
			    	if(!visitedNodes.contains(n))
			    	{	
						visitedNodes.add(n);
				    	nextNodes.add(nextNodes.size(), n);
				    	
				    	E e = graph.get(currentNode).get(n);
				    	e.SetWeight(e.GetWeight() + 1); //Add betweenness
				    }
			    }
			}
		}
	}
	
	public LinkedList<N> GetShortestPath(N origin, N destiny) //BFS
	{
		HashMap<N, N> parentNodes = new HashMap<N, N>();
		HashSet<N> visitedNodes = new HashSet<N>();
		LinkedList<N> nextNodes = new LinkedList<N>();
		
		N currentNode = origin;
		nextNodes.push(origin);
		visitedNodes.add(origin);
		
		boolean found = false;
		while(nextNodes.size() > 0 && !found)
		{
			currentNode = nextNodes.get(0); nextNodes.remove(0);
		    for(N n : graph.get(currentNode).keySet())
		    {
		    	if(!visitedNodes.contains(n))
		    	{	
					visitedNodes.add(n);
		    		parentNodes.put(n, currentNode);
			    	nextNodes.add(nextNodes.size(), n);
			    	if(n == destiny) { found = true; break; }
		    	}
		    }
		}
		
		LinkedList<N> path = new LinkedList<N>(); //Get the path
		if(found)
		{
			N n = destiny;
			while(n != origin)
			{
				path.add(0, n);
				n = parentNodes.get(n); //jump to the previous node
			}
			path.add(0, origin);
		}
		return path;
	}
	
	public void Print()
	{
		for(N n1 : GetNodes())
		{
			System.out.print(n1.GetId() + ": ");
			for(N n2 : GetAdjacentNodesTo(n1))
			{
				E e = GetEdge(n1, n2);
				System.out.print("(" + n2.GetId() + ", " + e.GetWeight() + "), ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Add a new edge between two nodes
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

	/**
	 * Removes a  given edge between EVERY pair of connected nodes using this edge.
	 * Think that you can connect thousands of nodes using the same edge instance.
	 * If this happens, all those thousands of connections will be removed.
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
}

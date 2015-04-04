package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map.Entry;
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
		Iterator<N> it = graph.get(node).keySet().iterator();
	    while (it.hasNext()) //Remove every edge connected to the removed node
	    {
	    	//Remove the removed node from others nodes' edge list
	    	graph.get( it.next() ).remove(node);
	    }
	    graph.remove(node); //Remove the node itself
	}
	
	private void ClearEdgeBetweenness()
	{
		Iterator<N> it = graph.keySet().iterator();
		while(it.hasNext())
		{
			Iterator<E> it2 = graph.get( it.next() ).values().iterator();
			while(it2.hasNext()) it2.next().SetWeight(0);
		}
	}
	
	public void UpdateEdgeBetweenness()
	{
		ClearEdgeBetweenness(); //All edges to zero
		
		Iterator<N> it = graph.keySet().iterator();
		//For every node, get the shortest path to every other node
		//For every edge every path passes by, add 1 to its betweenness
		while(it.hasNext()) 
		{
			N origin = it.next(); //FOR EVERY NODE

			HashSet<N> visitedNodes = new HashSet<N>();
			LinkedList<N> nextNodes = new LinkedList<N>();
			
			N currentNode = origin;
			nextNodes.push(origin);
			visitedNodes.add(origin);
			
			while(nextNodes.size() > 0)
			{
				currentNode = nextNodes.get(0); nextNodes.remove(0);
				Iterator<N> it2 = graph.get(currentNode).keySet().iterator();
			    while (it2.hasNext())
			    {
			    	N n = it2.next();
			    	if(!visitedNodes.contains(n))
			    	{	
						visitedNodes.add(n);
				    	nextNodes.add(nextNodes.size(), n);
				    	
				    	//Add 1 to the edge!
				    	E e = graph.get(currentNode).get(n);
				    	e.SetWeight(e.GetWeight() + 1);
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
			Iterator<N> it = graph.get(currentNode).keySet().iterator();
		    while (it.hasNext())
		    {
		    	N n = it.next();
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
		Iterator<N> it = GetNodes().iterator();
		while(it.hasNext())
		{
			N n1 = it.next();
			Iterator<N> it2 = GetAdjacentNodesTo(n1).iterator();
			while(it2.hasNext())
			{
				N n2 = it2.next();
				E e  = GetEdge(n1, n2);
				
				System.out.println(n1.GetId() + " <- " + e.GetWeight() + " -> " + n2.GetId());
			}
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
		Iterator<HashMap<N,E>> it = graph.values().iterator();
	    while (it.hasNext())
	    {
	    	HashMap<N,E> adjList = it.next();
			Iterator<Entry<N,E>> it2 = adjList.entrySet().iterator();
		    while (it2.hasNext())
		    {
		    	Entry<N,E> nodeEdge = it2.next();
		    	if(edge.equals(nodeEdge.getValue()))
		    	{
		    		adjList.remove(nodeEdge.getKey());
		    	}
		    }
	    }
	}
}

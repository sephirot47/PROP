package Domini;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Graph <N, E extends Edge>
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
	 * Remove a node and all its connections
	 * @param node The node to be removed
	 */
	public void RemoveNode(N node)
	{	
		//Get the nodes it was connected to
		Set<N> connections = graph.get(node).keySet(); 
		Iterator<N> it = (Iterator<N>) connections.iterator();
	    while (it.hasNext()) //Remove every edge connected to the removed node
	    {
	    	//Remove the removed node from others nodes' edge list
	    	graph.get( it.next() ).remove(node);
	    }
	    graph.remove(node); //Remove the node itself
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
			Set<Entry<N,E>> entryList = adjList.entrySet();
			Iterator<Entry<N,E>> it2 = entryList.iterator();
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

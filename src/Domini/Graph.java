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
	 * Returns the first occurrence of the two nodes connected
	 * by the edge e
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
	
	//////////////////////////////////////////////////////////////////////
	

	
	
	///// EDGE STUFF /////////////////////////////////////////////////////
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
	//////////////////////////////////////////////////////////////////////
	
	
	
	
	///// COMMUNITIES STUFF //////////////////////////////////////////////
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
			N currentNode = origin; nextNodes.push(origin);
			
			HashSet<N> cc = new HashSet<N>();
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
	
	
	
	
	////// GIRVAN NEWMAN STUFF (don't read if you don't want to throw up) /////
	/**
	 * Returns communities in the graph
	 * @param comNumber The number of communities that you want at least
	 */
	public ArrayList< HashSet<N> > GetCommunitiesGirvanNewman(int n)
	{
		if(n > GetNodes().size())
		{
			System.err.println("The number of communities can't be greater than the number of nodes.");
			return null;
		}
		
		ClearEdgeBetweenness(); //ALL edges to zero betweenness
		UpdateEdgeBetweennessGirvanNewman(); //Weight the edges
		ArrayList< HashSet<N> > connectedComponents = GetConnectedComponentsGirvanNewman();
		while(connectedComponents.size() < n)
		{	
			Print();
			
			//Search for the edge with maximum betweenness
			E edgeToRemove = null;
			float maxEdgeBetweenness = 0;
			for(N node1 : graph.keySet())
			{
				for(N node2 : graph.get(node1).keySet())
				{
					E currentEdge = GetEdge(node1, node2);
					if(currentEdge.GetWeight() > maxEdgeBetweenness)
					{
						edgeToRemove = currentEdge; //update the edge that'll be removed
						maxEdgeBetweenness = currentEdge.GetWeight();
					}
				}
			}
			
			//Remove it (pseudo remove it(put its weight to -1))
			if(edgeToRemove != null) edgeToRemove.SetWeight(-1);

			//Weight the edges. It doesn't take into account the negative weighted edges!!!
			//(as if they didn't exist)
			UpdateEdgeBetweennessGirvanNewman();
			
			//Count the connected components again, in order to know if we must continue
			//removing edges or not
			connectedComponents = GetConnectedComponentsGirvanNewman();
		}
		return connectedComponents;
	}


	/**
	 * Returns the connected components in the graph, but it doesn't
	 * take into account the edges with weight < 0. This is because for 
	 * the GirvanNewman algorithm implementation, you have to remove edges
	 * so this a way to preserve the edges and not doing copies of the graph. 
	 * Pene.
	 */
	public ArrayList< HashSet<N> > GetConnectedComponentsGirvanNewman()
	{
		ArrayList< HashSet<N> > connectedComponents = new ArrayList< HashSet<N> >();
		HashSet<N> visitedNodes = new HashSet<N>();
		for(N origin : graph.keySet())
		{
			if(visitedNodes.contains(origin)) continue;
			
			LinkedList<N> nextNodes = new LinkedList<N>();
			N currentNode = origin; nextNodes.push(origin);
			
			HashSet<N> cc = new HashSet<N>();
			connectedComponents.add(cc); cc.add(origin);
			while(nextNodes.size() > 0)
			{
				currentNode = nextNodes.get(0); nextNodes.remove(0);
			    for(N n : graph.get(currentNode).keySet())
			    {
			    	if(GetEdge(currentNode, n).GetWeight() >= 0 && //The only line that changes
			    	   !visitedNodes.contains(n))
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

	/**
	 * Sets the weight of all edges to 0
	 */
	private void ClearEdgeBetweenness()
	{
		for(N n : graph.keySet()) 
			for(E e : graph.get(n).values()) e.SetWeight(0);
	}

	/**
	 * Sets the weight of all edges to 0, except the negative ones
	 */
	private void ClearEdgeBetweennessExceptNegative()
	{
		for(N n : graph.keySet()) 
			for(E e : graph.get(n).values()) if(e.GetWeight() > 0) e.SetWeight(0);
	}
	
	/**
	 * Updates the betweenness of every edge, this is, it assigns a weight equal
	 * to [2 * (the number of shortest paths from every node to every node that pass through that edge)]
	 * (approximately hehehe)
	 */
	public void UpdateEdgeBetweennessGirvanNewman()
	{
		ClearEdgeBetweennessExceptNegative(); //All edges to zero, except the negative ones
		
		//For every node, get the shortest path to every other node
		//For every edge every path passes by, add 1 to its betweenness
		for(N n1 : graph.keySet())
		{
			for(N n2 : graph.keySet())
			{
				if(n1 == n2) continue;
				
				LinkedList<N> path = GetShortestPathGirvanNewman(n1, n2);
				Iterator<N> it = path.iterator();
				
				N prevN = null; 
				if(it.hasNext()) prevN = it.next();
				while(it.hasNext())
				{
					if(it.hasNext())
					{
						N n = it.next();
						Edge e = GetEdge(prevN, n);
						if(e != null) e.SetWeight(e.GetWeight() + 1);
						prevN = n;
					}
				}
				
				//System.out.println(n1.GetId() + " to " + n2.GetId());
				//Print();
			}
		}
	}
	
	public LinkedList<N> GetShortestPathGirvanNewman(N origin, N destiny) //BFS
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
		    	if(!visitedNodes.contains(n) && GetEdge(currentNode, n).GetWeight() >= 0)
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
	
	////////////////////////////////////////////////////////////////////////////
	
	
	//// UTILS ////////////
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
			System.out.println(""); // :3
		}
	}
	////////////////////////////////////////////////////////////////////////////
}

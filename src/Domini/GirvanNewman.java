package Domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;


public class GirvanNewman
{
	/**
	 * Returns communities in the graph, using the GirvanNewman algorithm
	 * @param graph The graph to be processed
	 * @param comNumber The number of communities that you want at least
	 */
	public static <N extends Node> ArrayList< Set<N> > getSolution(Graph g, int n)
	{
		if(n > g.getAllNodes().size())
		{
			System.err.println("The number of communities can't be greater than the number of nodes.");
			return null;
		}
		
		//Save the original weights to restore them later
		Set<Edge> edges = g.getAllEdges();
		Set<Pair<Edge, Float>> originalEdges = new HashSet<Pair<Edge, Float>>();
		for(Edge e : edges) originalEdges.add( new Pair<Edge, Float>(e, e.getWeight()) );
		//
				
		clearEdgeBetweenness(g); //ALL edges to zero betweenness
		updateEdgeBetweenness(g); //Weight the edges
		ArrayList< Set<N> > connectedComponents = GirvanNewman.getConnectedComponents(g);
		while(connectedComponents.size() < n)
		{	
			//Search for the edge with maximum betweenness
			Edge edgeToRemove = null;
			float maxEdgeBetweenness = 0;
			Set<N> nodes = g.getAllNodes();
			for(N node1 : nodes)
			{
				Set<N> adjNodes = g.getAdjacentNodesTo(node1);
				for(N node2 : adjNodes)
				{
					Edge currentEdge = g.getEdge(node1, node2);
					if(currentEdge.getWeight() > maxEdgeBetweenness)
					{
						edgeToRemove = currentEdge; //update the edge that'll be removed
						maxEdgeBetweenness = currentEdge.getWeight();
					}
				}
			}
			
			//Remove it (pseudo remove it(put its weight to -1))
			if(edgeToRemove != null) edgeToRemove.setWeight(-1);

			//Weight the edges. It doesn't take into account the negative weighted edges!!!
			//(as if they didn't exist)
			updateEdgeBetweenness(g);
			
			//Count the connected components again, in order to know if we must continue
			//removing edges or not
			connectedComponents = getConnectedComponents(g);
		}

		//Restore the original weights !!!!!
		for(Pair<Edge, Float> e : originalEdges)
		{
			e.getFirst().setWeight(e.getSecond()); //Deberia restaurarlos ya que los edges se guardan por referencia
		}
		//
		
		return connectedComponents;
	}
	


	/**
	 * Returns the connected components in the graph, but it doesn't
	 * take into account the edges with weight < 0. This is because for 
	 * the GirvanNewman algorithm implementation, you have to remove edges
	 * so this a way to preserve the edges and not doing copies of the graph. 
	 * Pene.
	 */
	private static <N extends Node> ArrayList< Set<N> > getConnectedComponents(Graph g)
	{
		ArrayList<Set<N>> connectedComponents = new ArrayList< Set<N> >();
		HashSet<N> visitedNodes = new HashSet<N>();
		Set<N> nodes = g.getAllNodes();
		for(N origin : nodes)
		{
			if(visitedNodes.contains(origin)) continue;
			
			LinkedList<N> nextNodes = new LinkedList<N>();
			N currentNode = origin; nextNodes.push(origin);
			
			HashSet<N> cc = new HashSet<N>();
			connectedComponents.add(cc); cc.add(origin);
			while(nextNodes.size() > 0)
			{
				currentNode = nextNodes.get(0); nextNodes.remove(0);
				Set<N> adjNodes = (Set<N>) g.getAdjacentNodesTo(currentNode);
			    for(N n : adjNodes)
			    {
			    	if(g.getEdge(currentNode, n).getWeight() >= 0 && //The only line that changes
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
	private static void clearEdgeBetweenness(Graph g)
	{
		Set<Edge> edges = g.getAllEdges();
		for(Edge e : edges)  e.setWeight(0);
	}

	/**
	 * Sets the weight of all edges to 0, except the negative ones
	 */
	private static void clearEdgeBetweennessExceptNegative(Graph g)
	{
		Set<Edge> edges = g.getAllEdges();
		for(Edge e : edges) 
			if(e.getWeight() > 0) e.setWeight(0);
	}
	
	/**
	 * Updates the betweenness of every edge, this is, it assigns a weight equal
	 * to [2 * (the number of shortest paths from every node to every node that pass through that edge)]
	 * (approximately hehehe)
	 */
	private static <N extends Node> void updateEdgeBetweenness(Graph g)
	{
		clearEdgeBetweennessExceptNegative(g); //All edges to zero, except the negative ones
		
		//For every node, get the shortest path to every other node
		//For every edge every path passes by, add 1 to its betweenness
		Set<N> nodes = g.getAllNodes();
		for(N n1 : nodes)
		{
			for(N n2 : nodes)
			{
				if(n1 == n2) continue;
				
				LinkedList<N> path = getShortestPath(g, n1, n2);
				Iterator<N> it = path.iterator();
				
				N prevNode = null; 
				if(it.hasNext()) prevNode = it.next();
				while(it.hasNext())
				{
					if(it.hasNext())
					{
						N n = it.next();
						Edge e = g.getEdge(prevNode, n);
						if(e != null) e.setWeight(e.getWeight() + 1);
						prevNode = n;
					}
				}
			}
		}
	}
	
	private static <N extends Node> LinkedList<N> getShortestPath(Graph g, N origin, N destiny) //BFS
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
		    for(N n : (Set<N>) g.getAllNodes())
		    {
		    	Edge e = g.getEdge(currentNode, n);
		    	if(e != null && !visitedNodes.contains(n) && e.getWeight() >= 0)
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
	
}

package Domini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class GirvanNewman extends Algorithm
{
	/**
	 * Returns communities in the graph, using the GirvanNewman algorithm
	 * @param graph The graph to be processed
	 * @param comNumber The number of communities that you want at least
	 */
	public Solution getSolution(Graph g, int n)
	{
		long startTime = System.nanoTime();
		
		if(n > g.getAllNodes().size())
		{
			System.err.println("The number of communities can't be greater than the number of nodes.");
			return null;
		}
		
		//Save the original weights to restore them later
		Set<Edge> edges = g.getAllEdges();
		HashMap<Edge, Float> originalWeights = new HashMap<Edge, Float>();
		for(Edge e : edges) originalWeights.put( e, e.getWeight() );
		//
		
		ArrayList< Set<Node> > connectedComponents = getConnectedComponents(g);
		while(connectedComponents.size() < n)
		{	 
			HashMap<Edge, Float> edgeBetweenness = new HashMap<Edge, Float>();
			for(Edge e : edges) edgeBetweenness.put(e, 0.0f);
			
			// DIJKSTRA, actualitzem betweenness de edges ///////
			Set<Node> nodes = g.getAllNodes();
			for(Node node : nodes)
			{
				Dijkstra dijkstra = new Dijkstra(g);
				dijkstra.execute(node);
				for(Node n2 : nodes)
				{
					if(node == n2) continue;
					
					LinkedList<Node> path = dijkstra.getPath(n2);
					if(path != null)
					{
						for(int i = 0; i < path.size() - 1; ++i)
						{
							Edge e = g.getEdge(path.get(i), path.get(i+1));
							Float betweenness = edgeBetweenness.get(e);
							edgeBetweenness.put(e, betweenness + 1); //edgeBetweenness++
						}
					}
				}	
			}
			///////////////////
			
			//Search for the edge with maximum betweenness
			Set<Edge> edgesToRemove = new HashSet<Edge>();
			Edge edgeToRemove = null;
			float maxEdgeBetweenness = Float.NEGATIVE_INFINITY;
			for(Node node1 : nodes)
			{
				Set<Node> adjNodes = g.getAdjacentNodesTo(node1);
				for(Node node2 : adjNodes)
				{
					Edge currentEdge = g.getEdge(node1, node2);
					if(currentEdge.getWeight() != -1.0f) //Si el edge existeix...
					{	
						Float betweenness = edgeBetweenness.get(currentEdge);
						if(betweenness != null && betweenness >= maxEdgeBetweenness)
						{
							if(betweenness > maxEdgeBetweenness)
							{
								edgesToRemove.clear();
								maxEdgeBetweenness = betweenness;
							}
							
							edgesToRemove.add(currentEdge);
						}
					}
				}
			}

			//System.out.println("");
			//System.out.println("Edges Left:");
			for(Edge e : edges)
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(e);
				//if(e.getWeight() >= 0)System.out.println(nn.getFirst().getId() + " ~" + e.getWeight() + "~ " + nn.getSecond().getId());
			}

			//Obtenim el edge amb MENOR pes, per eliminar, de la llista de edges amb la mateixa betweenness
			ArrayList<Edge> edgesWithSameBetweennesAndWeight = new ArrayList<Edge>();
			float minEdgeWeight = Float.POSITIVE_INFINITY;
			for(Edge e : edgesToRemove)
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(e);
				//System.out.println("Candidate to remove: " + nn.getFirst().getId() + " ~" + e.getWeight() + "~ " + nn.getSecond().getId() + " (edgeBetweenness=" + edgeBetweenness.get(e) + ")");
				if(e.getWeight() <= minEdgeWeight)
				{
					if(e.getWeight() < minEdgeWeight) 
					{
						edgesWithSameBetweennesAndWeight.clear();
						minEdgeWeight = e.getWeight();
					}
					
					edgesWithSameBetweennesAndWeight.add(e);
				}
			}

			//System.out.println("");
			//System.out.println("edgesWithSameBetweennesAndWeight: ");
			for(Edge e : edgesWithSameBetweennesAndWeight)
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(e);
				//System.out.println(nn.getFirst().getId() + " ~" + e.getWeight() + "~ " + nn.getSecond().getId());
			}
			
			//Trobem el edge que te la major S, on S es la suma de els nodes adjacents a cada un dels nodes que conecta el edge
			float maxS = Float.NEGATIVE_INFINITY;
			for(Edge e : edgesWithSameBetweennesAndWeight)
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(e);
				float s = getAdjacentNodesCountGN(g, nn.getFirst()) + getAdjacentNodesCountGN(g, nn.getSecond());
				if(s >= maxS)
				{
					edgeToRemove = e;
					maxS = s;
				}
			}
			//System.out.println("");
			//System.out.println("maxS: " + maxS);

			if(edgeToRemove != null)
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(edgeToRemove);
				//System.out.println("");
				//System.out.println("EDGE TO REMOVE:");
				//System.out.println(nn.getFirst().getId() + " ~" + edgeToRemove.getWeight() + "~ " + nn.getSecond().getId());
				//System.out.println("________________");
			}
			
			/*
			Set<Edge> edd = g.getAllEdges();
			for(Edge e : edd)
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(e);
				if(e != null) System.out.println( nn.getFirst().getId() + " ~" + edgeBetweenness.get(e) + "~ " + nn.getSecond().getId());
			}*/
			
			//Remove it (pseudo remove it(put its weight to -1))
			if(edgeToRemove != null) 
			{
				Pair<Node, Node> nn = g.getNodesConnectedBy(edgeToRemove);
				//System.out.println("Going to remove " + nn.getFirst().getId() + "~" + nn.getSecond().getId());
				edgeToRemove.setWeight(-1);
			}
			
			//System.out.println("_______________");
			
			//Count the connected components again, in order to know if we must continue
			//removing edges or not
			connectedComponents = getConnectedComponents(g);
			//System.out.println("*******************************************+");
		}

		//Restore the original weights !!!!!
		for(Edge e : edges)
		{
			e.setWeight( originalWeights.get(e) ); //Deberia restaurarlos ya que los edges se guardan por referencia
		}
		//
		
		ArrayList<Community> communities = new ArrayList<Community>();
		for(Set<Node> setNodes : connectedComponents)
		{
			Community com = new Community();
			for(Node nodo : setNodes) com.addNode(nodo);
			communities.add(com);
		}
		
		long genTime = System.nanoTime() - startTime;
		Solution s = new Solution();
		for(Community c : communities) s.addCommunity(c);
		s.setTime(genTime);
		s.setAlg('G');
		return s;
	}
	
	private static int getAdjacentNodesCountGN(Graph<Node> g, Node n)
	{
		Set<Edge> adjEdges = g.getAdjacentEdgesTo(n);
		int i = 0;
		for(Edge e : adjEdges)
			if(e.getWeight() >= 0) ++i;
		return i;
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
	 * We had to put this function here in order to be able to extend the Algorithm class........
	 */
	public Solution getSolution(Graph g)
	{
		int nCommunities = (int) Math.floor( Math.sqrt(g.getAllNodes().size()) );
		return getSolution(g, nCommunities);
	}
	
	private class Dijkstra
	{
	  private final List<Node> nodes;
	  private final List<Edge> edges;
	  private Set<Node> settledNodes;
	  private Set<Node> unSettledNodes;
	  private HashMap<Node, Node> predecessors;
	  private HashMap<Node, Float> distance;
	  private Graph g;
	  
	  public Dijkstra(Graph graph) 
	  {
		g = graph;
		
	    // create a copy of the array so that we can operate on this array
	    this.nodes = new ArrayList<Node>(graph.getAllNodes());
	    this.edges = new ArrayList<Edge>(graph.getAllEdges());
	  }
	
	  public void execute(Node source) 
	  {
	    settledNodes = new HashSet<Node>();
	    unSettledNodes = new HashSet<Node>();
	    distance = new HashMap<Node, Float>();
	    predecessors = new HashMap<Node, Node>();
	    distance.put(source, 0.0f);
	    unSettledNodes.add(source);
	    while (unSettledNodes.size() > 0) 
	    {
	      Node node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      findMinimalDistances(node);
	    }
	  }
	
	  private void findMinimalDistances(Node node) 
	  {
	    List<Node> adjacentNodes = getNeighbors(node);
	    for (Node target : adjacentNodes) 
	    {
	      if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) 
	      {
	        distance.put(target, getShortestDistance(node) + getDistance(node, target));
	        predecessors.put(target, node);
	        unSettledNodes.add(target);
	      }
	    }
	
	  }
	
	  private float getDistance(Node node, Node target) 
	  {
		return g.getEdge(node, target).getWeight();
		/*
	    for (Edge edge : edges)
	    {
	      if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
	        return edge.getWeight();
	      }
	    }
	    throw new RuntimeException("Should not happen");
	    */
	  }
	
	  private List<Node> getNeighbors(Node node) 
	  {
	    List<Node> neighbors = new ArrayList<Node>();
	     
	    Set<Node> adjNodes = g.getAdjacentNodesTo(node);
	    for(Node n : adjNodes)
	    {
	    	Edge e = g.getEdge(node, n);
	    	if(e != null && e.getWeight() != -1) 
	    		neighbors.add(n);
	    }
	    
	    return neighbors;
	  }
	
	  private Node getMinimum(Set<Node> Nodees) {
	    Node minimum = null;
	    for (Node Node : Nodees) {
	      if (minimum == null) {
	        minimum = Node;
	      } else {
	        if (getShortestDistance(Node) < getShortestDistance(minimum)) {
	          minimum = Node;
	        }
	      }
	    }
	    return minimum;
	  }
	
	  private boolean isSettled(Node Node) {
	    return settledNodes.contains(Node);
	  }
	
	  private Float getShortestDistance(Node destination) {
	    Float d = distance.get(destination);
	    if (d == null) {
	      return Float.POSITIVE_INFINITY;
	    } else {
	      return d;
	    }
	  }
	
	  /*
	   * This method returns the path from the source to the selected target and
	   * NULL if no path exists
	   */
	  public LinkedList<Node> getPath(Node target) {
	    LinkedList<Node> path = new LinkedList<Node>();
	    Node step = target;
	    // check if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    path.add(step);
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    // Put it into the correct order
	    Collections.reverse(path);
	    return path;
	  }
	};
}

package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class GraphTest extends TestCase 
{
	
	class N extends Node
	{
		public String GetId() { return "potato"; }
	}
	
	class E extends Edge
	{
		float weight;
		public float GetWeight() { return weight;}
		public void SetWeight(float weight) { this.weight = weight; }
	}
	public GraphTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testAddNode() 
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		g.AddNode(n);
			
	}
	public void TestGetAdjacentNodesTo()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.GetAdjacentNodesTo(n1);
	}
	public void TestGetNodesConnectedBy()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		E e = new E();
		e.SetWeight(3.14f);
		
		Pair<N,N> PairNodes = new Pair<N,N>(n,n1);
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddEdge(n, n1, e);
		
		assertEquals(PairNodes,g.GetNodesConnectedBy(e));
		
	}
	public void TestAddEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		
		E e = new E();
		e.SetWeight(3.14f);
		g.AddNode(n);
		g.AddNode(n1);
		
		g.AddEdge(n, n1, e);
	
	}
	public void TestGetAllNodes()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		Set<N> p = new HashSet<N>(Arrays.asList(n,n1,n2,n3,n4));
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		if(p.equals(g.GetAllNodes())) System.out.println("Execution Complete and OK");
		else System.out.println("Execution Complete and NOT OK");
	}
	
	public void TestRemoveNode()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		Set<N> p = new HashSet<N>(Arrays.asList(n1,n2,n3,n4));
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.RemoveNode(n);
		
		if(p.equals(g.GetAllNodes())) System.out.println("Execution Complete and OK");
		else System.out.println("Execution Complete and NOT OK");
		
	}
	public void TestGetEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		
		E e = new E();
		e.SetWeight(3.14f);
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddEdge(n, n, e);
		
		if(e.equals(g.GetEdge(n,n1))) System.out.println("Execution Complete and OK");
		else System.out.println("Execution Complete and NOT OK");
		
		
	}
	public void TestGetAllEdges()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		E e = new E();
		E e1 = new E();
		E e2 = new E();
		E e3 = new E();
		E e4 = new E();
		
		e.SetWeight(3.1f);
		e1.SetWeight(3.14f);
		e2.SetWeight(3.141f);
		e3.SetWeight(3.1415f);
		e4.SetWeight(3.14159f);
		
		
		g.AddEdge(n, n1, e);
		g.AddEdge(n1, n2, e1);
		g.AddEdge(n2, n3, e2);
		g.AddEdge(n3, n4, e3);
		g.AddEdge(n4, n, e4);
		
		Set<E> p = new HashSet<E>(Arrays.asList(e,e1,e2,e3,e4));
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.RemoveNode(n);
		
		if(p.equals(g.GetAllEdges())) System.out.println("Execution Complete and OK");
		else System.out.println("Execution Complete and NOT OK");
		
		
	}
	
	public void RemoveEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		E e = new E();
		E e1 = new E();
		E e2 = new E();
		E e3 = new E();
		E e4 = new E();
		
		e.SetWeight(3.1f);
		e1.SetWeight(3.14f);
		e2.SetWeight(3.141f);
		e3.SetWeight(3.1415f);
		e4.SetWeight(3.14159f);
		
		Set<E> p = new HashSet<E>(Arrays.asList(e,e1,e2,e3));
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.AddEdge(n, n1, e);
		g.AddEdge(n1, n2, e1);
		g.AddEdge(n2, n3, e2);
		g.AddEdge(n3, n4, e3);
		g.AddEdge(n4, n, e4);
		
		g.RemoveEdge(e4);
		
		if(p.equals(g.GetAllEdges())) System.out.println("Execution Complete and OK");
		else System.out.println("Execution Complete and NOT OK");
		
	}
	
	public void RemoveAllEdges()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		E e = new E();
		E e1 = new E();
		E e2 = new E();
		E e3 = new E();
		E e4 = new E();
		
		e.SetWeight(3.1f);
		e1.SetWeight(3.14f);
		e2.SetWeight(3.141f);
		e3.SetWeight(3.1415f);
		e4.SetWeight(3.14159f);
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.AddEdge(n, n1, e);
		g.AddEdge(n1, n2, e1);
		g.AddEdge(n2, n3, e2);
		g.AddEdge(n3, n4, e3);
		g.AddEdge(n4, n, e4);
		
		g.RemoveAllEdges();
	}
	
	public void TestGetConnectedComponents()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		E e = new E();
		E e1 = new E();
		E e2 = new E();
		E e3 = new E();
		E e4 = new E();
		
		e.SetWeight(3.1f);
		e1.SetWeight(3.14f);
		e2.SetWeight(3.141f);
		e3.SetWeight(3.1415f);
		e4.SetWeight(3.14159f);
		
		Set<N> p = new HashSet<N>(Arrays.asList(n,n1,n2));
		Set<N> p2 = new HashSet<N>(Arrays.asList(n3,n4));
		
		ArrayList< Set<GraphTest.N> > result = new ArrayList< Set<GraphTest.N> >();
		
		result.add(p);
		result.add(p2);
		
		ArrayList< Set<GraphTest.N> > result2 = new ArrayList< Set<GraphTest.N> >();
		
		result.add(p2);
		result.add(p);
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.AddEdge(n, n1, e);
		g.AddEdge(n1, n2, e1);
		g.AddEdge(n3, n4, e3);
		
		
		
		if(result.equals(g.GetConnectedComponents())) System.out.println("Equals");
		else if(result2.equals(g.GetConnectedComponents())) System.out.println("Equals");
		else System.out.println("Not equals");
	}
	public void TestPrint()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		N n3 = new N();
		N n4 = new N();
		
		E e = new E();
		E e1 = new E();
		E e2 = new E();
		E e3 = new E();
		E e4 = new E();
		
		e.SetWeight(3.1f);
		e1.SetWeight(3.14f);
		e2.SetWeight(3.141f);
		e3.SetWeight(3.1415f);
		e4.SetWeight(3.14159f);
		
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.AddEdge(n, n1, e);
		g.AddEdge(n1, n2, e1);
		g.AddEdge(n2, n3, e2);
		g.AddEdge(n3, n4, e3);
		g.AddEdge(n4, n, e4);
		
		g.Print();
	}
}
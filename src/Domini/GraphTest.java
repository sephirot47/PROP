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
		
		Set<N> p = new HashSet<N>(Arrays.asList(n));
		
		g.AddNode(n);
		
		assertEquals(p,g.GetAllNodes());
		
	}
	public void testGetAdjacentNodesTo()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		N n2 = new N();
		
		E e = new E();
		e.SetWeight(3.14f);
		
		Set<N> p = new HashSet<N>(Arrays.asList(n,n2));
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddEdge(n, n1, e);
		g.AddEdge(n2, n1, e);
		g.GetAdjacentNodesTo(n1);
		
		assertEquals(p,g.GetAdjacentNodesTo(n1));
	}
	public void testGetNodesConnectedBy()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		E e = new E();
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddEdge(n, n1, e);
		e.SetWeight(3.14f);
		
		Pair<Node, Node> p = g.GetNodesConnectedBy(e);
		
		assertEquals(n == p.GetFirst()  || n1 == p.GetSecond(), true); //L'ordre en que surt es indeterminat
		assertEquals(n1 == p.GetFirst() || n == p.GetSecond(), true); //Per aixo s'ha de fer aixi el test
	}
	public void testAddEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		
		E e = new E();
		e.SetWeight(3.14f);
		g.AddNode(n);
		g.AddNode(n1);
		
		Set<E> p = new HashSet<E>(Arrays.asList(e));
		g.AddEdge(n, n1, e);
		
		assertEquals(p,g.GetAllEdges());
	
	}
	public void testGetAllNodes()
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
		
		assertEquals(p,g.GetAllNodes());
		
	}
	
	public void testRemoveNode()
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
		
		assertEquals(p,g.GetAllNodes());
		
	}
	public void testGetEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N();
		N n1 = new N();
		
		E e = new E();
		e.SetWeight(3.14f);
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddEdge(n, n1, e);
		
		
		assertEquals(e,g.GetEdge(n, n1));
	}
	public void testGetAllEdges()
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
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
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
		
		assertEquals(p, g.GetAllEdges());		
	}
	
	public void testRemoveEdge()
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
		
		assertEquals(p,g.GetAllEdges());
		
	}
	
	public void testRemoveAllEdges()
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
		
		E e5 = new E();
		e4.SetWeight(5f);
		g.AddEdge(n4, n, e5);
		
		Set<E> p = new HashSet<E>(Arrays.asList(e5));
		assertEquals(p,g.GetAllEdges());
	}
	
	public void testGetConnectedComponents()
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
	public void testPrint()
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

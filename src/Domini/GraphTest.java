package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class GraphTest extends TestCase 
{
	public GraphTest()
	{
		super("GraphTest");
	}
	
	class N extends Node
	{
		String id;
		public N(String idd) { id = idd; }
		public String GetId() { return id; }
	}
	
	class E extends Edge
	{
		float weight;
		public E() { weight = 1.0f; }
		public E(float w) { weight = w; }
		public float GetWeight() { return weight;}
		public void SetWeight(float weight) { this.weight = weight; }
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testAddNode() 
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node");
		
		Set<N> p = new HashSet<N>(Arrays.asList(n));
		
		g.AddNode(n);
		
		assertEquals(p,g.GetAllNodes());
		
	}
	public void testGetAdjacentNodesTo()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node");
		N n1 = new N("node2");
		N n2 = new N("node3");
		
		E e = new E();
		E e1 = new E();
		e.SetWeight(3.14f);
		e1.SetWeight(3.14f);
		//Set<N> p = new HashSet<N>(Arrays.asList(n,n2));
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddEdge(n, n1, e);
		g.AddEdge(n2, n1, e1);
		g.GetAdjacentNodesTo(n1);
		
		Set<Node> p = g.GetAdjacentNodesTo(n1);
		assertEquals((p.contains(n2)&&(p.contains(n))),true);
	}
	public void testGetNodesConnectedBy()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node3");
		N n1 = new N("node2");
		E e = new E();
		
		g.AddNode(n);
		g.AddNode(n1);
		g.AddEdge(n, n1, e);
		e.SetWeight(3.14f);
		
		Pair<Node, Node> p = g.GetNodesConnectedBy(e);
		
		assertEquals((n == p.GetFirst()  || n1 == p.GetFirst()) && (n1 == p.GetSecond() || n == p.GetSecond()), true); //L'ordre en que surt es indeterminat
	
	}
	public void testAddEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node1");
		N n1 = new N("node2");
		
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
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		N n = new N("node1");
		N n1 = new N("node2");
		
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
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		
		Set<E> p = new HashSet<E>(Arrays.asList(e1,e2,e3));
		
		g.RemoveNode(n);
		
		assertEquals(p, g.GetAllEdges());		
	}
	
	public void testRemoveEdge()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		
		g.RemoveEdge(e4);
		Set<Edge> p = g.GetAllEdges();
		assertEquals(p.contains(e4),false);
		
	}
	
	public void testRemoveAllEdges()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		
		Set<Node> p = new HashSet<Node>(Arrays.asList(n,n1,n2));
		Set<Node> p2 = new HashSet<Node>(Arrays.asList(n3,n4));
		
		ArrayList< Set<Node> > result = new ArrayList< Set<Node> >();
		
		result.add(p);
		result.add(p2);
		
		ArrayList< Set<Node> > result2 = new ArrayList< Set<Node> >();
		
		result2.add(p2);
		result2.add(p);
		g.AddNode(n);
		g.AddNode(n1);
		g.AddNode(n2);
		g.AddNode(n3);
		g.AddNode(n4);
		
		g.AddEdge(n, n1, e);
		g.AddEdge(n1, n2, e1);
		g.AddEdge(n3, n4, e3);
		
		
		assertEquals(result.equals(g.GetConnectedComponents()) || result2.equals(g.GetConnectedComponents()), true);
		
		if(result.equals(g.GetConnectedComponents())) System.out.println("Equals");
		else if(result2.equals(g.GetConnectedComponents())) System.out.println("Equals");
		else System.out.println("Not equals");
	}
	
	public void testPrint()
	{
		Graph<Node,Edge> g = new Graph<Node,Edge>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
	
	public void testEquals() 
	{
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
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
		
		Graph<N, E> g1 = new Graph<N, E>();
		g1.AddNode(n);
		g1.AddNode(n1);
		g1.AddNode(n2);
		g1.AddNode(n3);
		g1.AddNode(n4);
		
		g1.AddEdge(n, n1, e);
		g1.AddEdge(n1, n2, e1);
		g1.AddEdge(n2, n3, e2);
		g1.AddEdge(n3, n4, e3);
		g1.AddEdge(n4, n, e4);
		
		Graph<N, E> g2 = new Graph<N, E>();
		//ELS AFEGIM EN DESORDRE
		g2.AddNode(n4);
		g2.AddNode(n2);
		g2.AddNode(n);
		g2.AddNode(n3);
		g2.AddNode(n1);
 
		//ELS AFEGIM EN DESORDRE
		g2.AddEdge(n3, n4, e3);
		g2.AddEdge(n1, n2, e1);
		g2.AddEdge(n4, n, e4);
		g2.AddEdge(n, n1, e);
		g2.AddEdge(n2, n3, e2);
		
		//MORE TESTS
		Graph<N, E> g3 = new Graph<N, E>();
		g3.AddNode(n);
		g3.AddNode(n1);
		g3.AddNode(n2);
		g3.AddNode(n3);
		g3.AddNode(n4);
		
		//A LOT OF EDGES
		g3.AddEdge(n, n1, new E(1.0f));
		g3.AddEdge(n, n2, new E(1.1f));
		g3.AddEdge(n, n3, new E(1.2f));
		g3.AddEdge(n, n4, new E(1.3f));
		g3.AddEdge(n2, n3, new E(1.4f));
		g3.AddEdge(n3, n4, new E(1.5f));
		g3.AddEdge(n4, n, new E(1.6f));
		g3.AddEdge(n4, n2, e);
		
		Graph<N, E> g4 = new Graph<N, E>();
		//ELS AFEGIM EN DESORDRE
		g4.AddNode(n4);
		g4.AddNode(n2);
		g4.AddNode(n);
		g4.AddNode(n3);
		g4.AddNode(n1);
 
		//ELS AFEGIM EN DESORDRE
		g4.AddEdge(n, n1, new E(1.0f));
		g4.AddEdge(n2, n3, new E(1.4f));
		g4.AddEdge(n, n3, new E(1.2f));
		g4.AddEdge(n, n4, new E(1.3f));
		g4.AddEdge(n4, n, new E(1.6f));
		g4.AddEdge(n, n2, new E(1.1f));
		g4.AddEdge(n3, n4, new E(1.5f));

		//Hauria de donar que son diferents, ja que a g4 li falta un edge (e)
		assertFalse(g3.equals(g4));

		g4.AddEdge(n4, n2, e);
		
		assertTrue(g3.equals(g4)); //Ara si son iguals

		g4.RemoveEdge(e);
		assertFalse(g3.equals(g4));
	
		g3.RemoveEdge(e);
		assertTrue(g4.equals(g3));
		
		
		//MOOOOOOOOORE TESTS (just nodes now)
		Graph<N, E> g5 = new Graph<N, E>();
		Graph<N, E> g6 = new Graph<N, E>();
		
		g5.AddNode(n);
		g6.AddNode(n);
		assertTrue(g5.equals(g6));
		
		g6.AddNode(n2);
		assertFalse(g5.equals(g6));
		
		g5.AddNode(n3);
		assertFalse(g5.equals(g6));
		
		g6.AddNode(n3);
		assertFalse(g5.equals(g6));
		
		g5.AddNode(n2);
		assertTrue(g5.equals(g6)); //Ara si son iguals ^^
	}
}

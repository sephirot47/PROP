package Presentacio;

import java.util.Iterator;
import java.util.LinkedList;

import Domini.Graph;
import Domini.Edge;
import Domini.Node;

public class Main 
{
	private static class City extends Node {
		private String name;
		public City(String name) { this.name = name; }
		public String GetId() { return name; }
	}

	private static class Road extends Edge 
	{
		float kilometers;
		public Road(float km) { this.kilometers = km; }
		public float GetWeight() { return kilometers; }
		public void  SetWeight(float km) { kilometers = km; }
	}
	
	private static Graph<City, Road> map;
	
	public static void main(String[] args)
	{
		//Example of the use of Graph class
		map = new Graph<City, Road>();
		City a, b, c, d, e, f, g, h, i, j, k;
		a = new City("A");
		b = new City("B");
		c = new City("C");
		d = new City("D");
		e = new City("E");
		f = new City("F");
		g = new City("G");
		h = new City("H");
		i = new City("I");
		j = new City("J");
		k = new City("K");
		
		map.AddNode(a); map.AddNode(b); 
		map.AddNode(c); map.AddNode(d);
		map.AddNode(e); map.AddNode(f);
		map.AddNode(g); map.AddNode(h);
		map.AddNode(i); map.AddNode(j);
		map.AddNode(k);
		
		map.AddEdge(a, b, new Road(1));
		map.AddEdge(a, c, new Road(1));
		map.AddEdge(b, c, new Road(1));
		map.AddEdge(b, d, new Road(1));
		map.AddEdge(b, e, new Road(1));
		map.AddEdge(e, f, new Road(1));
		
    	System.out.println(" ");
    	
		map.UpdateEdgeBetweenness();
		map.Print();
	}
}

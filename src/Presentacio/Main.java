package Presentacio;

import java.util.ArrayList;
import java.util.HashSet;

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
		City a = new City("A"); map.AddNode(a);
		City b = new City("B"); map.AddNode(b);
		City c = new City("C"); map.AddNode(c);
		City d = new City("D"); map.AddNode(d);
		City e = new City("E"); map.AddNode(e);
		City f = new City("F"); map.AddNode(f);
		City g = new City("G"); map.AddNode(g);
		City h = new City("H"); map.AddNode(h);
		City i = new City("I"); map.AddNode(i);
		City j = new City("J"); map.AddNode(j);
		City k = new City("K"); map.AddNode(k);
		City l = new City("L"); map.AddNode(l);
		City m = new City("M"); map.AddNode(m);
		City n = new City("N"); map.AddNode(n);

			map.AddEdge(a, b, new Road(1));
			map.AddEdge(a, e, new Road(1));
			map.AddEdge(b, c, new Road(1));
			map.AddEdge(b, d, new Road(1));
			map.AddEdge(b, e, new Road(1));
			map.AddEdge(c, d, new Road(1));
			map.AddEdge(c, k, new Road(1));
			map.AddEdge(d, g, new Road(1));
			map.AddEdge(e, j, new Road(1));
			map.AddEdge(f, g, new Road(1));
			map.AddEdge(f, h, new Road(1));
			map.AddEdge(f, i, new Road(1));
			map.AddEdge(g, h, new Road(1));
			map.AddEdge(g, l, new Road(1));
			map.AddEdge(h, i, new Road(1));
			map.AddEdge(j, k, new Road(1));
			map.AddEdge(j, l, new Road(1));
			map.AddEdge(j, m, new Road(1));
			map.AddEdge(k, l, new Road(1));
			map.AddEdge(k, n, new Road(1));
			map.AddEdge(l, m, new Road(1));
			map.AddEdge(l, n, new Road(1));
			map.AddEdge(m, n, new Road(1));
		
    	System.out.println(" ");
    	
		ArrayList< HashSet<City> > communities = map.GetCommunitiesGirvanNewman(3);
		map.Print();
		for(HashSet<City> cc : communities)
		{
			System.out.println("----- CC -----");
			for(City city : cc) System.out.println(city.name);
			System.out.println("----------"); System.out.println(" ");
		
		}
	}
}

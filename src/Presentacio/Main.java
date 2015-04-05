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
		City e = new City("E"); map.AddNode(e);
		City f = new City("F"); map.AddNode(f);
		City g = new City("G"); map.AddNode(g);
		City h = new City("H"); map.AddNode(h);
		City i = new City("I"); map.AddNode(i);
		City j = new City("J"); map.AddNode(j);
		City k = new City("K"); map.AddNode(k);
		City l = new City("L"); map.AddNode(l);
		City m = new City("M"); map.AddNode(m);
		City x = new City("X"); map.AddNode(x);
		City y = new City("Y"); map.AddNode(y);
		map.AddEdge(a, b, new Road(1));
		map.AddEdge(a, c, new Road(1));
		map.AddEdge(b, c, new Road(1));
		map.AddEdge(e, f, new Road(1));
		map.AddEdge(e, g, new Road(1));
		map.AddEdge(g, f, new Road(1));
		map.AddEdge(h, i, new Road(1));
		map.AddEdge(h, j, new Road(1));
		map.AddEdge(j, i, new Road(1));
		map.AddEdge(k, l, new Road(1));
		map.AddEdge(k, m, new Road(1));
		map.AddEdge(l, m, new Road(1));
		map.AddEdge(x, c, new Road(1));
		map.AddEdge(x, e, new Road(1));
		map.AddEdge(y, h, new Road(1));
		map.AddEdge(y, k, new Road(1));
		map.AddEdge(x, y, new Road(1));
		
    	System.out.println(" ");
    	
    	//map.UpdateEdgeBetweennessGirvanNewman();
		//map.Print();

		ArrayList< HashSet<City> > communities = map.GetCommunitiesGirvanNewman(6);
		for(HashSet<City> cc : communities)
		{
			System.out.println("----- CC -----");
			for(City city : cc) System.out.println(city.name);
			System.out.println("----------"); System.out.println(" ");
		
		}
	}
}

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
		City barcelona, madrid, valencia, tokyo, bombai, tarragona, cancun;
		barcelona = new City("Barcelona");
		madrid = new City("Madrid");
		valencia = new City("Valencia");
		tokyo = new City("Tokyo");
		bombai = new City("Bombai");
		tarragona = new City("Tarragona");
		cancun = new City("Cancun");
		
		map.AddNode(barcelona); map.AddNode(madrid); 
		map.AddNode(valencia); map.AddNode(tokyo);
		map.AddNode(bombai); map.AddNode(tarragona);
		map.AddNode(cancun);
		
		map.AddEdge(barcelona, madrid, new Road(1));
		map.AddEdge(madrid, valencia, new Road(1));
		map.AddEdge(valencia, tokyo, new Road(1));
		map.AddEdge(bombai, cancun, new Road(1));
		
    	System.out.println(" ");
    	
		map.UpdateEdgeBetweenness();
		ArrayList< HashSet<City> > communities = map.GetCommunities(2);
		for(HashSet<City> cc : communities)
		{
			System.out.println("CC:");
			for(City city : cc)
			{
				System.out.println(city.name);
			}
			System.out.println("----------");
		}
		map.Print();
	}
}

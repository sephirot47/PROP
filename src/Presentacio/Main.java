package Presentacio;

import java.util.Iterator;

import Domini.Graph;
import Domini.Edge;

public class Main 
{
	private static class City {
		String name;
		public City(String name) { this.name = name; }
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
		City madrid, bcn, valencia, tokyo;
		madrid = new City("Madrid");
		bcn = new City("Barcelona");
		valencia = new City("Valencia");
		tokyo = new City("Tokyo");
		
		map.AddNode(madrid); map.AddNode(bcn); 
		map.AddNode(valencia); map.AddNode(tokyo);
		
		map.AddEdge(madrid, bcn, new Road(500));
		map.AddEdge(madrid, valencia, new Road(200));
		map.AddEdge(madrid, tokyo, new Road(9000));
		map.AddEdge(valencia, tokyo, new Road(1500));
		map.AddEdge(bcn, tokyo, new Road(1234));
		
		System.out.println("De madrid a bcn hay " + map.GetEdge(madrid, bcn).GetWeight() + " km");
		System.out.println("De madrid a valencia hay " + map.GetEdge(madrid, valencia).GetWeight() + " km");
		System.out.println("De tokyo a bcn hay " + map.GetEdge(tokyo, bcn).GetWeight() + " km");

		System.out.println("*******");
		System.out.println("Valencia esta conectada a:");
		Iterator<City> it = map.GetAdjacentNodesTo(valencia).iterator();
		while(it.hasNext())
		{
			System.out.println("\t" + it.next().name);
		}
	}
}

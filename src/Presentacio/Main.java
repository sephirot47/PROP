package Presentacio;

import java.util.Iterator;
import java.util.LinkedList;

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
		City madrid, bcn, valencia, tokyo, sevilla, bombai, tarragona, murcia;
		madrid = new City("Madrid");
		bcn = new City("Barcelona");
		sevilla = new City("Sevilla");
		valencia = new City("Valencia");
		tokyo = new City("Tokyo");
		bombai = new City("Bombai");
		tarragona = new City("Tarragona");
		murcia = new City("Murcia");
		
		map.AddNode(madrid); map.AddNode(bcn); 
		map.AddNode(valencia); map.AddNode(tokyo);
		map.AddNode(sevilla); map.AddNode(bombai);
		map.AddNode(tarragona); map.AddNode(murcia);

    	System.out.println("Madrid: " + madrid.hashCode());
    	System.out.println("Barcelona: " + bcn.hashCode());
    	System.out.println("Valencia: " + valencia.hashCode());
    	System.out.println("Tokyo: " + tokyo.hashCode());
    	System.out.println("Sevilla: " + sevilla.hashCode());
    	System.out.println("Bombai: " + bombai.hashCode());
    	System.out.println("Tarragona: " + tarragona.hashCode());
    	System.out.println("Murcia: " + murcia.hashCode());
    	System.out.println(" ");
    	
		map.AddEdge(madrid, bcn, new Road(1));
		map.AddEdge(bcn, valencia, new Road(1));
		map.AddEdge(valencia, bombai, new Road(1));
		map.AddEdge(bombai, sevilla, new Road(1));
		map.AddEdge(bcn, tokyo, new Road(1));
		map.AddEdge(tokyo, valencia, new Road(1));
		map.AddEdge(tarragona, murcia, new Road(1));
		map.AddEdge(tokyo, madrid, new Road(1));
		map.AddEdge(tokyo, sevilla, new Road(1));
		map.AddEdge(sevilla, madrid, new Road(1));
		
		LinkedList<City> path = map.GetShortestPath(bombai, madrid);
    	System.out.println(" ");
    	System.out.println(path.size());
		Iterator<City> it = path.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next().name);
		}
	}
}

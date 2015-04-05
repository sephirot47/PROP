package Presentacio;

import java.util.ArrayList;
import java.util.HashSet;

import Domini.Graph;
import Domini.Edge;
import Domini.Node;

public class Main 
{
	private static class Song extends Node {
		private String name;
		public Song(String name) { this.name = name; }
		public String GetId() { return name; }
	}

	private static class Relation extends Edge 
	{
		float weight;
		public Relation() { weight = 0.0f; }
		public float GetWeight() { return weight; }
		public void  SetWeight(float w) { weight = w; }
	}
	
	private static Graph<Song, Relation> songGraph;
	
	public static void main(String[] args)
	{
		//Example of the use of Graph class
		songGraph = new Graph<Song, Relation>();
		
		Song a = new Song("A"); songGraph.AddNode(a);
		Song b = new Song("B"); songGraph.AddNode(b);
		Song c = new Song("C"); songGraph.AddNode(c);
		Song e = new Song("E"); songGraph.AddNode(e);
		Song f = new Song("F"); songGraph.AddNode(f);
		Song g = new Song("G"); songGraph.AddNode(g);
		Song h = new Song("H"); songGraph.AddNode(h);
		Song i = new Song("I"); songGraph.AddNode(i);
		Song j = new Song("J"); songGraph.AddNode(j);
		Song k = new Song("K"); songGraph.AddNode(k);
		Song l = new Song("L"); songGraph.AddNode(l);
		Song m = new Song("M"); songGraph.AddNode(m);
		Song x = new Song("X"); songGraph.AddNode(x);
		Song y = new Song("Y"); songGraph.AddNode(y);
		
		songGraph.AddEdge(a, b, new Relation());
		songGraph.AddEdge(a, c, new Relation());
		songGraph.AddEdge(b, c, new Relation());
		songGraph.AddEdge(e, f, new Relation());
		songGraph.AddEdge(e, g, new Relation());
		songGraph.AddEdge(g, f, new Relation());
		songGraph.AddEdge(h, i, new Relation());
		songGraph.AddEdge(h, j, new Relation());
		songGraph.AddEdge(j, i, new Relation());
		songGraph.AddEdge(k, l, new Relation());
		songGraph.AddEdge(k, m, new Relation());
		songGraph.AddEdge(l, m, new Relation());
		songGraph.AddEdge(x, c, new Relation());
		songGraph.AddEdge(x, e, new Relation());
		songGraph.AddEdge(y, h, new Relation());
		songGraph.AddEdge(y, k, new Relation());
		songGraph.AddEdge(x, y, new Relation());
		
    	System.out.println(" ");

		ArrayList< HashSet<Song> > solution = songGraph.GetCommunitiesGirvanNewman(6); //Obtinc el conjunt de llistes de Songs
		int foo = 0;
		for(HashSet<Song> songList : solution)
		{
			System.out.println("Song List " + (++foo) +":");
			for(Song Song : songList) System.out.println("-" + Song.name);
			System.out.println(" ");
		}
	}
}

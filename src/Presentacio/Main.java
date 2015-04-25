package Presentacio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.GirvanNewman;
import Domini.Graph;
import Domini.Edge;
import Domini.Node;
import Domini.SongRelation;

public class Main 
{
	private static class Song extends Node {
		private String name;
		public Song(String name) { this.name = name; }
		public String GetId() { return name; }
	}
	
	private static Graph<Song, SongRelation> songGraph;
	
	public static void main(String[] args)
	{
		//Example of the use of Graph class
		songGraph = new Graph<Song, SongRelation>();
		
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
		
		songGraph.AddEdge(a, b, new SongRelation());
		songGraph.AddEdge(a, c, new SongRelation());
		songGraph.AddEdge(b, c, new SongRelation());
		songGraph.AddEdge(e, f, new SongRelation());
		songGraph.AddEdge(e, g, new SongRelation());
		songGraph.AddEdge(g, f, new SongRelation());
		songGraph.AddEdge(h, i, new SongRelation());
		songGraph.AddEdge(h, j, new SongRelation());
		songGraph.AddEdge(j, i, new SongRelation());
		songGraph.AddEdge(k, l, new SongRelation());
		songGraph.AddEdge(k, m, new SongRelation());
		songGraph.AddEdge(l, m, new SongRelation());
		songGraph.AddEdge(x, c, new SongRelation());
		songGraph.AddEdge(x, e, new SongRelation());
		songGraph.AddEdge(y, h, new SongRelation());
		songGraph.AddEdge(y, k, new SongRelation());
		songGraph.AddEdge(x, y, new SongRelation());
		SongRelation r = new SongRelation();
		songGraph.AddEdge(x, a, r);
		songGraph.AddEdge(x, b, r);
		
    	System.out.println(" ");

		ArrayList< Set<Song> > solution = GirvanNewman.GetSolution(songGraph, 6); //Obtinc el conjunt de llistes de Songs
		int foo = 0;
		for(Set<Song> songList : solution)
		{
			System.out.println("Song List " + (++foo) +":");
			for(Song Song : songList) System.out.println("-" + Song.name);
			System.out.println(" ");
		}
	}
}

package Presentacio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.GirvanNewman;
import Domini.Graph;
import Domini.Edge;
import Domini.Node;
import Domini.Song;
import Domini.SongRelation;
import Domini.User;
import Persistencia.FileManager;
import Persistencia.FileParser;

public class Main 
{
	private static Graph<Song, SongRelation> songGraph;
	
	public static void main(String[] args)
	{
		//Example of the use of Graph class
		songGraph = new Graph<Song, SongRelation>();
		
		Song a = new Song("A", "A"); songGraph.AddNode(a);
		Song b = new Song("B", "A"); songGraph.AddNode(b);
		Song c = new Song("C", "A"); songGraph.AddNode(c);
		Song e = new Song("E", "A"); songGraph.AddNode(e);
		Song f = new Song("F", "A"); songGraph.AddNode(f);
		Song g = new Song("G", "A"); songGraph.AddNode(g);
		Song h = new Song("H", "A"); songGraph.AddNode(h);
		Song i = new Song("I", "A"); songGraph.AddNode(i);
		Song j = new Song("J", "A"); songGraph.AddNode(j);
		Song k = new Song("K", "A"); songGraph.AddNode(k);
		Song l = new Song("L", "A"); songGraph.AddNode(l);
		Song m = new Song("M", "A"); songGraph.AddNode(m);
		Song x = new Song("X", "A"); songGraph.AddNode(x);
		Song y = new Song("Y", "A"); songGraph.AddNode(y);
		
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

    	System.out.println(" ");

		ArrayList< Set<Song> > solution = GirvanNewman.GetSolution(songGraph, 6); //Get el conjunt de llistes de Songs
		int foo = 0;
		for(Set<Song> songList : solution)
		{
			System.out.println("Song List " + (++foo) +":");
			for(Song s : songList) System.out.println("-" + s.GetId());
			System.out.println(" ");
		}
		
		try
		{
			ArrayList<Song> songs = FileParser.GetSongs("data/songs/provaSongs.txt");
			for(Song s : songs)
			{
				s.Print();
			}
		}
		catch(IOException e1)
		{
			System.err.println("No existeix l'arxiu: " + e1.getMessage());
		}

		try
		{
			ArrayList<User> users = FileParser.GetUsers("data/users/provaUsers.txt");
			for(User u : users)
			{
				u.Print();
			}
		}
		catch(IOException e2)
		{
			System.err.println("No existeix l'arxiu: "  + e2.getMessage());
		}
	}
}

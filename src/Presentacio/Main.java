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
import Domini.Solution;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongManager;
import Domini.SongRelation;
import Domini.User;
import Domini.UserManager;
import Persistencia.FileManager;
import Persistencia.FileParser;

public class Main 
{
	private static SongGraph songGraph;
	
	public static void main(String[] args)
	{
		
		//Example of the use of Graph class
		songGraph = new SongGraph();

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
		SongRelation sr = new SongRelation();
		songGraph.AddEdge(x, y, sr);
		
    	System.out.println(" ");

    	/*
    	System.out.println("********************* ");
    	songGraph.Print();
    	System.out.println("********************* ");
    	songGraph.RemoveEdge(sr);
    	System.out.println("********************* ");
    	songGraph.Print();
    	System.out.println("********************* ");
    	*/

    	ArrayList< Set<Song> > rawSolution = GirvanNewman.GetSolution(songGraph, 6); //Get el conjunt de llistes de Songs
		int foo = 0;
		for(Set<Song> songList : rawSolution)
		{
			System.out.println("Song List " + (++foo) + ":");
			for(Song s : songList) System.out.println("-" + s.GetId());
			System.out.println(" ");
		}
		
		Solution solution = new Solution(songGraph, "GirvanNewman", rawSolution, 0.5f);
		try 
		{
			FileManager.SaveSolution(solution);
		} 
		catch (IOException e3) 
		{
			e3.printStackTrace();
		}
		
		SongGraph entrada = null;
		try {
			entrada = FileParser.GetGraph("data/solutions/solution_27-04-2015 13:43:12.647/entrada.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		entrada.Print();
		
		SongGraph s = new SongGraph();
		try {
			s.LoadSongs();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		s.Print();
		
		for(Song songu : SongManager.GetSongs("data/songs/songs.txt")) songu.Print();
		for(User user : UserManager.GetUsers()) user.Print();
	}
}

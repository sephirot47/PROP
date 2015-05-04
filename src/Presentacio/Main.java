package Presentacio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.Community;
import Domini.GirvanNewman;
import Domini.Graph;
import Domini.Edge;
import Domini.Node;
import Domini.Solution;
import Domini.SongSolution;
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
		try{
		Song ssss = new Song("\"\\sdj<>*ks/a|mfks:.asd", "\"\\sdj<>*ksa|mf?ks:.asd");
		ssss.print();
		
		//Example of the use of Graph class
		songGraph = new SongGraph();

		Song a = new Song("A", "A"); songGraph.addNode(a);
		Song b = new Song("B", "A"); songGraph.addNode(b);
		Song c = new Song("C", "A"); songGraph.addNode(c);
		Song e = new Song("E", "A"); songGraph.addNode(e);
		Song f = new Song("F", "A"); songGraph.addNode(f);
		Song g = new Song("G", "A"); songGraph.addNode(g);
		Song h = new Song("H", "A"); songGraph.addNode(h);
		Song i = new Song("I", "A"); songGraph.addNode(i);
		Song j = new Song("J", "A"); songGraph.addNode(j);
		Song k = new Song("K", "A"); songGraph.addNode(k);
		Song l = new Song("L", "A"); songGraph.addNode(l);
		Song m = new Song("M", "A"); songGraph.addNode(m);
		Song x = new Song("X", "A"); songGraph.addNode(x);
		Song y = new Song("Y", "A"); songGraph.addNode(y);
		
		songGraph.addEdge(a, b, new SongRelation());
		songGraph.addEdge(a, c, new SongRelation());
		songGraph.addEdge(b, c, new SongRelation());
		songGraph.addEdge(e, f, new SongRelation());
		songGraph.addEdge(e, g, new SongRelation());
		songGraph.addEdge(g, f, new SongRelation());
		songGraph.addEdge(h, i, new SongRelation());
		songGraph.addEdge(h, j, new SongRelation());
		songGraph.addEdge(j, i, new SongRelation());
		songGraph.addEdge(k, l, new SongRelation());
		songGraph.addEdge(k, m, new SongRelation());
		songGraph.addEdge(l, m, new SongRelation());
		songGraph.addEdge(x, c, new SongRelation());
		songGraph.addEdge(x, e, new SongRelation());
		songGraph.addEdge(y, h, new SongRelation());
		songGraph.addEdge(y, k, new SongRelation());
		SongRelation sr = new SongRelation();
		songGraph.addEdge(x, y, sr);
		
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

    	Solution rawSolution = new GirvanNewman().getSolution(songGraph, 6); //Get el conjunt de llistes de Songs
		int foo = 0;
		for(Community songList : rawSolution.getCommunities())
		{
			System.out.println("Song List " + (++foo) + ":");
			for(Node n : songList.getCommunity()) System.out.println("-" + ((Song)n).getId());
			System.out.println(" ");
		}
		
		/*
		SongGraph entrada = null;
		try {
			entrada = FileParser.GetGraph("data/solutions/solution_27-04-2015 13:43:12.647/entrada.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		entrada.Print();
		*/
		
		SongGraph s = new SongGraph();
		try {
			s.loadSongs();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		s.print();
		
		for(Song songu : SongManager.getSongs("data/songs/songs.txt")) songu.print();
		for(User user : UserManager.getUsers("data/users/users.txt", "data/reproductions")) user.print();
		
		System.out.println("*********************");
		System.out.println("*********************");
		System.out.println("*********************");
		}
		catch(Exception e){ e.printStackTrace(); }
	}
}

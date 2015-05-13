package Presentacio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.Clique;
import Domini.Community;
import Domini.GirvanNewman;
import Domini.Graph;
import Domini.Edge;
import Domini.Louvain;
import Domini.Node;
import Domini.Solution;
import Domini.SongSolution;
import Domini.Song;
import Domini.GraphManager;
import Domini.SongManager;
import Domini.Edge;
import Domini.User;
import Domini.UserManager;
import Persistencia.FileManager;

public class Main 
{
	private static Graph<Song> songGraph;
	
	public static void main(String[] args)
	{
		try{
		Song ssss = new Song("\"\\sdj<>*ks/a|mfks:.asd", "\"\\sdj<>*ksa|mf?ks:.asd");
		//ssss.print();
		
		//Example of the use of Graph class
		songGraph = new Graph<Song>();

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
		
		songGraph.addEdge(a, b, new Edge());
		songGraph.addEdge(a, c, new Edge());
		songGraph.addEdge(b, c, new Edge());
		songGraph.addEdge(e, f, new Edge());
		songGraph.addEdge(e, g, new Edge());
		songGraph.addEdge(g, f, new Edge());
		songGraph.addEdge(h, i, new Edge());
		songGraph.addEdge(h, j, new Edge());
		songGraph.addEdge(j, i, new Edge());
		songGraph.addEdge(k, l, new Edge());
		songGraph.addEdge(k, m, new Edge());
		songGraph.addEdge(l, m, new Edge());
		songGraph.addEdge(x, c, new Edge());
		songGraph.addEdge(x, e, new Edge());
		songGraph.addEdge(y, h, new Edge());
		songGraph.addEdge(y, k, new Edge());
		Edge sr = new Edge();
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

    	Solution rawSolution = new Clique().getSolution(songGraph); //Get el conjunt de llistes de Songs
		int foo = 0;
		for(Community songList : rawSolution.getCommunities())
		{
			System.out.println("Song List " + (++foo) + ":");
			for(Node n : songList.getCommunity()) System.out.println("-" + ((Song)n).getId());
			System.out.println(" ");
		}
		
		/*
		Graph<Song> entrada = null;
		try {
			entrada = FileParser.GetGraph("data/solutions/solution_27-04-2015 13:43:12.647/entrada.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		entrada.Print();
		*/

		
		//for(Song songu : SongManager.getSongs("data/songs/songs.txt")) songu.print();
		//for(User user : UserManager.getUsers("data/users/users.txt", "data/reproductions")) user.print();
		
		System.out.println("*********************");
		System.out.println("*********************");
		System.out.println("*********************");
		}
		catch(Exception e){ e.printStackTrace(); }
	}
}

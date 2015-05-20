package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.Edge;
import Domini.Graph;
import Domini.Node;
import Domini.Reproduction;
import Domini.Song;
import Domini.GraphManager;
import Domini.SongManager;
import Domini.Edge;
import Domini.User;
import Domini.UserManager;
import junit.framework.TestCase;

public class GraphManagerTest extends TestCase 
{
	public GraphManagerTest()
	{
		super("GraphManagerTest");
	}
	
	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	
	public void testGetGraph() throws Exception
	{
		//TEST 1
		Graph<Song> g1 = new Graph<Song>();
		Song s1 = new Song("A", "B");
		Song s2 = new Song("C", "D");
		Song s3 = new Song("E", "F");
		g1.addNode(s1);
		g1.addNode(s2);
		g1.addNode(s3);
		
		Graph<Song> g2 = GraphManager.getGraph("data/graph1.txt");
		
		assertEquals(g1, g2);
		
		//TEST 2
		g1 = new Graph<Song>();
		s1 = new Song("A", "B");
		s2 = new Song("C", "D");
		s3 = new Song("E", "F");
		g1.addNode(s1);
		g1.addNode(s2);
		g1.addNode(s3);
		Edge sr1 = new Edge(); sr1.setWeight(0.324f);
		g1.addEdge(s1, s2, sr1);
		Edge sr2 = new Edge(); sr2.setWeight(24.54f);
		g1.addEdge(s2, s3, sr2);
		
		//
		
		g2 = GraphManager.getGraph("data/graph2.txt");
		
		assertEquals(g1, g2);
	}
	

	
	class N extends Node
	{
		public String getId() { return "potato"; }
	}
	
	public void testGenerateEdges()
	{
		try {
		Ponderations p = new Ponderations();
		
		Graph<Song> g = new Graph<Song>();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);

		p.setAuthor(9);
		p.setDuration(8);
		p.setStyle(10);
		p.setUserAge(1);
		p.setNearbyReproductions(0);
		
		g.addNode(s);
		g.addNode(s1);
		//GraphManager.AddNode(s2);
		//GraphManager.AddNode(s3);
		//GraphManager.AddNode(s4);
		
		Set<Song> songs = new HashSet<Song>();
		songs.add(s);
		songs.add(s1);
		ArrayList<Set<Song>> ss = new ArrayList<Set<Song>>();
		ss.add(0, songs);
		
		
		//GraphManager.generateEdges(g,p);
		ArrayList<Community> Cjs = g.getConnectedComponents();
		
		ArrayList<Set<Song>> CjsSet = new ArrayList<Set<Song>>();
		for(Community com : Cjs)
		{
			Set<Song> set = new HashSet<Song>();
			for(Node n : com.getCommunity())
			{
				set.add((Song)n);
			}
			CjsSet.add(set);
		}
		
		g.getAllEdges();
		
		assertEquals(CjsSet,ss);
		} catch(Exception e) {}
	}
	
	
	public void testGetNearbyReproductionsAportation()
	{
		try {
		Graph<Song> g = new Graph<Song>();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("flamenquillo del weno");
		
		Song s = new Song("victor","cuando sarpa el hamor",2015,style,180);
		style.remove(0);
		style.add("ioroYOLO");
		Song s1 = new Song("jfons","tramboliko",1867,style,45);
		
		//assertEquals(GraphManager.getNearbyReproductionsAportation(g,s, s1),0.5f);
		} catch(Exception e) {}
	}
	
	public <T> boolean setsEquals(Set<T> ss1, Set<T> ss2)
	{
		//S'ha de passar a ArrayList perque si no, al comparar sets amb set.equals(set2), tambe compara
		//els hashcodes, i nomes volem que compari amb la funcio T.equals, per aixo aquesta conversio.
		ArrayList<T> ar1 = new ArrayList<T>(ss1);
		ArrayList<T> ar2 = new ArrayList<T>(ss2);
		
		//No uso equals, ja que al venir de sets el ordre no importa 
		return ar1.containsAll(ar2) && ar2.containsAll(ar1); //NO CAL TEST, ES UN AXIOMA DE TEORIA DE CONJUNTS
	}
}

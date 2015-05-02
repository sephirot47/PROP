package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import Domini.GraphTest.E;
import Domini.GraphTest.N;
import junit.framework.TestCase;

public class SongGraphTest extends TestCase 
{
	public SongGraphTest()
	{
		super("SongGraphTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	
	
	class N extends Node
	{
		public String GetId() { return "potato"; }
	}
	
	class E extends Edge
	{
		float weight;
		public float GetWeight() { return weight;}
		public void SetWeight(float weight) { this.weight = weight; }
	}
	public void testGenerateEdges()
	{
		Ponderations p = new Ponderations();
		
		SongGraph sg = new SongGraph();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);

		p.SetAuthor(9);
		p.SetDuration(8);
		p.SetStyle(10);
		p.SetUserAge(1);
		p.SetNearbyReproductions(0);
		
		sg.AddNode(s);
		sg.AddNode(s1);
		//sg.AddNode(s2);
		//sg.AddNode(s3);
		//sg.AddNode(s4);
		
		Set<Song> songs = new HashSet<Song>();
		songs.add(s);
		songs.add(s1);
		ArrayList<Set<Song>> ss = new ArrayList<Set<Song>>();
		ss.add(0, songs);
		
		
		sg.GenerateEdges(p);
		ArrayList<Set<Song>> Cjs = sg.GetConnectedComponents();
		
		sg.GetAllEdges();
		
		
		assertEquals(Cjs,ss);
			
		
	}
	
	
	public void testGetNearbyReproductionsAportation()
	{
		
		SongGraph sg = new SongGraph();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("flamenquillo del weno");
		
		Song s = new Song("victor","cuando sarpa el hamor",2015,style,180);
		style.remove(0);
		style.add("ioroYOLO");
		Song s1 = new Song("jfons","tramboliko",1867,style,45);
		
		assertEquals(sg.GetNearbyReproductionsAportation(s, s1),0.5f);
	}
}
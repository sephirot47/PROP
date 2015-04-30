package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Domini.GraphTest.E;
import Domini.GraphTest.N;
import junit.framework.TestCase;

public class SongGraphTest extends TestCase 
{
	public SongGraphTest(String name) 
	{
		super(name);
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
		SongRelation sr = new SongRelation();
		sr.SetWeight(3.14f);
		
		p.SetAuthor(9);
		p.SetDuration(8);
		p.SetStyle(10);
		p.SetUserAge(1);
		
		sg.AddNode(s);
		sg.AddNode(s1);
		sg.AddNode(s2);
		sg.AddNode(s3);
		sg.AddNode(s4);
		sg.AddEdge(s, s1, sr);
		
		sg.GenerateEdges(p);
	}
	
	public void testGetNearbyReproductionsAportation()
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
		p.SetDuration(1);
		p.SetStyle(10);
		p.SetUserAge(1);
		
		sg.AddNode(s);
		sg.AddNode(s1);
		sg.AddNode(s2);
		sg.AddNode(s3);
		sg.AddNode(s4);
	}
}
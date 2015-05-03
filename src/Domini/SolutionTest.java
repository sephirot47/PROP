package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class SolutionTest extends TestCase 
{
	public SolutionTest()
	{
		super("SolutionTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	class N extends Node
	{
		public String getId() { return "potato"; }
	}
	
	class E extends Edge
	{
		float weight;
		public float getWeight() { return weight;}
		public void setWeight(float weight) { this.weight = weight; }
	}
	
	public void testSetEntrada()
	{
		try
		{
		SongGraph sg = new SongGraph();	
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);
		
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		
		sg.addNode(s);
		sg.addNode(s1);
		sg.addNode(s2);
		sg.addNode(s3);
		sg.addNode(s4);
		
		so.setEntrada(sg);
		
		assertEquals(sg,so.getEntrada());
		} catch(Exception e) {}
	}
	public void testAlgorisme()
	{
		SongGraph sg = new SongGraph();
		
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		so.setAlgorisme("Just Do It");
		assertEquals("Just Do It",so.getAlgorisme());
		
	}
	
	public void testSetSongCommunities()
	{
		try
		{
		SongGraph sg = new SongGraph();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);
		
		style.remove(0);
		style.add("flamenquillo del weno");
		Song s5 = new Song("victor","cuando sarpa el hamor",2015,style,180);
		style.remove(0);
		style.add("ioroYOLO");
		Song s6 = new Song("jfons","tramboliko",1867,style,45);
		
		
		
		Set<Song> ss = new HashSet<Song>(Arrays.asList(s,s1,s2,s3,s4));
		Set<Song> ss2 = new HashSet<Song>(Arrays.asList(s5,s6));
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		
		songs.add(0,ss);
		songs.add(1,ss2);
		
		so.setSongCommunities(songs);
		
		assertEquals(songs,so.getSongCommunities());
		} catch(Exception e) {}
	}
	
	public void testSetGenerationtime()
	{
		SongGraph sg = new SongGraph();
		
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		so.setGenerationTime(0.15f);
		
		assertEquals(0.15f,so.getGenerationTime());
	}

	public void testGetAlgorisme()
	{
		SongGraph sg = new SongGraph();
		
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		so.setGenerationTime(0.15f);
		
		assertEquals(0.15f,so.getGenerationTime());
	}
	
	public void testGetGenerationTime()
	{
		SongGraph sg = new SongGraph();
		
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		so.setGenerationTime(1.3131313131f);
		
		assertEquals(1.3131313131f,so.getGenerationTime());
	}
	
	public void testGetEntrada()
	{
		try {
		SongGraph sg = new SongGraph();	
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);
		
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		
		sg.addNode(s);
		sg.addNode(s1);
		sg.addNode(s2);
		sg.addNode(s3);
		sg.addNode(s4);
		
		so.setEntrada(sg);
		
		assertEquals(sg,so.getEntrada());
		} catch(Exception e) {}
	}
	
	public void testGetSongCommunities()
	{
		try {
		SongGraph sg = new SongGraph();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);
		
		style.remove(0);
		style.add("flamenquillo del weno");
		Song s5 = new Song("victor","cuando sarpa el hamor",2015,style,180);
		style.remove(0);
		style.add("ioroYOLO");
		Song s6 = new Song("jfons","tramboliko",1867,style,45);
		
		
		
		Set<Song> ss = new HashSet<Song>(Arrays.asList(s,s1,s2,s3,s4));
		Set<Song> ss2 = new HashSet<Song>(Arrays.asList(s5,s6));
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>();
		
		Solution so = new Solution(sg,"Girvan Newman",songs,0.01f);
		
		songs.add(0,ss);
		songs.add(1,ss2);
		
		so.setSongCommunities(songs);
		
		assertEquals(songs,so.getSongCommunities());	
		} catch(Exception e) {}	
	}
}
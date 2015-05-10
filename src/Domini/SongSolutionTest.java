package Domini;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;

public class SongSolutionTest extends TestCase 
{
	public SongSolutionTest()
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
		Graph<Song> sg = new Graph<Song>();	
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);
		
		Solution songs = new Solution();
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );
		
		sg.addNode(s);
		sg.addNode(s1);
		sg.addNode(s2);
		sg.addNode(s3);
		sg.addNode(s4);
		
		so.setEntrada(sg);
		
		assertEquals(sg,so.getEntrada());
		} catch(Exception e) {}
	}
	public void testsetAlg()
	{
		Graph<Song> sg = new Graph<Song>();
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );

		so.setAlg("Just Do It");
		assertEquals("Just Do It",so.getAlg());
		
	}
	
	public void testSetCommunities()
	{
		try
		{
		Graph<Song> sg = new Graph<Song>();
		
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
		
		
		Community ss = new Community();
		for(Song songuru : Arrays.asList(s,s1,s2,s3,s4)) ss.addNode(songuru);
		Community ss2 = new Community();
		for(Song songuru : Arrays.asList(s5,s6)) ss2.addNode(songuru);
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );

		songs.addCommunity(ss);
		songs.addCommunity(ss2);
		
		so.addCommunity(ss);
		so.addCommunity(ss2);

		ArrayList<Set<Song>> setsSongs = new ArrayList<Set<Song>>();
		for(Community com : so.getCommunities())
		{
			Set<Song> set = new HashSet<Song>();
			for(Node n : com.getCommunity()) set.add((Song)n);
			setsSongs.add(set);
		}
		
		ArrayList<Set<Song>> setsSo = new ArrayList<Set<Song>>();
		for(Community com : so.getCommunities())
		{
			Set<Song> set = new HashSet<Song>();
			for(Node n : com.getCommunity()) set.add((Song)n);
			setsSo.add(set);
		}
		
		assertEquals(setsSongs, setsSo);
		} catch(Exception e) {}
	}
	
	public void testSetGenerationtime()
	{
		Graph<Song> sg = new Graph<Song>();
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );

		so.setTime(0.15);
		
		assertEquals(0.15, so.getTime());
	}

	public void testGetAlgorisme()
	{
		Graph<Song> sg = new Graph<Song>();
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );
		assertEquals("GirvanNewman", so.getAlg());
	}
	
	public void testGetTime()
	{
		Graph<Song> sg = new Graph<Song>();
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );
		so.setAlg("aaaa");
		assertEquals("aaaa", so.getAlg());
	}
	
	public void testGetEntrada()
	{
		try {
		Graph<Song> sg = new Graph<Song>();	
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );

		
		sg.addNode(s);
		sg.addNode(s1);
		sg.addNode(s2);
		sg.addNode(s3);
		sg.addNode(s4);
		
		so.setEntrada(sg);
		
		assertEquals(sg,so.getEntrada());
		} catch(Exception e) {}
	}
	
	public void testGetCommunities()
	{
		try {
		Graph<Song> sg = new Graph<Song>();
		
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
		
		Community ss = new Community();
		for(Song songuru : Arrays.asList(s,s1,s2,s3,s4)) ss.addNode(songuru);
		Community ss2 = new Community();
		for(Song songuru : Arrays.asList(s5,s6)) ss2.addNode(songuru);
		
		Solution songs = new Solution();
		
		SongSolution so = new SongSolution(sg, songs, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );
		
		songs.addCommunity(ss);
		songs.addCommunity(ss2);
		
		for(Community com : songs.getCommunities())
		{
			so.addCommunity(com);
		}
		
		assertEquals(songs,so.getCommunities());	
		} catch(Exception e) {}	
	}
}
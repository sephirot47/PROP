package Persistencia;

import java.io.IOException;
import java.util.ArrayList;

import Domini.Edge;
import Domini.Graph;
import Domini.Node;
import Domini.Reproduction;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongRelation;
import Domini.User;
import junit.framework.TestCase;

public class FileParserTest extends TestCase 
{
	public FileParserTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetUsers() throws IOException
	{
	}

	public void testGetSongs() throws IOException
	{
	}

	public void testGetReproductions() throws IOException
	{
	}
	
	public void testGetGraph() throws IOException
	{
		//TEST 1
		SongGraph g1 = new SongGraph();
		Song s1 = new Song("A", "B");
		Song s2 = new Song("C", "D");
		Song s3 = new Song("E", "F");
		g1.AddNode(s1);
		g1.AddNode(s2);
		g1.AddNode(s3);
		
		SongGraph g2 = FileParser.GetGraph("tests/graph1.txt");
		
		assertEquals(g1, g2);
		
		//TEST 2
		g1 = new SongGraph();
		s1 = new Song("A", "B");
		s2 = new Song("C", "D");
		s3 = new Song("E", "F");
		g1.AddNode(s1);
		g1.AddNode(s2);
		g1.AddNode(s3);
		SongRelation sr1 = new SongRelation(); sr1.SetWeight(0.324f);
		g1.AddEdge(s1, s2, sr1);
		SongRelation sr2 = new SongRelation(); sr2.SetWeight(24.54f);
		g1.AddEdge(s2, s3, sr2);
		
		//
		
		g2 = FileParser.GetGraph("tests/graph2.txt");
		
		g1.Print();
		System.out.println("*********");
		g2.Print();
		
		assertEquals(g1, g2);
	}
	
	public void testGetReproduction()
	{
		assertEquals(FileParser.GetReproduction("a;b;20"), new Reproduction("a", "b", 20));
		assertEquals(FileParser.GetReproduction("aaaa;bbbb;592"), new Reproduction("aaaa", "bbbb", 592));
	}
	
    public void testGetUser()
    {
    	assertEquals(FileParser.GetUser("nombreUser;73"), new User("nombreUser", 73));
    	assertEquals(FileParser.GetUser("aaaaa;122"), new User("aaaaa", 122));
    }

    public void testGetSong()
    {
		ArrayList<String> styles20 = new ArrayList<String>(); 
		styles20.add("s3"); styles20.add("s1"); styles20.add("s2"); 
    	Song s1 = FileParser.GetSong("c;t;2015;s1;s2;s3;212");
		Song s1bis = new Song("c", "t", 2015, styles20, 212);
		assertEquals(s1, s1bis);
		
		s1bis.SetDuration(2);
		assertFalse(s1.equals(s1bis));
    	
		ArrayList<String> styles30 = new ArrayList<String>(); 
		styles30.add("s1"); styles30.add("s43534");
    	Song s2 = FileParser.GetSong("x;y;2015;s1;-;s43534;212");
		Song s2bis = new Song("x", "y", 2015, styles30, 212);
		assertEquals(s2, s2bis);
    }
}

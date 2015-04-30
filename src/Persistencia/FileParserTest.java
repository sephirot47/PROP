package Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.Edge;
import Domini.Graph;
import Domini.Node;
import Domini.Reproduction;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongManager;
import Domini.SongRelation;
import Domini.User;
import Domini.UserManager;
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
		Set<User> usersRead = UserManager.GetUsers("tests/users1.txt", "tests"); //read from file
		Set<User> users = new HashSet<User>(); //created by us

		assertFalse(users.equals(usersRead));
		
		User u = new User("a", 12);
		u.AddReproduction(new Reproduction("a", "b", 20));
		u.AddReproduction(new Reproduction("a", "b", 30));
		users.add(u);
		assertFalse(SetsEquals(users, usersRead));
		
		u.AddReproduction(new Reproduction("a", "b", 592));
		assertTrue(SetsEquals(users, usersRead));
		
		users.add(new User("xza", 123));
		assertFalse(SetsEquals(users, usersRead));
		
		//TEST 2
		usersRead.clear();
		usersRead  = UserManager.GetUsers("tests/users2.txt", "tests"); //read from file
		users.clear();

		User u2 = new User("BbsdA124^!", 78);
		u2.AddReproduction(new Reproduction("xcvxcv", "xcvvc", 20));
		u2.AddReproduction(new Reproduction("xcvxcv", "ioppoi", 30));
		users.add(u2);
		assertFalse(SetsEquals(users, usersRead));
		
		User u3 = new User("a", 12);
		u3.AddReproduction(new Reproduction("a", "b", 20));
		u3.AddReproduction(new Reproduction("a", "b", 30));
		u3.AddReproduction(new Reproduction("a", "b", 592));
		users.add(u3);
		assertFalse(SetsEquals(users, usersRead));

		User u4 = new User("cxcvxcv asdas", 25);
		users.add(u4);
		assertFalse(SetsEquals(users, usersRead));
		
		u4.AddReproduction(new Reproduction("jkll", "kl", 20));
		u4.AddReproduction(new Reproduction("jjjj", "jk", 30));
		assertTrue(SetsEquals(users, usersRead));

		users.add(new User("asdsad", 2));
		assertFalse(SetsEquals(users, usersRead));
		
		users.clear();
		users.add(new User("xccvxxcv", 6));
		assertFalse(SetsEquals(users, usersRead));
	}

	public void testGetSongs() throws IOException
	{
		//TEST 1
		Set<Song> songsRead = SongManager.GetSongs("tests/songs1.txt"); //read from file
		Set<Song> songs = new HashSet<Song>(); //created by us

		assertFalse(SetsEquals(songs, songsRead)); 
		
		ArrayList<String> styles1 = new ArrayList<String>(); styles1.add("estil3");
		songs.add(new Song("a", "b", 2015, styles1, 42));
		
		assertTrue(SetsEquals(songs, songsRead));
		
		songs.add(new Song("xza", "b123", 22215, styles1, 678));
		assertFalse(SetsEquals(songs, songsRead));
		
		//TEST 2
		songsRead.clear();
		songsRead = SongManager.GetSongs("tests/songs2.txt"); //read from file
		songs.clear();
		
		ArrayList<String> styles20 = new ArrayList<String>(); 
		styles20.add("s3"); styles20.add("s1"); styles20.add("s2"); 
		
		ArrayList<String> styles30 = new ArrayList<String>(); 
		styles30.add("s1"); styles30.add("s43534");
		
		songs.add(new Song("a", "b", 2015, new ArrayList<String>(), 42));
		songs.add(new Song("c", "t", 2015, styles20, 212));
		songs.add(new Song("x", "y", 2015, styles30, 212));
		
		assertTrue(SetsEquals(songs, songsRead));
		
		songs.add(new Song("asdsad", "asddas", 202115, styles20, 345));
		assertFalse(SetsEquals(songs, songsRead));
		
		songs.clear();
		songs.add(new Song("xccvxxcv", "xcv", 45, styles30, 2));
		assertFalse(SetsEquals(songs, songsRead));
	}

	public void testGetReproductions() throws IOException
	{
		ArrayList<Reproduction> reprosRead = FileParser.GetReproductions("tests/repros1.txt");
		ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
		
		repros.add(new Reproduction("jkll", "kl", 20));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		
		repros.add(new Reproduction("ababa", "lololol", 3042));
		assertTrue(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));

		repros.add(new Reproduction("xzcczx", "zxcfdg", 3042));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		
		
		//TEST2
		reprosRead = FileParser.GetReproductions("tests/repros2.txt");
		repros = new ArrayList<Reproduction>();
		
		//Molts i en desordre
		repros.add(new Reproduction("aaa", "bbb", 111));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("lll", "bbb", 222));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("ccc", "bbb", 111));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("fff", "bbb", 222));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("ggg", "bbb", 111));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("ddd", "bbb", 222));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("eee", "bbb", 111));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("hhh", "bbb", 222));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("jjj", "bbb", 222));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("kkk", "bbb", 111));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		repros.add(new Reproduction("iii", "bbb", 111));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		
		//true now ^^
		repros.add(new Reproduction("bbb", "bbb", 222));
		assertTrue(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
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

	
	public <T> boolean SetsEquals(Set<T> ss1, Set<T> ss2)
	{
		//S'ha de passar a ArrayList perque si no, al comparar sets amb set.equals(set2), tambe compara
		//els hashcodes, i nomes volem que compari amb la funcio T.equals, per aixo aquesta conversio.
		ArrayList<T> ar1 = new ArrayList<T>(ss1);
		ArrayList<T> ar2 = new ArrayList<T>(ss2);
		
		//No uso equals, ja que al venir de sets el ordre no importa 
		return ar1.containsAll(ar2) && ar2.containsAll(ar1); //NO CAL TEST, ES UN AXIOMA DE TEORIA DE CONJUNTS
	}
}

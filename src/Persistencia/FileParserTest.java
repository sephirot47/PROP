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
	public FileParserTest()
	{
		super("FileParserTest");
	}
	
	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetUsers() throws Exception
	{
		Set<User> usersRead = UserManager.getUsers("data/users1.txt", "data"); //read from file
		Set<User> users = new HashSet<User>(); //created by us

		assertFalse(users.equals(usersRead));
		
		User u = new User("a", 12);
		u.addReproduction(new Reproduction("a", "b", 20));
		u.addReproduction(new Reproduction("a", "b", 30));
		users.add(u);
		assertFalse(setsEquals(users, usersRead));
		
		u.addReproduction(new Reproduction("a", "b", 592));
		assertTrue(setsEquals(users, usersRead));
		
		users.add(new User("xza", 123));
		assertFalse(setsEquals(users, usersRead));
		
		//TEST 2
		usersRead.clear();
		usersRead  = UserManager.getUsers("data/users2.txt", "data"); //read from file
		users.clear();

		User u2 = new User("BbsdA124^!", 78);
		u2.addReproduction(new Reproduction("xcvxcv", "xcvvc", 20));
		u2.addReproduction(new Reproduction("xcvxcv", "ioppoi", 30));
		users.add(u2);
		assertFalse(setsEquals(users, usersRead));
		
		User u3 = new User("a", 12);
		u3.addReproduction(new Reproduction("a", "b", 20));
		u3.addReproduction(new Reproduction("a", "b", 30));
		u3.addReproduction(new Reproduction("a", "b", 592));
		users.add(u3);
		assertFalse(setsEquals(users, usersRead));

		User u4 = new User("cxcvxcv asdas", 25);
		users.add(u4);
		assertFalse(setsEquals(users, usersRead));
		
		u4.addReproduction(new Reproduction("jkll", "kl", 20));
		u4.addReproduction(new Reproduction("jjjj", "jk", 30));
		assertTrue(setsEquals(users, usersRead));

		users.add(new User("asdsad", 2));
		assertFalse(setsEquals(users, usersRead));
		
		users.clear();
		users.add(new User("xccvxxcv", 6));
		assertFalse(setsEquals(users, usersRead));
	}

	public void testGetSongs() throws Exception
	{
		//TEST 1
		Set<Song> songsRead = SongManager.getSongs("data/songs1.txt"); //read from file
		Set<Song> songs = new HashSet<Song>(); //created by us

		assertFalse(setsEquals(songs, songsRead)); 
		
		ArrayList<String> styles1 = new ArrayList<String>(); styles1.add("estil3");
		songs.add(new Song("a", "b", 2015, styles1, 42));
		
		assertTrue(setsEquals(songs, songsRead));
		
		songs.add(new Song("xza", "b123", 22215, styles1, 678));
		assertFalse(setsEquals(songs, songsRead));
		
		//TEST 2
		songsRead.clear();
		songsRead = SongManager.getSongs("data/songs2.txt"); //read from file
		songs.clear();
		
		ArrayList<String> styles20 = new ArrayList<String>(); 
		styles20.add("s3"); styles20.add("s1"); styles20.add("s2"); 
		
		ArrayList<String> styles30 = new ArrayList<String>(); 
		styles30.add("s1"); styles30.add("s43534");
		
		songs.add(new Song("a", "b", 2015, new ArrayList<String>(), 42));
		songs.add(new Song("c", "t", 2015, styles20, 212));
		songs.add(new Song("x", "y", 2015, styles30, 212));
		
		assertTrue(setsEquals(songs, songsRead));
		
		songs.add(new Song("asdsad", "asddas", 202115, styles20, 345));
		assertFalse(setsEquals(songs, songsRead));
		
		songs.clear();
		songs.add(new Song("xccvxxcv", "xcv", 45, styles30, 2));
		assertFalse(setsEquals(songs, songsRead));
	}

	public void testGetReproductions() throws Exception
	{
		ArrayList<Reproduction> reprosRead = FileParser.getReproductions("data/repros1.txt");
		ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
		
		repros.add(new Reproduction("jkll", "kl", 20));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		
		repros.add(new Reproduction("ababa", "lololol", 3042));
		assertTrue(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));

		repros.add(new Reproduction("xzcczx", "zxcfdg", 3042));
		assertFalse(reprosRead.containsAll(repros) && repros.containsAll(reprosRead));
		
		
		//TEST2
		reprosRead = FileParser.getReproductions("data/repros2.txt");
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
	
	public void testGetGraph() throws Exception
	{
		//TEST 1
		SongGraph g1 = new SongGraph();
		Song s1 = new Song("A", "B");
		Song s2 = new Song("C", "D");
		Song s3 = new Song("E", "F");
		g1.addNode(s1);
		g1.addNode(s2);
		g1.addNode(s3);
		
		SongGraph g2 = FileParser.getGraph("data/graph1.txt");
		
		assertEquals(g1, g2);
		
		//TEST 2
		g1 = new SongGraph();
		s1 = new Song("A", "B");
		s2 = new Song("C", "D");
		s3 = new Song("E", "F");
		g1.addNode(s1);
		g1.addNode(s2);
		g1.addNode(s3);
		SongRelation sr1 = new SongRelation(); sr1.setWeight(0.324f);
		g1.addEdge(s1, s2, sr1);
		SongRelation sr2 = new SongRelation(); sr2.setWeight(24.54f);
		g1.addEdge(s2, s3, sr2);
		
		//
		
		g2 = FileParser.getGraph("data/graph2.txt");
		
		assertEquals(g1, g2);
	}
	
	public void testGetReproduction() throws Exception
	{
		assertEquals(FileParser.getReproduction("a;b;20"), new Reproduction("a", "b", 20));
		assertEquals(FileParser.getReproduction("aaaa;bbbb;592"), new Reproduction("aaaa", "bbbb", 592));
	}
	
    public void testGetUser() throws Exception
    {
    	assertEquals(FileParser.getUser("nombreUser;73"), new User("nombreUser", 73));
    	assertEquals(FileParser.getUser("aaaaa;122"), new User("aaaaa", 122));
    }

    public void testGetSong() throws Exception
    {
		ArrayList<String> styles20 = new ArrayList<String>(); 
		styles20.add("s3"); styles20.add("s1"); styles20.add("s2"); 
    	Song s1 = FileParser.getSong("c;t;2015;s1;s2;s3;212");
		Song s1bis = new Song("c", "t", 2015, styles20, 212);
		assertEquals(s1, s1bis);
		
		s1bis.setDuration(2);
		assertFalse(s1.equals(s1bis));
    	
		ArrayList<String> styles30 = new ArrayList<String>(); 
		styles30.add("s1"); styles30.add("s43534");
    	Song s2 = FileParser.getSong("x;y;2015;s1;-;s43534;212");
		Song s2bis = new Song("x", "y", 2015, styles30, 212);
		assertEquals(s2, s2bis);
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

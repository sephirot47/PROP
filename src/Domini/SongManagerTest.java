package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class SongManagerTest extends TestCase 
{
	public SongManagerTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetSongs() 
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
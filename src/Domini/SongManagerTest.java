package Domini;

import java.util.ArrayList;
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

		assertFalse(SongSetsEquals(songs, songsRead));
		
		ArrayList<String> styles1 = new ArrayList<String>(); styles1.add("estil3");
		songs.add(new Song("a", "b", 2015, styles1, 42));
		
		assertTrue(SongSetsEquals(songs, songsRead));
		
		songs.add(new Song("xza", "b123", 22215, styles1, 678));
		assertFalse(SongSetsEquals(songs, songsRead));
		
		//TEST 2
		songsRead.clear();
		songsRead  = SongManager.GetSongs("tests/songs2.txt"); //read from file
		songs.clear();
		
		ArrayList<String> styles20 = new ArrayList<String>(); 
		styles20.add("s3"); styles20.add("s1"); styles20.add("s2"); 
		
		ArrayList<String> styles30 = new ArrayList<String>(); 
		styles30.add("s1"); styles30.add("s43534");
		
		songs.add(new Song("a", "b", 2015, new ArrayList<String>(), 42));
		songs.add(new Song("c", "t", 2015, styles30, 212));
		songs.add(new Song("c", "t", 2015, styles20, 212));
		assertTrue(SongSetsEquals(songs, songsRead)); //TEST20
		
		songs.add(new Song("asdsad", "asddas", 202115, styles20, 345));
		assertFalse(SongSetsEquals(songs, songsRead)); //TEST21
		
		songs.clear();
		songs.add(new Song("xccvxxcv", "xcv", 45, styles30, 2));
		assertFalse(SongSetsEquals(songs, songsRead)); //TEST22
	}
	
	public boolean SongSetsEquals(Set<Song> ss1, Set<Song> ss2)
	{
		for(Song s : ss1) //songs i songsRead son iguals???
		{
			boolean trobatIgual = false;
			for(Song s2 : ss2) { if(s.Equals(s2)) {trobatIgual = true;}}
			if(!trobatIgual) return false;
		}
		return ss1.size() == ss2.size();
	}
	
	public void testSongSetsEquals()
	{
		Set<Song> ss1 = new HashSet<Song>();
		Set<Song> ss2 = new HashSet<Song>();

		ArrayList<String> styles1 = new ArrayList<String>(); 
		ArrayList<String> styles2 = new ArrayList<String>(); 
		
		ss1.add(new Song("asdsad", "asddas", 202115, styles1, 345));
		assertFalse(SongSetsEquals(ss1, ss2));
		
		ss1.add(new Song("xcvxc", "xcv", 123, styles1, 122));
		assertFalse(SongSetsEquals(ss1, ss2));
		
		ss2.add(new Song("asdsad", "asddas", 202115, styles1, 345));
		assertFalse(SongSetsEquals(ss1, ss2));
		
		ss2.add(new Song("xcvxc", "xcv", 123, styles1, 122));
		assertTrue(SongSetsEquals(ss1, ss2));
		
		styles1.add("asda");
		
		ss1.add(new Song("fvgb", "cvb", 31, styles1, 12));
		assertFalse(SongSetsEquals(ss1, ss2));
		
		ss2.add(new Song("fvgb", "cvb", 31, styles1, 12));
		assertTrue(SongSetsEquals(ss1, ss2));
		
		styles2.add("asda");
		ss1.add(new Song("fvgb", "cvb", 31, styles1, 12)); //different style arrays
		assertFalse(SongSetsEquals(ss1, ss2));
		ss2.add(new Song("fvgb", "cvb", 31, styles2, 12)); //different styles arrays
		assertTrue(SongSetsEquals(ss1, ss2));
		
		styles1.add("aaaa"); styles1.add("bbbb");
		styles2.add("bbbb"); styles2.add("aaaa");
		ss2.add(new Song("fvgb", "cvb", 31, styles1, 12)); //different style order
		assertFalse(SongSetsEquals(ss1, ss2));
		ss1.add(new Song("fvgb", "cvb", 31, styles2, 12)); //different styles order
		assertTrue(SongSetsEquals(ss1, ss2));
		
	}
}
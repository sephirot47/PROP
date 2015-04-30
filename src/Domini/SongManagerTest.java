package Domini;

import java.util.ArrayList;
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
		Set<Song> songs = SongManager.GetSongs("tests/songs1.txt");
		//a;b;2015;-;-;estil3;42
		ArrayList<String> styles1 = new ArrayList<String>(); styles1.add("estil3");
		Song s1 = new Song("a", "b", 2015, styles1, 42);
	}
}
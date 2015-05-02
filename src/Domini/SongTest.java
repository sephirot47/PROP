package Domini;

import java.util.ArrayList;

import junit.framework.TestCase;

public class SongTest extends TestCase 
{
	public SongTest() 
	{
		super("SongTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	
	public void testSongConstructor0()
	{
		String author = "Manolo Escobar";
		String song = "La rumbera";
		Song s = new Song(author, song);
		assertEquals("Manolo Escobar", s.GetAuthor());
		assertEquals("La rumbera", s.GetTitle());
	}
	
	public void testSongConstructor1()
	{
		String author = "John Powell";
		String title = "Horton Suite";
		int year = 2008;
		int duration = 461;
		ArrayList<String> styles = new ArrayList<String>();
		styles.add("OST");
		styles.add("Film Music");
		styles.add("Orchestral");
		Song s = new Song (author, title, year, styles, duration);
		assertEquals("John Powell", s.GetAuthor());
		assertEquals("Horton Suite", s.GetTitle());
		assertEquals(2008, s.GetYear());
		assertEquals(461, s.GetDuration());
		assertEquals("OST", s.GetStyles().get(0));
		assertEquals("Film Music", s.GetStyles().get(1));
		assertEquals("Orchestral", s.GetStyles().get(2));
	}

	public void testSetSong(){
		Song s = new Song();
		ArrayList<String> styles = new ArrayList<String>();
		styles.add("OST");
		String author = "Jerry Goldsmith";
		String title = "I'll Make A Man Out of You";
		s.SetAuthorTitle(author, title);
		s.AddStyles(styles);
		assertEquals("OST", s.GetStyles().get(0));
		assertEquals("Jerry Goldsmith", s.GetAuthor());
		assertEquals("I'll Make A Man Out of You", s.GetTitle());
	}
	
	public void testMeanUserAge(){
		User u = new User("Austin Powers", 33);
		String author = "Boney-M";
		String song = "Rasputin";
		Song s = new Song(author, song);
		Reproduction r = new Reproduction(author, song, 3390);
		u.AddReproduction(r);
		assertEquals(33, s.GetMeanUserAge());
	}
}
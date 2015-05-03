package Domini;

import java.util.ArrayList;

import Persistencia.FileManager;

import junit.framework.TestCase;

public class SongTest extends TestCase {
	public SongTest() {
		super("SongTest");
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSongConstructor0() {
		try {
			String author = "Manolo Escobar";
			String song = "La rumbera";
			Song s = new Song(author, song);
			assertEquals("Manolo Escobar", s.getAuthor());
			assertEquals("La rumbera", s.getTitle());
		} catch (Exception e) {
		}
	}

	public void testSongConstructor1() {
		try {
			String author = "John Powell";
			String title = "Horton Suite";
			int year = 2008;
			int duration = 461;
			ArrayList<String> styles = new ArrayList<String>();
			styles.add("OST");
			styles.add("Film Music");
			styles.add("Orchestral");
			Song s = new Song(author, title, year, styles, duration);
			assertEquals("John Powell", s.getAuthor());
			assertEquals("Horton Suite", s.getTitle());
			assertEquals(2008, s.getYear());
			assertEquals(461, s.getDuration());
			assertEquals("OST", s.getStyles().get(0));
			assertEquals("Film Music", s.getStyles().get(1));
			assertEquals("Orchestral", s.getStyles().get(2));
		} catch (Exception e) {
		}
	}

	public void testSetSong() {
		try {
			Song s = new Song();
			ArrayList<String> styles = new ArrayList<String>();
			styles.add("OST");
			String author = "Jerry Goldsmith";
			String title = "I'll Make A Man Out of You";
			s.setAuthorTitle(author, title);
			s.addStyles(styles);
			assertEquals("OST", s.getStyles().get(0));
			assertEquals("Jerry Goldsmith", s.getAuthor());
			assertEquals("I'll Make A Man Out of You", s.getTitle());
		} catch (Exception e) {
		}
	}

	public void testMeanUserAge() {
		try {
			Song s = new Song("author", "title");
			assertEquals(30.0f, s.getMeanUserAge());
		} catch (Exception e) {
		}
	}
}
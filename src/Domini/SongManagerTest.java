package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

import junit.framework.TestCase;

public class SongManagerTest extends TestCase 
{
	public SongManagerTest()
	{
		super("SongManagerTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetSongs() 
	{
		try {
		//TEST 1
		Set<Song> songsRead = SongManager.getSongsFromDisk("data/songs1.txt"); //read from file
		Set<Song> songs = new HashSet<Song>(); //created by us

		assertFalse(setsEquals(songs, songsRead)); 
		
		ArrayList<String> styles1 = new ArrayList<String>(); styles1.add("estil3");
		songs.add(new Song("a", "b", 2015, styles1, 42));
		
		assertTrue(setsEquals(songs, songsRead));
		
		songs.add(new Song("xza", "b123", 22215, styles1, 678));
		assertFalse(setsEquals(songs, songsRead));
		
		//TEST 2
		songsRead.clear();
		songsRead = SongManager.getSongsFromDisk("data/songs2.txt"); //read from file
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

		} catch(Exception e) {}
	}

	 
	 public static void testSaveSong() throws Exception
	 {
		Song s1 = new Song("autor1", "titol1", 2014, new ArrayList<String>(), 100);
		Song s2 = new Song("autor2", "titol2", 222, new ArrayList<String>(), 200);
		Song s3 = new Song("autor3", "titol3", 333, new ArrayList<String>(), 300);
		ArrayList<String> songsLines = new ArrayList<String>();

		FileManager.eraseData("data/songSaveProva1.txt"); //Comencem amb larxiu buit
		
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s1);
		songsLines.add("autor1;titol1;2014;-;-;-;100");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s2);
		songsLines.add("autor2;titol2;222;-;-;-;200");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s3);
		songsLines.add("autor3;titol3;333;-;-;-;300");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		s2.setDuration(5478); //Si canviem la primary key, es considera un nou user
		s2.setAuthorTitle("aaa", "bbb");
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s2);
		assertFalse(FileManager.loadData("data/songSaveProva1.txt").equals(songsLines));
		songsLines.add("aaa;bbb;222;-;-;-;5478");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		//Afegim estils, per veure si els guarda be
		ArrayList<String> styles = new ArrayList<String>();
		styles.add("Nightcore");
		s3.addStyles(styles);
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s3);
		assertFalse(FileManager.loadData("data/songSaveProva1.txt").equals(songsLines));
		songsLines.set(2, "autor3;titol3;333;Nightcore;-;-;300");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);

		//Guardar sense modificar no afecta
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s1);
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s2);
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s3);
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s1);
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s2);
		SongManager.saveSongToDisk("data/songSaveProva1.txt", s3);
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
	 }
	 
	 public static void testSaveSongs() throws Exception
	 {
		 ArrayList<Song> writtenSongs = new ArrayList<Song>();
		 ArrayList<String> songsLines = new ArrayList<String>();
		 
		 FileManager.eraseData("data/songSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenSongs.add(new Song("autor1", "titol1", 2014, new ArrayList<String>(), 120));
		 writtenSongs.add(new Song("autor2", "titol1", 1997, new ArrayList<String>(), 180));
		 writtenSongs.add(new Song("autor3", "titol123", 1870, new ArrayList<String>(), 60));
		 
		 songsLines.add("autor1;titol1;2014;-;-;-;120");
		 songsLines.add("autor2;titol1;1997;-;-;-;180");
		 songsLines.add("autor3;titol123;1870;-;-;-;60");
		 
		 SongManager.saveSongsToDisk("data/songSaveProva2.txt", writtenSongs);
		 assertEquals(FileManager.loadData("data/songSaveProva2.txt"), songsLines);
	 }

	 public static void testRemoveSong() throws IOException
	 {
		 ArrayList<String> songsLines = new ArrayList<String>();
		 
		 songsLines.add("Camela;cuando sarpa el hamor;2015;-;-;flamenquillo del weno;180"); //0
		 songsLines.add("SoyTanSutil;tramboliko;1867;dubstep;-;ioroYOLO;45"); //1
		 songsLines.add("AC/DC;Thunderstruck;1990;Rock;-;-;292"); //2
		 songsLines.add("AC/DC;Highway to Hell;1979;Rock;-;-;284"); //3
		 songsLines.add("AC/DC;You Shook Me All Night Long;1980;Rock;-;-;212"); //4
		 songsLines.add("AC/DC;T.N.T;1980;Rock;-;-;312"); //5
		 songsLines.add("AC/DC;Hells Bells;1980;Rock;-;-;312"); //6

		 FileManager.eraseData("data/songsRemoveTest.txt");
		 FileManager.saveData("data/songsRemoveTest.txt", songsLines);

		 songsLines.remove(5);
		 SongManager.removeSongFromDisk("data/songsRemoveTest.txt", "AC/DC", "T.N.T");
		 assertEquals(FileManager.loadData("data/songsRemoveTest.txt"), songsLines);
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
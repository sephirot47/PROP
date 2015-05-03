package Persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Domini.Reproduction;
import Domini.Song;
import Domini.User;
import junit.framework.TestCase;

public class FileManagerTest extends TestCase 
{
	public FileManagerTest()
	{
		super("FileManagerTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	
	public void testLoadData() throws IOException
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("This is going to be reloaded and compared");
		String path = "testfileload.txt";
		FileManager.SaveData(path, l);
		ArrayList<String> l2 = FileManager.LoadData(path);
		assertEquals(l, l2);					
	}
	
	public void testLoadData2() throws IOException
	{
		ArrayList<String> prefabtest = FileManager.LoadData("tests/FileManagerLoadTest.txt");
		ArrayList<String> expectedResult = new ArrayList<String>();
		expectedResult.add("This is the test file for the Load function in the file manager");
		assertEquals(expectedResult, prefabtest);		
	}
	
	public void testSaveData() throws IOException 
	{
		ArrayList<String> l = new ArrayList<String>();
		String l1 = "This is the first line of a file";
		String l2 = "This is the second line of a file";
		String l3 = "This is the final line of a file";
		l.add(l1);
		l.add(l2);
		l.add(l3);
		String path = "testfile.txt";
		FileManager.SaveData(path, l);		
		assertEquals(true, FileManager.Exists(path));
	}
	
	public void testSaveData2() throws IOException 
	{
		ArrayList<String> l = new ArrayList<String>();
		String l1 = "This is the first line of a file";
		String l2 = "This is the second line of a file";
		String l3 = "This is the final line of a file";
		l.add(l1);
		l.add(l2);
		l.add(l3);
		String path = "testfile.txt";
		FileManager.SaveData(path, l);
		ArrayList<String> li2 = FileManager.LoadData(path);
		assertEquals(l, li2);
	}
	
	public void testEraseData() throws IOException 
	{
		String path = "testfile.txt";
		assertEquals(true, FileManager.Exists(path));
		FileManager.EraseData(path);
		assertEquals(false, FileManager.Exists(path));
				
	}
	
	public void testAddData() throws IOException
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("For the moment the first line");
		String path = "testfileadd.txt";
		FileManager.SaveData(path, l);
		String newline = "Now this is the new line";
		FileManager.AddData(path, newline);
		l.add(newline);
		ArrayList<String> l2 = FileManager.LoadData(path);
		assertEquals(l, l2);
	}
	
	public void testSaveUser() throws IOException
	{
		try{
		User u1 = new User("usuari1", 23);
		User u2 = new User("usuari2", 21);
		User u3 = new User("usuari3", 45);
		ArrayList<String> usersLines = new ArrayList<String>();
	
		FileManager.EraseData("tests/userSaveProva1.txt"); //Comencem amb larxiu buit
		
		FileManager.SaveUser("tests/userSaveProva1.txt", u1);
		usersLines.add("usuari1;23");
		assertEquals(FileManager.LoadData("tests/userSaveProva1.txt"), usersLines);
		
		FileManager.SaveUser("tests/userSaveProva1.txt", u2);
		usersLines.add("usuari2;21");
		assertEquals(FileManager.LoadData("tests/userSaveProva1.txt"), usersLines);
		
		FileManager.SaveUser("tests/userSaveProva1.txt", u3);
		usersLines.add("usuari3;45");
		assertEquals(FileManager.LoadData("tests/userSaveProva1.txt"), usersLines);
		
		u2.SetAge(5478);
		u2.SetName("usuari567");
		FileManager.SaveUser("tests/userSaveProva1.txt", u2);
		usersLines.add("usuari567;5478");
		assertEquals(FileManager.LoadData("tests/userSaveProva1.txt"), usersLines);
		
		u2.SetName("usuari2");  //Modificarem una linia en especific
		u2.SetAge(656);
		FileManager.SaveUser("tests/userSaveProva1.txt", u2);
		usersLines.set(1, "usuari2;656");
		assertEquals(FileManager.LoadData("tests/userSaveProva1.txt"), usersLines);
		} catch(Exception e) {}
	}
	
	 public static void testSaveUsers() throws Exception
	 {
		 ArrayList<User> writtenUsers = new ArrayList<User>();
		 ArrayList<String> usersLines = new ArrayList<String>();
		 
		 FileManager.EraseData("tests/userSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenUsers.add(new User("usuari1", 23));
		 writtenUsers.add(new User("usuari2", 21));
		 writtenUsers.add(new User("usuari3", 45));
		 
		 usersLines.add("usuari1;23");
		 usersLines.add("usuari2;21");
		 usersLines.add("usuari3;45");
		 
		 FileManager.SaveUsers("tests/userSaveProva2.txt", writtenUsers);
		 assertEquals(FileManager.LoadData("tests/userSaveProva2.txt"), usersLines);
		 
		
		 writtenUsers = new ArrayList<User>();
		 usersLines = new ArrayList<String>();
		 
		 FileManager.EraseData("tests/userSaveProva3.txt"); //Comencem amb larxiu buit
		 
		 writtenUsers.add(new User("AAAAAAAAAAAAA", 213));
		 FileManager.SaveUsers("tests/userSaveProva3.txt", writtenUsers);
		 assertFalse(FileManager.LoadData("tests/userSaveProva3.txt").equals(usersLines));
		 
		 usersLines.add("AAAAAAAAAAAAA;213");
		 assertEquals(FileManager.LoadData("tests/userSaveProva3.txt"), usersLines);
	 }
	 
	 public static void testSaveSong() throws Exception
	 {
		Song s1 = new Song("autor1", "titol1", 2014, new ArrayList<String>(), 100);
		Song s2 = new Song("autor2", "titol2", 222, new ArrayList<String>(), 200);
		Song s3 = new Song("autor3", "titol3", 333, new ArrayList<String>(), 300);
		ArrayList<String> songsLines = new ArrayList<String>();

		FileManager.EraseData("tests/songSaveProva1.txt"); //Comencem amb larxiu buit
		
		FileManager.SaveSong("tests/songSaveProva1.txt", s1);
		songsLines.add("autor1;titol1;2014;-;-;-;100");
		assertEquals(FileManager.LoadData("tests/songSaveProva1.txt"), songsLines);
		
		FileManager.SaveSong("tests/songSaveProva1.txt", s2);
		songsLines.add("autor2;titol2;222;-;-;-;200");
		assertEquals(FileManager.LoadData("tests/songSaveProva1.txt"), songsLines);
		
		FileManager.SaveSong("tests/songSaveProva1.txt", s3);
		songsLines.add("autor3;titol3;333;-;-;-;300");
		assertEquals(FileManager.LoadData("tests/songSaveProva1.txt"), songsLines);
		
		s2.SetDuration(5478); //Si canviem la primary key, es considera un nou user
		s2.SetAuthorTitle("aaa", "bbb");
		FileManager.SaveSong("tests/songSaveProva1.txt", s2);
		assertFalse(FileManager.LoadData("tests/songSaveProva1.txt").equals(songsLines));
		songsLines.add("aaa;bbb;222;-;-;-;5478");
		assertEquals(FileManager.LoadData("tests/songSaveProva1.txt"), songsLines);
		
		//Afegim estils, per veure si els guarda be
		ArrayList<String> styles = new ArrayList<String>();
		styles.add("Nightcore");
		s3.AddStyles(styles);
		FileManager.SaveSong("tests/songSaveProva1.txt", s3);
		assertFalse(FileManager.LoadData("tests/songSaveProva1.txt").equals(songsLines));
		songsLines.set(2, "autor3;titol3;333;Nightcore;-;-;300");
		assertEquals(FileManager.LoadData("tests/songSaveProva1.txt"), songsLines);

		//Guardar sense modificar no afecta
		FileManager.SaveSong("tests/songSaveProva1.txt", s1);
		FileManager.SaveSong("tests/songSaveProva1.txt", s2);
		FileManager.SaveSong("tests/songSaveProva1.txt", s3);
		FileManager.SaveSong("tests/songSaveProva1.txt", s1);
		FileManager.SaveSong("tests/songSaveProva1.txt", s2);
		FileManager.SaveSong("tests/songSaveProva1.txt", s3);
		assertEquals(FileManager.LoadData("tests/songSaveProva1.txt"), songsLines);
	 }
	 
	 public static void testSaveSongs() throws Exception
	 {
		 ArrayList<Song> writtenSongs = new ArrayList<Song>();
		 ArrayList<String> songsLines = new ArrayList<String>();
		 
		 FileManager.EraseData("tests/songSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenSongs.add(new Song("autor1", "titol1", 2014, new ArrayList<String>(), 120));
		 writtenSongs.add(new Song("autor2", "titol1", 1997, new ArrayList<String>(), 180));
		 writtenSongs.add(new Song("autor3", "titol123", 1870, new ArrayList<String>(), 60));
		 
		 songsLines.add("autor1;titol1;2014;-;-;-;120");
		 songsLines.add("autor2;titol1;1997;-;-;-;180");
		 songsLines.add("autor3;titol123;1870;-;-;-;60");
		 
		 FileManager.SaveSongs("tests/songSaveProva2.txt", writtenSongs);
		 assertEquals(FileManager.LoadData("tests/songSaveProva2.txt"), songsLines);
	 }
	 
	 public static void testSaveReproduction() throws Exception
	 {
		Reproduction r1 = new Reproduction("autor1", "titol1", 1);
		Reproduction r2 = new Reproduction("autor2", "titol2", 2);
		Reproduction r3 = new Reproduction("autor3", "titol3", 3);
		ArrayList<String> reproductionsLines = new ArrayList<String>();

		FileManager.EraseData("tests/reproductionSaveProva1.txt"); //Comencem amb larxiu buit
		
		FileManager.SaveReproduction("tests/reproductionSaveProva1.txt", r1);
		reproductionsLines.add("autor1;titol1;1");
		assertEquals(FileManager.LoadData("tests/reproductionSaveProva1.txt"), reproductionsLines);
		
		FileManager.SaveReproduction("tests/reproductionSaveProva1.txt", r2);
		reproductionsLines.add("autor2;titol2;2");
		assertEquals(FileManager.LoadData("tests/reproductionSaveProva1.txt"), reproductionsLines);
		
		FileManager.SaveReproduction("tests/reproductionSaveProva1.txt", r3);
		reproductionsLines.add("autor3;titol3;3");
		assertEquals(FileManager.LoadData("tests/reproductionSaveProva1.txt"), reproductionsLines);
		
		r2.SetSongAuthor("autor2bis"); //Si canviem la primary key, es considera un nou user
		FileManager.SaveReproduction("tests/reproductionSaveProva1.txt", r2);
		assertFalse(FileManager.LoadData("tests/reproductionSaveProva1.txt").equals(reproductionsLines));
		reproductionsLines.add("autor2bis;titol2;2");
		assertEquals(FileManager.LoadData("tests/reproductionSaveProva1.txt"), reproductionsLines);
	 }
	 
	 public static void testSaveReproductions() throws Exception
	 {
		 ArrayList<Reproduction> writtenReproductions = new ArrayList<Reproduction>();
		 ArrayList<String> reproductionsLines = new ArrayList<String>();

		 FileManager.EraseData("tests/reproductionSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenReproductions.add(new Reproduction("autor1", "titol1", 1));
		 writtenReproductions.add(new Reproduction("autor2", "titol1", 2));
		 writtenReproductions.add(new Reproduction("autor3", "titol123", 3));
		 
		 reproductionsLines.add("autor1;titol1;1");
		 reproductionsLines.add("autor2;titol1;2");
		 reproductionsLines.add("autor3;titol123;3");
		 
		 FileManager.SaveReproductions("tests/reproductionSaveProva2.txt", writtenReproductions);
		 assertEquals(FileManager.LoadData("tests/reproductionSaveProva2.txt"), reproductionsLines);
	 }
	 
	 public static void testRemoveUser() throws IOException
	 {
		 ArrayList<String> usersLines = new ArrayList<String>();
		 
		 usersLines.add("victor;20"); //0
		 usersLines.add("aaa;45"); //1
		 usersLines.add("bbb;10"); //2
		 usersLines.add("xxx;102"); //3

		 FileManager.EraseData("tests/usersRemoveTest.txt");
		 FileManager.SaveData("tests/usersRemoveTest.txt", usersLines);

		 usersLines.remove(2);
		 FileManager.RemoveUser("tests/usersRemoveTest.txt", "bbb");
		 assertEquals(FileManager.LoadData("tests/usersRemoveTest.txt"), usersLines);

		 usersLines.remove(1);
		 FileManager.RemoveUser("tests/usersRemoveTest.txt", "aaa");
		 assertEquals(FileManager.LoadData("tests/usersRemoveTest.txt"), usersLines);
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

		 FileManager.EraseData("tests/songsRemoveTest.txt");
		 FileManager.SaveData("tests/songsRemoveTest.txt", songsLines);

		 songsLines.remove(5);
		 FileManager.RemoveSong("tests/songsRemoveTest.txt", "AC/DC", "T.N.T");
		 assertEquals(FileManager.LoadData("tests/songsRemoveTest.txt"), songsLines);
	 }
	 
	 public static void testRemoveReproductions() throws IOException
	 {
		 ArrayList<String> reproductionsLines = new ArrayList<String>();
		 
		 reproductionsLines.add("victor;cuando sarpa el hamor;30");
		 reproductionsLines.add("AC/DC;Thunderstruck;292");
		 reproductionsLines.add("AC/DC;Highway to Hell;284");
		 reproductionsLines.add("AC/DC;You Shook Me All Night Long;212");
		 reproductionsLines.add("jfons;tramboliko;20");
		 reproductionsLines.add("AC/DC;Hells Bells;312");
		 
		 String User = "abraham";
		 long time = 214;
		 FileManager.RemoveReproductions("tests/", User, time);
		 assertEquals(FileManager.LoadData("tests/abrahamReproductions.txt"), reproductionsLines);
	 }
}
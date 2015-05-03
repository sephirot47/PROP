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
		String path = "data/testfileload.txt";
		FileManager.saveData(path, l);
		ArrayList<String> l2 = FileManager.loadData(path);
		assertEquals(l, l2);					
	}
	
	public void testLoadData2() throws IOException
	{
		ArrayList<String> prefabtest = FileManager.loadData("data/FileManagerLoadTest.txt");
		ArrayList<String> expectedResult = new ArrayList<String>();
		expectedResult.add("This is the test file for the Load function in the file manager");
		expectedResult.add("aaa");
		expectedResult.add("bbb");
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
		String path = "data/testfile.txt";
		FileManager.saveData(path, l);		
		assertEquals(FileManager.loadData("data/testfile.txt"), l);
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
		String path = "data/testfile.txt";
		FileManager.saveData(path, l);
		ArrayList<String> li2 = FileManager.loadData(path);
		assertEquals(l, li2);
	}
	
	public void testEraseData() throws IOException 
	{
		String path = "data/testfile.txt";
		assertEquals(true, FileManager.exists(path));
		FileManager.eraseData(path);
		assertEquals(false, FileManager.exists(path));
				
	}
	
	public void testAddData() throws IOException
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("For the moment the first line");
		String path = "data/testfileadd.txt";
		FileManager.saveData(path, l);
		String newline = "Now this is the new line";
		FileManager.addData(path, newline);
		l.add(newline);
		ArrayList<String> l2 = FileManager.loadData(path);
		assertEquals(l, l2);
	}
	
	public void testSaveUser() throws IOException
	{
		try{
		User u1 = new User("usuari1", 23);
		User u2 = new User("usuari2", 21);
		User u3 = new User("usuari3", 45);
		ArrayList<String> usersLines = new ArrayList<String>();
	
		FileManager.eraseData("data/userSaveProva1.txt"); //Comencem amb larxiu buit
		
		FileManager.saveUser("data/userSaveProva1.txt", u1);
		usersLines.add("usuari1;23");
		assertEquals(FileManager.loadData("userSaveProva1.txt"), usersLines);
		
		FileManager.saveUser("data/userSaveProva1.txt", u2);
		usersLines.add("usuari2;21");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		
		FileManager.saveUser("data/userSaveProva1.txt", u3);
		usersLines.add("usuari3;45");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		
		u2.setAge(5478);
		u2.setName("usuari567");
		FileManager.saveUser("data/userSaveProva1.txt", u2);
		usersLines.add("usuari567;5478");
		assertEquals(FileManager.loadData("userSaveProva1.txt"), usersLines);
		
		u2.setName("usuari2");  //Modificarem una linia en especific
		u2.setAge(656);
		FileManager.saveUser("data/userSaveProva1.txt", u2);
		usersLines.set(1, "usuari2;656");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		} catch(Exception e) {}
	}
	
	 public static void testSaveUsers() throws Exception
	 {
		 ArrayList<User> writtenUsers = new ArrayList<User>();
		 ArrayList<String> usersLines = new ArrayList<String>();
		 
		 FileManager.eraseData("data/userSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenUsers.add(new User("usuari1", 23));
		 writtenUsers.add(new User("usuari2", 21));
		 writtenUsers.add(new User("usuari3", 45));
		 
		 usersLines.add("usuari1;23");
		 usersLines.add("usuari2;21");
		 usersLines.add("usuari3;45");
		 
		 FileManager.saveUsers("data/userSaveProva2.txt", writtenUsers);
		 assertEquals(FileManager.loadData("data/userSaveProva2.txt"), usersLines);
		 
		
		 writtenUsers = new ArrayList<User>();
		 usersLines = new ArrayList<String>();
		 
		 FileManager.eraseData("data/userSaveProva3.txt"); //Comencem amb larxiu buit
		 
		 writtenUsers.add(new User("AAAAAAAAAAAAA", 213));
		 FileManager.saveUsers("data/userSaveProva3.txt", writtenUsers);
		 assertFalse(FileManager.loadData("data/userSaveProva3.txt").equals(usersLines));
		 
		 usersLines.add("AAAAAAAAAAAAA;213");
		 assertEquals(FileManager.loadData("data/userSaveProva3.txt"), usersLines);
	 }
	 
	 public static void testSaveSong() throws Exception
	 {
		Song s1 = new Song("autor1", "titol1", 2014, new ArrayList<String>(), 100);
		Song s2 = new Song("autor2", "titol2", 222, new ArrayList<String>(), 200);
		Song s3 = new Song("autor3", "titol3", 333, new ArrayList<String>(), 300);
		ArrayList<String> songsLines = new ArrayList<String>();

		FileManager.eraseData("data/songSaveProva1.txt"); //Comencem amb larxiu buit
		
		FileManager.saveSong("data/songSaveProva1.txt", s1);
		songsLines.add("autor1;titol1;2014;-;-;-;100");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		FileManager.saveSong("data/songSaveProva1.txt", s2);
		songsLines.add("autor2;titol2;222;-;-;-;200");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		FileManager.saveSong("data/songSaveProva1.txt", s3);
		songsLines.add("autor3;titol3;333;-;-;-;300");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		s2.setDuration(5478); //Si canviem la primary key, es considera un nou user
		s2.setAuthorTitle("aaa", "bbb");
		FileManager.saveSong("data/songSaveProva1.txt", s2);
		assertFalse(FileManager.loadData("data/songSaveProva1.txt").equals(songsLines));
		songsLines.add("aaa;bbb;222;-;-;-;5478");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);
		
		//Afegim estils, per veure si els guarda be
		ArrayList<String> styles = new ArrayList<String>();
		styles.add("Nightcore");
		s3.addStyles(styles);
		FileManager.saveSong("data/songSaveProva1.txt", s3);
		assertFalse(FileManager.loadData("data/songSaveProva1.txt").equals(songsLines));
		songsLines.set(2, "autor3;titol3;333;Nightcore;-;-;300");
		assertEquals(FileManager.loadData("data/songSaveProva1.txt"), songsLines);

		//Guardar sense modificar no afecta
		FileManager.saveSong("data/songSaveProva1.txt", s1);
		FileManager.saveSong("data/songSaveProva1.txt", s2);
		FileManager.saveSong("data/songSaveProva1.txt", s3);
		FileManager.saveSong("data/songSaveProva1.txt", s1);
		FileManager.saveSong("data/songSaveProva1.txt", s2);
		FileManager.saveSong("data/songSaveProva1.txt", s3);
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
		 
		 FileManager.saveSongs("data/songSaveProva2.txt", writtenSongs);
		 assertEquals(FileManager.loadData("data/songSaveProva2.txt"), songsLines);
	 }
	 
	 public static void testSaveReproduction() throws Exception
	 {
		Reproduction r1 = new Reproduction("autor1", "titol1", 1);
		Reproduction r2 = new Reproduction("autor2", "titol2", 2);
		Reproduction r3 = new Reproduction("autor3", "titol3", 3);
		ArrayList<String> reproductionsLines = new ArrayList<String>();

		FileManager.eraseData("data/reproductionSaveProva1.txt"); //Comencem amb larxiu buit
		
		FileManager.saveReproduction("data/reproductionSaveProva1.txt", r1);
		reproductionsLines.add("autor1;titol1;1");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
		
		FileManager.saveReproduction("data/reproductionSaveProva1.txt", r2);
		reproductionsLines.add("autor2;titol2;2");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
		
		FileManager.saveReproduction("data/reproductionSaveProva1.txt", r3);
		reproductionsLines.add("autor3;titol3;3");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
		
		r2.setSongAuthor("autor2bis"); //Si canviem la primary key, es considera un nou user
		FileManager.saveReproduction("data/reproductionSaveProva1.txt", r2);
		assertFalse(FileManager.loadData("data/reproductionSaveProva1.txt").equals(reproductionsLines));
		reproductionsLines.add("autor2bis;titol2;2");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
	 }
	 
	 public static void testSaveReproductions() throws Exception
	 {
		 ArrayList<Reproduction> writtenReproductions = new ArrayList<Reproduction>();
		 ArrayList<String> reproductionsLines = new ArrayList<String>();

		 FileManager.eraseData("data/reproductionSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenReproductions.add(new Reproduction("autor1", "titol1", 1));
		 writtenReproductions.add(new Reproduction("autor2", "titol1", 2));
		 writtenReproductions.add(new Reproduction("autor3", "titol123", 3));
		 
		 reproductionsLines.add("autor1;titol1;1");
		 reproductionsLines.add("autor2;titol1;2");
		 reproductionsLines.add("autor3;titol123;3");
		 
		 FileManager.saveReproductions("data/reproductionSaveProva2.txt", writtenReproductions);
		 assertEquals(FileManager.loadData("data/reproductionSaveProva2.txt"), reproductionsLines);
	 }
	 
	 public static void testRemoveUser() throws IOException
	 {
		 ArrayList<String> usersLines = new ArrayList<String>();
		 
		 usersLines.add("victor;20"); //0
		 usersLines.add("aaa;45"); //1
		 usersLines.add("bbb;10"); //2
		 usersLines.add("xxx;102"); //3

		 FileManager.eraseData("data/usersRemoveTest.txt");
		 FileManager.saveData("data/usersRemoveTest.txt", usersLines);

		 usersLines.remove(2);
		 FileManager.removeUser("data/usersRemoveTest.txt", "bbb");
		 assertEquals(FileManager.loadData("data/usersRemoveTest.txt"), usersLines);

		 usersLines.remove(1);
		 FileManager.removeUser("data/sersRemoveTest.txt", "aaa");
		 assertEquals(FileManager.loadData("data/usersRemoveTest.txt"), usersLines);
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
		 FileManager.removeSong("data/songsRemoveTest.txt", "AC/DC", "T.N.T");
		 assertEquals(FileManager.loadData("data/songsRemoveTest.txt"), songsLines);
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
		 
		 FileManager.saveData("data/abrahamReproductions.txt", reproductionsLines);
		 FileManager.removeReproductions("data", "abraham");
		 assertEquals(FileManager.loadData("data/abrahamReproductions.txt"), reproductionsLines);
	 }
}
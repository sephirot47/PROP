package Persistencia;

import java.io.IOException;
import java.util.ArrayList;

import Domini.User;
import junit.framework.TestCase;

public class FileManagerTest extends TestCase 
{
	public FileManagerTest(String name) 
	{
		super(name);
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
		User u1 = new User("usuari1", 23);
		User u2 = new User("usuari2", 21);
		User u3 = new User("usuari3", 45);
		ArrayList<String> usersLines = new ArrayList<String>();
		try 
		{
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
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
}
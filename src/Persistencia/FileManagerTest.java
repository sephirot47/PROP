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
		String path = "testfile.txt";
		FileManager.saveData(path, l);		
		assertEquals(FileManager.loadData("testfile.txt"), l);
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
		FileManager.saveData(path, l);
		ArrayList<String> li2 = FileManager.loadData(path);
		assertEquals(l, li2);
	}
	
	public void testEraseData() throws IOException 
	{
		String path = "testfile.txt";
		assertEquals(true, FileManager.exists(path));
		FileManager.eraseData(path);
		assertEquals(false, FileManager.exists(path));
				
	}
	
	public void testAddData() throws IOException
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("For the moment the first line");
		String path = "testfileadd.txt";
		FileManager.saveData(path, l);
		String newline = "Now this is the new line";
		FileManager.addData(path, newline);
		l.add(newline);
		ArrayList<String> l2 = FileManager.loadData(path);
		assertEquals(l, l2);
	}
}
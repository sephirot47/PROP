package Persistencia;

import java.io.IOException;
import java.util.ArrayList;

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
	
	public void testLoadData() throws IOException
	{
		ArrayList<String> l = new ArrayList<String>();
		l.add("This is going to be reloaded and compared");
		String path = "testfileload.txt";
		FileManager.SaveData(path, l);
		ArrayList<String> l2 = FileManager.LoadData(path);
		assertEquals(l, l2);					
	}
}
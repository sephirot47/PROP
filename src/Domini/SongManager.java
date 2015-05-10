package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

public class SongManager 
{
	private static Set<Song> songs = new HashSet<Song>();
	
	public static Set<Song> getSongs(String filepath) throws Exception
	{
		songs.clear();
		
		if(songs.size() == 0)
		{
			ArrayList<Song> songsArray = new ArrayList<Song>();
			
			songsArray = SongManager.getSongsArray(filepath);
			
			for(Song s : songsArray)
			{
				songs.add(s);
			}
		}
		return songs;
	}
	
	public static void saveSong(String filepath, Song s) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	
    	String songLine = s.getAuthor() + ";" + s.getTitle() + ";" + s.getYear() + ";";
    	for(String style : s.getStyles()) songLine += style + ";";
    	for(int i = s.getStyles().size(); i < 3; ++i) songLine += "-;"; //Resta d'estils buits
    	songLine += s.getDuration();
    	
    	if(FileManager.exists(filepath))
    	{
    		fileLines = FileManager.loadData(filepath);
    		
	    	boolean existedInFile = false;
	    	for(String line : fileLines)
	    	{
	    		if(line.startsWith(s.getAuthor() + ";" + s.getTitle()))
	    		{
	    			fileLines.set(fileLines.indexOf(line), songLine);
	    			existedInFile = true;
	    			break;
	    		}
	    	}
	    	
	    	if(existedInFile) //replace the line
	    	{
	    		FileManager.eraseData(filepath);
	    		FileManager.saveData(filepath, fileLines);
	    	}
	    	else FileManager.addData(filepath, songLine); //Append to the eof
    	}
    	else
    	{
    		fileLines.add(songLine);
    		FileManager.saveData(filepath, fileLines);
    	}
    }
    
    public static void saveSongs(String filepath, ArrayList<Song> songs) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	for(Song s : songs)
    	{
        	String songLine = s.getAuthor() + ";" + s.getTitle() + ";" + s.getYear() + ";";
        	for(String style : s.getStyles()) songLine += style + ";";
        	for(int i = s.getStyles().size(); i < 3; ++i) songLine += "-;"; //Resta d'estils buits
        	songLine += s.getDuration();
        	
    		fileLines.add(songLine);
    	}
		FileManager.saveData(filepath, fileLines);
    }

    public static void removeSong(String filepath, String Author, String Title) throws IOException
    {
    	String search = Author+";"+Title;
	
    	ArrayList<String> lines = FileManager.loadData(filepath);
    	for(int i = 0; i < lines.size(); ++i)
    	{
    		String line = lines.get(i);
    		if(line.startsWith(search)) lines.remove(i);
    	}
    	
    	FileManager.saveData(filepath, lines);
    }


    private static Song getSong(String line) throws Exception
    {
    	String fields[] = line.split(";");
    	if(fields.length < 7) return null; 
    	
    	String author = fields[0];
    	String title = fields[1];
    	int year = Integer.parseInt(fields[2]);
    	
    	ArrayList<String> styles = new ArrayList<String>();
    	for(int i = 3; i <= 5; ++i) if(!fields[i].equals("-")) styles.add(fields[i]);
    	
    	int duration = Integer.parseInt(fields[6]);
    	
    	return new Song(author, title, year, styles, duration);
    }

    private static ArrayList<Song> getSongsArray(String filepath) throws Exception
	{
		ArrayList<Song> songs = new ArrayList<Song>();
		
		ArrayList<String> fileLines = FileManager.loadData(filepath);
		for(String line : fileLines)
		{
			songs.add(SongManager.getSong(line));
		}
		
		return songs;
	}
}

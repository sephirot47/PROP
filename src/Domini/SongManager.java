package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileParser;

public class SongManager 
{
	private static Set<Song> songs = new HashSet<Song>();
	
	public static Set<Song> GetSongs(String filepath)
	{
		songs.clear();
		
		if(songs.size() == 0)
		{
			ArrayList<Song> songsArray = new ArrayList<Song>();
			try 
			{
				songsArray = FileParser.GetSongs(filepath);
			} 
			catch (IOException e) { e.printStackTrace(); }
			
			for(Song s : songsArray)
			{
				songs.add(s);
			}
		}
		return songs;
	}
}

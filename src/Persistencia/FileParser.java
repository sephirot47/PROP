package Persistencia;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Domini.Edge;
import Domini.Reproduction;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongRelation;
import Domini.User;

public class FileParser
{
	public static ArrayList<User> GetUsers(String filepath) throws IOException
	{
		ArrayList<User> users = new ArrayList<User>();
		
		ArrayList<String> fileLines = FileManager.LoadData(filepath);
		for(String line : fileLines)
		{
			User u = FileParser.GetUser(line);
			
			//Afegim al user llegit les reproduccions corresponents
			String reproductionsFilepath = "data/reproductions/" + u.GetName() + "Reproductions.txt";
			ArrayList<Reproduction> userReproductions = FileParser.GetReproductions(reproductionsFilepath);
			u.AddReproductions(userReproductions);
			
			users.add(u);
		}
		
		return users;
	}

	public static ArrayList<Song> GetSongs(String filepath) throws IOException
	{
		ArrayList<Song> songs = new ArrayList<Song>();
		
		ArrayList<String> fileLines = FileManager.LoadData(filepath);
		for(String line : fileLines)
		{
			songs.add(FileParser.GetSong(line));
		}
		
		return songs;
	}

	public static ArrayList<Reproduction> GetReproductions(String filepath) throws IOException
	{
		ArrayList<Reproduction> reproductions = new ArrayList<Reproduction>();
		
		ArrayList<String> fileLines = FileManager.LoadData(filepath);
		for(String line : fileLines)
		{
			reproductions.add(FileParser.GetReproduction(line));
		}
		
		return reproductions;
	}
	
	public static SongGraph GetGraph(String filepath) throws IOException
	{
		SongGraph g = new SongGraph();
		ArrayList<String> lines = FileManager.LoadData(filepath);
		ArrayList<Song> songs = new ArrayList<Song>(); //2 be able to get the index of each node
		for(String line : lines)
		{
			if(line.charAt(0) == '(') //nodes (author,title)
			{
				String author = line.substring(1, line.indexOf(','));
				String title = line.substring(line.indexOf(',') + 1, line.indexOf(')'));
				Song s = new Song(author, title);
				g.AddNode(s);
				songs.add(s);
			}
			else //edges
			{
				String fields[] = line.split(";");
				int index0 = Integer.parseInt(fields[0]), index1 = Integer.parseInt(fields[1]);
				float weight = Float.parseFloat(fields[2]);
				SongRelation e = new SongRelation();
				g.AddEdge(songs.get(index0), songs.get(index1), e);	
			}
		}
		return g;
	}
	
	private static Reproduction GetReproduction(String line)
	{
    	String fields[] = line.split(";");
    	String author = fields[0];
    	String title = fields[1];
    	int time = Integer.parseInt(fields[2]);
    	
    	return new Reproduction(author, title, time);
	}
	
    private static User GetUser(String line)
    {
    	String fields[] = line.split(";");
    	
    	String name = fields[0];
    	int age = Integer.parseInt(fields[1]);
    	
    	return new User(name, age);
    }

    private static Song GetSong(String line)
    {
    	String fields[] = line.split(";");
    	
    	String author = fields[0];
    	String title = fields[1];
    	int year = Integer.parseInt(fields[2]);
    	
    	ArrayList<String> styles = new ArrayList<String>();
    	for(int i = 3; i <= 5; ++i) if(!fields[i].equals("-")) styles.add(fields[i]);
    	
    	int duration = Integer.parseInt(fields[6]);
    	
    	return new Song(author, title, year, styles, duration);
    }
}


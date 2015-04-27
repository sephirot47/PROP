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

import Domini.Reproduction;
import Domini.Song;
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


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
	public static ArrayList<User> getUsers(String userFilepath, String reprosDir) throws Exception
	{
		ArrayList<User> users = new ArrayList<User>();
		
		ArrayList<String> fileLines = FileManager.loadData(userFilepath);
		for(String line : fileLines)
		{
			User u = FileParser.getUser(line);
			
			try //Si no existeix el seu arxiu de repros pues no te repros
			{ 
				//Afegim al user llegit les reproduccions corresponents
				String reproductionsFilepath = reprosDir + "/" + u.getName() + "Reproductions.txt";
				ArrayList<Reproduction> userReproductions = FileParser.getReproductions(reproductionsFilepath);
				u.addReproductions(userReproductions);
			}
			catch(Exception e){}
			
			users.add(u);
		}
		
		return users;
	}

	public static ArrayList<Song> getSongs(String filepath) throws Exception
	{
		ArrayList<Song> songs = new ArrayList<Song>();
		
		ArrayList<String> fileLines = FileManager.loadData(filepath);
		for(String line : fileLines)
		{
			songs.add(FileParser.getSong(line));
		}
		
		return songs;
	}

	public static ArrayList<Reproduction> getReproductions(String filepath) throws IOException
	{
		ArrayList<Reproduction> reproductions = new ArrayList<Reproduction>();
		
		ArrayList<String> fileLines = FileManager.loadData(filepath);
		for(String line : fileLines)
		{
			try
			{
			 reproductions.add(FileParser.getReproduction(line));
			}
			catch(Exception e){}
		}
		
		return reproductions;
	}
	
	public static SongGraph getGraph(String filepath) throws Exception
	{
		SongGraph g = new SongGraph();
		ArrayList<String> lines = FileManager.loadData(filepath);
		ArrayList<Song> songs = new ArrayList<Song>(); //2 be able to get the index of each node
		for(String line : lines)
		{
			if(line.charAt(0) == '(') //nodes (author,title)
			{
				String author = line.substring(1, line.indexOf(','));
				String title = line.substring(line.indexOf(',') + 1, line.indexOf(')'));
				Song s = new Song(author, title);
				g.addNode(s);
				songs.add(s);
			}
			else //edges
			{
				String fields[] = line.split(";");
				int index0 = Integer.parseInt(fields[0]), index1 = Integer.parseInt(fields[1]);
				float weight = Float.parseFloat(fields[2]);
				SongRelation e = new SongRelation();
				e.setWeight(weight);
				g.addEdge(songs.get(index0), songs.get(index1), e);	
			}
		}
		return g;
	}
	
	public static Reproduction getReproduction(String line) throws Exception
	{
    	String fields[] = line.split(";");
    	if(fields.length < 3) return null; 
    	String author = fields[0];
    	String title = fields[1];
    	int time = Integer.parseInt(fields[2]);
    	
    	return new Reproduction(author, title, time);
	}
	
    public static User getUser(String line) throws Exception
    {
    	String fields[] = line.split(";");
    	if(fields.length < 2) return null; 
    	
    	String name = fields[0];
    	int age = Integer.parseInt(fields[1]);
    	
    	return new User(name, age);
    }

    public static Song getSong(String line) throws Exception
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
}


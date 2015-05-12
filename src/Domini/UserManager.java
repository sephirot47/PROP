package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

public class UserManager 
{
	private static Set<User> users = new HashSet<User>();
	
	public static void loadUsers() throws Exception
	{
		users.clear();
		
		if(users.size() == 0)
		{
			ArrayList<User> usersArray = new ArrayList<User>();
			usersArray = UserManager.getUsersArray("data/users/users.txt", "");
			
			for(User u : usersArray)
			{
				users.add(u);
			}
		}
	}

	public static void addUsersFrom(String filepath) throws Exception
	{
		ArrayList<User> usersArray = new ArrayList<User>();
		usersArray = UserManager.getUsersArray(filepath, "");
		
		for(User u : usersArray)
		{
			users.add(u);
		}
	}
	
	public static void createUser(String username, int edat) throws Exception 
	{
		for(User u: users)
		{
			if(u.getName().equals(username))
			{
				Exception e = new Exception("El nom d'usuari ja existeix");
			}
		}
		
		User u = new User(username, edat);
		users.add(u);
		
		saveReproductions("data/reproductions/" + username + "Reproductions.txt", new ArrayList<Reproduction>());
		
		saveCurrentUsers();
	}
	
	public static Set<User> getUsers(String filepath, String reprosDir) throws Exception
	{
		users.clear();
		
		if(users.size() == 0)
		{
			ArrayList<User> usersArray = new ArrayList<User>();
			usersArray = UserManager.getUsersArray(filepath, reprosDir);
			
			for(User u : usersArray)
			{
				users.add(u);
			}
		}
		return users;
	}
	
	public static ArrayList<String> getUsersNames()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(User u : users)
		{
			list.add(u.getName());
		}
		return list;
	}
	
	public static int getUserAge(String username)
	{
		for(User u : users)
		{
			if(u.getName().equals(username))
				return u.getAge();
		}
		return 0;
	}
	
	public static ArrayList<Pair<String, Long>> getUserReproductions(String username) throws Exception
	{
		ArrayList<Pair<String, Long>> result = new ArrayList<Pair<String, Long>>();
		for(User u : users)
		{
			if(u.getName().equals(username))
			{
				ArrayList<Reproduction> repros = getReproductions("data/reproductions/" + username + "Reproductions.txt");
				for(Reproduction r : repros)
					result.add(new Pair<String, Long>(r.getSongAuthor() + ", " + r.getSongTitle(), r.getTime()));
				return result;
			}
		}
		return null;
	}
	
	 public static void saveUser(String filepath, User u) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	String songLine = u.getName() + ";" + u.getAge();
	    	
	    	if(FileManager.exists(filepath))
	    	{
	    		fileLines = FileManager.loadData(filepath);
	    		
		    	boolean existedInFile = false;
		    	for(String line : fileLines)
		    	{
		    		if(line.startsWith(u.getName()))
		    		{
		    			fileLines.set(fileLines.indexOf(line), songLine);
		    			existedInFile = true;
		    			break;
		    		}
		    	}
		    	
		    	if(existedInFile)
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
	    

	    public static void saveCurrentUsers() throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	for(User u : users)
	    	{
	    		String songLine = u.getName() + ";" + u.getAge();
	    		fileLines.add(songLine);
	    	}
	    	FileManager.saveData("data/users/users.txt", fileLines);
	    }
	 
	    public static void saveUsers(String filepath, ArrayList<User> users) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	for(User u : users)
	    	{
	    		String songLine = u.getName() + ";" + u.getAge();
	    		fileLines.add(songLine);
	    	}
	    	FileManager.saveData(filepath, fileLines);
	    }

	    
	    public static void removeUser(String filepath, String username) throws IOException
	    {
	    	String search = username;
		
	    	ArrayList<String> lines = FileManager.loadData(filepath);
	    	for(int i = 0; i < lines.size(); ++i)
	    	{
	    		String line = lines.get(i);
	    		if(line.startsWith(search)) lines.remove(i);
	    	}
	    	
	    	FileManager.saveData(filepath, lines);
	    }

	    public static void saveReproduction(String filepath, Reproduction r) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	
	    	String reproductionLine = r.getSongAuthor() + ";" + r.getSongTitle() + ";" + r.getTime();
	    	
	    	if(FileManager.exists(filepath))
	    	{
	    		FileManager.addData(filepath, reproductionLine); //Append to the eof
	    	}
	    	else
	    	{
	    		fileLines.add(reproductionLine);
	    		FileManager.saveData(filepath, fileLines);
	    	}
	    }
	    
	    public static void saveReproductions(String filepath, ArrayList<Reproduction> reproductions) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	for(Reproduction r : reproductions)
	    	{
	    		String reproductionLine = r.getSongAuthor() + ";" + r.getSongTitle() + ";" + r.getTime();
	    		fileLines.add(reproductionLine);
	    	}
			FileManager.saveData(filepath, fileLines);
	    }

		
	    private static User getUser(String line) throws Exception
	    {
	    	String fields[] = line.split(";");
	    	if(fields.length < 2) return null; 
	    	
	    	String name = fields[0];
	    	int age = Integer.parseInt(fields[1]);
	    	
	    	return new User(name, age);
	    }

		
	    private static Reproduction getReproduction(String line) throws Exception
		{
	    	String fields[] = line.split(";");
	    	if(fields.length < 3) return null; 
	    	String author = fields[0];
	    	String title = fields[1];
	    	int time = Integer.parseInt(fields[2]);
	    	
	    	return new Reproduction(author, title, time);
		}

		private static ArrayList<User> getUsersArray(String userFilepath, String reprosDir) throws Exception
		{
			ArrayList<User> users = new ArrayList<User>();
			
			ArrayList<String> fileLines = FileManager.loadData(userFilepath);
			for(String line : fileLines)
			{
				User u = UserManager.getUser(line);
				
				if(!reprosDir.equals(""))
				{
					try //Si no existeix el seu arxiu de repros pues no te repros
					{ 
						//Afegim al user llegit les reproduccions corresponents
						String reproductionsFilepath = reprosDir + "/" + u.getName() + "Reproductions.txt";
						ArrayList<Reproduction> userReproductions = UserManager.getReproductions(reproductionsFilepath);
						u.addReproductions(userReproductions);
					}
					catch(Exception e){}
				}
				
				users.add(u);
			}
			
			return users;
		}

		public static ArrayList<Reproduction> getReproductions(String filepath) throws IOException
		{
			ArrayList<Reproduction> reproductions = new ArrayList<Reproduction>();
			
			ArrayList<String> fileLines = FileManager.loadData(filepath);
			for(String line : fileLines)
			{
				try
				{
				 reproductions.add(UserManager.getReproduction(line));
				}
				catch(Exception e){}
			}
			
			return reproductions;
		}
}
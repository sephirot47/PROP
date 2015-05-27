package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

public class UserManager 
{
	private static Set<User> users = new HashSet<User>();
	
	public static void loadUsersFromDisk() throws Exception
	{
		users.clear();
		
		ArrayList<User> usersArray = new ArrayList<User>();
		usersArray = UserManager.getUsersArrayFromDisk("data/users/users.txt", "");
		
		for(User u : usersArray)
		{
			ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
			ArrayList<String> reprosLines = FileManager.loadData("data/reproductions/" + u.getName() + "Reproductions.txt");
			
			for(String line : reprosLines)
			{
				Reproduction r = getReproduction(line);
				if(r != null) u.addReproduction(r);
			}
			
			addUser(u);
		}
	}
	
	public static Set<User> getUsers() { return users; }

	public static void addUsersFrom(String filepath) throws Exception
	{
		ArrayList<User> usersArray = new ArrayList<User>();
		usersArray = UserManager.getUsersArrayFromDisk(filepath, "");
		
		for(User u : usersArray)
		{
			addUser(u);
		}
	}
	
	public static void addUser(User user)
	{
		for(User u : users)
			if(u.getName().equals(user.getName())) return;
		users.add(user);
	}
	
	public static void createUserAndSaveToDisk(String username, int edat) throws Exception 
	{
		for(User u: users)
		{
			if(u.getName().equals(username))
			{
				throw new Exception("El nom d'usuari ja existeix");
			}
		}
		
		User u = new User(username, edat);
		addUser(u);
		
		saveReproductionsToDisk("data/reproductions/" + username + "Reproductions.txt", new ArrayList<Reproduction>());
		saveUserToDisk("data/users/users.txt", u);
	}
	
	public static Set<User> getUsers(String filepath, String reprosDir) throws Exception
	{
		ArrayList<User> usersArray = new ArrayList<User>();
		usersArray = UserManager.getUsersArrayFromDisk(filepath, reprosDir);
		
		for(User u : usersArray)
		{
			addUser(u);
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
	
	public static void setUserAge(String username, int age)
	{
		for(User u : users)
		{
			if(u.getName().equals(username))
			{
				u.setAge(age);
				return;
			}
		}
	}
	
	public static void setUserReproductions(String username, ArrayList<String> reprosStrings) throws Exception
	{
		ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
		for(String line : reprosStrings)
		{
			//Passem al format adient, el dels arxiu
			line = line.replaceAll(",", ";");
			Reproduction r = getReproduction(line);
			repros.add(r);
		}
		
		User user = getUserByName(username);
		if(username != null)
		{
			user.setReproductions(repros);
		}
	}

	private static User getUserByName(String username)
	{
		for(User u : users) if(u.getName().equals(username)) return u;
		return null;
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
	
	 public static void saveUserToDisk(String filepath, User u) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	String songLine = u.getName() + ";" + u.getAge();
	    	
	    	if(FileManager.exists(filepath))
	    	{
	    		fileLines = FileManager.loadData(filepath);
	    		
		    	boolean existedInFile = false;
		    	for(String line : fileLines)
		    	{
		    		String name = line.split(";")[0];
		    		if(name.equals(u.getName()))
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
	    

	    public static void saveCurrentUsersToDisk() throws IOException
	    {
	    	//Actualitzar fitxers usuaris
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	for(User u : users)
	    	{
	    		String songLine = u.getName() + ";" + u.getAge();
	    		fileLines.add(songLine);
	    	}
	    	FileManager.saveData("data/users/users.txt", fileLines);
	    	

	    	//Actualitzar fitxers reproduccions
	    	for(User u : users)
	    	{
		    	fileLines = new ArrayList<String>();
	    		ArrayList<Reproduction> repros = u.getReproductions();
	    		for(Reproduction r : repros)
    			{
	    			String line = r.getSongAuthor() + ";" + r.getSongTitle() + ";" + r.getTime();
		    		fileLines.add(line);
    			}
		    	FileManager.saveData("data/reproductions/" + u.getName() + "Reproductions.txt", fileLines);
	    	}
	    }
	 
	    public static void saveUsersToDisk(String filepath, ArrayList<User> users) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	for(User u : users)
	    	{
	    		String songLine = u.getName() + ";" + u.getAge();
	    		fileLines.add(songLine);
	    	}
	    	FileManager.saveData(filepath, fileLines);
	    }

	    
	    public static void removeUserFromDisk(String filepath, String username) throws IOException
	    {
	    	String search = username;
		
	    	ArrayList<String> lines = FileManager.loadData(filepath);
	    	for(int i = 0; i < lines.size(); ++i)
	    	{
	    		String line = lines.get(i);
	    		String name = line.split(";")[0];
	    		if(name.equals(search)) lines.remove(i);
	    	}
	    	
	    	FileManager.saveData(filepath, lines);
	    	FileManager.eraseData("data/reproductions/" + username + "Reproductions.txt");
	    }
	    
	    public static void removeUserFromDisk(String username) throws IOException
	    {
	    	String search = username;

	    	ArrayList<String> lines = FileManager.loadData("data/users/users.txt");
	    	for(int i = 0; i < lines.size(); ++i)
	    	{
	    		String line = lines.get(i);
	    		String name = line.split(";")[0];
	    		if(name.equals(search)) lines.remove(i);
	    	}
	    	
	    	FileManager.saveData("data/users/users.txt", lines);
	    	FileManager.eraseData("data/reproductions/" + username + "Reproductions.txt");
	    	
	    	users.clear();
	    	try {
				loadUsersFromDisk();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	
	    	//Eliminem les seves reproduccions
	    	FileManager.eraseData("data/reproductions/" + username + "Reproductions.txt");
	    }

	    public static void saveReproductionToDisk(String filepath, Reproduction r) throws IOException
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
	    
	    public static void saveReproductionsToDisk(String filepath, ArrayList<Reproduction> reproductions) throws IOException
	    {
	    	ArrayList<String> fileLines = new ArrayList<String>();
	    	for(Reproduction r : reproductions)
	    	{
	    		String reproductionLine = r.getSongAuthor() + ";" + r.getSongTitle() + ";" + r.getTime();
	    		fileLines.add(reproductionLine);
	    	}
			FileManager.saveData(filepath, fileLines);
	    }

		
	    private static User getUserFromString(String line) throws Exception
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
	    	if(fields.length != 3) { throw new Exception("Bad reproduction string format"); }
	    	String author = fields[0].trim();
	    	String title = fields[1].trim();
	    	int time = Integer.parseInt(fields[2].trim());
	    	
	    	return new Reproduction(author, title, time);
		}

		private static ArrayList<User> getUsersArrayFromDisk(String userFilepath, String reprosDir) throws Exception
		{
			ArrayList<User> theUsers = new ArrayList<User>();
			
			ArrayList<String> fileLines = FileManager.loadData(userFilepath);
			for(String line : fileLines)
			{
				User u = UserManager.getUserFromString(line);
				
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
				
				theUsers.add(u);
			}
			
			return theUsers;
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

		public static void importUsers(String path) throws Exception 
		{
			getUsers(path, "");
			saveCurrentUsersToDisk();	
		}

		public static void importReproductions(String path, String username) throws Exception
		{
			User u = getUserByName(username);
			ArrayList<Reproduction> repros = getReproductions(path);
			for(Reproduction r : repros) u.addReproduction(r);
			saveReproductionsToDisk("data/reproductions/" + u.getName() + "Reproductions.txt", u.getReproductions());
		}
		
		public static void removeAllUsers() 
		{
			FileManager.eraseData("data/users/users.txt");
			try { FileManager.saveData("data/users/users.txt", new ArrayList<String>()); } catch(Exception e) { e.printStackTrace(); return; }
			users.clear();
		}
}
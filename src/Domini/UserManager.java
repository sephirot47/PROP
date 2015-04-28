package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileParser;

public class UserManager 
{
	private static Set<User> users = new HashSet<User>();
	
	public static Set<User> GetUsers()
	{
		users.clear();
		
		if(users.size() == 0)
		{
			ArrayList<User> usersArray = new ArrayList<User>();
			try 
			{
				usersArray = FileParser.GetUsers("data/users/users.txt");
			} 
			catch (IOException e) { e.printStackTrace(); }
			
			for(User u : usersArray)
			{
				users.add(u);
			}
		}
		return users;
	}
}
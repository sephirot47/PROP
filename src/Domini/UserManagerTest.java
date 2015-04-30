package Domini;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class UserManagerTest extends TestCase 
{
	public UserManagerTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetUsers() 
	{
		//TEST 1
		Set<User> usersRead = UserManager.GetUsers("tests/users1.txt", "tests"); //read from file
		Set<User> users = new HashSet<User>(); //created by us
	
		assertFalse(UserSetsEquals(users, usersRead));
		
		users.add(new User("a", 25));
		assertTrue(UserSetsEquals(users, usersRead));
		
		users.add(new User("xza", 123));
		assertFalse(UserSetsEquals(users, usersRead));
		
		//TEST 2
		usersRead.clear();
		usersRead  = UserManager.GetUsers("tests/users2.txt", "tests"); //read from file
		users.clear();
		
		users.add(new User("a", 12));
		users.add(new User("cxcvxcv asdas", 22));
		users.add(new User("BbsdA124*^!", 78));
		assertTrue(UserSetsEquals(users, usersRead)); //TEST20
		
		users.add(new User("asdsad", 2));
		assertFalse(UserSetsEquals(users, usersRead)); //TEST21
		
		users.clear();
		users.add(new User("xccvxxcv", 6));
		assertFalse(UserSetsEquals(users, usersRead)); //TEST22
	}
	
	public boolean UserSetsEquals(Set<User> su1, Set<User> su2)
	{
		for(User u : su1)
		{
			boolean trobatIgual = false;
			for(User u2 : su2) 
			{ 
				if(u.GetName().equals(u2.GetName())) 
				{
					trobatIgual = true;
				}
			}
			if(!trobatIgual) return false;
		}
		return su1.size() == su2.size();
	}
	
	public void testUserSetsEquals()
	{
		Set<User> su1 = new HashSet<User>();
		Set<User> su2 = new HashSet<User>();
		
		assertTrue(UserSetsEquals(su1, su2));
	
		su1.add(new User("asdsad", 202115));
		assertFalse(UserSetsEquals(su1, su2));
		
		su1.add(new User("xcvxc", 122));
		assertFalse(UserSetsEquals(su1, su2));
		
		su2.add(new User("asdsad", 202115));
		assertFalse(UserSetsEquals(su1, su2));
		
		su2.add(new User("xcvxc", 122));
		assertTrue(UserSetsEquals(su1, su2));
	}
}
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

		assertFalse(users.equals(usersRead));
		
		User u = new User("a", 12);
		u.AddReproduction(new Reproduction("a", "b", 20));
		u.AddReproduction(new Reproduction("a", "b", 30));
		users.add(u);
		assertFalse(SetsEquals(users, usersRead));
		
		u.AddReproduction(new Reproduction("a", "b", 592));
		assertTrue(SetsEquals(users, usersRead));
		
		users.add(new User("xza", 123));
		assertFalse(SetsEquals(users, usersRead));
		
		//TEST 2
		usersRead.clear();
		usersRead  = UserManager.GetUsers("tests/users2.txt", "tests"); //read from file
		users.clear();

		User u2 = new User("BbsdA124^!", 78);
		u2.AddReproduction(new Reproduction("xcvxcv", "xcvvc", 20));
		u2.AddReproduction(new Reproduction("xcvxcv", "ioppoi", 30));
		users.add(u2);
		assertFalse(SetsEquals(users, usersRead));
		
		User u3 = new User("a", 12);
		u3.AddReproduction(new Reproduction("a", "b", 20));
		u3.AddReproduction(new Reproduction("a", "b", 30));
		u3.AddReproduction(new Reproduction("a", "b", 592));
		users.add(u3);
		assertFalse(SetsEquals(users, usersRead));

		User u4 = new User("cxcvxcv asdas", 25);
		users.add(u4);
		assertFalse(SetsEquals(users, usersRead));
		
		u4.AddReproduction(new Reproduction("jkll", "kl", 20));
		u4.AddReproduction(new Reproduction("jjjj", "jk", 30));
		assertTrue(SetsEquals(users, usersRead));

		users.add(new User("asdsad", 2));
		assertFalse(SetsEquals(users, usersRead));
		
		users.clear();
		users.add(new User("xccvxxcv", 6));
		assertFalse(SetsEquals(users, usersRead));
	}
	
	public <T> boolean SetsEquals(Set<T> ss1, Set<T> ss2)
	{
		//S'ha de passar a ArrayList perque si no, al comparar sets amb set.equals(set2), tambe compara
		//els hashcodes, i nomes volem que compari amb la funcio T.equals, per aixo aquesta conversio.
		ArrayList<T> ar1 = new ArrayList<T>(ss1);
		ArrayList<T> ar2 = new ArrayList<T>(ss2);
		
		//No uso equals, ja que al venir de sets el ordre no importa 
		return ar1.containsAll(ar2) && ar2.containsAll(ar1); //NO CAL TEST, ES UN AXIOMA DE TEORIA DE CONJUNTS
	}
}
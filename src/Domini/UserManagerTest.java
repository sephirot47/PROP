package Domini;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class UserManagerTest extends TestCase 
{
	public UserManagerTest()
	{
		super("UserManagerTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetUsers() 
	{
		try{
		//TEST 1
		Set<User> usersRead = UserManager.getUsers("data/users1.txt", "tests"); //read from file
		Set<User> users = new HashSet<User>(); //created by us

		assertFalse(users.equals(usersRead));
		
		User u = new User("a", 12);
		u.addReproduction(new Reproduction("a", "b", 20));
		u.addReproduction(new Reproduction("a", "b", 30));
		users.add(u);
		assertFalse(setsEquals(users, usersRead));
		
		u.addReproduction(new Reproduction("a", "b", 592));
		assertTrue(setsEquals(users, usersRead));
		
		users.add(new User("xza", 123));
		assertFalse(setsEquals(users, usersRead));
		
		//TEST 2
		usersRead.clear();
		usersRead  = UserManager.getUsers("data/users2.txt", "tests"); //read from file
		users.clear();

		User u2 = new User("BbsdA124^!", 78);
		u2.addReproduction(new Reproduction("xcvxcv", "xcvvc", 20));
		u2.addReproduction(new Reproduction("xcvxcv", "ioppoi", 30));
		users.add(u2);
		assertFalse(setsEquals(users, usersRead));
		
		User u3 = new User("a", 12);
		u3.addReproduction(new Reproduction("a", "b", 20));
		u3.addReproduction(new Reproduction("a", "b", 30));
		u3.addReproduction(new Reproduction("a", "b", 592));
		users.add(u3);
		assertFalse(setsEquals(users, usersRead));

		User u4 = new User("cxcvxcv asdas", 25);
		users.add(u4);
		assertFalse(setsEquals(users, usersRead));
		
		u4.addReproduction(new Reproduction("jkll", "kl", 20));
		u4.addReproduction(new Reproduction("jjjj", "jk", 30));
		assertTrue(setsEquals(users, usersRead));

		users.add(new User("asdsad", 2));
		assertFalse(setsEquals(users, usersRead));
		
		users.clear();
		users.add(new User("xccvxxcv", 6));
		assertFalse(setsEquals(users, usersRead));
		} catch(Exception e) {}
	}
	
	public <T> boolean setsEquals(Set<T> ss1, Set<T> ss2)
	{
		//S'ha de passar a ArrayList perque si no, al comparar sets amb set.equals(set2), tambe compara
		//els hashcodes, i nomes volem que compari amb la funcio T.equals, per aixo aquesta conversio.
		ArrayList<T> ar1 = new ArrayList<T>(ss1);
		ArrayList<T> ar2 = new ArrayList<T>(ss2);
		
		//No uso equals, ja que al venir de sets el ordre no importa 
		return ar1.containsAll(ar2) && ar2.containsAll(ar1); //NO CAL TEST, ES UN AXIOMA DE TEORIA DE CONJUNTS
	}
}
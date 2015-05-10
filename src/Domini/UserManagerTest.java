package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

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
		Set<User> usersRead = UserManager.getUsers("data/users1.txt", "data"); //read from file
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
		usersRead  = UserManager.getUsers("data/users2.txt", "data"); //read from file
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

	public void testSaveUser() throws IOException
	{
		try{
		User u1 = new User("usuari1", 23);
		User u2 = new User("usuari2", 21);
		User u3 = new User("usuari3", 45);
		ArrayList<String> usersLines = new ArrayList<String>();
	
		FileManager.eraseData("data/userSaveProva1.txt"); //Comencem amb larxiu buit
		
		UserManager.saveUser("data/userSaveProva1.txt", u1);
		usersLines.add("usuari1;23");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		
		UserManager.saveUser("data/userSaveProva1.txt", u2);
		usersLines.add("usuari2;21");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		
		UserManager.saveUser("data/userSaveProva1.txt", u3);
		usersLines.add("usuari3;45");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		
		u2.setAge(5478);
		u2.setName("usuari567");
		UserManager.saveUser("data/userSaveProva1.txt", u2);
		usersLines.add("usuari567;5478");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		
		u2.setName("usuari2");  //Modificarem una linia en especific
		u2.setAge(656);
		UserManager.saveUser("data/userSaveProva1.txt", u2);
		usersLines.set(1, "usuari2;656");
		assertEquals(FileManager.loadData("data/userSaveProva1.txt"), usersLines);
		} catch(Exception e) {}
	}
	
	 public static void testSaveUsers() throws Exception
	 {
		 ArrayList<User> writtenUsers = new ArrayList<User>();
		 ArrayList<String> usersLines = new ArrayList<String>();
		 
		 FileManager.eraseData("data/userSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenUsers.add(new User("usuari1", 23));
		 writtenUsers.add(new User("usuari2", 21));
		 writtenUsers.add(new User("usuari3", 45));
		 
		 usersLines.add("usuari1;23");
		 usersLines.add("usuari2;21");
		 usersLines.add("usuari3;45");
		 
		 UserManager.saveUsers("data/userSaveProva2.txt", writtenUsers);
		 assertEquals(FileManager.loadData("data/userSaveProva2.txt"), usersLines);
		 
		
		 writtenUsers = new ArrayList<User>();
		 usersLines = new ArrayList<String>();
		 
		 FileManager.eraseData("data/userSaveProva3.txt"); //Comencem amb larxiu buit
		 
		 writtenUsers.add(new User("AAAAAAAAAAAAA", 213));
		 UserManager.saveUsers("data/userSaveProva3.txt", writtenUsers);
		 assertFalse(FileManager.loadData("data/userSaveProva3.txt").equals(usersLines));
		 
		 usersLines.add("AAAAAAAAAAAAA;213");
		 assertEquals(FileManager.loadData("data/userSaveProva3.txt"), usersLines);
	 }
	 
	 public static void testSaveReproduction() throws Exception
	 {
		Reproduction r1 = new Reproduction("autor1", "titol1", 1);
		Reproduction r2 = new Reproduction("autor2", "titol2", 2);
		Reproduction r3 = new Reproduction("autor3", "titol3", 3);
		ArrayList<String> reproductionsLines = new ArrayList<String>();
	
		FileManager.eraseData("data/reproductionSaveProva1.txt"); //Comencem amb larxiu buit
		
		UserManager.saveReproduction("data/reproductionSaveProva1.txt", r1);
		reproductionsLines.add("autor1;titol1;1");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
		
		UserManager.saveReproduction("data/reproductionSaveProva1.txt", r2);
		reproductionsLines.add("autor2;titol2;2");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
		
		UserManager.saveReproduction("data/reproductionSaveProva1.txt", r3);
		reproductionsLines.add("autor3;titol3;3");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
		
		r2.setSongAuthor("autor2bis"); //Si canviem la primary key, es considera un nou user
		UserManager.saveReproduction("data/reproductionSaveProva1.txt", r2);
		assertFalse(FileManager.loadData("data/reproductionSaveProva1.txt").equals(reproductionsLines));
		reproductionsLines.add("autor2bis;titol2;2");
		assertEquals(FileManager.loadData("data/reproductionSaveProva1.txt"), reproductionsLines);
	 }
	 
	 public static void testSaveReproductions() throws Exception
	 {
		 ArrayList<Reproduction> writtenReproductions = new ArrayList<Reproduction>();
		 ArrayList<String> reproductionsLines = new ArrayList<String>();
	
		 FileManager.eraseData("data/reproductionSaveProva2.txt"); //Comencem amb larxiu buit
		
		 writtenReproductions.add(new Reproduction("autor1", "titol1", 1));
		 writtenReproductions.add(new Reproduction("autor2", "titol1", 2));
		 writtenReproductions.add(new Reproduction("autor3", "titol123", 3));
		 
		 reproductionsLines.add("autor1;titol1;1");
		 reproductionsLines.add("autor2;titol1;2");
		 reproductionsLines.add("autor3;titol123;3");
		 
		 UserManager.saveReproductions("data/reproductionSaveProva2.txt", writtenReproductions);
		 assertEquals(FileManager.loadData("data/reproductionSaveProva2.txt"), reproductionsLines);
	 }
	 
	 public static void testRemoveUser() throws IOException
	 {
		 ArrayList<String> usersLines = new ArrayList<String>();
		 
		 usersLines.add("victor;20"); //0
		 usersLines.add("aaa;45"); //1
		 usersLines.add("bbb;10"); //2
		 usersLines.add("xxx;102"); //3
	
		 FileManager.eraseData("data/usersRemoveTest.txt");
		 FileManager.saveData("data/usersRemoveTest.txt", usersLines);
	
		 usersLines.remove(2);
		 UserManager.removeUser("data/usersRemoveTest.txt", "bbb");
		 assertEquals(FileManager.loadData("data/usersRemoveTest.txt"), usersLines);
	
		 usersLines.remove(1);
		 UserManager.removeUser("data/usersRemoveTest.txt", "aaa");
		 assertEquals(FileManager.loadData("data/usersRemoveTest.txt"), usersLines);
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
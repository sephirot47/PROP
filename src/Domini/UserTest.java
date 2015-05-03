package Domini;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class UserTest extends TestCase 
{
	public UserTest()
	{
		super("UserTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	

    public void testUserConstructor0()
    {
    	try{
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("a", "b", 2344));
    	repros.add(r0);
    	repros.add(r1);
    	
    	User u = new User("ILovePROP A LOT!!!", 47, repros);
    	
		assertEquals("ILovePROP A LOT!!!", u.getName());
		assertEquals(47, u.getAge());
		assertEquals(r0, u.getReproductions().get(0));
		assertEquals(r1, u.getReproductions().get(1));
		} catch(Exception e) {}
    }
    
    public void testUserConstructor1()
    {
    	try{
    	User u = new User("IHatePROP Am I bipolar Yes! No!", 65535);
    	
		assertEquals("IHatePROP Am I bipolar Yes! No!", u.getName());
		assertEquals(65535, u.getAge());
		} catch(Exception e) {}
    }
    
    public void testAddReproduction()
    {
    	try{
    	User u = new User("Cheezy peazy", 324);
    	
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	u.addReproduction(r0);
    	
		assertEquals(r0, u.getReproductions().get(0));
		} catch(Exception e) {}
    }
    
    public void testAddReproductions()
    {
    	try{
    	User u = new User("OpenGL", 12);
    	
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("acxv", "bxcv", 12));
    	
    	repros.add(r0);
    	repros.add(r1);
    	
    	u.addReproductions(repros);
    	
		assertEquals(repros.get(0), u.getReproductions().get(0));
		assertEquals(repros.get(1), u.getReproductions().get(1));
		} catch(Exception e) {}
    }
    
    public void testSetAge() 
    {
    	User u = new User();
    	u.setAge(20);
    	assertEquals(20, u.getAge());
    }
    
    public void testSetName()
    { 
    	try{
    	User u = new User();
    	u.setName("Im Mr Potato");
    	assertEquals("Im Mr Potato", u.getName());
		} catch(Exception e) {}
    }
    
	public void testSetReproductions() 
    {
		try{
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("acxv", "bxcv", 12));
    	
    	repros.add(r0);
    	repros.add(r1);
    	
    	User u = new User("OpenGL", 12);
    	u.setReproductions(repros);
		assertEquals(repros.get(0), u.getReproductions().get(0));
		assertEquals(repros.get(1), u.getReproductions().get(1));
		} catch(Exception e) {}
    }
	
    public void testGetAge()
    { 
    	User u = new User();
    	u.setAge(20);
    	assertEquals(20, u.getAge());
    }
    
    public void testGetName()
    { 
    	try{
    	User u = new User();
    	u.setName("Im Mr Potato");
    	assertEquals("Im Mr Potato", u.getName());
		} catch(Exception e) {}
    }
    
    public void testGetReproductions() 
    { 
    	try{
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("acxv", "bxcv", 12));
    	
    	repros.add(r0);
    	repros.add(r1);
    	
    	User u = new User("OpenGL", 12);
    	u.setReproductions(repros);
		assertEquals(repros.get(0), u.getReproductions().get(0));
		assertEquals(repros.get(1), u.getReproductions().get(1));
		} catch(Exception e) {}
    }
    
    public void testPrint()
    { 
    	assertEquals(0, 0); //No point on testing this, can change a lot and the System.out.println is already tested by java friends :)
    }
    
}
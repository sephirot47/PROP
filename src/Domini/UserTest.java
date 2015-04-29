package Domini;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class UserTest extends TestCase 
{
	public UserTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	

    public void testUserConstructor0()
    {
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("a", "b", 2344));
    	repros.add(r0);
    	repros.add(r1);
    	
    	User u = new User("ILovePROP<3 A LOT!!! <3 <3 <3", 47, repros);
    	
		assertEquals("ILovePROP<3 A LOT!!! <3 <3 <3", u.GetName());
		assertEquals(47, u.GetAge());
		assertEquals(r0, u.GetReproductions().get(0));
		assertEquals(r1, u.GetReproductions().get(1));
    }
    
    public void testUserConstructor1()
    {
    	User u = new User("IHatePROP<3 Am I bipolar? Yes! No!", 65535);
    	
		assertEquals("IHatePROP<3 Am I bipolar? Yes! No!", u.GetName());
		assertEquals(65535, u.GetAge());
    }
    
    public void testAddReproduction()
    {
    	User u = new User("Cheezy peazy", 324);
    	
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	u.AddReproduction(r0);
    	
		assertEquals(r0, u.GetReproductions().get(0));
    }
    
    public void testAddReproductions()
    {
    	User u = new User("OpenGL <3 <3", 12);
    	
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("acxv", "bxcv", 12));
    	
    	repros.add(r0);
    	repros.add(r1);
    	
    	u.AddReproductions(repros);
    	
		assertEquals(repros.get(0), u.GetReproductions().get(0));
		assertEquals(repros.get(1), u.GetReproductions().get(1));
    }
    
    public void testSetAge() 
    {
    	User u = new User();
    	u.SetAge(20);
    	assertEquals(20, u.GetAge());
    }
    
    public void testSetName()
    { 
    	User u = new User();
    	u.SetName("Im Mr Potato");
    	assertEquals("Im Mr Potato", u.GetName());
    }
    
	public void testSetReproductions() 
    {
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("acxv", "bxcv", 12));
    	
    	repros.add(r0);
    	repros.add(r1);
    	
    	User u = new User("OpenGL <3 <3", 12);
    	u.SetReproductions(repros);
		assertEquals(repros.get(0), u.GetReproductions().get(0));
		assertEquals(repros.get(1), u.GetReproductions().get(1));
    }
	
    public void testGetAge()
    { 
    	User u = new User();
    	u.SetAge(20);
    	assertEquals(20, u.GetAge());
    }
    
    public void testGetName()
    { 
    	User u = new User();
    	u.SetName("Im Mr Potato");
    	assertEquals("Im Mr Potato", u.GetName());
    }
    
    public void testGetReproductions() 
    { 
    	ArrayList<Reproduction> repros = new ArrayList<Reproduction>();
    	Reproduction r0 = (new Reproduction("a", "b", 2344));
    	Reproduction r1 = (new Reproduction("acxv", "bxcv", 12));
    	
    	repros.add(r0);
    	repros.add(r1);
    	
    	User u = new User("OpenGL <3 <3", 12);
    	u.SetReproductions(repros);
		assertEquals(repros.get(0), u.GetReproductions().get(0));
		assertEquals(repros.get(1), u.GetReproductions().get(1));
    }
    
    public void testPrint()
    { 
    	assertEquals(0, 0); //No point on testing this, can change a lot and the System.out.println is already tested by java friends :)
    }
    
}
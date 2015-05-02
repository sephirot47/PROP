package Domini;

import junit.framework.TestCase;

public class ReproductionTest extends TestCase 
{
	public ReproductionTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testPrint()
	{
		Reproduction r = new Reproduction("AC/DC","T.N.T",101);
		r.Print();
	}
	
	public void testGetSongAuthor() 
	{
		Reproduction r = new Reproduction("AC/DC","T.N.T",101);
		assertEquals("AC/DC",r.GetSongAuthor());
	}

	public void testSetSongAuthor() 
	{
		Reproduction r = new Reproduction("Justin Bebier","T.N.T",101);
		r.SetSongAuthor("AC/DC");
		assertEquals("AC/DC",r.GetSongAuthor());
		
	}

	public void testGetSongTitle() 
	{
		Reproduction r = new Reproduction("AC/DC","Highway to hell",208);
		assertEquals("Highway to hell",r.GetSongTitle());
	}

	public void testSetSongTitle() 
	{
		Reproduction r = new Reproduction("AC/DC","La Macarena",208);
		r.SetSongTitle("Highway to hell");
		assertEquals("Highway to hell",r.GetSongTitle());
		
	}

	public void testGetTime() 
	{
		Reproduction r = new Reproduction("AC/DC","Thunderstruck",294);
		assertEquals(294,r.GetTime());
	}

	public void testSetTime() {
		Reproduction r = new Reproduction("AC/DC","Thunderstruck",1);
		r.SetTime(294);
		assertEquals(294,r.GetTime());
	}
	
	public void testEquals() 
	{
		Reproduction r1 = new Reproduction("autor", "titol", 1);
		Reproduction r2 = new Reproduction("autor", "titol", 1);
		assertTrue(r1.equals(r2));
		r2.SetSongAuthor("rotau");
		assertFalse(r1.equals(r2));
	}

}
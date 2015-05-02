package Domini;

import junit.framework.TestCase;

public class ReproductionTest extends TestCase 
{
	public ReproductionTest()
	{
		super("ReproductionTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testPrint() throws Exception
	{
		Reproduction r = new Reproduction("AC/DC","T.N.T",101);
		r.Print();
	}
	
	public void testGetSongAuthor() throws Exception 
	{
		Reproduction r = new Reproduction("AC/DC","T.N.T",101);
		assertEquals("AC/DC",r.GetSongAuthor());
	}

	public void testSetSongAuthor()  throws Exception 
	{
		Reproduction r = new Reproduction("Justin Bebier","T.N.T",101);
		r.SetSongAuthor("AC/DC");
		assertEquals("AC/DC",r.GetSongAuthor());
		
	}

	public void testGetSongTitle()  throws Exception 
	{
		Reproduction r = new Reproduction("AC/DC","Highway to hell",208);
		assertEquals("Highway to hell",r.GetSongTitle());
	}

	public void testSetSongTitle()  throws Exception 
	{
		Reproduction r = new Reproduction("AC/DC","La Macarena",208);
		r.SetSongTitle("Highway to hell");
		assertEquals("Highway to hell",r.GetSongTitle());
		
	}

	public void testGetTime()  throws Exception 
	{
		Reproduction r = new Reproduction("AC/DC","Thunderstruck",294);
		assertEquals(294,r.GetTime());
	}

	public void testSetTime() throws Exception 
	{
		Reproduction r = new Reproduction("AC/DC","Thunderstruck",1);
		r.SetTime(294);
		assertEquals(294,r.GetTime());
	}
	
	public void testEquals() throws Exception 
	{
		Reproduction r1 = new Reproduction("autor", "titol", 1);
		Reproduction r2 = new Reproduction("autor", "titol", 1);
		assertTrue(r1.equals(r2));
		r2.SetSongAuthor("rotau");
		assertFalse(r1.equals(r2));
	}

}
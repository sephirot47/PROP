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

	public void TestPrint()
	{
		Reproduction r = new Reproduction("AC/DC","T.N.T",101);
		r.Print();
		
	}
	
	public void TestGetSongAuthor() 
	{
		Reproduction r = new Reproduction("AC/DC","T.N.T",101);
		assertEquals("AC/DC",r.GetSongAuthor());
	}

	public void TestSetSongAuthor() 
	{
		Reproduction r = new Reproduction("Justin Bebier","T.N.T",101);
		r.SetSongAuthor("AC/DC");
		assertEquals("AC/DC",r.GetSongAuthor());
		
	}

	public void TestGetSongTitle() 
	{
		Reproduction r = new Reproduction("AC/DC","Highway to hell",208);
		assertEquals("Highway to hell",r.GetSongTitle());
	}

	public void TestSetSongTitle() 
	{
		Reproduction r = new Reproduction("AC/DC","La Macarena",208);
		r.SetSongTitle("Highway to hell");
		assertEquals("Highway to hell",r.GetSongTitle());
		
	}

	public void TestGetTime() 
	{
		Reproduction r = new Reproduction("AC/DC","Thunderstruck",294);
		assertEquals(294,r.GetTime());
	}

	public void TestSetTime() {
		Reproduction r = new Reproduction("AC/DC","Thunderstruck",1);
		r.SetTime(294);
		assertEquals(294,r.GetTime());
	}

}
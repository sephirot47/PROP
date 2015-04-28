package Domini;

import junit.framework.TestCase;

public class SongRelationTest extends TestCase 
{
	public SongRelationTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetWeight() 
	{
		SongRelation sr = new SongRelation();
		sr.SetWeight(30.0f);
		assertEquals(30.0f, sr.GetWeight());
	}
	
	public void testSetWeight() 
	{
		SongRelation sr = new SongRelation();
		sr.SetWeight(30.0f);
		assertEquals(30.0f, sr.GetWeight());
	}
}

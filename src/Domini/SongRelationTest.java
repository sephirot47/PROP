package Domini;

import junit.framework.TestCase;

public class SongRelationTest extends TestCase 
{
	public SongRelationTest()
	{
		super("SongRelationTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetWeight() 
	{
		SongRelation sr = new SongRelation();
		sr.setWeight(30.0f);
		assertEquals(30.0f, sr.getWeight());
	}
	
	public void testSetWeight() 
	{
		SongRelation sr = new SongRelation();
		sr.setWeight(30.0f);
		assertEquals(30.0f, sr.getWeight());
	}
}

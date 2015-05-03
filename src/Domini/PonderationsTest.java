package Domini;

import junit.framework.TestCase;

public class PonderationsTest extends TestCase 
{
	public PonderationsTest()
	{
		super("PonderationsTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}


	
	public void testGetThreshold()
	{
		Ponderations t = new Ponderations();
		
		assertEquals(5.0f,t.getThreshold());
	}

	public void testGetDuration() {
		
		Ponderations t = new Ponderations();
		assertEquals(5,t.getDuration());
	
	}

	public void testSetDuration() {
		Ponderations t = new Ponderations();
		
		t.setDuration(8);
		assertEquals(8,t.getDuration());
		
	}

	public void testGetYear() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.getYear());
		
	}

	public void testSetYear() {
		Ponderations t = new Ponderations();
		t.setYear(8);
		assertEquals(8,t.getYear());
	}

	public void testGetStyle() {
		Ponderations t = new Ponderations();
		assertEquals(5, t.getStyle());
	}

	public void testSetStyle() {
		Ponderations t = new Ponderations();
		t.setStyle(8);
		assertEquals(8,t.getStyle());
	}

	public void testGetUserAge() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.getUserAge());
	}

	public void testSetUserAge() {
		Ponderations t = new Ponderations();
		t.setUserAge(33);
		assertEquals(33,t.getUserAge());
	}

	public void testGetNearbyReproductions() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.getNearbyReproductions());
	}

	public void testSetNearbyReproductions() {
		Ponderations t = new Ponderations();
		t.setNearbyReproductions(8);
		assertEquals(8,t.getNearbyReproductions());
	}

	public void testGetAuthor() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.getAuthor());
	}

	public void testSetAuthor() {
		Ponderations t = new Ponderations();
		t.setAuthor(25);
		assertEquals(25,t.getAuthor());
	}

}


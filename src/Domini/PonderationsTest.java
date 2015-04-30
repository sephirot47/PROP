package Domini;

import junit.framework.TestCase;

public class PonderationsTest extends TestCase 
{
	public PonderationsTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}


	
	public void testGetThreshold()
	{
		Ponderations t = new Ponderations();
		
		assertEquals(5.0f,t.GetThreshold());
	}

	public void testGetDuration() {
		
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetDuration());
	
	}

	public void testSetDuration() {
		Ponderations t = new Ponderations();
		
		t.SetDuration(8);
		assertEquals(8,t.GetDuration());
		
	}

	public void testGetYear() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetYear());
		
	}

	public void testSetYear() {
		Ponderations t = new Ponderations();
		t.SetYear(8);
		assertEquals(8,t.GetYear());
	}

	public void testGetStyle() {
		Ponderations t = new Ponderations();
		assertEquals(5, t.GetStyle());
	}

	public void testSetStyle(int style) {
		Ponderations t = new Ponderations();
		t.SetStyle(8);
		assertEquals(8,t.GetStyle());
	}

	public void testGetUserAge() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetUserAge());
	}

	public void testSetUserAge() {
		Ponderations t = new Ponderations();
		t.SetUserAge(33);
		assertEquals(33,t.GetUserAge());
	}

	public void testGetNearbyReproductions() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetNearbyReproductions());
	}

	public void testSetNearbyReproductions() {
		Ponderations t = new Ponderations();
		t.SetNearbyReproductions(8);
		assertEquals(8,t.GetNearbyReproductions());
	}

	public void testGetAuthor() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetAuthor());
	}

	public void testSetAuthor() {
		Ponderations t = new Ponderations();
		t.SetAuthor(25);
		assertEquals(25,t.GetAuthor());
	}

}


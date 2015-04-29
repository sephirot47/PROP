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


	
	public void TestGetThreshold()
	{
		Ponderations t = new Ponderations();
		
		assertEquals(5.0,t.GetThreshold());
	}

	public void TestGetDuration() {
		
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetDuration());
	
	}

	public void TestSetDuration() {
		Ponderations t = new Ponderations();
		
		t.SetDuration(8);
		assertEquals(8,t.GetDuration());
		
	}

	public void TestGetYear() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetYear());
		
	}

	public void TestSetYear() {
		Ponderations t = new Ponderations();
		t.SetYear(8);
		assertEquals(8,t.GetYear());
	}

	public void TestGetStyle() {
		Ponderations t = new Ponderations();
		assertEquals(5, t.GetStyle());
	}

	public void TestSetStyle(int style) {
		Ponderations t = new Ponderations();
		t.SetStyle(8);
		assertEquals(8,t.GetStyle());
	}

	public void TestGetUserAge() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetUserAge());
	}

	public void TestSetUserAge() {
		Ponderations t = new Ponderations();
		t.SetUserAge(33);
		assertEquals(33,t.GetUserAge());
	}

	public void TestGetNearbyReproductions() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetNearbyReproductions());
	}

	public void TestSetNearbyReproductions() {
		Ponderations t = new Ponderations();
		t.SetNearbyReproductions(8);
		assertEquals(8,t.GetNearbyReproductions());
	}

	public void TestGetAuthor() {
		Ponderations t = new Ponderations();
		assertEquals(5,t.GetAuthor());
	}

	public void TestSetAuthor() {
		Ponderations t = new Ponderations();
		t.SetAuthor(25);
		assertEquals(25,t.GetAuthor());
	}

}


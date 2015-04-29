package Domini;

import junit.framework.TestCase;

public class EdgeTest extends TestCase 
{
	public EdgeTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	
	public void testSetWeight() 
	{
		class TestEdge extends Edge
		{
			float weight;
			public float GetWeight() { return weight;}
			public void SetWeight(float weight) { this.weight = weight; }
		}
		
		TestEdge e = new TestEdge();
		e.SetWeight(3.14159265f);
		assertEquals(3.14159265f, e.GetWeight());
	}
	
	public void testGetWeight() 
	{
		class TestEdge extends Edge
		{
			float weight;
			public float GetWeight() { return weight;}
			public void SetWeight(float weight) { this.weight = weight; }
		}
		
		TestEdge e = new TestEdge();
		e.SetWeight(3.14159265f);
		assertEquals(3.14159265f, e.GetWeight());
	}
}
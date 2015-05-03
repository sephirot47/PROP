package Domini;

import junit.framework.TestCase;

public class EdgeTest extends TestCase 
{
	public EdgeTest()
	{
		super("EdgeTest");
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
			public float getWeight() { return weight;}
			public void setWeight(float weight) { this.weight = weight; }
		}
		
		TestEdge e = new TestEdge();
		e.setWeight(3.14159265f);
		assertEquals(3.14159265f, e.getWeight());
	}
	
	public void testGetWeight() 
	{
		class TestEdge extends Edge
		{
			float weight;
			public float getWeight() { return weight;}
			public void setWeight(float weight) { this.weight = weight; }
		}
		
		TestEdge e = new TestEdge();
		e.setWeight(3.14159265f);
		assertEquals(3.14159265f, e.getWeight());
	}
}
package Domini;

import junit.framework.TestCase;

public class PairTest extends TestCase 
{
	public PairTest()
	{
		super("PairTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testSetFirst() 
	{
		Pair<Integer, Boolean> p = new Pair<Integer, Boolean>();
		p.setFirst(42);
		assertEquals(42, (int)p.getFirst());
	}

	public void testSetSecond() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>();
		p.setSecond(2347.12732f);
		assertEquals(2347.12732f, (float)p.getSecond());
	}

	public void testGetFirst() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>();
		p.setFirst(347);
		assertEquals(347, (int)p.getFirst());
	}
	
	public void testGetSecond() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>();
		p.setSecond(347.2347f);
		assertEquals(347.2347f, (float)p.getSecond());
	}

	public void testPairConstructor() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>(42, 3.141592f);
		assertEquals(42,        (int)  p.getFirst());
		assertEquals(3.141592f, (float)p.getSecond());
	}
}
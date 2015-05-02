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
		p.SetFirst(42);
		assertEquals(42, (int)p.GetFirst());
	}

	public void testSetSecond() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>();
		p.SetSecond(2347.12732f);
		assertEquals(2347.12732f, (float)p.GetSecond());
	}

	public void testGetFirst() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>();
		p.SetFirst(347);
		assertEquals(347, (int)p.GetFirst());
	}
	
	public void testGetSecond() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>();
		p.SetSecond(347.2347f);
		assertEquals(347.2347f, (float)p.GetSecond());
	}

	public void testPairConstructor() 
	{
		Pair<Integer, Float> p = new Pair<Integer, Float>(42, 3.141592f);
		assertEquals(42,        (int)  p.GetFirst());
		assertEquals(3.141592f, (float)p.GetSecond());
	}
}
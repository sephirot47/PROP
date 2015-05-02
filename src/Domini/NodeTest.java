package Domini;

import junit.framework.TestCase;

public class NodeTest extends TestCase 
{
	public NodeTest()
	{
		super("NodeTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetId() 
	{
		class TestNode extends Node
		{
			public String GetId() { return "potato"; }
		}
		
		TestNode n = new TestNode();
		assertEquals("potato", n.GetId());
	}
}
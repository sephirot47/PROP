package Domini;

import junit.framework.TestCase;

public class NodeTest extends TestCase 
{
	public NodeTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void test() 
	{
		class TestNode extends Node
		{
			public String GetId() { return "potato"; }
		}
		
		TestNode n = new TestNode();
		assertEquals("potato", n.GetId());
	}
}
package Domini;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class GirvanNewmanTest extends TestCase 
{
	public GirvanNewmanTest()
	{
		super("GirvanNewman");
	}
	
	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetSolution() 
	{
		try{
		Graph<Song, SongRelation> g = new Graph<Song, SongRelation>();
		
		Song a = new Song("A","A"); g.addNode(a);
		Song b = new Song("B","B"); g.addNode(b);
		Song c = new Song("C","C"); g.addNode(c);
		Song d = new Song("D","D"); g.addNode(d);
		Song e = new Song("E","E"); g.addNode(e);
		Song f = new Song("F","F"); g.addNode(f);
		Song h = new Song("H","H"); g.addNode(h);
		Song i = new Song("I","I"); g.addNode(i);
		Song j = new Song("J","J"); g.addNode(j);
		Song k = new Song("K","K"); g.addNode(k);
		Song l = new Song("L","L"); g.addNode(l);
		Song m = new Song("M","M"); g.addNode(m);
		Song x = new Song("X","X"); g.addNode(x);
		Song y = new Song("Y","Y"); g.addNode(y);
		
		g.addEdge(a, b, new SongRelation());
		g.addEdge(a, c, new SongRelation());
		g.addEdge(b, c, new SongRelation());
		g.addEdge(e, f, new SongRelation());
		g.addEdge(e, d, new SongRelation());
		g.addEdge(d, f, new SongRelation());
		g.addEdge(h, i, new SongRelation());
		g.addEdge(h, j, new SongRelation());
		g.addEdge(j, i, new SongRelation());
		g.addEdge(k, l, new SongRelation());
		g.addEdge(k, m, new SongRelation());
		g.addEdge(l, m, new SongRelation());
		g.addEdge(x, c, new SongRelation());
		g.addEdge(x, e, new SongRelation());
		g.addEdge(y, h, new SongRelation());
		g.addEdge(y, k, new SongRelation());
		g.addEdge(x, y, new SongRelation());
		
		/// Una comunitat 
		Solution result1 = new GirvanNewman().getSolution(g, 1);
		Solution expectedResult1 = new Solution();
		Community set = new Community();
		set.addNode(a);
		set.addNode(b);
		set.addNode(c);
		set.addNode(x);
		set.addNode(e);
		set.addNode(f);
		set.addNode(d);
		set.addNode(h);
		set.addNode(i);
		set.addNode(j);
		set.addNode(y);
		set.addNode(k);
		set.addNode(l);
		set.addNode(m);
		expectedResult1.addCommunity(set);
		
		assertTrue(expectedResult1.getCommunities().containsAll(result1.getCommunities()) && result1.getCommunities().containsAll(expectedResult1.getCommunities()));
		
		
		// Dues comunitats
		Solution result2 = new GirvanNewman().getSolution(g, 2);
		Solution expectedResult2 = new Solution();
		Community set1 = new Community();
		set1.addNode(a);
		set1.addNode(b);
		set1.addNode(c);
		set1.addNode(x);
		set1.addNode(e);
		set1.addNode(f);
		set1.addNode(d);
		expectedResult2.addCommunity(set1);
		
		Community set2 = new Community();
		set2.addNode(h);
		set2.addNode(i);
		set2.addNode(j);
		set2.addNode(y);
		set2.addNode(k);
		set2.addNode(l);
		set2.addNode(m);
		expectedResult2.addCommunity(set2);

		
		assertTrue(expectedResult2.getCommunities().containsAll(result2.getCommunities()) && result2.getCommunities().containsAll(expectedResult2.getCommunities()));
		
		
		// Sis comunitats
		Solution result6 = new GirvanNewman().getSolution(g, 6);
		Solution expectedResult6 = new Solution();
		set1 = new Community();
		set1.addNode(a);
		set1.addNode(b);
		set1.addNode(c);
		
		set2 = new Community();
		set2.addNode(d);
		set2.addNode(e);
		set2.addNode(f);
		
		Community set3 = new Community();
		set3.addNode(h);
		set3.addNode(i);
		set3.addNode(j);
		
		Community set4 = new Community();
		set4.addNode(k);
		set4.addNode(l);
		set4.addNode(m);
		
		Community set5 = new Community();
		set5.addNode(x);
		
		Community set6 = new Community();
		set6.addNode(y);
		
		expectedResult6.addCommunity(set1);
		expectedResult6.addCommunity(set2);
		expectedResult6.addCommunity(set3);
		expectedResult6.addCommunity(set4);
		expectedResult6.addCommunity(set5);
		expectedResult6.addCommunity(set6);
		
		assertTrue(expectedResult6.getCommunities().containsAll(result6.getCommunities()) && result6.getCommunities().containsAll(expectedResult6.getCommunities()));
		}
		catch(Exception e){}
	}
}
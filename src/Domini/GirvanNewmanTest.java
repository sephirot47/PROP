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
		ArrayList< Set<Song> > result1 = GirvanNewman.getSolution(g, 1);
		ArrayList< Set<Song> > expectedResult1 = new ArrayList<Set<Song>>();
		Set<Song> set = new HashSet<Song>();
		set.add(a);
		set.add(b);
		set.add(c);
		set.add(x);
		set.add(e);
		set.add(f);
		set.add(d);
		set.add(h);
		set.add(i);
		set.add(j);
		set.add(y);
		set.add(k);
		set.add(l);
		set.add(m);
		expectedResult1.add(set);
		
		assertTrue(expectedResult1.containsAll(result1) && result1.containsAll(expectedResult1));
		
		
		// Dues comunitats
		ArrayList< Set<Song> > result2 = GirvanNewman.getSolution(g, 2);
		ArrayList< Set<Song> > expectedResult2 = new ArrayList<Set<Song>>();
		Set<Song> set1 = new HashSet<Song>();
		set1.add(a);
		set1.add(b);
		set1.add(c);
		set1.add(x);
		set1.add(e);
		set1.add(f);
		set1.add(d);
		expectedResult2.add(set1);
		
		Set<Song> set2 = new HashSet<Song>();
		set2.add(h);
		set2.add(i);
		set2.add(j);
		set2.add(y);
		set2.add(k);
		set2.add(l);
		set2.add(m);
		expectedResult2.add(set2);

		
		assertTrue(expectedResult2.containsAll(result2) && result2.containsAll(expectedResult2));
		
		
		// Sis comunitats
		ArrayList< Set<Song> > result6 = GirvanNewman.getSolution(g, 6);
		ArrayList< Set<Song> > expectedResult6 = new ArrayList<Set<Song>>();
		set1 = new HashSet<Song>();
		set1.add(a);
		set1.add(b);
		set1.add(c);
		
		set2 = new HashSet<Song>();
		set2.add(d);
		set2.add(e);
		set2.add(f);
		
		Set<Song> set3 = new HashSet<Song>();
		set3.add(h);
		set3.add(i);
		set3.add(j);
		
		Set<Song> set4 = new HashSet<Song>();
		set4.add(k);
		set4.add(l);
		set4.add(m);
		
		Set<Song> set5 = new HashSet<Song>();
		set5.add(x);
		
		Set<Song> set6 = new HashSet<Song>();
		set6.add(y);
		
		expectedResult6.add(set1);
		expectedResult6.add(set2);
		expectedResult6.add(set3);
		expectedResult6.add(set4);
		expectedResult6.add(set5);
		expectedResult6.add(set6);
		
		assertTrue(expectedResult6.containsAll(result6) && result6.containsAll(expectedResult6));
		}
		catch(Exception e){}
	}
}
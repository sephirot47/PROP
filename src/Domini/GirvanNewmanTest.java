package Domini;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class GirvanNewmanTest extends TestCase 
{

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testGetSolution() 
	{
		
		Graph<Song, SongRelation> g = new Graph<Song, SongRelation>();
		
		Song a = new Song("A","A"); g.AddNode(a);
		Song b = new Song("B","B"); g.AddNode(b);
		Song c = new Song("C","C"); g.AddNode(c);
		Song d = new Song("D","D"); g.AddNode(d);
		Song e = new Song("E","E"); g.AddNode(e);
		Song f = new Song("F","F"); g.AddNode(f);
		Song h = new Song("H","H"); g.AddNode(h);
		Song i = new Song("I","I"); g.AddNode(i);
		Song j = new Song("J","J"); g.AddNode(j);
		Song k = new Song("K","K"); g.AddNode(k);
		Song l = new Song("L","L"); g.AddNode(l);
		Song m = new Song("M","M"); g.AddNode(m);
		Song x = new Song("X","X"); g.AddNode(x);
		Song y = new Song("Y","Y"); g.AddNode(y);
		
		g.AddEdge(a, b, new SongRelation());
		g.AddEdge(a, c, new SongRelation());
		g.AddEdge(b, c, new SongRelation());
		g.AddEdge(e, f, new SongRelation());
		g.AddEdge(e, d, new SongRelation());
		g.AddEdge(d, f, new SongRelation());
		g.AddEdge(h, i, new SongRelation());
		g.AddEdge(h, j, new SongRelation());
		g.AddEdge(j, i, new SongRelation());
		g.AddEdge(k, l, new SongRelation());
		g.AddEdge(k, m, new SongRelation());
		g.AddEdge(l, m, new SongRelation());
		g.AddEdge(x, c, new SongRelation());
		g.AddEdge(x, e, new SongRelation());
		g.AddEdge(y, h, new SongRelation());
		g.AddEdge(y, k, new SongRelation());
		g.AddEdge(x, y, new SongRelation());
		
		/// Una comunitat 
		ArrayList< Set<Song> > result1 = GirvanNewman.GetSolution(g, 1);
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
		ArrayList< Set<Song> > result2 = GirvanNewman.GetSolution(g, 2);
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
		ArrayList< Set<Song> > result6 = GirvanNewman.GetSolution(g, 6);
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
}
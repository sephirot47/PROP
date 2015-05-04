package Persistencia;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Domini.Community;
import Domini.Reproduction;
import Domini.Solution;
import Domini.SongSolution;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongRelation;
import junit.framework.TestCase;

public class HistoryTest extends TestCase {

	public HistoryTest() {
		super("HistoryTest");
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public static void testSaveSolution() throws Exception {
		// Generem graf
		SongGraph g = new SongGraph();
		Song s1 = new Song("A", "B");
		Song s2 = new Song("C", "D");
		Song s3 = new Song("E", "F");
		g.addNode(s1);
		g.addNode(s2);
		g.addNode(s3);

		SongRelation e1 = new SongRelation();
		SongRelation e2 = new SongRelation();
		SongRelation e3 = new SongRelation();

		g.addEdge(s1, s2, e1);
		g.addEdge(s2, s3, e2);
		g.addEdge(s3, s1, e3);

		// Resultat esperat per entrada.txt
		ArrayList<String> expectedGraph = new ArrayList<String>();
		expectedGraph.add("(A,B)");
		expectedGraph.add("(E,F)");
		expectedGraph.add("(C,D)");
		expectedGraph.add("0;2;0.0");
		expectedGraph.add("1;2;0.0");
		expectedGraph.add("0;1;0.0");

		// Generem comunitats
		Solution comunities = new Solution();
		Community set = new Community();
		set.addNode(s1);
		comunities.addCommunity(set);
		set = new Community();
		set.addNode(s2);
		set.addNode(s3);
		comunities.addCommunity(set);

		// Resultat esperat per comunitats.txt
		ArrayList<String> expectedComunities = new ArrayList<String>();
		expectedComunities.add("0");
		expectedComunities.add("(A, B)");
		expectedComunities.add("1");
		expectedComunities.add("(E, F)");
		expectedComunities.add("(C, D)");

		// Resultat esperat per info.txt
		ArrayList<String> expectedInfo = new ArrayList<String>();
		expectedInfo.add("GirvanNewman");
		expectedInfo.add("3");
		expectedInfo.add("0.45");

		SongSolution s = new SongSolution(g, comunities, 0.01, "GirvanNewman", new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()) );

		History.saveSolution(s, "test");
		
		ArrayList<String> realGraph = FileManager.loadData("data/solutions/solution_test/entrada.txt");
		assertTrue(realGraph.containsAll(expectedGraph) && expectedGraph.containsAll(realGraph));
		
		ArrayList<String> realComunities = FileManager.loadData("data/solutions/solution_test/comunitats.txt");
		assertEquals(realComunities.subList(0, 2), expectedComunities.subList(0, 2)); //Mirem que les 3 primeres linies siguin iguals
		assertTrue(realComunities.containsAll(expectedComunities) && expectedComunities.containsAll(realComunities));
		assertEquals(FileManager.loadData("data/solutions/solution_test/info.txt"), expectedInfo);
	}

	public static void testRemoveSolution() throws IOException {
		String nomSolucio = "solution_test";
		History.removeSolution(nomSolucio);
		File solucio = new File("data/solutions/" + nomSolucio);
		assertFalse(solucio.exists());
	}
}
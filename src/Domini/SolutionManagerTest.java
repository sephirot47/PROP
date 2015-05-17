package Domini;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import Persistencia.FileManager;

public class SolutionManagerTest  extends TestCase {

	public SolutionManagerTest() {
		super("SolutionManagerTest");
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public static void testSaveSolution() throws Exception {
		// Generem graf
		Graph<Song> g = new Graph<Song>();
		Song s1 = new Song("A", "B");
		Song s2 = new Song("C", "D");
		Song s3 = new Song("E", "F");
		g.addNode(s1);
		g.addNode(s2);
		g.addNode(s3);

		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();

		g.addEdge(s1, s2, e1);
		g.addEdge(s2, s3, e2);
		g.addEdge(s3, s1, e3);

		// Resultat esperat per entrada.txt
		ArrayList<String> expectedGraph = new ArrayList<String>();
		expectedGraph.add("(A,B)");
		expectedGraph.add("(C,D)");
		expectedGraph.add("(E,F)");
		expectedGraph.add("1;2;0.0");
		expectedGraph.add("0;2;0.0");
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
		expectedInfo.add("0.01");
		
		SongSolution s = new SongSolution(g, comunities);

		SolutionManager.saveSolution(s);
		
		ArrayList<String> realGraph = FileManager.loadData("data/solutions/solution_test/entrada.txt");
		assertTrue(realGraph.containsAll(expectedGraph) && expectedGraph.containsAll(realGraph));
		
		ArrayList<String> realComunities = FileManager.loadData("data/solutions/solution_test/comunitats.txt");
		assertTrue(realComunities.containsAll(expectedComunities) && expectedComunities.containsAll(realComunities)); //Mirem que les 3 primeres linies siguin iguals
		assertTrue(realComunities.containsAll(expectedComunities) && expectedComunities.containsAll(realComunities));
		assertTrue(FileManager.loadData("data/solutions/solution_test/info.txt").containsAll(expectedInfo) &&  expectedInfo.containsAll(FileManager.loadData("data/solutions/solution_test/info.txt")));
	}

	public static void testRemoveSolution() throws IOException 
	{
		String nomSolucio = "solution_test";
		SolutionManager.removeSolutionFromDisk(nomSolucio);
		File solucio = new File("data/solutions/" + "solution_" + nomSolucio);
		assertFalse(solucio.exists());
	}
}
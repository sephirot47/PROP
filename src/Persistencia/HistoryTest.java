package Persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Domini.Reproduction;
import Domini.Solution;
import Domini.Song;
import Domini.SongGraph;
import junit.framework.TestCase;

public class HistoryTest extends TestCase {

	public HistoryTest() {
		super("HistoryTest");
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public static void testSaveSolution() throws Exception {

		SongGraph g = new SongGraph();
		Song s1 = new Song("A", "B");
		Song s2 = new Song("C", "D");
		Song s3 = new Song("E", "F");
		g.AddNode(s1);
		g.AddNode(s2);
		g.AddNode(s3);

		SongGraph g2 = FileParser.GetGraph("tests/graph1.txt");

		ArrayList<Set<Song>> comunities = new ArrayList<Set<Song>>();
		Set<Song> set = new HashSet<Song>();
		set.add(s1);
		comunities.add(set);
		set = new HashSet<Song>();
		set.add(s2);
		set.add(s3);
		comunities.add(set);
		Solution s = new Solution(g, "GirvaNewman",comunities, 0.45f);

		History.SaveSolution(s);
		assertEquals(FileManager.LoadData("tests/reproductionSaveProva2.txt"), History.GetSolutions(solutionsDir));
	}

	public static void testRemoveSolution() throws IOException {
		String nomSolucio = "solution_01-05-2015 00,10,53,681";
		History.RemoveSolution(nomSolucio);
		File solucio = new File("tests/" + nomSolucio);
		assertFalse(solucio.exists());
	}
}
package Persistencia;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Domini.Community;
import Domini.Solution;
import Domini.SongSolution;
import Domini.SongGraph;
import Domini.Song;

public class History {

	public static ArrayList<SongSolution> getSolutions(String solutionsDir) throws Exception 
	{
		ArrayList<SongSolution> result = new ArrayList<SongSolution>();

		File baseDir = new File(solutionsDir);
		File[] solutions = baseDir.listFiles();

		for (File dir : solutions) {

			// Legir el graph
			SongGraph graph = FileParser.getGraph(dir.getPath() + "/entrada.txt");

			// Llegir les comunitats
			ArrayList<String> resultLines = FileManager.loadData(dir.getPath() + "/comunitats.txt");
			Solution comunities = new Solution();
			Community com = new Community();

			for (String line : resultLines) {
				if (line.equals("0"))
					continue;
				if (line.charAt(0) == '(') {
					String author = line.substring(1, line.indexOf(','));
					String title = line.substring(line.indexOf(',') + 1, line.indexOf(')'));
					com.addNode(new Song(author, title));
				} else {
					comunities.addCommunity(com);
					com = new Community();
				}
			}
			comunities.addCommunity(com);

			// Llegir info
			ArrayList<String> infoLines = FileManager.loadData(dir.getPath() + "/info.txt");

			String id = new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date());
			result.add(new SongSolution(graph, comunities, Double.parseDouble(infoLines.get(2)), infoLines.get(0), id));
		}

		return result;
	}

	public static void saveSolution(SongSolution s, String id) throws IOException 
	{
		//String date = new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date());
		String filedir = "data/solutions/solution_" + id + "/";

		// arxiu de Graph(entrada)
		FileManager.saveEntradaSolution(filedir, s.getEntrada());

		// arxiu de solucio(communities)
		FileManager.saveCommunitiesSolution(filedir, s);

		// arxiu de info extra
		{
			ArrayList<String> lines = new ArrayList<String>();
			lines.add(s.getAlg()); // Alg usat
			lines.add(String.valueOf(s.getEntrada().getAllNodes().size())); // Nombre de cancons processades
			lines.add(String.valueOf(s.getTime())); // Temps que ha tardat a generar la solucio
			FileManager.saveData(filedir + "info.txt", lines);
		}
	}

	public static void removeSolution(String nomSolucio) throws IOException 
	{
		File communities = new File("data/solutions/" + nomSolucio + "/comunitats.txt");
		File entrada = new File("data/solutions/" + nomSolucio + "/entrada.txt");
		File info = new File("data/solutions/" + nomSolucio + "/info.txt");

		communities.delete();
		entrada.delete();
		info.delete();
		File folder = new File("data/solutions/" + nomSolucio);
		folder.delete();

	}
}

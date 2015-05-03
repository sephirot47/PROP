package Persistencia;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Domini.Solution;
import Domini.SongGraph;
import Domini.Song;

public class History {

	public static ArrayList<Solution> getSolutions(String solutionsDir) throws Exception 
	{
		ArrayList<Solution> result = new ArrayList<Solution>();

		File baseDir = new File(solutionsDir);
		File[] solutions = baseDir.listFiles();

		for (File dir : solutions) {

			// Legir el graph
			SongGraph graph = FileParser.getGraph(dir.getPath() + "/entrada.txt");

			// Llegir les comunitats
			ArrayList<String> resultLines = FileManager.loadData(dir.getPath() + "/comunitats.txt");
			ArrayList<Set<Song>> comunities = new ArrayList<Set<Song>>();
			Set<Song> set = new HashSet<Song>();

			for (String line : resultLines) {
				if (line.equals("0"))
					continue;
				if (line.charAt(0) == '(') {
					String author = line.substring(1, line.indexOf(','));
					String title = line.substring(line.indexOf(',') + 1, line.indexOf(')'));
					set.add(new Song(author, title));
				} else {
					comunities.add(set);
					set = new HashSet<Song>();
				}
			}
			comunities.add(set);

			// Llegir info
			ArrayList<String> infoLines = FileManager.loadData(dir.getPath() + "/info.txt");

			result.add(new Solution(graph, infoLines.get(0), comunities, Float.parseFloat(infoLines.get(2))));

		}

		return result;
	}

	public static void saveSolution(Solution s, String id) throws IOException 
	{
		//String date = new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date());
		String filedir = "data/solutions/solution_" + id + "/";

		// arxiu de Graph(entrada)
		FileManager.saveEntradaSolution(filedir, s.getEntrada());

		// arxiu de solucio(communities)
		FileManager.saveCommunitiesSolution(filedir, s.getSongCommunities());

		// arxiu de info extra
		{
			ArrayList<String> lines = new ArrayList<String>();
			lines.add(s.getAlgorisme()); // Algorisme usat
			lines.add(String.valueOf(s.getEntrada().getAllNodes().size())); // Nombre de cancons processades
			lines.add(String.valueOf(s.getGenerationTime())); // Temps que ha tardat a generar la solucio
			FileManager.saveData(filedir + "info.txt", lines);
		}
	}

	public static void removeSolution(String nomSolucio) throws IOException 
	{
		File communities = new File("tests/" + nomSolucio + "/communities.txt");
		File entrada = new File("tests/" + nomSolucio + "/entrada.txt");
		File info = new File("tests/" + nomSolucio + "/generationInfo.txt");
		communities.delete();
		entrada.delete();
		info.delete();
		File folder = new File("tests/" + nomSolucio);
		folder.delete();

	}
}

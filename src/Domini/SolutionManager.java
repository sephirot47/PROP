package Domini;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import Persistencia.FileManager;

public class SolutionManager 
{
	public static ArrayList<SongSolution> getSolutions(String solutionsDir) throws Exception 
	{
		ArrayList<SongSolution> result = new ArrayList<SongSolution>();

		File baseDir = new File(solutionsDir);
		File[] solutions = baseDir.listFiles();

		for (File dir : solutions) {

			// Legir el graph
			Graph<Song> graph = GraphManager.getGraph(dir.getPath() + "/entrada.txt");

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
		SolutionManager.saveEntradaSolution(filedir, s.getEntrada());

		// arxiu de solucio(communities)
		SolutionManager.saveCommunitiesSolution(filedir, s);

		// arxiu de info extra
		{
			ArrayList<String> lines = new ArrayList<String>();
			lines.add(s.getAlg()); // Alg usat
			lines.add(String.valueOf(s.getEntrada().getAllNodes().size())); // Nombre de cancons processades
			lines.add(String.valueOf(s.getTime())); // Temps que ha tardat a generar la solucio
			FileManager.saveData(filedir + "info.txt", lines);
		}
	}


    public static void saveEntradaSolution(String filedir, Graph<Song> entrada) throws IOException
    {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	Set<Song> songs = entrada.getAllNodes();
    	ArrayList<Song> songsArray = new ArrayList<Song>();
    	for(Song s : songs) //Nodes pels que esta format el graph
    	{
    		String line = "(" + s.getAuthor() + "," + s.getTitle() + ")";
    		lines.add(line);
    		
    		songsArray.add(s);
    	}
    	
    	
    	Set<Edge> edges = entrada.getAllEdges();
    	for(Edge e : edges)
    	{
    		Pair<Song, Song> songSong = entrada.getNodesConnectedBy(e);
    		Song s1 = songSong.getFirst(), s2 = songSong.getSecond();
    		
    		String line = songsArray.indexOf(s1) + ";" + songsArray.indexOf(s2) + ";" + e.getWeight();
    		lines.add(line);
    	}
    	
    	FileManager.saveData(filedir + "entrada.txt",  lines);
    	
    	//Se guardara, por ejemplo:
    	/*
    	 * (victor, cuando sarpa el hamor)
    	 * (jfons, tramboliko)
    	 * (aina, mesigualno?)
    	 * 0;1;0.5    //del 0 al 1, con peso 0.5
    	 * 1;2;1.3	  //del 1 al 2, con peso 1.3
    	 * (hi ha un edge de la canco del victor al jfons, i del jfons a l'aina)
    	 */
    }

    public static void saveCommunitiesSolution(String filedir, Solution songCommunities) throws IOException
    {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	int i = 0;
    	for(Community com : songCommunities.getCommunities())
    	{
    		lines.add( String.valueOf(i++) );
	    	for(Node n : com.getCommunity())
	    	{
	    		Song s = (Song) n;
	    		String line = "(" + s.getAuthor() + ", " + s.getTitle() + ")";
	    		lines.add(line);
	    	}
    	}
		FileManager.saveData(filedir + "comunitats.txt",  lines);
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

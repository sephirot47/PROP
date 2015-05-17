package Domini;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import Persistencia.FileManager;

public class SolutionManager 
{
	private static ArrayList<SongSolution> solutions = new ArrayList<SongSolution>();
	
	public static void loadSolutionsFromDisk() throws Exception
	{
		try 
		{
			solutions = getSolutions("data/solutions");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("No es troba el directori de solucions('data/solutions')");
		}
	}
	
	public static ArrayList<Double[]> getInfos(String SolutionsDir) throws Exception
	{
		ArrayList<Double[]> result = new ArrayList<Double[]>();
		
		ArrayList<SongSolution> solutions = new ArrayList<SongSolution>();
		solutions = getSolutions(SolutionsDir);
		File baseDir = new File(SolutionsDir);
		
		Double[] Girvan = new Double[(baseDir.listFiles()).length];
		Double[] Clique = new Double[(baseDir.listFiles()).length];
		Double[] Louvain = new Double[(baseDir.listFiles()).length];
		
		for(int i = 0; i < (baseDir.listFiles()).length; i++)
		{
			if(solutions.get(i).getAlg() == 'G'){
				Girvan[i] = solutions.get(i).getTime();
				Clique[i] = -1.0;
				Louvain[i] = -1.0;
			}
			else if(solutions.get(i).getAlg() == 'L'){
				Girvan[i] = -1.0;
				Clique[i] = solutions.get(i).getTime();
				Louvain[i] = -1.0;
			}
			else
			{
				Girvan[i] = -1.0;
				Clique[i] = -1.0;
				Louvain[i] = solutions.get(i).getTime();
			}
			
		}
		
		return result;
	}
	
	private static SongSolution getSolutionFromDate(String date)
	{
		for(SongSolution s : solutions) if(s.getId().equals(date)) return s;
		return null;
	}
	
	public static ArrayList<String> getCurrentSolutionsDates()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(SongSolution s : solutions) result.add( s.getId() );
		return result;
	}

	public static double getSolutionGenTime(String date)
	{
		SongSolution sol = getSolutionFromDate(date); 
		return sol == null ? 0.1 : sol.getTime();
	}

	public static char getSolutionAlgorithm(String date)
	{
		SongSolution sol = getSolutionFromDate(date); 
		return sol == null ? '-' : sol.getAlg();
	}
	
	public static int getSolutionNumberOfCommunities(String date)
	{
		SongSolution sol = getSolutionFromDate(date); 
		return sol == null ? 0 : sol.getNumCommunities();
	}
	
	public static ArrayList<SongSolution> getSolutions(String solutionsDir) throws Exception 
	{
		ArrayList<SongSolution> result = new ArrayList<SongSolution>();

		File baseDir = new File(solutionsDir);
		File[] solutions = baseDir.listFiles();

		for (File dir : solutions) 
		{
			// Legir el graph
			Graph<Song> graph = GraphManager.getGraph(dir.getPath() + "/entrada.txt");

			// Llegir les comunitats
			ArrayList<String> resultLines = FileManager.loadData(dir.getPath() + "/comunitats.txt");
			Solution solution = new Solution();
			Community com = new Community();

			for (String line : resultLines) 
			{
				if (line.equals("0")) continue;
				if (line.charAt(0) == '(') {
					String author = line.substring(1, line.indexOf(','));
					String title = line.substring(line.indexOf(',') + 1, line.indexOf(')'));
					com.addNode(new Song(author, title));
				} else {
					solution.addCommunity(com);
					com = new Community();
				}
			}
			if(com.getNumberOfNodes() > 0) solution.addCommunity(com);
			
			// Llegir info
			ArrayList<String> infoLines = FileManager.loadData(dir.getPath() + "/info.txt");
			String id = dir.getName().substring(dir.getName().indexOf("_") + 1);
			
			solution.setTime(Double.parseDouble(infoLines.get(2)));
			solution.setAlg(infoLines.get(0).charAt(0));
			solution.setId(id);
			
			result.add(new SongSolution(graph, solution));
		}

		return result;
	}

	public static void saveSolution(SongSolution s) throws IOException 
	{
		String id = s.getId();
		String filedir = "data/solutions/solution_" + id + "/";

		// arxiu de Graph(entrada)
		SolutionManager.saveEntradaSolution(filedir, s.getEntrada());

		// arxiu de solucio(communities)
		SolutionManager.saveCommunitiesSolution(filedir, s);

		// arxiu de info extra
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(String.valueOf(s.getAlg())); // Alg usat
		lines.add(String.valueOf(s.getEntrada().getAllNodes().size())); // Nombre de cancons processades
		lines.add(String.valueOf(s.getTime())); // Temps que ha tardat a generar la solucio
		FileManager.saveData(filedir + "info.txt", lines);
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

	public static void removeSolutionFromDisk(String date) throws IOException 
	{
		removeSolution("solution_" + date);
	}
	
	private static void removeSolution(String nomSolucio) throws IOException 
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

	public static ArrayList<ArrayList<String>> getSolutionCommunities(String solutionDate) 
	{
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		SongSolution s = getSolutionFromDate(solutionDate);
		if(s != null)
		{
			ArrayList<Community> communities = s.getCommunities();
			for(Community c : communities)
			{
				ArrayList<String> cStrings = new ArrayList<String>();
				for(Node n : c.getCommunity()) 	cStrings.add(n.getId());
				result.add(cStrings);
			}
		}
		return result;
	}
}

package Domini;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

public class SolutionManager 
{
	private static ArrayList<SongSolution> solutions = new ArrayList<SongSolution>();
	private static String lastGeneratedSolutionId = "";
	
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
	
	public static ArrayList<Pair<Double,Integer>> getInfos(String SolutionsDir,char algorisme) throws Exception
	{
		File baseDir = new File(SolutionsDir);
		File[] solutions = baseDir.listFiles();
		ArrayList<Pair<Double,Integer>> Dades = new ArrayList<Pair<Double,Integer>>(); 
		
		for (File dir : solutions) {
			ArrayList<String> infoLines = FileManager.loadData(dir.getPath() + "/info.txt");
			Pair<Double, Integer> dades = new Pair<Double,Integer>();
			dades.setFirst(Double.parseDouble(infoLines.get(2)));
			dades.setSecond(Integer.parseInt(infoLines.get(1)));
			
			if(infoLines.get(0).charAt(0) == algorisme){
				Dades.add(dades);
			}
		}
		

		Collections.sort(Dades, new Comparator<Pair<Double,Integer>>(){

			@Override
			public int compare(Pair<Double, Integer> arg0,
				Pair<Double, Integer> arg1) {				
					return arg0.getSecond() - arg1.getSecond();
				}			
			});
		
		
		ArrayList<Pair<Double,Integer>> sorted = new ArrayList<Pair<Double,Integer>>();
		if(Dades.size() > 1){
			
			Pair<Double,Integer> n = Dades.get(0);
			int count = 1;
			for(int i = 1; i < Dades.size(); i++){
				
				if(n.getSecond() == Dades.get(i).getSecond()){
					n.setFirst(n.getFirst() + Dades.get(i).getFirst());
					count++;
					
				}
				else{
					n.setFirst(n.getFirst()/count);
					sorted.add(n);
					count = 1;
					n = Dades.get(i);
				}
			}
			if(count != 1){
				n.setFirst(n.getFirst()/count);
				sorted.add(n);
			}
			else if(Dades.get(Dades.size()-1).getSecond() != Dades.get(Dades.size()-2).getSecond()) sorted.add(Dades.get(Dades.size()-1));
		}
		else sorted = Dades;
		
		return sorted;
	}
	
	private static SongSolution getSolutionFromDate(String date)
	{
		for(SongSolution s : solutions) { if(s.getId().equals(date)) return s; }
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
		SongSolution s = getSolutionFromDate(date);
		if(s != null) solutions.remove(s);
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

	public static void discardLastGeneratedSoution()
	{
		for(SongSolution s : solutions)
		{
			if( s.getId().equals(lastGeneratedSolutionId) )
			{
				lastGeneratedSolutionId = "";
				solutions.remove(s);
				return;
			}
		}
	}

	public static void setLastGeneratedSolution(SongSolution sol)
	{
		lastGeneratedSolutionId = sol.getId();
		addSolution(sol);
	}

	public static void removeSolutionList(String solutionId, int listIndex) 
	{
		SongSolution sol = getSolutionFromDate(solutionId);
		if(sol != null) {
			sol.removeCommunity( sol.getCommunities().get(listIndex) );
		}
	}

	public static void removeSolutionSong(String solutionId, String songAuthor, String songTitle) 
	{
		SongSolution sol = getSolutionFromDate(solutionId);
		if(sol == null) return;
		
		ArrayList<Community> communities = sol.getCommunities();
		for(Community c : communities)
		{
			for(Node n : c.getCommunity())
			{
				Song s = (Song)n;
				if(s.getAuthor().equals(songAuthor) && s.getTitle().equals(songTitle))
				{
					c.deleteNode(n.getId());
					return;
				}
			}
		}
	}

	public static void saveLastGeneratedSolution() throws Exception
	{
		SongSolution s = getSolutionFromDate(lastGeneratedSolutionId);
		saveSolution(s);
		lastGeneratedSolutionId = "";
	}

	public static void addSolution(SongSolution solution)
	{
		for(SongSolution s : solutions)
		{
			if(solution.getId().equals(s.getId())) return;
		}
		
		solutions.add(solution);
	}
	
	public static void importSolutions(String path) throws Exception
	{
		ArrayList<SongSolution> importedSolutions = getSolutions(path);
		for(SongSolution s : importedSolutions)
		{
			addSolution(s);
			saveSolution(s);
		}
	}

	public static Pair<ArrayList<Pair<String, ArrayList<Pair<String, Float>>>>, ArrayList<Pair<String, Integer>>> getSolutionStringGraphCommunities(String solutionId) 
	{
		Pair<ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > , ArrayList< Pair<String, Integer> >> result =
				new Pair<ArrayList< Pair< String, ArrayList< Pair<String, Float> > > >, ArrayList< Pair<String, Integer> >>();
		
		SongSolution s = getSolutionFromDate(solutionId);
		ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > stringGraph = GraphManager.getStringGraph(s.entrada);
		result.setFirst(stringGraph);
		
		ArrayList< Pair<String, Integer> > communities = new ArrayList< Pair<String, Integer> >(); 
		Set<Song> communitiedSongs = new HashSet<Song>();
		int cIndex = 0;
		for(Community c : s.getCommunities())
		{
			for(Node n : c.getCommunity())
			{
				Pair<String, Integer> stringNode = new Pair<String, Integer>(n.getId(), cIndex);
				communities.add(stringNode);
				communitiedSongs.add((Song)n);
			}
			
			++cIndex;
		}

		
		Graph<Song> g = s.getEntrada();
		Set<Song> allSongs = g.getAllNodes();
		for(Song song : allSongs)
		{
			if(!communitiedSongs.contains(song))
			{
				Pair<String, Integer> stringNode = new Pair<String, Integer>(song.getId(), -1);
				communities.add(stringNode);
			}
		}
			
		result.setSecond(communities);
		
		return result;
	}

	public static Pair<ArrayList<Pair<String, ArrayList<Pair<String, Float>>>>, ArrayList<Pair<String, Integer>>> getLastGeneratedSolutionStringGraphCommunities() 
	{
		return getSolutionStringGraphCommunities(lastGeneratedSolutionId);
	}

	public static void modifyLastGeneratedSolution(String songName, int from, int to) 
	{
		SongSolution s = getSolutionFromDate(lastGeneratedSolutionId);
		ArrayList<Community> communities = s.getCommunities();
		
		Song song = null;
		Community cFrom = communities.get(from);
		ArrayList<Node> nodes = cFrom.getCommunity();
		for(Node n : nodes)
		{
			if(n.getId().equals(songName))
			{
				song = (Song) n;
				break;
			}
		}

		if(song == null) return;
		
		Community cTo = communities.get(to);
		cTo.addNode((Node) song);
		cFrom.deleteNode(songName);
	}
}
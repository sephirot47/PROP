package Domini;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import Persistencia.FileManager;

public class GraphManager 
{
	public static Graph<Song> getGraph(String filepath) throws Exception
	{
		Graph<Song> g = new Graph<Song>();
		ArrayList<String> lines = FileManager.loadData(filepath);
		ArrayList<Song> songs = new ArrayList<Song>(); //2 be able to get the index of each node
		for(String line : lines)
		{
			if(line.charAt(0) == '(') //nodes (author,title)
			{
				String author = line.substring(1, line.indexOf(','));
				String title = line.substring(line.indexOf(',') + 1, line.indexOf(')'));
				Song s = new Song(author, title);
				g.addNode(s);
				songs.add(s);
			}
			else //edges
			{
				String fields[] = line.split(";");
				int index0 = Integer.parseInt(fields[0]), index1 = Integer.parseInt(fields[1]);
				float weight = Float.parseFloat(fields[2]);
				Edge e = new Edge();
				e.setWeight(weight);
				g.addEdge(songs.get(index0), songs.get(index1), e);	
			}
		}
		return g;
	}

	public static void generateEdges(Graph<Song> g, Ponderations p)
	{
		g.removeAllEdges();
		float threshold = (p.getThreshold()*0.1f)/2;
		
		Set<Song> songs = g.getAllNodes();
		//Set<User> users = UserManager.GetUsers("data/users/users.txt", "data/reproductions");
		for(Song s : songs)
		{
			for(Song s2 : songs)
			{
				if(s != s2)
				{
					float affinity = 0.0f;
					//		duration = year = style = userAge = nearbyReproductions = author;
					float authorAportation, styleAportation, durationAportation, yearAportation, userAgeAportation,  nearbyReproductionsAportation; //Entre 0 y 1
					authorAportation = styleAportation = durationAportation = yearAportation = userAgeAportation = nearbyReproductionsAportation = 0;
					
					//Autor
					if(s.getAuthor().equalsIgnoreCase(s2.getAuthor())) authorAportation = ((float) p.getAuthor()*0.1f);
					else authorAportation = 0.0f;
					//
					
					//Estils
					ArrayList<String> styles1 = s.getStyles(), styles2 = s2.getStyles();
					float sameStyles = 0;
					for(String st1 : styles1)
						for(String st2 : styles2)
							if(st1.equals(st2)) 
								sameStyles++;
							

					styleAportation = (sameStyles/styles1.size())*((float) p.getStyle()*0.1f); //0.0, 0.33, 0.66 o 1.0
					//
					
					//DurationAportation
					float durationDistance = Math.abs(s.getDuration() - s2.getDuration());
					durationAportation = (30.0f / durationDistance) * ((float) p.getDuration()*0.1f);
					if(durationAportation > 1.0f) durationAportation = p.getDuration()*0.1f;
					//
					
					//Year
					float yearDistance = Math.abs(s.getYear() - s2.getYear());
					yearAportation = (3.0f / yearDistance)*((float) p.getYear()*0.1f);
					if(yearAportation > 1.0f) yearAportation = p.getYear()*0.1f;
					//
					
					//User Ages
					float userAgeDistance = Math.abs(s.getMeanUserAge() - s2.getMeanUserAge());
					userAgeAportation = (5.0f / userAgeDistance)*((float) p.getUserAge()*0.1f);
					if(userAgeAportation > 1.0f) userAgeAportation = p.getUserAge()*0.1f;
					//
					
					//Nearby Reproductions 
					nearbyReproductionsAportation = (getNearbyReproductionsAportation(g, s,s2)) * ((float) p.getNearbyReproductions()*0.1f);
					
					affinity =  (authorAportation + styleAportation + durationAportation + yearAportation + userAgeAportation + nearbyReproductionsAportation)/(float) 6;
					
				
					Edge edge = new Edge();
					edge.setWeight(affinity);
					if(affinity >= threshold) g.addEdge(s, s2, edge);
				}
			}
		}
	}
	
	public static float getNearbyReproductionsAportation(Graph<Song> g, Song s1, Song s2) //Entre 0.0f y 1.0f
	{
		float aportation = 0.0f;
		try{
		Set<User> users = UserManager.getUsers("data/users/users.txt", "data/reproductions");
		for(User u : users)
		{
			boolean findit1 = false;
			boolean findit2 = false;
			
			Reproduction r1 = new Reproduction("buit","buit",0);
			Reproduction r2 = new Reproduction("buit","buit",0);
			ArrayList<Reproduction> repros = u.getReproductions();
			for(Reproduction r : repros)
			{				
				if(r.getSongAuthor().equals(s1.getAuthor()) && r.getSongTitle().equals(s1.getTitle())){
					findit1 = true;
					r1 = r;
					
				}
				if(r.getSongAuthor().equals(s2.getAuthor()) && r.getSongTitle().equals(s2.getTitle())){
					findit2 = true;
					r2 = r;
				}
				
			}
			if((findit1&&findit2)&&(Math.abs(r1.getTime() - r2.getTime()) <= 180000)){
				aportation += 1.0/(float) users.size();
			}
		}
		}
		catch (Exception e){}
		return aportation;
	}

	public static String generateSolution(char algorisme, int durationP, int yearP, int styleP, int publicP, int proximityP, int authorP) throws Exception
	{
		try { SongManager.loadSongsFromDisk(); } 
		catch(Exception e) { throw new Exception("No es troba el arxiu de cancons 'data/songs/songs.txt'"); }
		
		Set<Song> songs = SongManager.getSongs();
		Graph<Song> g = new Graph<Song>();
		for(Song s : songs) g.addNode(s);
		
		Ponderations p = new Ponderations();
		p.setDuration(durationP);
		p.setAuthor(authorP);
		p.setYear(yearP);
		p.setStyle(styleP);
		p.setUserAge(publicP);
		p.setNearbyReproductions(proximityP);
		
		generateEdges(g, p);
		
		Solution rawSol = null;
		if(algorisme == 'G') rawSol = new GirvanNewman().getSolution(g);
		else if(algorisme == 'C') rawSol = new Clique().getSolution(g);
		else rawSol = new Louvain().getSolution(g);
		
		SongSolution sol = new SongSolution(g, rawSol);
		sol.setId(new SimpleDateFormat("dd-MM-yyyy HH,mm,ss,SSS").format(new Date()));
		SolutionManager.setLastGeneratedSolution(sol);
		
		return sol.getId();
	}
}

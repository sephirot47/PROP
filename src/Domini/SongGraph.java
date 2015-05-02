package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import Persistencia.FileManager;
import Persistencia.FileParser;

public class SongGraph extends Graph<Song, SongRelation>
{
	public SongGraph()
	{
		super();
	}
	
	public void LoadSongs() throws IOException
	{
	}
	
	public void GenerateEdges(Ponderations p)
	{
		RemoveAllEdges();
		float threshold = (p.GetThreshold()*0.1f)/2;
		
		Set<Song> songs = GetAllNodes();
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
					if(s.GetAuthor().equalsIgnoreCase(s2.GetAuthor())) authorAportation = ((float) p.GetAuthor()*0.1f);
					else authorAportation = 0.0f;
					//
					
					//Estils
					ArrayList<String> styles1 = s.GetStyles(), styles2 = s2.GetStyles();
					float sameStyles = 0;
					for(String st1 : styles1)
						for(String st2 : styles2)
							if(st1.equals(st2)) 
								sameStyles++;
							

					styleAportation = (sameStyles/styles1.size())*((float) p.GetStyle()*0.1f); //0.0, 0.33, 0.66 o 1.0
					//
					
					//DurationAportation
					float durationDistance = Math.abs(s.GetDuration() - s2.GetDuration());
					durationAportation = (30.0f / durationDistance) * ((float) p.GetDuration()*0.1f);
					if(durationAportation > 1.0f) durationAportation = p.GetDuration()*0.1f;
					//
					
					//Year
					float yearDistance = Math.abs(s.GetYear() - s2.GetYear());
					yearAportation = (3.0f / yearDistance)*((float) p.GetYear()*0.1f);
					if(yearAportation > 1.0f) yearAportation = p.GetYear()*0.1f;
					//
					
					//User Ages
					float userAgeDistance = Math.abs(s.GetMeanUserAge() - s2.GetMeanUserAge());
					userAgeAportation = (5.0f / userAgeDistance)*((float) p.GetUserAge()*0.1f);
					if(userAgeAportation > 1.0f) userAgeAportation = p.GetUserAge()*0.1f;
					//
					
					//Nearby Reproductions 
					nearbyReproductionsAportation = (GetNearbyReproductionsAportation(s,s2)) * ((float) p.GetNearbyReproductions()*0.1f);
					
					affinity =  (authorAportation + styleAportation + durationAportation + yearAportation + userAgeAportation + nearbyReproductionsAportation)/(float) 6;
					
				
					SongRelation edge = new SongRelation();
					edge.SetWeight(affinity);
					if(affinity >= threshold) AddEdge(s, s2, edge);
				}
			}
		}
	}
	
	public float GetNearbyReproductionsAportation(Song s1, Song s2) //Entre 0.0f y 1.0f
	{
		float aportation = 0.0f;
		try{
		Set<User> users = UserManager.GetUsers("data/users/users.txt", "data/reproductions");
		for(User u : users)
		{
			boolean findit1 = false;
			boolean findit2 = false;
			
			Reproduction r1 = new Reproduction("buit","buit",0);
			Reproduction r2 = new Reproduction("buit","buit",0);
			ArrayList<Reproduction> repros = u.GetReproductions();
			for(Reproduction r : repros)
			{				
				if(r.GetSongAuthor().equals(s1.GetAuthor()) && r.GetSongTitle().equals(s1.GetTitle())){
					findit1 = true;
					r1 = r;
					
				}
				if(r.GetSongAuthor().equals(s2.GetAuthor()) && r.GetSongTitle().equals(s2.GetTitle())){
					findit2 = true;
					r2 = r;
				}
								
				/*{
					float minTimeBetweenReproductions = Float.POSITIVE_INFINITY; 
					for(Reproduction r2 : repros)
					{
						if(r == r2) continue;
						if(r2.GetSongAuthor().equals(s2.GetAuthor()) && r2.GetSongTitle().equals(s2.GetTitle()))
						{
							float t1 = r.GetTime(), t2 = r2.GetTime();
							float timeBetweenRepros = Math.abs(t1 - t2);
							if(timeBetweenRepros < minTimeBetweenReproductions)
								minTimeBetweenReproductions = timeBetweenRepros;
						}
					}
					++userCoincidences;
					userAportation += minTimeBetweenReproductions;
				}*/

			//if((findit1 && findit2)&&())
				
			}
			if((findit1&&findit2)&&(Math.abs(r1.GetTime() - r2.GetTime()) <= 180000)){
				aportation += 1.0/(float) users.size();
			}
		}
		}
		catch (Exception e){}
		return aportation;
	}
}

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
		float threshold = p.GetThreshold();
		
		Set<Song> songs = GetAllNodes();
		Set<User> users = UserManager.GetUsers();
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
					if(s.GetAuthor().equalsIgnoreCase(s2.GetAuthor())) authorAportation = 1.0f;
					else authorAportation = 0.0f;
					//
					
					//Estils
					ArrayList<String> styles1 = s.GetStyles(), styles2 = s2.GetStyles();
					float sameStyles = 0;
					for(String st1 : styles1)
						for(String st2 : styles2) 
							if(st1.equals(st2)) 
								sameStyles++;
					styleAportation = sameStyles/3.0f; //0.0, 0.33, 0.66 o 1.0
					//
					
					//DurationAportation
					float durationDistance = Math.abs(s.GetDuration() - s2.GetDuration());
					durationAportation = 30.0f / durationDistance;
					if(durationAportation > 1.0f) durationAportation = 1.0f;
					//
					
					//Year
					float yearDistance = Math.abs(s.GetYear() - s2.GetYear());
					yearAportation = 3.0f / yearDistance;
					if(yearAportation > 1.0f) yearAportation = 1.0f;
					//
					
					//User Ages
					float userAgeDistance = Math.abs(s.GetMeanUserAge() - s2.GetMeanUserAge());
					userAgeAportation = 5.0f / userAgeDistance;
					if(userAgeAportation > 1.0f) userAgeAportation = 1.0f;
					//
					
					//Nearby Reproductions (omg)
					
					//
					
					SongRelation edge = new SongRelation();
					edge.SetWeight(affinity);
					if(affinity >= threshold) AddEdge(s, s2, edge);
				}
			}
		}
	}
	
	private float GetNearbyReproductionsAportation(Song s1, Song s2) //Entre 0.0f y 1.0f
	{
		float aportation = 0.0f;
		int coincidences = 0;
		
		Set<User> users = UserManager.GetUsers();
		for(User u : users)
		{
			float userAportation = 0.0f;
			int userCoincidences = 0;
			ArrayList<Reproduction> repros = u.GetReproductions();
			for(Reproduction r : repros)
			{
				if(r.GetSongAuthor().equals(s1.GetAuthor()) && r.GetSongTitle().equals(s1.GetTitle()))
				{
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
				}
			}
			aportation += userAportation / userCoincidences;
			++coincidences;
		}
		return aportation / coincidences;
	}
}

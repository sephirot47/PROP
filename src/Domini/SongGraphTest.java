package Domini;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.TestCase;

public class SongGraphTest extends TestCase 
{
	public SongGraphTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void TestGenerateEdges()
	{
		Ponderations p = new Ponderations();
		SongGraph sg = new SongGraph();
		
		sg.GenerateEdges(p);
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
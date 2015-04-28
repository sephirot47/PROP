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
		ArrayList<Song> songs = FileParser.GetSongs("data/songs/songs.txt");
		for(Song s : songs)
		{
			AddNode(s);
		}
	}
	
	public void GenerateEdges(Ponderations p)
	{
		RemoveAllEdges();
		float threshold = p.GetThreshold();
		
		Set<Song> songs = GetAllNodes();
		for(Song s : songs)
		{
			for(Song s2 : songs)
			{
				if(s != s2)
				{
					float affinity = 0.0f;
					if(s.GetAuthor().equalsIgnoreCase(s2.GetAuthor())) affinity += p.GetAuthor();
					ArrayList<String> styles1 = s.GetStyles(), styles2 = s2.GetStyles();
					int sameStyles = 0;
					for(String st1 : styles1) for(String st2 : styles2) if(st1.equals(st2)) sameStyles++;
					
					
					SongRelation edge = new SongRelation();
					edge.SetWeight(affinity);
					if(affinity >= threshold) AddEdge(s, s2, edge);
				}
			}
		}
	}
}

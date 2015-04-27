package Domini;

public class SongGraph extends Graph<Song, SongRelation>
{
	public SongGraph()
	{
		super();
	}
	
	public void GenerateEdges()
	{
		RemoveAllEdges();
	}
}

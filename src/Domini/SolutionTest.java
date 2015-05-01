package Domini;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.TestCase;

public class SolutionTest extends TestCase 
{
	public SolutionTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	class N extends Node
	{
		public String GetId() { return "potato"; }
	}
	
	class E extends Edge
	{
		float weight;
		public float GetWeight() { return weight;}
		public void SetWeight(float weight) { this.weight = weight; }
	}
	
	/*public void testSetEntrada()
	{
		ArrayList<Set<Song>> songs = new ArrayList<Set<Song>>());
		Graph<N,E> g = new Graph<N,E>();
		Solution s = new Solution(g,"Girvan Newman",,0.01f)
	}
	
	public Solution(SongGraph entrada, String algorisme, ArrayList<Set<Song>> solution, float genTime)


	public void SetEntrada(SongGraph entrada) { this.entrada = entrada; }
	public void SetAlgorisme(String algorisme) { this.algorisme = algorisme; }
	public void SetSongCommunities(ArrayList<Set<Song>> songCommunities) { this.songCommunities = songCommunities; }
	public void SetGenerationTime(float generationTime) { this.generationTime = generationTime; }

	public String GetAlgorisme() { return algorisme; }
	public float GetGenerationTime() { return generationTime; }
	public SongGraph GetEntrada() { return entrada; }
	public ArrayList<Set<Song>> GetSongCommunities() { return songCommunities; }*/
}
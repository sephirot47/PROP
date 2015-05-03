package Domini;

import java.util.ArrayList;
import java.util.Set;

public class SongSolution extends Solution
{
	SongGraph graph;
	
	String algorisme;
	
	public SongSolution(SongGraph graph, String algorithm, ArrayList<Community> solution, float genTime)
	{
		this.graph = graph;
		this.algorithm = algorisme;
		communities = solution;
		time = genTime;
	}

	public void setGraph(SongGraph graph) { this.graph = graph; }
	public void setAlgorisme(String algorisme) { this.algorisme = algorisme; }
	public void setSongCommunities(ArrayList<Community> songCommunities) { this.communities = communities; }
	public void setGenerationTime(float generationTime) { this.time = generationTime; }

	public String getAlgorisme() { return algorisme; }
	public double getGenerationTime() { return time; }
	public SongGraph getEntrada() { return graph; }
	public ArrayList<Community> getSongCommunities() { return communities; }
	
	public void print()
	{
		System.out.println("Entrada: ---");
		graph.print();
		System.out.println("---");
		System.out.println("Sortida(comunitats): ---");
		int i = 0;
		for(Set<Song> ss : songCommunities)
		{
			System.out.println("Comunitat " + (++i));
			for(Song s : ss)
			{
				s.print();
				System.out.println("-");
			}
		}
		System.out.println("---");
		System.out.print("Algorisme: "); System.out.println("algorisme");
		System.out.print("Temps generacio: "); System.out.println("generationTime");
	}
}
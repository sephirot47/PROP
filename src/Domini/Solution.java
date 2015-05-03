package Domini;

import java.util.ArrayList;
import java.util.Set;

public class Solution
{
	SongGraph entrada;
	ArrayList<Set<Song>> songCommunities;
	
	String algorisme;
	float generationTime;
	
	public Solution(SongGraph entrada, String algorisme, ArrayList<Set<Song>> solution, float genTime)
	{
		this.entrada = entrada;
		this.algorisme = algorisme;
		songCommunities = solution;
		generationTime = genTime;
	}

	public void setEntrada(SongGraph entrada) { this.entrada = entrada; }
	public void setAlgorisme(String algorisme) { this.algorisme = algorisme; }
	public void setSongCommunities(ArrayList<Set<Song>> songCommunities) { this.songCommunities = songCommunities; }
	public void setGenerationTime(float generationTime) { this.generationTime = generationTime; }

	public String getAlgorisme() { return algorisme; }
	public float getGenerationTime() { return generationTime; }
	public SongGraph getEntrada() { return entrada; }
	public ArrayList<Set<Song>> getSongCommunities() { return songCommunities; }
	
	public void print()
	{
		System.out.println("Entrada: ---");
		entrada.print();
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

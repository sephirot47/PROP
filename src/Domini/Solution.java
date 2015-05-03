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

	public void SetEntrada(SongGraph entrada) { this.entrada = entrada; }
	public void SetAlgorisme(String algorisme) { this.algorisme = algorisme; }
	public void SetSongCommunities(ArrayList<Set<Song>> songCommunities) { this.songCommunities = songCommunities; }
	public void SetGenerationTime(float generationTime) { this.generationTime = generationTime; }

	public String GetAlgorisme() { return algorisme; }
	public float GetGenerationTime() { return generationTime; }
	public SongGraph GetEntrada() { return entrada; }
	public ArrayList<Set<Song>> GetSongCommunities() { return songCommunities; }
	
	public void Print()
	{
		System.out.println("Entrada: ---");
		entrada.Print();
		System.out.println("---");
		System.out.println("Sortida(comunitats): ---");
		int i = 0;
		for(Set<Song> ss : songCommunities)
		{
			System.out.println("Comunitat " + (++i));
			for(Song s : ss)
			{
				s.Print();
				System.out.println("-");
			}
		}
		System.out.println("---");
		System.out.print("Algorisme: "); System.out.println("algorisme");
		System.out.print("Temps generacio: "); System.out.println("generationTime");
	}
}

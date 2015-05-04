package Domini;

import java.util.ArrayList;
import java.util.Set;

public class SongSolution extends Solution
{
	SongGraph entrada;
	
	public SongSolution(SongGraph entrada, Solution solution,  double genTime, String algorisme, String id)
	{
		super(solution.getCommunities(), genTime, algorisme, id);
		this.entrada = entrada;
	}

	public void setEntrada(SongGraph entrada) { this.entrada = entrada; }
	public SongGraph getEntrada() { return entrada; }
	
	public void print()
	{
		System.out.println("Entrada: ---");
		entrada.print();
		System.out.println("---");
		System.out.println("Sortida(comunitats): ---");
		int i = 0;
		for(Community com : communities)
		{
			System.out.println("Comunitat " + (++i));
			for(Node n : com.getCommunity())
			{
				Song s = (Song)n;
				s.print();
				System.out.println("-");
			}
		}
		System.out.println("---");
		System.out.print("Algorisme: "); System.out.println(algorithm);
		System.out.print("Temps generacio: "); System.out.println(time);
	}
}

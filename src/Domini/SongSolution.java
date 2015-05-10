package Domini;

import java.util.ArrayList;
import java.util.Set;

public class SongSolution extends Solution
{
	Graph<Song> entrada;
	
	public SongSolution(Graph<Song> entrada, Solution solution,  double genTime, String algorisme, String id)
	{
		super(solution.getCommunities(), genTime, algorisme, id);
		this.entrada = entrada;
	}

	public void setEntrada(Graph<Song> entrada) { this.entrada = entrada; }
	public Graph<Song> getEntrada() { return entrada; }
}

package Domini;

import java.util.ArrayList;
import java.util.Set;

public class SongSolution extends Solution
{
	Graph<Song> entrada;
	
	public SongSolution(Graph<Song> entrada, Solution solution)
	{
		super();
		setId(solution.getId());
		setMemory(1);
		setAlg(solution.getAlg());
		setTime(solution.getTime());
		for(Community c : solution.getCommunities()) addCommunity(c);
		setEntrada(entrada);
	}

	public void setEntrada(Graph<Song> entrada) { this.entrada = entrada; }
	public Graph<Song> getEntrada() { return entrada; }
}

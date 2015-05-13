package Domini;

import java.util.ArrayList;
import java.util.Set;

public class SongSolution extends Solution
{
	Graph<Song> entrada;
	
	public SongSolution(Graph<Song> entrada, Solution solution,  double genTime, char algorisme)
	{
		super();
		for(Community c : solution.getCommunities()) addCommunity(c);
		setTime(genTime);
		setAlg(algorisme);
		
		this.entrada = entrada;
	}

	public void setEntrada(Graph<Song> entrada) { this.entrada = entrada; }
	public Graph<Song> getEntrada() { return entrada; }
}

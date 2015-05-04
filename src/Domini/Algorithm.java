package Domini;

public abstract class Algorithm 
{
	// Pre: true;
	// Post: generates a solution;
	public abstract Solution getSolution(Graph g);
	
	// Pre: true;
	// Post: generates a solution, with n communities at least;
	public abstract Solution getSolution(Graph g, int n);
}

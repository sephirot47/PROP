package Domini;

public class Pair<F, S> 
{
	private F f; 
	private S s;
	
	public Pair() {}
	public Pair(F first, S second) { f = first; s = second; }

	public void setFirst(F first) { f = first; }
	public void setSecond(S second) { s = second; }
	
	public F getFirst()  { return f; }
	public S getSecond() { return s; }
}

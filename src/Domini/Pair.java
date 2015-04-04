package Domini;

public class Pair<F, S> 
{
	private F f; 
	private S s;
	
	public Pair() {}
	public Pair(F first, S second) { f = first; s = second; }

	public void SetFirst(F first) { f = first; }
	public void SetSecond(S second) { s = second; }
	
	public F GetFirst()  { return f; }
	public S GetSecond() { return s; }
}

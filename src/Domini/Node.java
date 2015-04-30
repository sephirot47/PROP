package Domini;

public abstract class Node
{
	public abstract String GetId();
	public boolean equals(Object o) { return GetId().equals( ((Node)o).GetId() ); }
}

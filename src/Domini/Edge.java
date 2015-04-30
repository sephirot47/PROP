package Domini;

public abstract class Edge
{
	public abstract float GetWeight();
	public abstract void  SetWeight(float weight);
	public boolean equals(Object o) { return GetWeight() == ((Edge)o).GetWeight(); }
}

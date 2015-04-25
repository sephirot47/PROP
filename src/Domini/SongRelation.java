package Domini;

public class SongRelation extends Edge 
{
	float weight;
	public SongRelation() { weight = 0.0f; }
	public float GetWeight() { return weight; }
	public void  SetWeight(float w) { weight = w; }
}

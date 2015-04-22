package Domini;

import java.util.ArrayList;

public class User {
    
    String name;
    int age;
    ArrayList<Reproduction> reproductions;

	public User()
    {
    	reproductions = new ArrayList<Reproduction>();
    }
    
    public User(String a, int b, ArrayList<Reproduction> r)
    {
        name = a;
        age = b;
        reproductions = new ArrayList<Reproduction>();
        reproductions.addAll(r);
        
    }
    
    public void AddReproduction(Reproduction r)
    {
    	reproductions.add(r);
    }
    
    public void SetAge(int a) { age = a; }
    public void SetName(String n) { name = n; }
	public void SetReproductions(ArrayList<Reproduction> reproductions) 
	{
		this.reproductions = reproductions;
	}
	
    public int GetAge() { return age; }
    public String GetName() { return name; }
    public ArrayList<Reproduction> GetReproductions() 
    {
		return reproductions;
	}

}

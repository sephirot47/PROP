package Domini;

import java.util.ArrayList;
import java.util.List;

public class User 
{
	private static String[] forbiddenCharacters = { "\\", "/", ".", ";", ":", "\"", "*", "?",
													"<", ">", "|", "*"};
	
    String name;
    int age;
    ArrayList<Reproduction> reproductions;

	public User()
    {
    	reproductions = new ArrayList<Reproduction>();
    }
    
    public User(String name, int age, ArrayList<Reproduction> r) throws Exception
    {
    	checkUsername(name);
        this.name = name;
        this.age = age;
        reproductions = new ArrayList<Reproduction>();
        reproductions.addAll(r);
    }
    
    public User(String name, int age) throws Exception
    {
    	checkUsername(name);
        this.name = name;
        this.age = age;
        reproductions = new ArrayList<Reproduction>();
    }
    
    public void addReproduction(Reproduction r)
    {
    	reproductions.add(r);
    }
    
    public void addReproductions(List<Reproduction> r)
    {
    	reproductions.addAll(r);
    }
    
    public void setAge(int a) { age = a; }
    public void setName(String n) throws Exception
    { 
    	checkUsername(name);
    	name = n; 
    } 
	public void setReproductions(ArrayList<Reproduction> reproductions) 
	{
		this.reproductions = reproductions;
	}
	
    public int getAge() { return age; }
    public String getName() { return name; }
    public ArrayList<Reproduction> getReproductions() 
    {
		return reproductions;
	}
    
    
    private void checkUsername(String name) throws Exception
    {
    	for(String c : forbiddenCharacters)
		if(name.contains(c)) 
		{
			String characters = ""; 
			for(String c2 : forbiddenCharacters) 
				characters += "\"" + c2 + "\",  ";
			throw new Exception("A user can't contain any of these characters: " + characters);
		}
    }
    
    public boolean equals(Object obj)
    {
    	User u = (User) obj;
    	return getName().equals(u.getName()) && getAge() == u.getAge() && getReproductions().equals(u.getReproductions());
    }
}

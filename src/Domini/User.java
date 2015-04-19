import java.util.ArrayList;

public class User {
    
    String name;
    int age;
    ArrayList<structRerpduccions> lreproductions;
    
    public User(){
    	LlReproduccions = new ArrayList<structRerpduccions>();
    	
    }
    
    public User(String a, int b, ArraList<structReproduccions> repro)
    {
        name = a;
        age = b;
        lreproductions = new ArrayList<structRerpduccions>();
        lreproductions.add(repro);
        
    }
    
    public void AddAge(int a)
    {
        age = a;
    }
    
    public int ConsultAge()
    {
        return age;
    }
    
    public void AddReproduction(Song s, int time_repro){
    	structReproducton f;
    	f.Reproduction = s;
    	f.time = time_repro; 
    	LlReproduccions.add(f);
    }
}

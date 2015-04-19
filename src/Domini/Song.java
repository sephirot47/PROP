import java.util.ArrayList;

public class Song{
	
	String author;
	String title;
	int year;
	ArrayList<String> styles;
	int duration; //en segons
	
	public Song(){
		//inicialitzar Estils
		styles = new ArrayList<String>();
		
	}
	
	public Song(String at, String ti, int any, ArrayList<String> est, int dur)
	{
		styles = new ArrayList<String>();
		author = at;
		title = ti;
		styles.add(est);
		duration = dur;
	}	
	
	public void AddSong(String at, String ti, int any, ArrayList<String> est, int dur)
	{
		author = at;
		title = ti;
		styles.add(est);
		duration = dur;
	}
	
	public void AddAuthorTitle(String at, String ti){
		author= at;
		title = ti;
	}
	
	public int AddStyles(ArrayList<String> est)
	{
		int error = 0;
		if((styles.size() + est.size()) <= 3){
					Estils.add(est);
		}
		else{
			//retornar error
			error = -1;
		}
		return error;
	}
	
	public int AddDuration(int dur)
	{
		duration = dur;
	}
	
}

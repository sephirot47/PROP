package Domini;

import java.util.ArrayList;

public class Song
{	
	String author;
	String title;
	int year;
	int duration; //en segons
	ArrayList<String> styles;
	
	public Song()
	{
		styles = new ArrayList<String>();
		year = duration = 0;
		title = "";
		author = "";
	}
	
	public Song(String at, String ti, int y, ArrayList<String> est, int dur)
	{
		styles = new ArrayList<String>();
		author = at;
		title = ti;
		styles.addAll(est);
		duration = dur;
		year = y;
	}	
	
	public void SetSong(String at, String ti, int y, ArrayList<String> est, int dur)
	{
		author = at;
		title = ti;
		styles.addAll(est);
		duration = dur;
		year = y;
	}
	
	public void SetAuthorTitle(String at, String ti)
	{
		author= at;
		title = ti;
	}
	
	public int AddStyles(ArrayList<String> est)
	{
		int error = 0;
		if((styles.size() + est.size()) <= 3)
		{
			styles.addAll(est);
		}
		else
		{
			//retornar error
			error = -1;
		}
		return error;
	}
	
	public void SetDuration(int dur)
	{
		duration = dur;
	}
	
	public String GetAuthor() { return author; }
	public String GetTitle() { return author; }
	public int GetDuration() { return duration; }
	public int GetYear() { return year; }
	public ArrayList<String> GetStyles() { return styles; }
}

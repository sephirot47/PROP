package Domini;

import java.util.ArrayList;
import java.util.Set;

public class Song extends Node
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
	
	public Song(String author, String title)
	{
		this.author = author;
		this.title = title;
	}	
	
	public Song(String author, String title, int year, ArrayList<String> styles, int duration)
	{
		this.styles = new ArrayList<String>();
		this.author = author;
		this.title = title;
		this.styles.addAll(styles);
		this.duration = duration;
		this.year = year;
	}	
	
	public void SetSong(String author, String title, int year, ArrayList<String> styles, int duration)
	{
		this.author = author;
		this.title = title;
		this.styles.addAll(styles);
		this.duration = duration;
		this.year = year;
	}
	
	public void SetAuthorTitle(String author, String title)
	{
		this.author = author;
		this.title = title;
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
	public String GetTitle() { return title; }
	public int GetDuration() { return duration; }
	public int GetYear() { return year; }
	public ArrayList<String> GetStyles() { return styles; }
	
	public void Print()
	{
    	System.out.println("------");
		System.out.println("Author: " + author);
		System.out.println("Title: " + title);
		System.out.println("Duration: " + duration);
		System.out.println("Year: " + year);
		for(int i = 0; i < styles.size(); ++i) 
			System.out.println("Style " + i + ": " + styles.get(i));
    	System.out.println("------");
	}
	
	/*
	 * Returns the mean of the age of all the users who have listened to this song
	 */
	public float GetMeanUserAge()
	{
		float sum = 0.0f;
		int usersWhoHaveListenedToThisAwesomeOneHourSong = 0;
		
		Set<User> users = UserManager.GetUsers("data/users/users.txt", "data/reproductions");
		for(User u : users)
		{
			ArrayList<Reproduction> repros = u.GetReproductions();
			for(Reproduction r : repros)
			{
				if(r.GetSongAuthor().equals(author) && r.GetSongTitle().equals(title))
				{
					sum += u.GetAge();
					++usersWhoHaveListenedToThisAwesomeOneHourSong;
					break; //Nomes suma una vegada per user
				}
			}
		}
		
		return sum / usersWhoHaveListenedToThisAwesomeOneHourSong;
	}

	@Override
	public String GetId() 
	{
		return author + ", " + title;
	}
	
	public boolean Equals(Song s2)
	{
		return GetAuthor().equals(s2.GetAuthor()) && GetTitle().equals(s2.GetTitle()) &&
			   GetId().equals(s2.GetId()) && GetYear() == s2.GetYear() && GetDuration() == s2.GetDuration() &&
			   GetStyles().containsAll(s2.GetStyles()) && GetStyles().size() == s2.GetStyles().size();
	}
}

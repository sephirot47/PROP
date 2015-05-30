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
	
	public Song(String author, String title) throws Exception
	{
		this.styles = new ArrayList<String>();
		checkString(author); checkString(title);
		this.author = author;
		this.title = title;
	}
	
	public Song(String author, String title, int year, ArrayList<String> styles, int duration) throws Exception
	{
		checkString(author); checkString(title);
		for(String style : styles) checkString(style);
		
		this.styles = new ArrayList<String>();
		this.author = author;
		this.title = title;
		this.styles.addAll(styles);
		
		this.duration = duration;
		this.year = year;
	}	
	
	public void setSong(String author, String title, int year, ArrayList<String> styles, int duration) throws Exception
	{
		checkString(author); checkString(title);
		for(String style : styles) checkString(style);
		
		this.author = author;
		this.title = title;
		this.styles.addAll(styles);
		
		this.duration = duration;
		this.year = year;
	}
	
	public void setAuthorTitle(String author, String title) throws Exception
	{
		checkString(author); checkString(title);
		this.author = author;
		this.title = title;
	}

	public void setStyles(ArrayList<String> est) throws Exception
	{
		for(String style : est) checkString(style);
		
		styles.clear();
		if((styles.size() + est.size()) <= 3)
		{
			styles.addAll(est);
		}
		else
		{
			throw new Exception("No es poden afegir mes de 3 estils a una canco");
		}
	}
	
	public void addStyles(ArrayList<String> est) throws Exception
	{
		for(String style : est) checkString(style);
		
		if((styles.size() + est.size()) <= 3)
		{
			styles.addAll(est);
		}
		else
		{
			throw new Exception("No es poden afegir mes de 3 estils a una canco");
		}
	}

	public void setYear(int y)
	{
		year = y;
	}
	
	public void setDuration(int dur)
	{
		duration = dur;
	}
	
	public String getAuthor() { return author; }
	public String getTitle() { return title; }
	public int getDuration() { return duration; }
	public int getYear() { return year; }
	public ArrayList<String> getStyles() { return styles; }
	
	/*
	 * Returns the mean of the age of all the users who have listened to this song
	 */
	public float getMeanUserAge()
	{
		float sum = 0.0f;
		int numUsers = 0;
		
		Set<User> users = null;
		try 
		{
			users = UserManager.getUsers();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		for(User u : users)
		{
			ArrayList<Reproduction> repros = u.getReproductions();
			System.out.println(u.getName() + " ~  " + repros.size());
			for(Reproduction r : repros)
			{
				System.out.println(r.getSongAuthor()  + "==" + author + ", " + r.getSongTitle() + "==" + title);
				if(r.getSongAuthor().equals(author) && r.getSongTitle().equals(title))
				{
					sum += u.getAge();
					++numUsers;
					break; //Nomes suma una vegada per user
				}
			}
		}
		
		return numUsers == 0 ? -1.0f : sum / numUsers ;
	}
	
	private void checkString(String str) throws Exception
	{
		if(str.contains(";") || str.contains(",")) 
			throw new Exception("Les dades d'una canco no poden contenir els caracters \" ; \" ni \" , \" ");
	}

	@Override
	public String getId() 
	{
		return author + ", " + title;
	}
	
	public boolean equals(Object obj)
	{
		Song s2 = (Song) obj;
		return getAuthor().equals(s2.getAuthor()) && getTitle().equals(s2.getTitle()) &&
			   getId().equals(s2.getId()) && getYear() == s2.getYear() && getDuration() == s2.getDuration() &&
			   getStyles().containsAll(s2.getStyles()) && s2.getStyles().containsAll(getStyles()); //No importa l'ordre dels styles
	}
}

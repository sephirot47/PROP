package Domini;

import java.util.Calendar;
import java.util.TimeZone;

public class Reproduction
{
	private String songAuthor, songTitle;
	private long time; //en segons
	
	public Reproduction(String author,String title) throws Exception
	{
		checkString(author); checkString(title);
		songAuthor = author;
		songTitle = title;
		
		long milis = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
		this.time = milis;
	}
	
	public Reproduction(String author,String title, long time) throws Exception
	{
		checkString(author); checkString(title);
		songAuthor = author;
		songTitle = title;
		this.time = time;
	}
	
	public void print()
	{
		System.out.println(songAuthor);
		System.out.println(songTitle);
		System.out.println(time);
	}
	
	public String getSongAuthor() {
		return songAuthor;
	}

	public void setSongAuthor(String songAuthor) throws Exception {
		checkString(songAuthor);
		this.songAuthor = songAuthor;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) throws Exception {
		checkString(songTitle);
		this.songTitle = songTitle;
	}

	public long getTime() 
	{
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	private void checkString(String str) throws Exception
	{
		if(str.contains(";")) throw new Exception("Song strings can't contain the \" ; \" character (\'" + 
												  str + "\')");
	}

	public boolean equals(Object obj) 
	{
		Reproduction r = (Reproduction) obj;
		return getTime() == r.getTime() && 
			   getSongAuthor().equals(r.getSongAuthor()) &&
			   getSongTitle().equals(r.getSongTitle());
	}
}

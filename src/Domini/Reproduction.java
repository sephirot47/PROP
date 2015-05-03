package Domini;

import java.util.Calendar;
import java.util.TimeZone;

public class Reproduction
{
	private String songAuthor, songTitle;
	private long time; //en segons
	
	public Reproduction(String author,String title) throws Exception
	{
		CheckString(author); CheckString(title);
		songAuthor = author;
		songTitle = title;
		
		long milis = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
		this.time = milis;
	}
	
	public Reproduction(String author,String title, long time) throws Exception
	{
		CheckString(author); CheckString(title);
		songAuthor = author;
		songTitle = title;
		this.time = time;
	}
	
	public void Print()
	{
		System.out.println(songAuthor);
		System.out.println(songTitle);
		System.out.println(time);
	}
	
	public String GetSongAuthor() {
		return songAuthor;
	}

	public void SetSongAuthor(String songAuthor) throws Exception {
		CheckString(songAuthor);
		this.songAuthor = songAuthor;
	}

	public String GetSongTitle() {
		return songTitle;
	}

	public void SetSongTitle(String songTitle) throws Exception {
		CheckString(songTitle);
		this.songTitle = songTitle;
	}

	public long GetTime() 
	{
		return time;
	}

	public void SetTime(int time) {
		this.time = time;
	}

	private void CheckString(String str) throws Exception
	{
		if(str.contains(";")) throw new Exception("Song strings can't contain the \" ; \" character (\'" + 
												  str + "\')");
	}

	public boolean equals(Object obj) 
	{
		Reproduction r = (Reproduction) obj;
		return GetTime() == r.GetTime() && 
			   GetSongAuthor().equals(r.GetSongAuthor()) &&
			   GetSongTitle().equals(r.GetSongTitle());
	}
}

package Domini;

public class Reproduction
{
	private String songAuthor, songTitle;
	private int time; //en segons
	
	public Reproduction(String author,String title, int time)
	{
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

	public void SetSongAuthor(String songAuthor) {
		this.songAuthor = songAuthor;
	}

	public String GetSongTitle() {
		return songTitle;
	}

	public void SetSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public int GetTime() {
		return time;
	}

	public void SetTime(int time) {
		this.time = time;
	}
	
	public boolean Equals(Reproduction r) 
	{
		return GetTime() == r.GetTime() && 
			   GetSongAuthor().equals(r.GetSongAuthor()) &&
			   GetSongTitle().equals(r.GetSongTitle());
	}


}

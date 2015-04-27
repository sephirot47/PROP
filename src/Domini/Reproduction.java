package Domini;

public class Reproduction
{
	String songAuthor, songTitle;
	int time; //en segons
	
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
}

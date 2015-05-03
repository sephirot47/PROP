package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Persistencia.FileManager;

public class TurmoDriver 
{

	static Scanner sc;
	
	public static void pl(String msg)
	{
		System.out.println(msg);
	}
	
	public static void p(String msg)
	{
		System.out.print(msg);
	}
	
	public static void main(String[] args)
	{
		sc = new Scanner(System.in);
		while(true)
		{
		String[] options = {"Afegir Song a arxiu", 
							"Llegir arxiu de Songs",
							"Borrar Song de arxiu",
							"Afegir User a arxiu",
							"Llegir arxiu de Users",
							"Borar User de arxiu"};
		
		pl("--------------- Menu de test ----------------");
		pl("");
		int i = 0;
		for(String option : options) pl((++i) + ": " + option);
		pl("");
		p("Introdueixi el numero del test que vol provar: ");
		int option = sc.nextInt();
		
		switch(option)
		{
		case 1: TestAddSongToFile(); break;
		case 2: TestReadSongFile(); break;
		case 3: TestRemoveSongOfFile(); break;
		case 4: TestAddUserToFile(); break;
		case 5: TestReadUserFile(); break;
		}
		
		}
	}
	
	public static void TestAddSongToFile()
	{
		String author, title;
		ArrayList<String> styles = new ArrayList<String>();
		int year, duration;
		
		pl(""); pl("");
		pl("TEST: Afegir/Modificar Song a arxiu (si vol modificar la canco, posi el mateix autor i titol de la canco que vol modificar)");
		pl("");pl("");
		String filepath; 
		p("Introdueixi el arxiu on vol afegir/modificar la canco: ");  filepath = sc.next(); pl("");
		
		p("Introdueixi un autor per la canço: ");  author = sc.next();
		p("Introdueixi un titol per la canço: ");  title = sc.next();
		p("Introdueixi el primer estil de la canco ( \"-\" si no vol cap ): ");  styles.add(sc.next());
		p("Introdueixi el segon estil de la canco ( \"-\" si no vol cap ): ");  styles.add(sc.next());
		p("Introdueixi el tercer estil de la canco ( \"-\" si no vol cap ): ");  styles.add(sc.next());
		p("Introdueixi l'any de la canco: ");  year = sc.nextInt();
		p("Introdueixi la duracio en segons de la canco: ");  duration = sc.nextInt();
		
		pl(""); pl("Creant o modificant canco...");
		
		Song s = null;
		try
		{
			s = new Song(author, title, year, styles, duration);
		}
		catch(Exception e)
		{
			pl("Alguna dada entrada no es valida!!! ");
			e.printStackTrace();
		}
		
		try
		{
			FileManager.SaveSong(filepath, s);
		}
		catch(IOException e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}
		
		
		pl("Canco creada amb exit!!!!");
	}
	
	public static void TestReadSongFile()
	{
		pl(""); pl("");
		pl("TEST: Llegir Song de arxiu");
		
		String filepath; 
		p("Introdueixi el arxiu d'on vol llegir les cancons: ");  filepath = sc.next(); pl("");
		
		Set<Song> songs = null;
		try
		{
			songs = SongManager.GetSongs(filepath);
		}
		catch(Exception e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}

		pl("*********** Cancons llegides ***********");
		int i = 0;
		for(Song s : songs)
		{
			pl("Canco " + (++i));
			s.Print();
			pl("");
		}
		pl("***************************************");
		pl("");
	}
	
	public static void TestRemoveSongOfFile()
	{
		pl(""); pl("");
		pl("TEST: Borrar Song de arxiu");
		
		String filepath; 
		p("Introdueixi el arxiu d'on vol borrar una canco: ");  filepath = sc.next(); pl("");
		
		Set<Song> songs = null;
		try
		{
			songs = SongManager.GetSongs(filepath);
		}
		catch(Exception e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}
		
		String author, title;
		p("Introdueixi un autor per la canço: ");  author = sc.next();
		p("Introdueixi un titol per la canço: ");  title = sc.next();
		
		try 
		{
			FileManager.RemoveSong(filepath, author, title);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		pl("La canco amb autor \"" + author + 
		   "\" i amb titol \"" + title + 
		   "\" ja no existeix al arxiu \"" + filepath + "\""); pl("");
	}
	
	public static void TestAddUserToFile()
	{
		
	}
	
	public static void TestReadUserFile()
	{
		
	}
}

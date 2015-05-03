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
							"Borrar User de arxiu"};
		
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
		case 6: TestRemoveUserOfFile(); break;
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
		
		
		pl("Canco creada/modificada amb exit!!!!");
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
		
		String author, title;
		p("Introdueixi el autor de la canço que vol borrar: ");  author = sc.next();
		p("Introdueixi el titol de la canço que vol borrar: ");  title = sc.next();
		
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
		String username;
		int age;
		
		pl(""); pl("");
		pl("TEST: Afegir/Modificar User a arxiu (si vol modificar el user, posi el mateix nom de user)");
		pl("");pl("");
		
		String filepath; 
		p("Introdueixi el arxiu on vol afegir/modificar el user: ");  filepath = sc.next(); pl("");
		
		p("Introdueixi el nom del usuari: ");  username = sc.next();
		p("Introdueixi la edat del usuari: ");  age = sc.nextInt();
		
		pl(""); pl("Creant o modificant user...");
		
		User u = null;
		try
		{
			u = new User(username, age);
		}
		catch(Exception e)
		{
			pl("Alguna dada entrada no es valida!!! ");
			e.printStackTrace();
		}
		
		try
		{
			FileManager.SaveUser(filepath, u);
		}
		catch(IOException e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}
		
		
		pl("User creat/modificat amb exit!!!!");
	}
	
	public static void TestReadUserFile()
	{
		pl(""); pl("");
		pl("TEST: Llegir User de arxiu");
		
		String filepath, reprosFilepath; 
		p("Introdueixi el arxiu d'on vol llegir els users: ");  filepath = sc.next(); pl("");
		p("Introdueixi el DIRECTORI on es troben les reproduccions dels users(\"-\" si no vol tenirles en compte): ");  
		reprosFilepath = sc.next(); pl("");
		
		Set<User> users = null;
		try
		{
			users = UserManager.GetUsers(filepath, reprosFilepath.equals("-") ? "tests/" : reprosFilepath);
		}
		catch(Exception e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}

		pl("*********** Users llegits ***********");
		int i = 0;
		for(User u : users)
		{
			pl("User " + (++i));
			u.Print();
			pl("");
		}
		pl("***************************************");
		pl("");
	}
	
	public static void TestRemoveUserOfFile()
	{
		pl(""); pl("");
		pl("TEST: Borrar User de arxiu");
		
		String filepath; 
		p("Introdueixi el arxiu d'on vol borrar un user: ");  filepath = sc.next(); pl("");

		String username;
		p("Introdueixi el username del user que vol borrar: ");  username = sc.next();
		
		try 
		{
			FileManager.RemoveUser(filepath, username);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		pl("El user amb nom \"" + username +  "\" ja no existeix al arxiu \"" + filepath + "\""); pl("");
	}
}

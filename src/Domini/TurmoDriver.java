package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

import Persistencia.FileManager;
import Persistencia.FileParser;
import Persistencia.History;

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
							"Borrar User de arxiu",
							"Afegir Reproduccio a User",
							"Llegir Reproduccions de User",
							"Llegir carpeta de Solucions"};
		
		pl("--------------- Menu de test ----------------");
		pl("");
		int i = 0;
		for(String option : options) pl((++i) + ": " + option);
		pl("");
		p("Introdueixi el numero del test que vol provar: ");
		int option = sc.nextInt();
		
		switch(option)
		{
		case 1: testAddSongToFile(); break;
		case 2: testReadSongFile(); break;
		case 3: testRemoveSongOfFile(); break;
		
		case 4: testAddUserToFile(); break;
		case 5: testReadUserFile(); break;
		case 6: testRemoveUserOfFile(); break;
		
		case 7: testAddReproductionToFile(); break;
		case 8: testReadReproductions(); break;
		
		case 9: testReadSolutions(); break;
		}
		
		}
	}
	
	public static void testAddSongToFile()
	{
		String author, title;
		ArrayList<String> styles = new ArrayList<String>();
		int year, duration;
		
		pl(""); pl("");
		pl("TEST: Afegir/Modificar Song a arxiu (si vol modificar la canco, posi el mateix autor i titol de la canco que vol modificar)");
		pl("");pl("");
		String filepath; 
		p("Introdueixi el arxiu on vol afegir/modificar la canco: ");  filepath = sc.next(); pl("");
		
		p("Introdueixi un autor per la can�o: ");  author = sc.next();
		p("Introdueixi un titol per la can�o: ");  title = sc.next();
		p("Introdueixi el primer estil de la canco ( \"-\" si no en vol cap ): ");  styles.add(sc.next());
		p("Introdueixi el segon estil de la canco ( \"-\" si no en vol cap ): ");  styles.add(sc.next());
		p("Introdueixi el tercer estil de la canco ( \"-\" si no en vol cap ): ");  styles.add(sc.next());
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
			FileManager.saveSong(filepath, s);
		}
		catch(IOException e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}
		
		
		pl("Canco creada/modificada amb exit!!!!");
	}
	
	public static void testReadSongFile()
	{
		pl(""); pl("");
		pl("TEST: Llegir Song de arxiu");
		
		String filepath; 
		p("Introdueixi el arxiu d'on vol llegir les cancons: ");  filepath = sc.next(); pl("");
		
		Set<Song> songs = null;
		try
		{
			songs = SongManager.getSongs(filepath);
		}
		catch(Exception e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}

		pl("::::::::::: Cancons llegides :::::::::::");
		int i = 0;
		for(Song s : songs)
		{
			pl("Canco " + (++i));
			s.print();
			pl("");
		}
		pl(":::::::::::::::::::::::::::::::::::::::::");
		pl("");
	}
	
	public static void testRemoveSongOfFile()
	{
		pl(""); pl("");
		pl("TEST: Borrar Song de arxiu");
		
		String filepath; 
		p("Introdueixi el arxiu d'on vol borrar una canco: ");  filepath = sc.next(); pl("");
		
		String author, title;
		p("Introdueixi el autor de la can�o que vol borrar: ");  author = sc.next();
		p("Introdueixi el titol de la can�o que vol borrar: ");  title = sc.next();
		
		try 
		{
			FileManager.removeSong(filepath, author, title);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		pl("La canco amb autor \"" + author + 
		   "\" i amb titol \"" + title + 
		   "\" ja no existeix al arxiu \"" + filepath + "\""); pl("");
	}
	
	public static void testAddUserToFile()
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
			FileManager.saveUser(filepath, u);
		}
		catch(IOException e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}
		
		
		pl("User creat/modificat amb exit!!!!");
	}
	
	public static void testReadUserFile()
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
			users = UserManager.getUsers(filepath, reprosFilepath.equals("-") ? "tests/" : reprosFilepath);
		}
		catch(Exception e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}

		pl("::::::::::: Users llegits :::::::::::");
		int i = 0;
		for(User u : users)
		{
			pl("User " + (++i));
			u.print();
			pl("");
		}
		pl(":::::::::::::::::::::::::::::::::::::");
		pl("");
	}
	
	public static void testRemoveUserOfFile()
	{
		pl(""); pl("");
		pl("TEST: Borrar User de arxiu");
		
		String filepath; 
		p("Introdueixi el arxiu d'on vol borrar un user: ");  filepath = sc.next(); pl("");

		String username;
		p("Introdueixi el username del user que vol borrar: ");  username = sc.next();
		
		try 
		{
			FileManager.removeUser(filepath, username);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		pl("El user amb nom \"" + username +  "\" ja no existeix al arxiu \"" + filepath + "\""); pl("");
	}
	
	public static void testAddReproductionToFile()
	{
		String username;
		
		pl(""); pl("");
		pl("TEST: Afegir Reproduccio a user");
		pl("");pl("");
				
		String filepath; 
		p("Introdueixi el nom del DIRECTORI on vol guardar la reproduccio: ");  filepath = sc.next(); pl("");
		p("Introdueixi el nom del user al qual vol afegir una reproduccio: ");  username = sc.next(); pl("");
		
		String songAuthor, songTitle;
		p("Introdueixi el nom del autor de la reproduccio: "); songAuthor = sc.next(); pl("");
		p("Introdueixi el nom del autor de la reproduccio: "); songTitle = sc.next(); pl("");

		
		pl(""); pl("Afegint reproduccio a user " + username + "...");
		
		Reproduction r = null;
		try
		{
			long milis = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000L;
			r = new Reproduction(songAuthor, songTitle, milis);
		}
		catch(Exception e)
		{
			pl("Alguna dada entrada no es valida!!! ");
			e.printStackTrace();
		}
		
		try
		{
			FileManager.saveReproduction(filepath + "/" + username + "Reproductions.txt", r);
		}
		catch(IOException e)
		{
			pl("Sembla que hi ha algun problema amb el arxiu que ha entrat!");
			e.printStackTrace();
		}
		
		
		pl("Reproduccio afegida amb exit!!!!");
	}
	
	public static void testReadReproductions()
	{
		String username;
		
		pl(""); pl("");
		pl("TEST: Llegir Reproduccions de user");
		pl("");pl("");
				
		String filepath; 
		p("Introdueixi el nom del DIRECTORI on esta guardada llegir les reproduccions: ");  filepath = sc.next(); pl("");
		p("Introdueixi el nom del user del que vol llegir una reproduccio: ");  username = sc.next(); pl("");
		
		User user = null;
		try
		{
			user = new User(username, 0);
		}
		catch(Exception e)
		{
			pl("Sembla que hi ha algun problema amb nom del user que ha entrat!");
			e.printStackTrace();
		}
		
		ArrayList<Reproduction> userReproductions = new ArrayList<Reproduction>();
		try //Si no existeix el seu arxiu de repros pues no te repros
		{ 
			//Afegim al user llegit les reproduccions corresponents
			String reproductionsFilepath = filepath + "/" + user.getName() + "Reproductions.txt";
			userReproductions = FileParser.getReproductions(reproductionsFilepath);
		}
		catch(Exception e){}

		int i = 0;
		pl(":::::::::::::::::::::::::::::::::::::::");
		pl("Number of reproductions: " + userReproductions.size() );
		pl("");
		for(Reproduction r : userReproductions)
		{
			pl("Reproduction " + (++i) + ":");
			r.print();
			pl("");
		}
		pl(":::::::::::::::::::::::::::::::::::::::");
		pl("");
	}
	
	public static void testReadSolutions()
	{
		pl(""); pl("");
		pl("TEST: Llegir Solucions:");
		
		String filepath; 
		p("Introdueixi el DIRECTORI on son les carpetes de les solucions: ");  filepath = sc.next(); pl("");
		
		ArrayList<Solution> solutions = null;
		try 
		{
			solutions = History.getSolutions(filepath);
		} 
		catch (Exception e) 
		{

			e.printStackTrace();
		}
		
		pl("");
		pl("Solucions llegides:");
		pl("::::::::::: Solucions llegits :::::::::::");
		pl(":::::::::::::::::::::::::::::::::::::::::");
		pl(":::::::::::::::::::::::::::::::::::::::::");
		int i = 0;
		for(Solution s : solutions)
		{
			pl("");
			pl(":::::: Solucio " + (++i) + " :::::::");
			pl(":::::::::::::::::::::::::::");
			s.print();
			pl(":::::::::::::::::::::::::::");
			pl("");
		}
		pl(":::::::::::::::::::::::::::::::::::::::::");
		pl(":::::::::::::::::::::::::::::::::::::::::");
		pl("");
	}
}

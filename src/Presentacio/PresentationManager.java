package Presentacio;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.plaf.FileChooserUI;

import Domini.Graph;
import Domini.GraphManager;
import Domini.Pair;
import Domini.SolutionManager;
import Domini.Song;
import Domini.SongManager;
import Domini.SongSolution;
import Domini.User;
import Domini.UserManager;

public class PresentationManager 
{
	private static Stack<String> windowHistory;
	private static MainWindow window;
	
	public static void main(String[] args) 
	{
		windowHistory = new Stack<String>();

		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					window = new MainWindow();
					window.frmYoutube.setVisible(true);
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void initialize()
	{
		PresentationManager.goToCard(MainPanel.class.getSimpleName());
		PresentationManager.loadUsersFromDisk();
		PresentationManager.loadSongsFromDisk();
		PresentationManager.loadSolutionsFromDisk();
	}
	
	
	// SOLUTIONS /////////////////////////

	public static ArrayList<Pair<Double,Integer>> getInfos(char algorisme) throws Exception
	{
		return SolutionManager.getInfos("data/solutions",algorisme);
	}
	
	public static ArrayList<String> getSolutionDates()
	{
		return SolutionManager.getCurrentSolutionsDates();
	}
	
	public static void loadSolutionsFromDisk()
	{
		try 
		{
			SolutionManager.loadSolutionsFromDisk();
		} catch (Exception e)
		{
			errorWindow(e.getMessage());
		}
	}

	
	public static void removeSolutionFromDisk(String date)
	{
		try 
		{
			SolutionManager.removeSolutionFromDisk(date);
		} catch (Exception e)
		{
			errorWindow(e.getMessage());
		}
	}
	
	public static double getSolutionGenTime(String date)
	{
		date = date.replaceAll(":", ",");
		return SolutionManager.getSolutionGenTime(date);
	}

	public static String getSolutionAlgorithm(String date)
	{
		date = date.replaceAll(":", ",");
		char alg =  SolutionManager.getSolutionAlgorithm(date);
		if(alg == 'C') return "Clique";
		if(alg == 'L') return "Louvain";
		return "Girvan Newman";
	}
	
	public static int getSolutionNumberOfCommunities(String date)
	{
		date = date.replaceAll(":", ",");
		return SolutionManager.getSolutionNumberOfCommunities(date);
	}
	
	public static void goToConsultarSolucioPanel(String selectedSolutionDate)
	{
		ConsultarSolucioPanel.setCurrentSolutionDate(selectedSolutionDate);
		goToCard(ConsultarSolucioPanel.class.getSimpleName());
	}
	
	public static void goToEditarSolucioPanel(String lastGeneratedSolutionId)
	{
		EditarSolucioPanel.setCurrentSolutionDate(lastGeneratedSolutionId);
		goToCard(EditarSolucioPanel.class.getSimpleName());
	}
	
	public static void removeSolutionList(String solutionId, int listIndex)
	{
		SolutionManager.removeSolutionList(solutionId, listIndex);
	}
	
	public static void removeSolutionSong(String solutionId, String songAuthor, String songTitle)
	{
		SolutionManager.removeSolutionSong(solutionId, songAuthor, songTitle);
	}

	public static ArrayList<ArrayList<String>> getSolutionCommunities(String solutionDate) 
	{
		return SolutionManager.getSolutionCommunities(solutionDate);
	}

	public static void generateSolution(char algorisme, int durationP, int yearP, int styleP, int publicP, int proximityP, int authorP) 
	{
		String id = "";
		try 
		{ 
			 id = GraphManager.generateSolution(algorisme, durationP, yearP, styleP, publicP, proximityP, authorP); 
		}
		catch(Exception e) { errorWindow(e.getMessage()); return; }
		
		goToEditarSolucioPanel(id);
	}
	
	public static void discardLastGeneratedSolution()
	{
		SolutionManager.discardLastGeneratedSoution();
	}

	public static Pair< ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > , ArrayList< Pair<String, Integer> > >
					getLastGeneratedSolutionStringGraph()
	{
		return SolutionManager.getLastGeneratedSolutionStringGraphCommunities();
	}

	public static void goToViewGraphLastGeneratedSolution() 
	{
		ViewGraphPanel.setCurrentGraph( SolutionManager.getLastGeneratedSolutionStringGraphCommunities() );
		goToCard(ViewGraphPanel.class.getSimpleName());
	}
	
	//////////////////////////////////////
	
	
	
	
	
	
	//USERS ///////////////////////////////////////////
	public static void createUser(String username, int edat) throws Exception
	{
		UserManager.createUserAndSaveToDisk(username, edat);
	}
	
	public static ArrayList<String> getUsersNames()
	{
		return UserManager.getUsersNames();
	}
	
	public static int getUserAge(String username)
	{
		return UserManager.getUserAge(username);
	}
	
	public static ArrayList<Pair<String, Long>> getUserReproductions(String username) throws Exception
	{
		return UserManager.getUserReproductions(username);
	}
	
	public static void goToEditUsuarisPanel(String username)
	{
		EditarUsuarisPanel.setCurrentUsername(username);
		goToCard(EditarUsuarisPanel.class.getSimpleName());
	}
	
	public static void saveCurrentUsers()
	{
		try 
		{
			UserManager.saveCurrentUsersToDisk();
		} 
		catch (IOException e) 
		{
			PresentationManager.errorWindow("No es troba l'arxiu de usuaris");
		}
	}
	
	public static void setUserAge(String username, int age)
	{
		UserManager.setUserAge(username, age);
	}
	
	public static void setUserReproductions(String username, ArrayList<String> repros)
	{
		try 
		{
			for(String line : repros)
			{
				//Passem al format adient, el dels arxiu
				line = line.replaceAll(",", ";");
			}

			UserManager.setUserReproductions(username, repros);
		} 
		catch (Exception e) 
		{
			PresentationManager.errorWindow(e.getMessage());
		}
	}
	
	public static void goBack()
	{
		windowHistory.pop();
		if(windowHistory.empty())
		{
			changeCard("Main");
		}
		else
		{
			changeCard(windowHistory.peek());
		}
	}

	public static void loadUsersFromDisk()
	{
		try 
		{
			UserManager.loadUsersFromDisk();
		} 
		catch (Exception e) 
		{
			PresentationManager.errorWindow("No s'ha pogut trobar el arxiu de usuaris o hi ha usuaris corruptes al arxiu (caracters no valids)");
			return;
		}
	}
	
	public static void removeUser(String user) throws IOException 
	{
		System.out.println("delete user " + user + "~");
		UserManager.removeUserFromDisk(user);
	}
	/////////////////////////////////////
	
	
	
	
	
	
	
	//SONGS /////////////////////////////
	public static void goToEditCanconsPanel(String authorName, String title)
	{
		EditarCanconsPanel.setCurrentAuthorTitle(authorName, title);
		goToCard(EditarCanconsPanel.class.getSimpleName());
	}
	
	public static void createSong(String author, String title, int year, int duration, ArrayList<String> styles) throws Exception
	{
		SongManager.createSong(author, title, year, duration, styles);
	}
	
	public static ArrayList<Pair<String, String>> getSongsAuthorsAndTitles()
	{
		return SongManager.getSongsAuthorsAndTitles();
	}

	public static void loadSongsFromDisk()
	{
		try 
		{
			SongManager.loadSongsFromDisk();
		} 
		catch (Exception e) 
		{
			PresentationManager.errorWindow("No s'ha pogut trobar el arxiu de cancons");
			return;
		}
	}
	
	public static int getSongYear(String author, String title)
	{
		return SongManager.getSongYear(author, title);
	}

	public static int getSongDuration(String author, String title)
	{
		return SongManager.getSongDuration(author, title);
	}

	public static ArrayList<String> getSongStyles(String author, String title)
	{
		return SongManager.getSongStyles(author, title);
	}
	
	public static void setSongYear(String author, String title, int year)
	{
		SongManager.setSongYear(author, title, year);
	}
	
	public static void setSongDuration(String author, String title, int duration)
	{
		SongManager.setSongDuration(author, title, duration);
	}

	public static void setSongStyles(String author, String title, ArrayList<String> styles) throws Exception
	{
		SongManager.setSongStyles(author, title, styles);
	}
	
	public static void removeSongFromDisk(String author, String title)
	{
		try 
		{ 
			SongManager.removeSongFromDisk(author, title);
		}
		catch(Exception e){}
	}
	
	public static void saveCurrentSongsToDisk()
	{
		try
		{
			SongManager.saveCurrentSongsToDisk();
		}
		catch(Exception e)
		{
			errorWindow(e.getMessage());
		}
	}
	/////////////////////////////////////

	
	
	
	
	// UTILS /////////////////////////////
	public static String openFileWindow(boolean directoryOnly)
	{
		final JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("data/"));
		
		if (directoryOnly) fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int returnVal = fc.showOpenDialog(MainWindow.frmYoutube);
		
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            return file.getAbsolutePath();
	        } else {
	            //errorWindow("Ha);
	        }
		 
		return null;
	}
	
	public static void infoWindow(String msg)
	{
		JOptionPane.showMessageDialog(MainWindow.frmYoutube, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void errorWindow(String msg)
	{
		JOptionPane.showMessageDialog(MainWindow.frmYoutube, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean confirmWindow(String msg)
	{
		return JOptionPane.showConfirmDialog(MainWindow.frmYoutube, msg, "Estas segur?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ? true : false;
	}
	
	public static void goToCard(String cardName)
	{
		changeCard(cardName);
		JPanel p = (JPanel) MainWindow.frmYoutube.getContentPane();
		windowHistory.push(cardName);
	}
	
	private static void changeCard(String cardName)
	{
		if(cardName.equals("MainPanel"))
		{
			JPanel p = (JPanel) MainWindow.frmYoutube.getContentPane();
			CardLayout cl = (CardLayout) (p.getLayout());
			cl.show(p, cardName);
		}
		else
		{
			JPanel p = (JPanel) MainWindow.frmYoutube.getContentPane();
			CardLayout cl = (CardLayout) (p.getLayout());
			cl.show(p, ViewPanel.class.getSimpleName());

			ViewPanel.viewPanel.refreshInsidePanels(cardName);
			p = (JPanel) ViewPanel.viewPanel.cardContainer;
			cl = (CardLayout) (p.getLayout());
			cl.show(p, cardName);
		}
	}
	//////////////////////////////////////////////////

	public static void saveLastGeneratedSolution() 
	{
		try 
		{
			SolutionManager.saveLastGeneratedSolution();
			PresentationManager.infoWindow("Solucio desada correctament");
		} 
		catch (Exception e)
		{
			errorWindow( e.getMessage() );
		}
	}
	
	public static void modifyLastGeneratedSolution(String song, int from, int to)  
	{
		SolutionManager.modifyLastGeneratedSolution(song, from, to);
	}

	public static void goToViewGraph(String graphId) 
	{
		ViewGraphPanel.setCurrentGraph( SolutionManager.getSolutionStringGraphCommunities(graphId) );
		goToCard(ViewGraphPanel.class.getSimpleName());
	}

	public static void importSongs() {
		String path = openFileWindow(false);
		if (path != null){
			try {
				SongManager.importSongs(path);
			} catch (Exception e) {
				errorWindow(e.getMessage());
			}
		}
	}

	public static void importUsers() {
		String path = openFileWindow(false);
		if (path != null){
			try {
				UserManager.importUsers(path);
			} catch (Exception e) {
				errorWindow(e.getMessage());
			}
		}
	}

	public static void importSolutions() 
	{
		String path = openFileWindow(true);
		if (path != null){
			try {
				SolutionManager.importSolutions(path);
			} catch (Exception e) {
				errorWindow(e.getMessage());
			}
		}
	}

	public static void importReproductions(String username) 
	{
		String path = openFileWindow(false);
		if (path != null){
			try {
				System.out.println(path);
				UserManager.importReproductions(path, username);
			} catch (Exception e) {
				errorWindow(e.getMessage());
			}
		}
	}
}



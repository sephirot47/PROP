package Presentacio;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Domini.Pair;
import Domini.SolutionManager;
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
	}
	
	
	
	// SOLUTIONS /////////////////////////
	public static ArrayList<Double[]> getInfos() throws Exception
	{
		return SolutionManager.getInfos("data/solucions");
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
	public static void infoWindow(String msg)
	{
		JOptionPane.showMessageDialog(MainWindow.frmYoutube, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void errorWindow(String msg)
	{
		JOptionPane.showMessageDialog(MainWindow.frmYoutube, msg, "Error", JOptionPane.ERROR_MESSAGE);
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

			ViewPanel.viewPanel.refreshInsidePanels();
			p = (JPanel) ViewPanel.viewPanel.cardContainer;
			cl = (CardLayout) (p.getLayout());
			cl.show(p, cardName);
		}
		
	}
	//////////////////////////////////////////////////
}

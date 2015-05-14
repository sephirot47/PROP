package Presentacio;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

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
		EditarUsuarisPanel.currentUsername = username;
		goToCard(EditarUsuarisPanel.class.getSimpleName());
	}
	
	public static void saveCurrentUsers()
	{
		try 
		{
			UserManager.saveCurrentUsersToDisk();
		} catch (IOException e) 
		{
			WarningDialog.show("Error", "No es troba l'arxiu de usuaris");
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
			WarningDialog.show("Error", e.getMessage());
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
		SongManager.loadSongsFromDisk();
	}
	
	public static void removeUser(String user) throws IOException 
	{
		UserManager.removeUserFromDisk(user);
	}
	/////////////////////////////////////
	
	
	
	
	
	
	
	//SONGS /////////////////////////////
	public static void goToEditCanconsPanel(String authorName, String title)
	{
	}
	
	public static ArrayList<Pair<String, String>> getSongsAuthorsAndTitles()
	{
		return new ArrayList<Pair<String, String>>();
	}

	public static void loadSongsFromDisk()
	{
		SongManager.loadSongsFromDisk();
	}
	
	public static void removeSongFromDisk(String authorName, String title)
	{
	}
	/////////////////////////////////////

	
	
	
	
	// UTILS /////////////////////////////
	public static void goToCard(String cardName)
	{
		changeCard(cardName);
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
			
			p = (JPanel) ViewPanel.viewPanel.cardContainer;
			cl = (CardLayout) (p.getLayout());
			cl.show(p, cardName);
		}
		
	}
	//////////////////////////////////////////////////
}

package Presentacio;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.Stack;

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
	
	public static void goToCard(String cardName)
	{
		changeCard(cardName);
		windowHistory.push(cardName);
	}
	
	private static void changeCard(String cardName)
	{
		CardLayout cl = (CardLayout) (window.frmYoutube.getContentPane().getLayout());
		cl.show(window.frmYoutube.getContentPane(), cardName);
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
}

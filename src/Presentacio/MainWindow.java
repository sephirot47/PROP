package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.util.Stack;

import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

public class MainWindow 
{
	public static JFrame frmYoutube;
	private JButton sortir;
	public static final String title = "Youtube mix";
	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{		
		frmYoutube = new JFrame();
		frmYoutube.setResizable(false);
		frmYoutube.setTitle(title);
		frmYoutube.setBounds(100, 100, 800, 600);
		frmYoutube.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYoutube.getContentPane().setLayout(new CardLayout(0, 0));
		
		MainPanel mp = new MainPanel();
		frmYoutube.getContentPane().add(mp, MainPanel.class.getSimpleName());
		
		ViewPanel vp = new ViewPanel();
		frmYoutube.getContentPane().add(vp, ViewPanel.class.getSimpleName());
		
		PresentationManager.initialize();
	}
}

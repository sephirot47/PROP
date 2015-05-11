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
import javax.swing.JEditorPane;

public class MainWindow {

	private static JFrame frmYoutube;
	private JButton sortir;
	private JPanel GestioCancons, GestioUsuaris, Recomanacions, Estadistiques, Credits;
	private static final String title = "Youtube Mix";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmYoutube.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frmYoutube.setBounds(100, 100, 1000, 500);
		frmYoutube.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYoutube.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel Main = new JPanel();
		Main.setName("Main");
		frmYoutube.getContentPane().add(Main, "Main");
		Main.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCosaArriba = new JPanel();
		Main.add(panelCosaArriba, BorderLayout.NORTH);
		FlowLayout fl_panelCosaArriba = (FlowLayout) panelCosaArriba.getLayout();
		fl_panelCosaArriba.setVgap(100);
		
		JPanel panelBotonesIzquierda = new JPanel();
		Main.add(panelBotonesIzquierda, BorderLayout.WEST);
		panelBotonesIzquierda.setBorder(new EmptyBorder(10, 10, 0, 0));
		panelBotonesIzquierda.setLayout(new BoxLayout(panelBotonesIzquierda, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(32000, 30));
		panel_2.setAlignmentY(0.0f);
		panelBotonesIzquierda.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("Gestionar cançons");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangeCard("Gestió Cançons");
			}
		});
		panel_2.add(btnNewButton);
		btnNewButton.setMaximumSize(new Dimension(999, 25));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setAlignmentY(0.0f);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32000, 30));
		panel_3.setAlignmentY(0.0f);
		panelBotonesIzquierda.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JButton btnGestionarUsuaris = new JButton("Gestionar usuaris");
		btnGestionarUsuaris.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ChangeCard("Gestió Usuaris");
			}
		});
		panel_3.add(btnGestionarUsuaris);
		btnGestionarUsuaris.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionarUsuaris.setMaximumSize(new Dimension(999, 25));
		btnGestionarUsuaris.setAlignmentY(0.0f);
		
		JPanel panel_5 = new JPanel();
		panel_5.setMaximumSize(new Dimension(32000, 30));
		panel_5.setAlignmentY(0.0f);
		panelBotonesIzquierda.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		JButton btnRecomanacions = new JButton("Recomanacions");
		btnRecomanacions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangeCard("Recomanacions");
			}
		});
		panel_5.add(btnRecomanacions);
		btnRecomanacions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecomanacions.setMaximumSize(new Dimension(999, 25));
		btnRecomanacions.setAlignmentY(0.0f);
		
		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(32000, 30));
		panel_4.setAlignmentY(0.0f);
		panelBotonesIzquierda.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JButton btnEstadstiques = new JButton("Estadístiques");
		btnEstadstiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangeCard("Estadístiques");
			}
		});
		panel_4.add(btnEstadstiques);
		btnEstadstiques.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadstiques.setMaximumSize(new Dimension(999, 25));
		btnEstadstiques.setAlignmentY(0.0f);
		
		JPanel panelBotonesAbajo = new JPanel();
		Main.add(panelBotonesAbajo, BorderLayout.SOUTH);
		panelBotonesAbajo.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBotonesAbajo.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(0.0f);
		panelBotonesAbajo.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton credits = new JButton("Crèdits");
		credits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(credits);
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(1.0f);
		panelBotonesAbajo.add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		sortir = new JButton("Desar sessió i sortir");
		sortir.setAlignmentX(1.0f);
		sortir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sortir.setHorizontalAlignment(SwingConstants.RIGHT);
		sortir.addMouseListener(new MouseAdapter() {
			@Override
			public void  mouseClicked(MouseEvent e) 
			{
				System.exit(0);
			}
		});
		panel.add(sortir);

		GestioCancons = CreateStandardTemplateFrame("Gestió Cançons");
		Recomanacions = CreateStandardTemplateFrame("Recomanacions");
		Estadistiques = CreateStandardTemplateFrame("Estadístiques");
		GestioUsuaris = CreateStandardTemplateFrame("Gestió Usuaris");
	}
	
	public static JPanel CreateStandardTemplateFrame(String name)
	{
		JPanel panel = new JPanel();
		panel.setName(name);
		frmYoutube.getContentPane().add(panel, name);
		panel.setLayout(new BorderLayout(0, 0));
		JPanel BackButtonpanel = new JPanel();
		BackButtonpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(BackButtonpanel, BorderLayout.SOUTH);
		BackButtonpanel.setLayout(new BoxLayout(BackButtonpanel, BoxLayout.X_AXIS));
		JButton btnEnrerepanel = new JButton("Enrere");
		btnEnrerepanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnrerepanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangeCard("Main");
			}
		});
		btnEnrerepanel.setHorizontalAlignment(SwingConstants.LEFT);
		BackButtonpanel.add(btnEnrerepanel);
		return panel;
	}
	
	public static void ChangeCard(String cardName)
	{
		CardLayout cl = (CardLayout) (frmYoutube.getContentPane().getLayout());
		cl.show(frmYoutube.getContentPane(), cardName);
		
		if(!cardName.equals("Main"))
		{
			frmYoutube.setTitle(title + " - " + cardName);
		}
		else frmYoutube.setTitle(title); 
	}
}

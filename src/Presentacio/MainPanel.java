package Presentacio;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;







import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.SystemColor;
import javax.swing.UIManager;

import com.orsoncharts.Chart3DHints.Key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainPanel extends JPanel implements KeyListener{

	/**
	 * Create the panel.
	 */
	public MainPanel() 
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel Main = new JPanel();
		Main.setPreferredSize(new Dimension(800, 10));
		Main.setMinimumSize(new Dimension(1000, 10));
		Main.setName("Main");
		add(Main);
		Main.setLayout(null);
		
		JPanel panelCosaArriba = new JPanel();
		panelCosaArriba.setBounds(10, 11, 780, 166);
		panelCosaArriba.setForeground(Color.BLUE);
		panelCosaArriba.setPreferredSize(new Dimension(1000, 250));
		
		BufferedImage picture;
		try 
		{
			picture = ImageIO.read(new File("header.jpg"));
			panelCosaArriba.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			JLabel picLabel = new JLabel( new ImageIcon("header.jpg") );
			picLabel.setIconTextGap(0);
			picLabel.setVerticalAlignment(SwingConstants.TOP);
			panelCosaArriba.add(picLabel);
		} 
		catch (IOException e1) { e1.printStackTrace(); }
		 
		Main.add(panelCosaArriba);
		
		
		JPanel panelBotonesAbajo = new JPanel();
		panelBotonesAbajo.setBounds(10, 507, 800, 50);
		panelBotonesAbajo.setPreferredSize(new Dimension(800, 50));
		panelBotonesAbajo.setMaximumSize(new Dimension(800, 50));
		Main.add(panelBotonesAbajo);
		panelBotonesAbajo.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBotonesAbajo.setLayout(null);
		
		JButton credits = new JButton("Credits");
		credits.setBounds(10, 16, 96, 23);
		panelBotonesAbajo.add(credits);
		credits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goToCard(CreditsPanel.class.getSimpleName());
			}
		});
		credits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		JButton sortir = new JButton("Desar sessio i sortir");
		sortir.setBounds(589, 16, 180, 23);
		panelBotonesAbajo.add(sortir);
		sortir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sortir.addMouseListener(new MouseAdapter() {
			@Override
			public void  mouseClicked(MouseEvent e) 
			{
				System.exit(0);
			}
		});
		
		JButton btnNewButton = new JButton("Gestionar cancons");
		btnNewButton.setBounds(314, 334, 190, 25);
		Main.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(GestioCanconsPanel.class.getSimpleName());
			}
		});
		btnNewButton.setMaximumSize(new Dimension(999, 25));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setAlignmentY(0.0f);
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				PresentationManager.goToCard(HistorialPanel.class.getSimpleName());
			}
		});
		btnHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHistorial.setBounds(314, 406, 190, 25);
		Main.add(btnHistorial);
		btnHistorial.setMaximumSize(new Dimension(999, 25));
		btnHistorial.setAlignmentY(0.0f);
		
		JButton btnGestionarUsuaris = new JButton("Gestionar usuaris");
		btnGestionarUsuaris.setBounds(314, 370, 190, 25);
		Main.add(btnGestionarUsuaris);
		btnGestionarUsuaris.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goToCard(GestioUsuarisPanel.class.getSimpleName());
			}
		});
		btnGestionarUsuaris.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionarUsuaris.setMaximumSize(new Dimension(999, 25));
		btnGestionarUsuaris.setAlignmentY(0.0f);
		
		JButton btnGenerarLlistes = new JButton("Generar llistes");
		btnGenerarLlistes.setBounds(314, 298, 190, 25);
		Main.add(btnGenerarLlistes);
		btnGenerarLlistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(GenerarLlistesPanel.class.getSimpleName());
			}
		});
		btnGenerarLlistes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerarLlistes.setMaximumSize(new Dimension(999, 25));
		btnGenerarLlistes.setAlignmentY(0.0f);
		
		JButton btnEstadstiques = new JButton("Estadistiques");
		btnEstadstiques.setBounds(314, 442, 190, 25);
		Main.add(btnEstadstiques);
		btnEstadstiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(EstadistiquesPanel.class.getSimpleName());
			}
		});
		btnEstadstiques.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadstiques.setMaximumSize(new Dimension(999, 25));
		btnEstadstiques.setAlignmentY(0.0f);
		
		this.addKeyListener(this);
		setFocusable(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 10)
		{
			System.out.println("SDASDADS");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}

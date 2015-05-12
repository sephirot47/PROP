package Presentacio;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.util.Stack;

import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;


import java.awt.event.ActionListener;


public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel() 
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel Main = new JPanel();
		Main.setPreferredSize(new Dimension(1000, 10));
		Main.setMinimumSize(new Dimension(1000, 10));
		Main.setName("Main");
		add(Main);
		Main.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCosaArriba = new JPanel();
		panelCosaArriba.setPreferredSize(new Dimension(1000, 166));
		Main.add(panelCosaArriba, BorderLayout.NORTH);
		FlowLayout fl_panelCosaArriba = (FlowLayout) panelCosaArriba.getLayout();
		fl_panelCosaArriba.setVgap(100);
		
		JPanel panelBotonesIzquierda = new JPanel();
		panelBotonesIzquierda.setMinimumSize(new Dimension(10, 0));
		panelBotonesIzquierda.setPreferredSize(new Dimension(200, 200));
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
				PresentationManager.goToCard(GestioCanconsPanel.class.getSimpleName());
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
				PresentationManager.goToCard(GestioUsuarisPanel.class.getSimpleName());
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
		
		JButton btnGenerarLlistes = new JButton("Generar llistes");
		btnGenerarLlistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//PresentationManager.goToCard(GenerarLlistesPanel.class.getSimpleName());
				PresentationManager.goToCard(GenerarLlistesPanel.class.getSimpleName());
			}
		});
		panel_5.add(btnGenerarLlistes);
		btnGenerarLlistes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerarLlistes.setMaximumSize(new Dimension(999, 25));
		btnGenerarLlistes.setAlignmentY(0.0f);
		
		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(32000, 30));
		panel_4.setAlignmentY(0.0f);
		panelBotonesIzquierda.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JButton btnEstadstiques = new JButton("Estadistiques");
		btnEstadstiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(EstadistiquesPanel.class.getSimpleName());
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
		
		JButton credits = new JButton("Credits");
		credits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(CreditsPanel.class.getSimpleName());
				//WarningDialog.show("Ostia!", "Estás mirando los créditos...");
			}
		});
		credits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(credits);
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(1.0f);
		panelBotonesAbajo.add(panel, BorderLayout.EAST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		JButton sortir = new JButton("Desar sessió i sortir");
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
	}

}

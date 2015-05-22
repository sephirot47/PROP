package Presentacio;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ArrayList;

public class NovaCancoPanel extends JPanel 
{
	private JTextField textTitle;
	private JTextField textAuthor;
	private JTextField textYear;
	private JTextField textDuration;
	private JTextField textStyle0;
	private JTextField textStyle1;
	private JTextField textStyle2;

	public NovaCancoPanel() 
	{
		setPreferredSize(new Dimension(800, 560));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(68, 172, 308, 190);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitol = new JLabel("Titol:");
		lblTitol.setBounds(10, 58, 36, 15);
		panel.add(lblTitol);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 14, 52, 15);
		panel.add(lblAutor);
		
		textTitle = new JTextField();
		textTitle.setBounds(78, 56, 211, 19);
		panel.add(textTitle);
		textTitle.setColumns(10);
		
		textAuthor = new JTextField();
		textAuthor.setBounds(79, 11, 211, 19);
		panel.add(textAuthor);
		textAuthor.setColumns(10);
		
		textYear = new JTextField();
		textYear.setColumns(10);
		textYear.setBounds(79, 101, 211, 19);
		panel.add(textYear);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setBounds(10, 103, 37, 15);
		panel.add(lblAny);
		
		textDuration = new JTextField();
		textDuration.setColumns(10);
		textDuration.setBounds(79, 149, 211, 19);
		panel.add(textDuration);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setBounds(10, 149, 70, 15);
		panel.add(lblDuracio);
		
		JLabel lblDadesBasiques = new JLabel("Dades basiques");
		lblDadesBasiques.setBounds(68, 151, 315, 15);
		add(lblDadesBasiques);
		
		JButton btnCrearUsuari = new JButton("Crear canco");
		btnCrearUsuari.setBounds(293, 390, 142, 25);
		add(btnCrearUsuari);
		btnCrearUsuari.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				onCreateSongClicked();
			}
		});
		btnCrearUsuari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblEstilsDeLa_1 = new JLabel("Estils de la canco");
		lblEstilsDeLa_1.setBounds(408, 151, 315, 15);
		add(lblEstilsDeLa_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(408, 174, 368, 142);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEstil = new JLabel("Estil 1:");
		lblEstil.setBounds(10, 15, 67, 15);
		panel_1.add(lblEstil);
		
		textStyle0 = new JTextField();
		textStyle0.setBounds(95, 10, 169, 19);
		panel_1.add(textStyle0);
		textStyle0.setColumns(10);
		
		JLabel lblEstil_1 = new JLabel("Estil 2:");
		lblEstil_1.setBounds(10, 44, 67, 15);
		panel_1.add(lblEstil_1);
		
		textStyle1 = new JTextField();
		textStyle1.setBounds(95, 40, 169, 19);
		panel_1.add(textStyle1);
		textStyle1.setColumns(10);
		
		JLabel lblEstil_2 = new JLabel("Estil 3:");
		lblEstil_2.setBounds(10, 72, 67, 15);
		panel_1.add(lblEstil_2);
		
		textStyle2 = new JTextField();
		textStyle2.setBounds(95, 68, 169, 19);
		panel_1.add(textStyle2);
		textStyle2.setColumns(10);
		
		JLabel lblEstilsDeLa = new JLabel("(deixar en blanc si no es volen afegir mes estils)");
		lblEstilsDeLa.setBounds(10, 115, 355, 15);
		panel_1.add(lblEstilsDeLa);
	}
	
	void onCreateSongClicked()
	{ 
		String title = textTitle.getText().trim();
		if(title.equals("")) { PresentationManager.errorWindow("El titol d'una canco no pot ser buit."); return; }
		
		String author = textAuthor.getText().trim();
		if(author.equals("")) {PresentationManager.errorWindow( "El autor d'una canco no pot ser buit."); return; }
		int year = -1, duration = -1;
		
		try { year  = Integer.parseInt(textYear.getText().trim()); }
		catch(NumberFormatException e) {PresentationManager.errorWindow( "L'any de la canco ha de ser un numero valid."); return; }
		if(year <= 0) {PresentationManager.errorWindow( "L'any de la canco ha de ser un numero > 0."); return; }
		
		try { duration  = Integer.parseInt(textDuration.getText().trim()); }
		catch(NumberFormatException e) {PresentationManager.errorWindow( "La duracio de la canco ha de ser un numero valid."); return; }
		if(duration <= 0) {PresentationManager.errorWindow( "La duracio de la canco ha de ser un numero > 0."); return; }

		String style0 = textStyle0.getText().trim();
		String style1 = textStyle1.getText().trim();
		String style2 = textStyle2.getText().trim();
		ArrayList<String> styles = new ArrayList<String>();
		if(!style0.equals("")) styles.add(style0);
		if(!style1.equals("")) styles.add(style1);
		if(!style2.equals("")) styles.add(style2);
		
		if(styles.size() <= 0) {PresentationManager.errorWindow( "La canco ha de tenir un estil com a minim."); return; }
		
		if(PresentationManager.confirmWindow("<html>Estas segur que vols crear la canco '" + title + "' de l'autor '" + author + "' ?" +
				"<br/>Pensa que no podras canviar el nom de la canco ni el autor mes tard...</html>"))
		{
			try
			{
				PresentationManager.createSong(author, title, year, duration, styles);	
			}
			catch(Exception e)
			{
				PresentationManager.errorWindow(e.getMessage());
				return;
			}
			
			PresentationManager.goBack();
		}
	}
}

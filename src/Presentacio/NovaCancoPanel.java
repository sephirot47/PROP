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

	/**
	 * Create the panel.
	 */
	public NovaCancoPanel() 
	{
		setPreferredSize(new Dimension(800, 560));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(95, 172, 308, 191);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitol = new JLabel("Titol:");
		lblTitol.setBounds(10, 59, 36, 15);
		panel.add(lblTitol);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 14, 37, 15);
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
		lblAny.setBounds(10, 101, 37, 15);
		panel.add(lblAny);
		
		textDuration = new JTextField();
		textDuration.setColumns(10);
		textDuration.setBounds(79, 149, 211, 19);
		panel.add(textDuration);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setBounds(10, 149, 52, 15);
		panel.add(lblDuracio);
		
		JLabel lblDadesBasiques = new JLabel("Dades basiques");
		lblDadesBasiques.setBounds(95, 151, 315, 15);
		add(lblDadesBasiques);
		
		JButton btnCrearUsuari = new JButton("Crear canco");
		btnCrearUsuari.setBounds(320, 390, 142, 25);
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
		lblEstilsDeLa_1.setBounds(435, 151, 315, 15);
		add(lblEstilsDeLa_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(435, 174, 249, 142);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEstil = new JLabel("Estil 1:");
		lblEstil.setBounds(10, 15, 37, 15);
		panel_1.add(lblEstil);
		
		textStyle0 = new JTextField();
		textStyle0.setBounds(70, 11, 169, 19);
		panel_1.add(textStyle0);
		textStyle0.setColumns(10);
		
		JLabel lblEstil_1 = new JLabel("Estil 2:");
		lblEstil_1.setBounds(10, 44, 37, 15);
		panel_1.add(lblEstil_1);
		
		textStyle1 = new JTextField();
		textStyle1.setBounds(70, 41, 169, 19);
		panel_1.add(textStyle1);
		textStyle1.setColumns(10);
		
		JLabel lblEstil_2 = new JLabel("Estil 3:");
		lblEstil_2.setBounds(10, 72, 37, 15);
		panel_1.add(lblEstil_2);
		
		textStyle2 = new JTextField();
		textStyle2.setBounds(70, 69, 169, 19);
		panel_1.add(textStyle2);
		textStyle2.setColumns(10);
		
		JLabel lblEstilsDeLa = new JLabel("(deixar en blanc si no es volen afegir mes estils)");
		lblEstilsDeLa.setBounds(10, 116, 315, 15);
		panel_1.add(lblEstilsDeLa);
	}
	
	void onCreateSongClicked()
	{ 
		String title = textTitle.getText().trim();
		if(title.equals("")) { WarningDialog.show("Error", "El titol d'una canco no pot ser buit."); return; }
		
		String author = textAuthor.getText().trim();
		if(author.equals("")) { WarningDialog.show("Error", "El autor d'una canco no pot ser buit."); return; }
		int year = -1, duration = -1;
		
		try { year  = Integer.parseInt(textYear.getText().trim()); }
		catch(NumberFormatException e) { WarningDialog.show("Error", "L'any de la canco ha de ser un numero valid."); return; }
		if(year <= 0) { WarningDialog.show("Error", "L'any de la canco ha de ser un numero > 0."); return; }
		
		try { duration  = Integer.parseInt(textDuration.getText().trim()); }
		catch(NumberFormatException e) { WarningDialog.show("Error", "La duracio de la canco ha de ser un numero valid."); return; }
		if(duration <= 0) { WarningDialog.show("Error", "La duracio de la canco ha de ser un numero > 0."); return; }

		String style0 = textStyle0.getText().trim();
		String style1 = textStyle1.getText().trim();
		String style2 = textStyle2.getText().trim();
		ArrayList<String> styles = new ArrayList<String>();
		if(!style0.equals("")) styles.add(style0);
		if(!style1.equals("")) styles.add(style1);
		if(!style2.equals("")) styles.add(style2);
		
		if(styles.size() <= 0) { WarningDialog.show("Error", "La canco ha de tenir un estil com a minim."); return; }
		
		try
		{
			PresentationManager.createSong(author, title, year, duration, styles);	
		}
		catch(Exception e)
		{
			WarningDialog.show("Error", e.getMessage());
			return;
		}
		
		PresentationManager.goBack();
	}
}

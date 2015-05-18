package Presentacio;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JSeparator;

import Domini.SongManager;

public class EditarCanconsPanel extends JPanel 
{
	private JTextField textYear;
	private JTextField textDuration;

	private static String currentAuthor, currentTitle;
	private JTextField textEstil0;
	private JTextField textEstil1;
	private JTextField textEstil2;
	private JLabel labelAuthorValue, labelTitleValue;
	
	public EditarCanconsPanel() 
	{
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(134, 92, 501, 356);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Titol:");
		lblTitle.setBounds(22, 22, 70, 15);
		panel.add(lblTitle);
		
		JLabel lblAutor = new JLabel("Autor: ");
		lblAutor.setBounds(22, 80, 70, 15);
		panel.add(lblAutor);
		
		textYear = new JTextField();
		textYear.setColumns(10);
		textYear.setBounds(76, 142, 140, 19);
		panel.add(textYear);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setBounds(22, 144, 70, 15);
		panel.add(lblAny);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setBounds(234, 144, 70, 15);
		panel.add(lblDuracio);
		
		textDuration = new JTextField();
		textDuration.setColumns(10);
		textDuration.setBounds(310, 142, 165, 19);
		panel.add(textDuration);
		
		JLabel lblEstils = new JLabel("Estils:");
		lblEstils.setBounds(22, 191, 70, 15);
		panel.add(lblEstils);
		
		JButton btnSaveChanges = new JButton("Desar canvis");
		btnSaveChanges.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				saveChanges();
			}
		});
		btnSaveChanges.setBounds(322, 304, 153, 25);
		panel.add(btnSaveChanges);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(22, 218, 254, 111);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEstil = new JLabel("Estil1:");
		lblEstil.setBounds(12, 12, 70, 15);
		panel_1.add(lblEstil);
		
		textEstil0 = new JTextField();
		textEstil0.setBounds(68, 14, 167, 19);
		panel_1.add(textEstil0);
		textEstil0.setColumns(10);
		
		JLabel lblEstil_1 = new JLabel("Estil2:");
		lblEstil_1.setBounds(12, 46, 70, 15);
		panel_1.add(lblEstil_1);
		
		textEstil1 = new JTextField();
		textEstil1.setBounds(68, 43, 167, 19);
		panel_1.add(textEstil1);
		textEstil1.setColumns(10);
		
		JLabel lblEstil_2 = new JLabel("Estil3:");
		lblEstil_2.setBounds(12, 81, 70, 15);
		panel_1.add(lblEstil_2);
		
		textEstil2 = new JTextField();
		textEstil2.setBounds(68, 77, 167, 19);
		panel_1.add(textEstil2);
		textEstil2.setColumns(10);
		
		labelTitleValue = new JLabel("titololololol");
		labelTitleValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelTitleValue.setBounds(87, 22, 375, 15);
		panel.add(labelTitleValue);
		
		labelAuthorValue = new JLabel("autorrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		labelAuthorValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelAuthorValue.setBounds(87, 80, 375, 15);
		panel.add(labelAuthorValue);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 57, 467, 15);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 119, 467, 15);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(22, 179, 467, 15);
		panel.add(separator_2);
		
		JLabel lblEditarCanco = new JLabel("Editar canco:");
		lblEditarCanco.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEditarCanco.setBounds(134, 65, 127, 15);
		add(lblEditarCanco);
	}
	
	public static void setCurrentAuthorTitle(String author, String title)
	{
		currentAuthor = author;
		currentTitle = title;
	}
	
	public void refresh()
	{
		int year = PresentationManager.getSongYear(currentAuthor, currentTitle);
		int duration = PresentationManager.getSongDuration(currentAuthor, currentTitle);
		ArrayList<String> styles = PresentationManager.getSongStyles(currentAuthor, currentTitle);
		
		labelAuthorValue.setText(currentAuthor);
		labelTitleValue.setText(currentTitle);
		textYear.setText(String.valueOf(year));
		textDuration.setText(String.valueOf(duration));
		textEstil0.setText(""); textEstil1.setText(""); textEstil2.setText("");
		for(int i = 0; i < styles.size(); ++i) 
		{
			if(i == 0) textEstil0.setText(styles.get(i));
			else if(i == 1) textEstil1.setText(styles.get(i));
			else if(i == 2) textEstil2.setText(styles.get(i));
		}
	}
	
	public void saveChanges()
	{
		int year = 0;
		try{ year = Integer.parseInt(textYear.getText().trim()); }
		catch(Exception e) { PresentationManager.errorWindow("L'any de la canco no te un format numeric valid"); return; }
		if(year <= 0) { PresentationManager.errorWindow("La canco ha de tenir un any > 0"); return; }

		int duration = 0;
		try{ duration= Integer.parseInt(textDuration.getText().trim()); }
		catch(Exception e) { PresentationManager.errorWindow("La duracio de la canco no te un format numeric valid"); return; }
		if(duration <= 0) { PresentationManager.errorWindow("La canco ha de tenir duracio > 0"); return; }
		
		ArrayList<String> styles = new ArrayList<String>();
		if( !textEstil0.getText().trim().equals("") ) styles.add(textEstil0.getText().trim());
		if( !textEstil1.getText().trim().equals("") ) styles.add(textEstil1.getText().trim());
		if( !textEstil2.getText().trim().equals("") ) styles.add(textEstil2.getText().trim());

		if(styles.size() == 0) { PresentationManager.errorWindow("La canco ha de tenir com a minim un estil"); return; }
			
		PresentationManager.setSongYear(currentAuthor, currentTitle, year);
		PresentationManager.setSongDuration(currentAuthor, currentTitle, duration);
		try 
		{
			PresentationManager.setSongStyles(currentAuthor, currentTitle, styles);
		} 
		catch (Exception e) 
		{
			PresentationManager.errorWindow(e.getMessage());
			return;
		}
		
		PresentationManager.saveCurrentSongsToDisk();
		
		PresentationManager.infoWindow("La canco ha estat modificada correctament!");
		PresentationManager.goBack();
	}
}

package Presentacio;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.LineBorder;

import Domini.Pair;
import Domini.UserManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

public class EditarUsuarisPanel extends JPanel
{
	private static String currentUsername = "";
	final private JList listReproductions, songList;
	private JLabel textNom;
	private JTextField textEdat;
	private JButton btnEliminarReproduccio;
	private JTextField textTimestamp;
	
	public EditarUsuarisPanel()
	{
		super();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) 
			{
				refreshAll();
			}
		});
		setPreferredSize(new Dimension(800, 561));
		setLayout(null);
		
		JPanel panelUserDetail = new JPanel();
		panelUserDetail.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelUserDetail.setBounds(29, 58, 364, 418);
		add(panelUserDetail);
		panelUserDetail.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNom.setBounds(12, 12, 36, 20);
		panelUserDetail.add(lblNom);
		
		JLabel lblEdat = new JLabel("Edat:");
		lblEdat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdat.setBounds(12, 40, 48, 20);
		panelUserDetail.add(lblEdat);
		
		JLabel lblReproduccions = new JLabel("Reproduccions:");
		lblReproduccions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReproduccions.setBounds(12, 72, 119, 20);
		panelUserDetail.add(lblReproduccions);
		
		listReproductions = new JList();
		listReproductions.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 336, 231);
		panelUserDetail.add(scrollPane);
		listReproductions.setBounds(12, 127, 340, 304);
		scrollPane.setViewportView(listReproductions);
		
		btnEliminarReproduccio = new JButton("Eliminar reproduccio");
		btnEliminarReproduccio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				deleteSelectedReproduction();
			}
		});
		btnEliminarReproduccio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarReproduccio.setBounds(12, 340, 336, 25);
		panelUserDetail.add(btnEliminarReproduccio);
		
		textNom = new JLabel();
		textNom.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) 
			{
				refreshAll();
			}
		});
		textNom.setBounds(59, 13, 114, 19);
		panelUserDetail.add(textNom);
		//textNom.setColumns(10);
		
		textEdat = new JTextField();
		textEdat.setColumns(10);
		textEdat.setBounds(59, 41, 114, 19);
		panelUserDetail.add(textEdat);
		
		JButton btnImportarFitxer = new JButton("Importar fitxer");
		btnImportarFitxer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportarFitxer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.importReproductions(currentUsername);
				refreshReproductionsList();
			}
		});
		btnImportarFitxer.setBounds(12, 374, 336, 25);
		panelUserDetail.add(btnImportarFitxer);
		
		JPanel panelUsers = new JPanel();
		panelUsers.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelUsers.setBounds(445, 58, 311, 405);
		add(panelUsers);
		panelUsers.setLayout(null);
		
		JButton btnAfegir = new JButton("Afegir");
		btnAfegir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(songList.getSelectedIndex() < 0) return;
				
				String line = (String) songList.getSelectedValue();
				String autor = line.split(",")[0].trim();
				String titol = line.split(",")[1].trim();
				int timestamp = -1;
				try
				{
					timestamp = Integer.parseInt(textTimestamp.getText().trim());
				}
				catch(NumberFormatException eee)
				{
					PresentationManager.errorWindow("El timestamp ha de ser un numero valid.");
					return;
				}
				
				if(titol.length() == 0){PresentationManager.errorWindow( "El titol d'una reproduccio no pot ser buit."); return;}
				if(autor.length() == 0){PresentationManager.errorWindow( "El autor d'una reproduccio no pot ser buit."); return;}
				
				if(timestamp < 0){PresentationManager.errorWindow("El timestamp no pot ser < 0."); return;}
				
				((DefaultListModel)listReproductions.getModel()).addElement(autor + ", " + titol + ", " + timestamp);

				refreshDeleteReproductionsButton();
			}
		});

		btnAfegir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAfegir.setBounds(12, 368, 287, 25);
		panelUsers.add(btnAfegir);
		
		textTimestamp = new JTextField();
		textTimestamp.setColumns(10);
		textTimestamp.setBounds(108, 337, 191, 19);
		panelUsers.add(textTimestamp);
		
		JLabel lblTimestamp = new JLabel("Timestamp:");
		lblTimestamp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTimestamp.setBounds(12, 339, 112, 15);
		panelUsers.add(lblTimestamp);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 12, 287, 312);
		panelUsers.add(scrollPane_1);
		
		songList = new JList();
		songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		songList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane_1.setViewportView(songList);
		
		JButton btnDesar = new JButton("Desar canvis");
		btnDesar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ArrayList<String> repros = new ArrayList<String>();
				DefaultListModel dlm = (DefaultListModel) listReproductions.getModel();
				for(int i = 0; i < dlm.getSize(); ++i)
				{
					repros.add((String)dlm.getElementAt(i));
				}

				int age = -1;
				try { age = Integer.parseInt(textEdat.getText()); }
				catch(NumberFormatException eee)
				{
					PresentationManager.errorWindow("La edat ha de ser un numero valid.");
					return;
				}
				
				if(age <= 0){PresentationManager.errorWindow("La edat d'un usuari ha de ser > 0."); return;}

				PresentationManager.setUserAge(currentUsername, age);
				PresentationManager.setUserReproductions(currentUsername, repros);
				PresentationManager.saveCurrentUsers();
				PresentationManager.infoWindow("Usuari editat correctament!");
				PresentationManager.goBack(false);
			}
		});
		btnDesar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDesar.setBounds(445, 475, 311, 42);
		add(btnDesar);
		
		JLabel lblNovaReproduccio = new JLabel("Afegir nova reproduccio:");
		lblNovaReproduccio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNovaReproduccio.setBounds(445, 31, 311, 15);
		add(lblNovaReproduccio);
		
		JLabel lblDetallsUsuari = new JLabel("Detalls usuari:");
		lblDetallsUsuari.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDetallsUsuari.setBounds(37, 26, 114, 20);
		add(lblDetallsUsuari);
		
		refreshAll();
	}
	
	public void refreshAll()
	{
		refreshReproductionsList();
		refreshSongsList();
		refreshTexts();
	}
	
	public void refreshTexts()
	{
		textNom.setText(currentUsername);
		
		int age = PresentationManager.getUserAge(currentUsername);
		textEdat.setText( String.valueOf(age) );
	}

	private void refreshSongsList()
	{	
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();

		ArrayList<Pair<String, String>> authorsAndTitles = PresentationManager.getSongsAuthorsAndTitles();
		ArrayList<String> authorsAndTitlesTogether = new ArrayList<String>();
		for(Pair<String, String> p : authorsAndTitles) authorsAndTitlesTogether.add(p.getFirst() + ", " + p.getSecond());
		Collections.sort(authorsAndTitlesTogether);
		
		if(authorsAndTitlesTogether != null)
		{
			for(String song : authorsAndTitlesTogether)
			{
				dlm.addElement(song);
			}
		}
		
		songList.setModel(dlm);
	}
	
	private void refreshReproductionsList()
	{	
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();
		
		ArrayList<Pair<String, Long>> repros = new ArrayList<Pair<String, Long>>();
		try 
		{
			repros = PresentationManager.getUserReproductions(currentUsername);
		} 
		catch (Exception e) 
		{
			PresentationManager.errorWindow("No es troba l'arxiu de reproduccions d'aquest usuari");
			e.printStackTrace();
		}
		
		if(repros != null)
		{
			for(Pair<String, Long> r : repros)
			{
				dlm.addElement(r.getFirst() + ", " + String.valueOf(r.getSecond()) );
			}
		}
		
		listReproductions.setModel(dlm);
		refreshDeleteReproductionsButton();
	}
	
	private void refreshDeleteReproductionsButton()
	{
		DefaultListModel dlm = ((DefaultListModel)listReproductions.getModel());

		if(dlm.getSize() <= 0) btnEliminarReproduccio.setEnabled(false);
		else
		{
			listReproductions.setSelectedIndex(0);
			btnEliminarReproduccio.setEnabled(true);
		}
	}
	
	private void deleteSelectedReproduction()
	{
		int firstIndex = listReproductions.getMinSelectionIndex();
		int lastIndex = listReproductions.getMaxSelectionIndex();
		DefaultListModel dlm = ((DefaultListModel)listReproductions.getModel());
		if(firstIndex >= 0)
		{
			dlm.removeRange(firstIndex, lastIndex);
		}
		refreshDeleteReproductionsButton();
	}
	
	public static void setCurrentUsername(String username)
	{
		currentUsername = username;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}

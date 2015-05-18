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
import Domini.SongManager;

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
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;


public class GestioCanconsPanel extends JPanel
{
	final private JTextField txtBuscar;
	final private JLabel labelTitleValue, labelAuthorValue, labelYearValue, labelDurationValue;
	private JList listEstils, listSongs;
	
	public GestioCanconsPanel()
	{
		super();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) 
			{
				refreshSongList();
			}
		});
		setPreferredSize(new Dimension(800, 555));
		setLayout(null);
		
		JPanel panelSongs = new JPanel();
		panelSongs.setBounds(30, 29, 365, 497);
		add(panelSongs);
		panelSongs.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
				new Thread(new Runnable()
				{
					public void run() 
					{
						try{ Thread.sleep(100); } catch(Exception e){}
						refreshSongList();
					}
				}
                ).start();
			}
		});
		txtBuscar.setBounds(12, 36, 216, 19);
		panelSongs.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		listSongs = new JList();
		listSongs.setAutoscrolls(false);
		listSongs.setAlignmentY(0.0f);
		listSongs.setAlignmentX(0.0f);
		listSongs.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				String authorAndTitle = (String) listSongs.getSelectedValue();
				populateSongDetails(authorAndTitle == null ? "" : authorAndTitle);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 76, 341, 363);
		panelSongs.add(scrollPane_1);
		listSongs.setBounds(12, 67, 278, 369);
		scrollPane_1.setViewportView(listSongs);
		
		JButton btnNouCanco = new JButton("Nova canco");
		btnNouCanco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNouCanco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goToCard(NovaCancoPanel.class.getSimpleName());
			}
		});
		btnNouCanco.setBounds(12, 448, 145, 25);
		panelSongs.add(btnNouCanco);
		
		JButton btnImportarFitxer = new JButton("Importar fitxer");
		btnImportarFitxer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.importSongs();
				refreshSongList();
			}
		});
		btnImportarFitxer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportarFitxer.setBounds(192, 448, 161, 25);
		panelSongs.add(btnImportarFitxer);
		
		JLabel lblBuscar = new JLabel("Buscar canco:");
		lblBuscar.setBounds(12, 12, 145, 20);
		panelSongs.add(lblBuscar);
		
		JPanel panelSongDetail = new JPanel();
		panelSongDetail.setBounds(407, 95, 364, 431);
		add(panelSongDetail);
		panelSongDetail.setLayout(null);
		
		JLabel lblNom = new JLabel("Titol:");
		lblNom.setBounds(12, 33, 36, 20);
		panelSongDetail.add(lblNom);
		
		JLabel lblEdat = new JLabel("Autor:");
		lblEdat.setBounds(12, 76, 48, 20);
		panelSongDetail.add(lblEdat);
		
		JLabel lblReproduccions = new JLabel("Estils:");
		lblReproduccions.setBounds(12, 207, 119, 20);
		panelSongDetail.add(lblReproduccions);
		
		labelAuthorValue = new JLabel("-");
		labelAuthorValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelAuthorValue.setBounds(84, 76, 198, 20);
		panelSongDetail.add(labelAuthorValue);
		
		labelTitleValue = new JLabel("-");
		labelTitleValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelTitleValue.setBounds(84, 33, 198, 20);
		panelSongDetail.add(labelTitleValue);
		
		JButton buttonEditarCanco = new JButton("Editar canco");
		buttonEditarCanco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(listSongs.getSelectedIndex() != -1)
				{
					String author = ((String) listSongs.getSelectedValue()).split(",")[0].trim();
					String title =  ((String) listSongs.getSelectedValue()).split(",")[1].trim();
					PresentationManager.goToEditCanconsPanel(author, title);
				}
			}
		});
		buttonEditarCanco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonEditarCanco.setBounds(12, 381, 161, 25);
		panelSongDetail.add(buttonEditarCanco);
		
		JButton btnEliminarCanco = new JButton("Eliminar canco");
		btnEliminarCanco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(listSongs.getSelectedIndex() != -1)
				{
					String author = ((String) listSongs.getSelectedValue()).split(",")[0].trim();
					String title =  ((String) listSongs.getSelectedValue()).split(",")[1].trim();
					PresentationManager.removeSongFromDisk(author, title);
					refreshSongList();
				}
			}
		});
		btnEliminarCanco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarCanco.setBounds(191, 381, 161, 25);
		panelSongDetail.add(btnEliminarCanco);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 238, 340, 120);
		panelSongDetail.add(scrollPane);
		
		listEstils = new JList();
		listEstils.setAutoscrolls(false);
		listEstils.setAlignmentY(0.0f);
		listEstils.setAlignmentX(0.0f);
		listEstils.setBounds(12, 127, 340, 304);
		scrollPane.setColumnHeaderView(listEstils);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setBounds(12, 119, 48, 20);
		panelSongDetail.add(lblAny);
		
		labelYearValue = new JLabel("-");
		labelYearValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelYearValue.setBounds(84, 119, 198, 20);
		panelSongDetail.add(labelYearValue);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setBounds(12, 161, 80, 20);
		panelSongDetail.add(lblDuracio);
		
		labelDurationValue = new JLabel("-");
		labelDurationValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelDurationValue.setBounds(84, 161, 198, 20);
		panelSongDetail.add(labelDurationValue);
		
		JLabel lblDetallsCanco = new JLabel("Detalls canco:");
		lblDetallsCanco.setBounds(419, 72, 114, 20);
		add(lblDetallsCanco);
		
		refreshSongList();
	}
	
	public void refreshSongList()
	{	
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();

		ArrayList<Pair<String, String>> authorsAndTitles = PresentationManager.getSongsAuthorsAndTitles();
		ArrayList<String> authorsAndTitlesTogether = new ArrayList<String>();
		for(Pair<String, String> p : authorsAndTitles) authorsAndTitlesTogether.add(p.getFirst() + ", " + p.getSecond());
		Collections.sort(authorsAndTitlesTogether);
		
		for(String entry : authorsAndTitlesTogether)
		{
			String search = txtBuscar.getText().trim();
			boolean addIt = search.equals("") || entry.contains(search);
			if(addIt) dlm.addElement(entry);
		}
				
		listSongs.setModel(dlm);

		if(listSongs.getModel().getSize() > 0)
			listSongs.setSelectedIndex(0);
	}
	
	public void populateSongDetails(String authorAndTitle)
	{
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();
		if(!authorAndTitle.equals(""))
		{
			String author = authorAndTitle.split(",")[0].trim();
			labelAuthorValue.setText(author);
			
			String title = authorAndTitle.split(",")[1].trim();
			labelTitleValue.setText(title);

			int year = PresentationManager.getSongYear(author, title);
			labelYearValue.setText( String.valueOf(year) );
			
			int duration = PresentationManager.getSongDuration(author, title);
			labelDurationValue.setText( String.valueOf(duration) + " segons" );
			
			ArrayList<String> styles = PresentationManager.getSongStyles(author, title);
			for(String style : styles) dlm.addElement(style);
		}

		listEstils.setModel(dlm);
	}
}

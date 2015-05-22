package Presentacio;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;

import Domini.SolutionManager;

import javax.swing.JComboBox;

public class EditarSolucioPanel extends JPanel 
{
	private static ArrayList<ArrayList<String>> currentSolutionLists = new ArrayList<ArrayList<String>>();
	private static String currentSolutionDate = "";
	private JLabel lblLlistaSelected;
	private JList listsList, songsList;
	private JComboBox<String> comboLists;
	public EditarSolucioPanel() 
	{
		setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(397, 54, 265, 221);
		add(scrollPane_1);
		
		songsList = new JList();
		songsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		songsList.setBounds(0, 0, 1, 1);
		scrollPane_1.setViewportView(songsList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 54, 265, 384);
		add(scrollPane);
		
		listsList = new JList();
		listsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(listsList.getSelectedIndex() != -1)
				{
					lblLlistaSelected.setText("Llista " + (listsList.getSelectedIndex()  + 1) + ":");
					populateSongList(listsList.getSelectedIndex());
				}
			}
		});
		scrollPane.setViewportView(listsList);
		
		JButton btnRemoveList = new JButton("Eliminar llista de la solucio");
		btnRemoveList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(listsList.getSelectedIndex() != -1)
				{
					PresentationManager.removeSolutionList(currentSolutionDate, listsList.getSelectedIndex());
					refresh();
				}
			}
		});
		btnRemoveList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoveList.setBounds(33, 450, 265, 25);
		add(btnRemoveList);
		lblLlistaSelected = new JLabel("Llista x:");
		lblLlistaSelected.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLlistaSelected.setBounds(397, 27, 265, 15);
		add(lblLlistaSelected);
		
		JButton btnRemoveSong = new JButton("Eliminar canco de la llista");
		btnRemoveSong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(songsList.getSelectedIndex() != -1)
				{
					String line = (String) songsList.getSelectedValue();
					String songAuthor = line.split(",")[0].trim();
					String songTitle = line.split(",")[1].trim();
					PresentationManager.removeSolutionSong(currentSolutionDate, songAuthor, songTitle);
					
					refresh();
					for(int i = 0; i < currentSolutionLists.size(); ++i)
					{
						if(currentSolutionLists.get(i).size() <= 0)
						{
							PresentationManager.removeSolutionList(currentSolutionDate, i);
							break;
						}
					}
					refresh();
				}
			}
		});
		btnRemoveSong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoveSong.setBounds(397, 287, 265, 25);
		add(btnRemoveSong);
		
		JLabel lblLlistes = new JLabel("Llistes:");
		lblLlistes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLlistes.setBounds(33, 28, 265, 15);
		add(lblLlistes);
		
		JButton btnViewGraph = new JButton("Visualitzar graf");
		btnViewGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goToViewGraphLastGeneratedSolution();
			}
		});
		btnViewGraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewGraph.setBounds(237, 487, 265, 25);
		add(btnViewGraph);
		
		JButton buttonSaveSolution = new JButton("Desar solucio");
		buttonSaveSolution.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.saveLastGeneratedSolution();
				PresentationManager.goBack();
			}
		});
		buttonSaveSolution.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonSaveSolution.setBounds(397, 368, 265, 66);
		add(buttonSaveSolution);
		
		comboLists = new JComboBox<String>();
		comboLists.setBounds(397, 324, 105, 24);
		add(comboLists);
		
		JButton btnMoureALa = new JButton("Moure a la llista");
		btnMoureALa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (songsList.getSelectedIndex() == -1) {
					PresentationManager.errorWindow("Has de seleccionar una canco");
					return;
				}
				
				String song = (String) songsList.getSelectedValue();
				
				
			}
		});
		btnMoureALa.setBounds(514, 324, 148, 25);
		add(btnMoureALa);
	}

	public static void setCurrentSolutionDate(String date)
	{
		currentSolutionDate = date;
		currentSolutionLists = PresentationManager.getSolutionCommunities(currentSolutionDate);
	}
	
	public void refresh()
	{
		System.out.println("REFRESH esp");
		currentSolutionLists = PresentationManager.getSolutionCommunities(currentSolutionDate);
		
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();
		DefaultComboBoxModel<String> listsModel = new DefaultComboBoxModel<String>();
		int i = 0;
		for(ArrayList<String> list : currentSolutionLists) {
			++i;
			dlm.addElement("Llista " + String.valueOf(i));
			listsModel.addElement("Llista " + String.valueOf(i));
		}
		listsList.setModel(dlm);

		if(listsList.getSelectedIndex() == -1) songsList.setModel(new DefaultListModel());
		else populateSongList(listsList.getSelectedIndex());

		
		comboLists.setModel(listsModel);
	}
	
	public void populateSongList(int listIndex)
	{
		DefaultListModel dlm = new DefaultListModel();
		ArrayList<String> list = currentSolutionLists.get(listIndex);
		for(String song : list) dlm.addElement(song);
		songsList.setModel(dlm);
	}
}

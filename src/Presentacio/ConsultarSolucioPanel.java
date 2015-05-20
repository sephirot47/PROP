package Presentacio;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.Cursor;

import javax.swing.ListSelectionModel;

import Domini.Graph;
import Domini.Song;

public class ConsultarSolucioPanel extends JPanel 
{
	private static ArrayList<ArrayList<String>> currentSolutionLists = new ArrayList<ArrayList<String>>();
	private static String currentSolutionDate = "";
	private JList listsList, songsList;
	
	public ConsultarSolucioPanel() 
	{
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 61, 255, 414);
		add(scrollPane);
		
		listsList = new JList();
		listsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(listsList.getSelectedIndex() != -1)
				{
					populateSongList(listsList.getSelectedIndex());
				}
			}
		});
		scrollPane.setViewportView(listsList);
		
		JButton btnViewGraph = new JButton("Veure el graf d'entrada");
		btnViewGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goToViewGraph(currentSolutionDate);
			}
		});
		btnViewGraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewGraph.setBounds(264, 503, 255, 23);
		add(btnViewGraph);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(471, 61, 255, 414);
		add(scrollPane_1);
		
		songsList = new JList();
		songsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		songsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(songsList);
		
		JButton btnConsultarCanco = new JButton("Consultar canco");
		btnConsultarCanco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultarCanco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(songsList.getSelectedIndex() != -1)
				{
					String line = (String) songsList.getSelectedValue();
					String author = line.split(",")[0].trim();
					String title = line.split(",")[1].trim();
					PresentationManager.goToEditCanconsPanel(author, title);
				}
			}
		});
		btnConsultarCanco.setBounds(471, 452, 255, 23);
		//add(btnConsultarCanco);
		//tret perque no te molt sentit consultar una canco si a lo millor ja ni existeix :/
		
		JLabel lblNewLabel = new JLabel("Cancons llista:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(471, 36, 255, 14);
		add(lblNewLabel);
		
		JLabel lblLlistesDeReproduccio = new JLabel("Llistes de reproduccio:");
		lblLlistesDeReproduccio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLlistesDeReproduccio.setBounds(105, 36, 255, 14);
		add(lblLlistesDeReproduccio);
	}
	
	public static void setCurrentSolutionDate(String date)
	{
		currentSolutionDate = date;
		currentSolutionLists = PresentationManager.getSolutionCommunities(currentSolutionDate);
	}
	
	public void refresh()
	{
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();
		int i = 0;
		for(ArrayList<String> list : currentSolutionLists) 
			dlm.addElement("Llista " + String.valueOf(++i));
		listsList.setModel(dlm);
		//if(dlm.getSize() > 0) listsList.setSelectedIndex(0);
		if(listsList.getSelectedIndex() == -1) songsList.setModel(new DefaultListModel());
		else populateSongList(listsList.getSelectedIndex());
	}
	
	public void populateSongList(int listIndex)
	{
		DefaultListModel dlm = new DefaultListModel();
		ArrayList<String> list = currentSolutionLists.get(listIndex);
		for(String song : list) dlm.addElement(song);
		songsList.setModel(dlm);
		//if(dlm.getSize() > 0) songsList.setSelectedIndex(0);
	}
}

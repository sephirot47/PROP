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


public class EditarUsuarisPanel extends JPanel
{
	public static String currentUsername = "";
	final private JList listReproductions;
	private JTextField textNom;
	private JTextField textEdat;
	private JButton btnEliminarReproduccio;
	
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
		panelUserDetail.setBounds(0, 30, 364, 498);
		add(panelUserDetail);
		panelUserDetail.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(12, 33, 36, 20);
		panelUserDetail.add(lblNom);
		
		JLabel lblEdat = new JLabel("Edat:");
		lblEdat.setBounds(12, 61, 48, 20);
		panelUserDetail.add(lblEdat);
		
		JLabel lblReproduccions = new JLabel("Reproduccions:");
		lblReproduccions.setBounds(12, 93, 119, 20);
		panelUserDetail.add(lblReproduccions);
		
		listReproductions = new JList();
		listReproductions.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listReproductions.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) 
			{
				System.out.println(arg0.getFirstIndex());
			}
		});
		listReproductions.setBorder(new LineBorder(new Color(0, 0, 0)));
		listReproductions.setBounds(12, 127, 340, 304);
		panelUserDetail.add(listReproductions);
		
		btnEliminarReproduccio = new JButton("Eliminar reproduccio");
		btnEliminarReproduccio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				deleteSelectedReproduction();
			}
		});
		btnEliminarReproduccio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarReproduccio.setBounds(154, 443, 198, 25);
		panelUserDetail.add(btnEliminarReproduccio);
		
		JLabel lblDetallsUsuari = new JLabel("Detalls usuari:");
		lblDetallsUsuari.setBounds(122, 0, 114, 20);
		panelUserDetail.add(lblDetallsUsuari);
		
		textNom = new JTextField();
		textNom.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) 
			{
				refreshAll();
			}
		});
		textNom.setBounds(59, 34, 114, 19);
		panelUserDetail.add(textNom);
		textNom.setColumns(10);
		
		textEdat = new JTextField();
		textEdat.setColumns(10);
		textEdat.setBounds(59, 62, 114, 19);
		panelUserDetail.add(textEdat);
		
		JPanel panelUsers = new JPanel();
		panelUsers.setBounds(400, 29, 311, 497);
		add(panelUsers);
		panelUsers.setLayout(null);
		
		refreshAll();
	}
	
	public void refreshAll()
	{
		refreshReproductionsList();
		refreshTexts();
	}
	
	public void refreshTexts()
	{
		textNom.setText(currentUsername);
		
		int age = PresentationManager.getUserAge(currentUsername);
		textEdat.setText( String.valueOf(age) );
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
			WarningDialog.show("Error", "No es troba l'arxiu de reproduccions d'aquest usuari");
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
	
	private void onSaveChangesClicked()
	{
		String name = textNom.getText();
		int age = 0;
		
		try
		{
			age  = Integer.parseInt(textEdat.getText());
		}
		catch(NumberFormatException e)
		{
			WarningDialog.show("Error", "La edat ha de ser un numero valid.");
			return;
		}
		
		try
		{
			PresentationManager.createUser(name, age);	
		}
		catch(Exception e)
		{
			WarningDialog.show("Error", e.getMessage());
			return;
		}
		
		PresentationManager.goBack();
	}
}

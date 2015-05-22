package Presentacio;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistorialPanel extends JPanel 
{	
	private JList solutionsList;
	private JLabel lblAlgorismeUsatValue, labelNumeroLlistesValue, lblGenerationDateValue;
	
	public HistorialPanel() 
	{
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 58, 232, 411);
		add(scrollPane);
		
		solutionsList = new JList();
		solutionsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solutionsList.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent arg0) 
			{
				String date = (String) solutionsList.getSelectedValue();
				populateSolutionDetails(date);
			}
		});
		solutionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(solutionsList);
		
		JButton btnImport = new JButton("Importar d'un fitxer");
		btnImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.importSolutions();
				refresh();
			}
		});
		btnImport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImport.setBounds(72, 480, 232, 23);
		add(btnImport);
		
		JLabel lblResumSolucio = new JLabel("Resum solucio:");
		lblResumSolucio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResumSolucio.setBounds(414, 35, 175, 23);
		add(lblResumSolucio);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(414, 58, 283, 179);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDataDeGeneracio = new JLabel("Temps de generacio:");
		lblDataDeGeneracio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataDeGeneracio.setBounds(10, 10, 263, 23);
		panel.add(lblDataDeGeneracio);
		
		lblGenerationDateValue = new JLabel("22-2-1995");
		lblGenerationDateValue.setBounds(10, 33, 263, 23);
		panel.add(lblGenerationDateValue);
		
		JLabel lblAlgorismeUsat = new JLabel("Algorisme usat:");
		lblAlgorismeUsat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlgorismeUsat.setBounds(10, 66, 263, 23);
		panel.add(lblAlgorismeUsat);
		
		lblAlgorismeUsatValue = new JLabel("Girvan Newman");
		lblAlgorismeUsatValue.setBounds(10, 90, 263, 23);
		panel.add(lblAlgorismeUsatValue);
		
		JLabel lblNumeroDeLlistes = new JLabel("Numero de llistes generades:");
		lblNumeroDeLlistes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroDeLlistes.setBounds(10, 124, 263, 23);
		panel.add(lblNumeroDeLlistes);
		
		labelNumeroLlistesValue = new JLabel("21332423");
		labelNumeroLlistesValue.setBounds(10, 148, 263, 23);
		panel.add(labelNumeroLlistesValue);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 58, 263, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 115, 263, 2);
		panel.add(separator_1);
		
		JButton btnEliminarSolucio = new JButton("Eliminar Solucio");
		btnEliminarSolucio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(solutionsList.getSelectedIndex() != -1)
				{
					String date = (String) solutionsList.getSelectedValue();
					if(PresentationManager.confirmWindow("Estas segur que vols esborrar la solucio '" + date + "' ?"))
					{
						date = date.replaceAll(":", ",");
						PresentationManager.removeSolutionFromDisk(date);
						refresh();
					}
				}
			}
		});
		btnEliminarSolucio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarSolucio.setBounds(414, 285, 283, 23);
		add(btnEliminarSolucio);
		
		JButton btnConsultarSolucio = new JButton("Consultar Solucio");
		btnConsultarSolucio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(solutionsList.getSelectedIndex() != -1)
				{
					String selectedDate = (String) solutionsList.getSelectedValue();
					selectedDate = selectedDate.replaceAll(":", ",");
					PresentationManager.goToConsultarSolucioPanel(selectedDate);
				}
			}
		});
		btnConsultarSolucio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultarSolucio.setBounds(414, 251, 283, 23);
		add(btnConsultarSolucio);
		
		JLabel lblLlistaSolucions = new JLabel("Llista solucions:");
		lblLlistaSolucions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLlistaSolucions.setBounds(72, 35, 175, 23);
		add(lblLlistaSolucions);
	}
	
	public void refresh()
	{
		PresentationManager.discardLastGeneratedSolution();
		
		ArrayList<String> solutionDates = PresentationManager.getSolutionDates();
		Collections.sort(solutionDates);
		
		DefaultListModel dlm = new DefaultListModel();
		dlm.clear();
		for(String date : solutionDates) 
		{ 
			date = date.replaceFirst(",", ":").replaceFirst(",", ":");
			dlm.addElement(date); 
		}
		solutionsList.setModel(dlm);
		if(solutionsList.getSelectedIndex() == -1) populateSolutionDetails("");
	}
	
	public void populateSolutionDetails(String date)
	{
		if(date != null && !date.equals(""))
		{
			double genTime = PresentationManager.getSolutionGenTime(date) / 1E6;
			int nCommunities = PresentationManager.getSolutionNumberOfCommunities(date);
			String algorithm = PresentationManager.getSolutionAlgorithm(date);
			
			lblGenerationDateValue.setText( String.valueOf(genTime) + " ms");
			labelNumeroLlistesValue.setText( String.valueOf(nCommunities) );
			lblAlgorismeUsatValue.setText( algorithm );
		}
		else
		{
			lblGenerationDateValue.setText("-");
			labelNumeroLlistesValue.setText("-");
			lblAlgorismeUsatValue.setText("-");
		}
	}
}

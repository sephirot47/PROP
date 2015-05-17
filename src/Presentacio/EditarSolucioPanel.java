package Presentacio;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class EditarSolucioPanel extends JPanel 
{
	public EditarSolucioPanel() 
	{
		setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(397, 54, 265, 384);
		add(scrollPane_1);
		
		JList songsList = new JList();
		songsList.setBounds(0, 0, 1, 1);
		scrollPane_1.setViewportView(songsList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 54, 265, 384);
		add(scrollPane);
		
		JList listsList = new JList();
		scrollPane.setViewportView(listsList);
		
		JButton btnRemoveList = new JButton("Eliminar llista de la solucio");
		btnRemoveList.setBounds(33, 450, 265, 25);
		add(btnRemoveList);
		
		JLabel lblNewLabel = new JLabel("Llista x:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(397, 27, 265, 15);
		add(lblNewLabel);
		
		JButton btnRemoveSong = new JButton("Eliminar canco de la llista");
		btnRemoveSong.setBounds(397, 450, 265, 25);
		add(btnRemoveSong);
		
		JLabel lblLlistes = new JLabel("Llistes:");
		lblLlistes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLlistes.setBounds(33, 28, 265, 15);
		add(lblLlistes);
	}
}

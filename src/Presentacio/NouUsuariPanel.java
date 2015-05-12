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

public class NouUsuariPanel extends JPanel {
	private JTextField textNom;
	private JTextField textEdat;

	/**
	 * Create the panel.
	 */
	public NouUsuariPanel() 
	{
		setPreferredSize(new Dimension(800, 560));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(300, 230, 200, 100);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Nom:");
		label.setBounds(0, 12, 36, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Edat:");
		label_1.setBounds(0, 34, 37, 15);
		panel.add(label_1);
		
		JButton btnCrearUsuari = new JButton("Crear usuari");
		btnCrearUsuari.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				onCreateUserClicked();
			}
		});
		btnCrearUsuari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCrearUsuari.setBounds(47, 63, 122, 25);
		panel.add(btnCrearUsuari);
		
		textNom = new JTextField();
		textNom.setBounds(55, 8, 114, 19);
		panel.add(textNom);
		textNom.setColumns(10);
		
		textEdat = new JTextField();
		textEdat.setBounds(55, 32, 114, 19);
		panel.add(textEdat);
		textEdat.setColumns(10);
	}
	
	void onCreateUserClicked()
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

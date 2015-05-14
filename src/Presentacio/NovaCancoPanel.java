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

public class NovaCancoPanel extends JPanel 
{
	private JTextField textNom;
	private JTextField textEdat;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		lblTitol.setBounds(10, 11, 36, 15);
		panel.add(lblTitol);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 53, 37, 15);
		panel.add(lblAutor);
		
		textNom = new JTextField();
		textNom.setBounds(79, 11, 211, 19);
		panel.add(textNom);
		textNom.setColumns(10);
		
		textEdat = new JTextField();
		textEdat.setBounds(79, 53, 211, 19);
		panel.add(textEdat);
		textEdat.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(79, 101, 211, 19);
		panel.add(textField);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setBounds(10, 101, 37, 15);
		panel.add(lblAny);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(79, 149, 211, 19);
		panel.add(textField_1);
		
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
				onCreateUserClicked();
			}
		});
		btnCrearUsuari.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblEstilsDeLa_1 = new JLabel("Estils de la canco");
		lblEstilsDeLa_1.setBounds(435, 151, 315, 15);
		add(lblEstilsDeLa_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(435, 174, 249, 130);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEstil = new JLabel("Estil 1:");
		lblEstil.setBounds(10, 15, 37, 15);
		panel_1.add(lblEstil);
		
		textField_2 = new JTextField();
		textField_2.setBounds(70, 11, 169, 19);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEstil_1 = new JLabel("Estil 2:");
		lblEstil_1.setBounds(10, 44, 37, 15);
		panel_1.add(lblEstil_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(70, 41, 169, 19);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEstil_2 = new JLabel("Estil 3:");
		lblEstil_2.setBounds(10, 72, 37, 15);
		panel_1.add(lblEstil_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(70, 69, 169, 19);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEstilsDeLa = new JLabel("(deixar en blanc si no es volen afegir mes estils)");
		lblEstilsDeLa.setBounds(10, 98, 315, 15);
		panel_1.add(lblEstilsDeLa);
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

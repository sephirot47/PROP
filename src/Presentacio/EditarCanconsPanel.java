package Presentacio;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;

public class EditarCanconsPanel extends JPanel {
	private JTextField textTitle;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public EditarCanconsPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(66, 53, 501, 334);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Titol:");
		lblTitle.setBounds(22, 28, 70, 15);
		panel.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setBounds(85, 26, 360, 19);
		panel.add(textTitle);
		textTitle.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor: ");
		lblAutor.setBounds(22, 86, 70, 15);
		panel.add(lblAutor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(85, 84, 360, 19);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(87, 148, 114, 19);
		panel.add(textField_1);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setBounds(22, 150, 70, 15);
		panel.add(lblAny);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setBounds(234, 150, 70, 15);
		panel.add(lblDuracio);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(297, 148, 148, 19);
		panel.add(textField_2);
		
		JLabel lblEstils = new JLabel("Estils:");
		lblEstils.setBounds(22, 205, 70, 15);
		panel.add(lblEstils);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(22, 232, 235, 85);
		panel.add(list);
		
		JButton btnSaveChanges = new JButton("Desar canvis");
		btnSaveChanges.setBounds(280, 292, 165, 25);
		panel.add(btnSaveChanges);

	}
}

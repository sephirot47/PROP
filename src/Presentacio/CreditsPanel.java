package Presentacio;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CreditsPanel extends JPanel {

	public CreditsPanel()
	{
		super();
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(266, 205, 266, 194);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblcCredits = new JLabel("<html>Credits<br><br>Abraham Cortes<br>Aina Soler<br>Joan Fons<br>Victor Anton Dominguez");
		lblcCredits.setBounds(20, 29, 226, 136);
		panel.add(lblcCredits);
		lblcCredits.setHorizontalAlignment(SwingConstants.CENTER);
	}
}

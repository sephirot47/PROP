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
		
		JLabel lblcCredits = new JLabel("<html>Crèdits<br><br>Víctor Antón Domínguez<br>Joan Fons<br>Abraham<br>Aina Soler");
		lblcCredits.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblcCredits, BorderLayout.CENTER);
	}
}

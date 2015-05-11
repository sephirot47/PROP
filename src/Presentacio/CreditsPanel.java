package Presentacio;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CreditsPanel extends TemplatePanel {

	public CreditsPanel()
	{
		super("Crèdits");
		
		JLabel lblcCredits = new JLabel("<html>Crèdits<br><br>Víctor Antón Domínguez<br>Joan Fons<br>Abraham<br>Aina Soler");
		lblcCredits.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblcCredits, BorderLayout.CENTER);
	}
}

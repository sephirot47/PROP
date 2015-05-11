package Presentacio;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class EstadistiquesPanel extends TemplatePanel {

	public EstadistiquesPanel()
	{
		super();

		JButton btnEstadstiques = new JButton("Go to crèdits");
		btnEstadstiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(CreditsPanel.class.getSimpleName());
			}
		});
		
		add(btnEstadstiques);
		btnEstadstiques.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadstiques.setMaximumSize(new Dimension(999, 25));
		btnEstadstiques.setAlignmentY(0.0f);
	}
}

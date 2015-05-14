package Presentacio;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class ViewPanel extends JPanel {

	public static ViewPanel viewPanel;
	public JPanel cardContainer;
	
	public ViewPanel() 
	{
		viewPanel = this;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		cardContainer = new JPanel();
		add(cardContainer);
		cardContainer.setLayout(new CardLayout(0, 0));
		
		JPanel frame = new JPanel();
		frame.setMaximumSize(new Dimension(32767, 20));
		add(frame);
		frame.setLayout(new BorderLayout(0, 0));
		
		frame.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(frame, BorderLayout.WEST);
		JButton btnEnrerepanel = new JButton("Enrere");
		btnEnrerepanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnrerepanel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				PresentationManager.goBack();
			}
		});
		btnEnrerepanel.setHorizontalAlignment(SwingConstants.LEFT);
		frame.add(btnEnrerepanel, BorderLayout.WEST);

		GestioCanconsPanel gcp = new GestioCanconsPanel();
		GestioUsuarisPanel gup = new GestioUsuarisPanel();
		GenerarLlistesPanel recp = new GenerarLlistesPanel();
		EstadistiquesPanel estp = new EstadistiquesPanel();
		CreditsPanel credp = new CreditsPanel();
		NouUsuariPanel nup = new NouUsuariPanel();
		EditarUsuarisPanel edup= new EditarUsuarisPanel();
		EditarCanconsPanel ecp = new EditarCanconsPanel();
		
		cardContainer.add(gcp, GestioCanconsPanel.class.getSimpleName());
		cardContainer.add(gup, GestioUsuarisPanel.class.getSimpleName());
		cardContainer.add(recp, GenerarLlistesPanel.class.getSimpleName());
		cardContainer.add(estp, EstadistiquesPanel.class.getSimpleName());
		cardContainer.add(credp, CreditsPanel.class.getSimpleName());
		cardContainer.add(nup, NouUsuariPanel.class.getSimpleName());
		cardContainer.add(edup, EditarUsuarisPanel.class.getSimpleName());
		cardContainer.add(ecp, EditarCanconsPanel.class.getSimpleName());
	}

}

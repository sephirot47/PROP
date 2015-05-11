package Presentacio;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TemplatePanel extends JPanel
{
	public TemplatePanel()
	{
		String name = getClass().getSimpleName(); 
		setName(name);
		MainWindow.frmYoutube.getContentPane().add(this, name);
		setLayout(new BorderLayout(0, 0));
		JPanel BackButtonpanel = new JPanel();
		BackButtonpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(BackButtonpanel, BorderLayout.SOUTH);
		BackButtonpanel.setLayout(new BoxLayout(BackButtonpanel, BoxLayout.X_AXIS));
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
		BackButtonpanel.add(btnEnrerepanel);
	}
}

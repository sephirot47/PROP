package Presentacio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class DialogGenerantSolucio extends JDialog 
{
	public static JLabel label;

	public DialogGenerantSolucio() 
	{
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 373, 140);
		getContentPane().setLayout(null);
		
		label = new JLabel("Generant solucio");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 23, 367, 41);
		getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Cancelar generacio");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				GenerarLlistesPanel.aborted = true;
			}
		});
		btnNewButton.setBounds(111, 67, 152, 23);
		getContentPane().add(btnNewButton);
		
		setResizable(false);
		setVisible(true);
	}
}

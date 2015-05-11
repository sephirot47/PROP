package Presentacio;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
public class PanelPrueba extends TemplatePanel 
{
	public PanelPrueba() 
	{
		super();
		
		JFileChooser fileChooser = new JFileChooser();
		add(fileChooser);
	}

}

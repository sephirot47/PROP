package Presentacio;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import org.jgrapht.graph.*; //Aina: He comentat esto perque em deixes compilar. Si l'useu descomenteu ;)

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class EstadistiquesPanel extends JPanel 
{
	private final ButtonGroup algorisme = new ButtonGroup();
	private final ButtonGroup metodes = new ButtonGroup();
	private final ButtonGroup ym = new ButtonGroup();
	public EstadistiquesPanel()
	{
		super();

		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panelLeft, BorderLayout.WEST);
		
		JPanel panelRight = new JPanel();
		panelRight.setBorder(new EmptyBorder(40, 40, 40, 40));
		add(panelRight, BorderLayout.EAST);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JButton btnEstadstiques = new JButton("Go to cr�dits");
		btnEstadstiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(CreditsPanel.class.getSimpleName());
			}
		});
		
		
		Box left = Box.createVerticalBox();
		
		
		JRadioButton Girvan = new JRadioButton("Newman-Girvan");
		algorisme.add(Girvan);
		left.add(Girvan);
		
		JRadioButton clique = new JRadioButton("Clique Percolation");
		algorisme.add(clique);
		left.add(clique);
		
		JRadioButton louvain = new JRadioButton("Louvain");
		algorisme.add(louvain);
		left.add(louvain);
		
		
		JLabel label = new JLabel("---------------------------");
		left.add(label);
		
		
		JRadioButton Grafica = new JRadioButton("Grafica");
		metodes.add(Grafica);
		left.add(Grafica);
		
		JRadioButton taula = new JRadioButton("Taula");
		metodes.add(taula);
		left.add(taula);
		
		JLabel label1 = new JLabel("---------------------------");
		left.add(label1);
		
		
		JRadioButton nodes = new JRadioButton("Temps-Nodes");
		ym.add(nodes);
		left.add(nodes);
		
		JRadioButton edges = new JRadioButton("Temps-Edges");
		ym.add(edges);
		left.add(edges);
		JRadioButton altres = new JRadioButton("Altres");
		ym.add(altres);
		left.add(altres);
		
		panelLeft.add(left);
		
			ArrayList<Double[]> dadesinfo = new ArrayList<Double[]>();
		try {
			dadesinfo = PresentationManager.getInfos();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		 if (!dadesinfo.isEmpty())
		 {
			 
		 }
		
		
		btnEstadstiques.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadstiques.setMaximumSize(new Dimension(999, 25));
		btnEstadstiques.setAlignmentY(0.0f);
		panelRight.add(btnEstadstiques);
		
	}
}

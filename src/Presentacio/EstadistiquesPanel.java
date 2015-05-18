package Presentacio;


import org.jfree.chart.*;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.*;
import org.jfree.data.xy.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Domini.Pair;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EstadistiquesPanel extends JPanel 
{
	private final ButtonGroup algorisme = new ButtonGroup();
	private final ButtonGroup metodes = new ButtonGroup();
	private final ButtonGroup ym = new ButtonGroup();
	private JPanel panelLeft = new JPanel();
	private JLabel vuit = new JLabel("No hi ha dades");
	
	private ArrayList<ArrayList<Pair<Double,Integer>>> dadesinfo = new ArrayList<ArrayList<Pair<Double,Integer>>>();
	private Panel grafica = new Panel();
	public EstadistiquesPanel()
	{
		super();
		initComponents();
	}
	public void initComponents(){
		
		
		vuit.setSize(30, 90);
		vuit.setVisible(false);
		panelLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panelLeft, BorderLayout.WEST);
		
		
		grafica.setPreferredSize(new Dimension(500, 500));
		grafica.setMinimumSize(new Dimension(50, 50));
		add(grafica);
		grafica.setLayout(null);
		
		grafica.removeAll();
		
		
		
		
		JPanel panelRight = new JPanel();
		panelRight.setBorder(new EmptyBorder(40, 40, 40, 40));
		add(panelRight, BorderLayout.EAST);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JButton btnEstadstiques = new JButton("Go to crèdits");
		btnEstadstiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PresentationManager.goToCard(CreditsPanel.class.getSimpleName());
				
			}
		});
		btnEstadstiques.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadstiques.setMaximumSize(new Dimension(999, 25));
		btnEstadstiques.setAlignmentY(0.0f);
		panelRight.add(btnEstadstiques);
		
		Box left = Box.createVerticalBox();
		
		final JRadioButton Girvan = new JRadioButton("Newman-Girvan");
		algorisme.add(Girvan);
		left.add(Girvan);
		
		final JRadioButton clique = new JRadioButton("Clique Percolation");
		algorisme.add(clique);
		left.add(clique);
		
		final JRadioButton louvain = new JRadioButton("Louvain");
		algorisme.add(louvain);
		left.add(louvain);
		
		final JRadioButton tots = new JRadioButton("Tots");
		algorisme.add(tots);
		left.add(tots);
		
		JLabel label = new JLabel("---------------------------");
		left.add(label);
		
		final JRadioButton Grafica = new JRadioButton("Grafica");
		metodes.add(Grafica);
		left.add(Grafica);
		
		final JRadioButton taula = new JRadioButton("Taula");
		metodes.add(taula);
		left.add(taula);
		
		JLabel label1 = new JLabel("---------------------------");
		left.add(label1);
		
		
		final JRadioButton nodes = new JRadioButton("Temps-Nodes");
		ym.add(nodes);
		left.add(nodes);
		
		final JRadioButton edges = new JRadioButton("Temps-Edges");
		ym.add(edges);
		left.add(edges);
		final JRadioButton altres = new JRadioButton("Altres");
		ym.add(altres);
		left.add(altres);

		panelLeft.add(left);

		Girvan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafiques(Girvan.isSelected(), Grafica.isSelected(), nodes.isSelected(), "Girvan-Newman");
			}
		});
		
		clique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafiques(clique.isSelected(), Grafica.isSelected(), nodes.isSelected(), "Clique");
			}
		});
		
		louvain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafiques(louvain.isSelected(), Grafica.isSelected(), nodes.isSelected(), "Louvain");
			}
		});
		
		tots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafiques(tots.isSelected(), Grafica.isSelected(), nodes.isSelected(), "Tots");
			}
		});
		Grafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String alg = "";
				String met = "";
				boolean but = false;
				if(Girvan.isSelected()){
					alg = "Girvan-Newman";
					but = true;
				}
				else if(louvain.isSelected()){
					alg = "Louvain";
					but = true;
				}
				else if(clique.isSelected()){
					alg = "Clique";
					but = true;
				}
				else{
					alg = "Tots";
					but = true;
				}

				grafiques(but, true, nodes.isSelected(), alg);
			}
		});
		
		nodes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String alg = "";		
				boolean but = false;
				if(Girvan.isSelected()){
					alg = "Girvan-Newman";
					but = true;
				}
				else if(louvain.isSelected()){
					alg = "Louvain";
					but = true;
				}
				else if(clique.isSelected()){
					alg = "Clique";
					but = true;
				}
				else{
					alg = "Tots";
					but = true;
				}

				grafiques(but, Grafica.isSelected(), true, alg);			
			}
		});
				
	}
	public void grafiques(boolean algoritme, boolean forma, boolean tipus, String alg) {
		
		ChartPanel panel = null;
		
		//grafica.add(vuit);
		
		if(algoritme && forma && tipus){	
			grafica.removeAll();
			
			try {
				dadesinfo.removeAll(dadesinfo);
				if(alg != "Tots"){
					dadesinfo.add(PresentationManager.getInfos(alg.charAt(0)));
				}
				else{
					dadesinfo.add(PresentationManager.getInfos('G'));
					dadesinfo.add(PresentationManager.getInfos('C'));
					dadesinfo.add(PresentationManager.getInfos('L'));
				}
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
			if (!dadesinfo.isEmpty())
			{
				
				
				JFreeChart chart = null;
				
				XYSplineRenderer renderer = new XYSplineRenderer();
				XYSeriesCollection dades = new XYSeriesCollection();
				
				ValueAxis x = new NumberAxis();
				ValueAxis y = new NumberAxis();
				
				
				XYPlot plot;
				if(alg != "Tots"){
					XYSeries serie = new XYSeries(alg);			
					for(int i = 0; i < dadesinfo.get(0).size(); i++){						
							 serie.add(dadesinfo.get(0).get(i).getSecond(),dadesinfo.get(0).get(i).getFirst());
							 
					}
					dades.addSeries(serie);
				}
				else{
					XYSeries serie = new XYSeries("Girvan-Newman");
					XYSeries serie1 = new XYSeries("Clique");
					XYSeries serie2 = new XYSeries("Louvain");
					for(int x1 = 0; x1 < dadesinfo.size(); x1++){
						for(int i = 0; i < dadesinfo.get(x1).size(); i++){
							double n = dadesinfo.get(x1).get(i).getFirst()/1000;
							if(x1 == 0)	serie.add(n,dadesinfo.get(x1).get(i).getSecond());							
							if(x1 == 1)serie1.add(n,dadesinfo.get(x1).get(i).getSecond());
							else serie2.add(n,dadesinfo.get(x1).get(i).getSecond());
						}
					}
					if(!serie.isEmpty()) dades.addSeries(serie);
					
					if(!serie1.isEmpty()) {
						dades.addSeries(serie1);
						renderer.setSeriesPaint(1, Color.GREEN);
					}
					if(!serie2.isEmpty()){
						dades.addSeries(serie2);
						renderer.setSeriesPaint(2, Color.BLUE);
					}				
				}
				chart = ChartFactory.createXYLineChart(alg,"Temps","Nodes",dades,PlotOrientation.HORIZONTAL,true,false,false);
				chart.setBackgroundPaint(Color.white);
				plot =  chart.getXYPlot(); 
				renderer.setSeriesPaint(0, Color.RED);				
				renderer.setSeriesStroke(0, new BasicStroke(1.0f));
				plot.setRenderer(renderer);
				
				panel = new ChartPanel(chart);
				panel.setBounds(5, 10, 410, 400);
				 
				grafica.add(panel);
				grafica.repaint();
				
				
			 }
			 else
			 {
				grafica.repaint();
			 }
		} 
	}		
}
	


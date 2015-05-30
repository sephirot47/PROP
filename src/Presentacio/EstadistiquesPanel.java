package Presentacio;

import org.jfree.chart.*;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
import javax.swing.JScrollPane;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

public class EstadistiquesPanel extends JPanel 
{
	private final ButtonGroup algorisme = new ButtonGroup();
	private final ButtonGroup metodes = new ButtonGroup();
	private final ButtonGroup ym = new ButtonGroup();
	private JPanel panelLeft = new JPanel();
	
	
	private ArrayList<ArrayList<Pair<Double,Integer>>> dadesinfo = new ArrayList<ArrayList<Pair<Double,Integer>>>();
	private JPanel grafica = new JPanel();
	public EstadistiquesPanel()
	{
		super();
		initComponents();
	}
	public void initComponents(){

		panelLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panelLeft, BorderLayout.WEST);		
		
		grafica.setPreferredSize(new Dimension(500, 500));
		grafica.setMinimumSize(new Dimension(50, 50));
		add(grafica);
		grafica.setLayout(null);
		grafica.removeAll();
		
		Box left = Box.createVerticalBox();
		
		
		
		final JRadioButton Girvan = new JRadioButton("Newman-Girvan");
		Girvan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		algorisme.add(Girvan);
		left.add(Girvan);
		
		final JRadioButton clique = new JRadioButton("Clique Percolation");
		clique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		algorisme.add(clique);
		left.add(clique);
		
		final JRadioButton louvain = new JRadioButton("Louvain");
		louvain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		algorisme.add(louvain);
		left.add(louvain);
		
		final JRadioButton tots = new JRadioButton("Tots");
		tots.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		algorisme.add(tots);
		left.add(tots);
		
		JLabel label = new JLabel("---------------------------");
		left.add(label);
		
		final JRadioButton Grafica = new JRadioButton("Grafica");
		Grafica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		metodes.add(Grafica); 
		left.add(Grafica);
		
		final JRadioButton taula = new JRadioButton("Taula");
		taula.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		metodes.add(taula);
		left.add(taula);
		
		JLabel label1 = new JLabel("---------------------------");
		left.add(label1);
		
		
		final JRadioButton nodes = new JRadioButton("Temps-Nodes");
		nodes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ym.add(nodes);
		left.add(nodes);
		
		final JRadioButton edges = new JRadioButton("Temps-Edges");
		edges.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ym.add(edges);
		left.add(edges);
		final JRadioButton altres = new JRadioButton("Altres");
		altres.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ym.add(altres);
		left.add(altres);

		panelLeft.add(left);

		Girvan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String met = "";
				if(taula.isSelected()) met = "Taula";
				else if(Grafica.isSelected()) met = "Grafica";
				grafiques(Girvan.isSelected(), met, nodes.isSelected(), "Girvan-Newman");
			}
		});
		
		clique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String met = "";
				if(taula.isSelected()) met = "Taula";
				else if(Grafica.isSelected()) met = "Grafica";
				grafiques(clique.isSelected(), met, nodes.isSelected(), "Clique");
			}
		});
		
		louvain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String met = "";
				if(taula.isSelected()) met = "Taula";
				else if(Grafica.isSelected()) met = "Grafica";
				grafiques(louvain.isSelected(), met, nodes.isSelected(), "Louvain");
			}
		});
		
		tots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String met = "";
				if(taula.isSelected()) met = "Taula";
				else if(Grafica.isSelected()) met = "Grafica";
				grafiques(tots.isSelected(), met, nodes.isSelected(), "Tots");
			}
		});
		Grafica.addActionListener(new ActionListener() {
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
				else if(tots.isSelected()){
					alg = "Tots";
					but = true;
				}

				grafiques(but, "Grafica", nodes.isSelected(), alg);
			}
		});
		taula.addActionListener(new ActionListener() {
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
				else if(tots.isSelected()){
					alg = "Tots";
					but = true;
				}

				grafiques(but, "Taula", nodes.isSelected(), alg);
			}
		});
		
		nodes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String alg = "";
				String met = "";
				if(taula.isSelected()) met = "Taula";
				else if(Grafica.isSelected()) met = "Grafica";
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
				else if(tots.isSelected()){
					alg = "Tots";
					but = true;
				}
				
				grafiques(but, met, true, alg);			
			}
		});
				
	}
	public void grafiques(boolean algoritme, String forma, boolean tipus, String alg) {
		
		ChartPanel panel = null;
		if(algoritme && forma.equals("Grafica") && tipus){	
			grafica.removeAll();
			
			try {
				grafica.removeAll();
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
				double mitja = 0.0f;
				final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
				XYSeriesCollection dades = new XYSeriesCollection();
				
				XYPlot plot;
				if(alg != "Tots"){
					XYSeries serie = new XYSeries(alg);
					XYSeries desviationp = new XYSeries("Desviació positiva " + alg);
					XYSeries desviationn = new XYSeries("Desviació negativa " + alg);
					if(alg == "Louvain"){
						for(int i = 0; i < dadesinfo.get(0).size(); i++){
							serie.add(dadesinfo.get(0).get(i).getSecond().doubleValue(),dadesinfo.get(0).get(i).getFirst()/100);
							mitja += dadesinfo.get(0).get(i).getFirst()/100;
						}
						
					}else{	
						for(int i = 0; i < dadesinfo.get(0).size(); i++){						
							 serie.add(dadesinfo.get(0).get(i).getSecond().doubleValue(),dadesinfo.get(0).get(i).getFirst()/1000000 );
							 mitja += dadesinfo.get(0).get(i).getFirst()/1000000; 
						}
					}
					mitja = mitja/dadesinfo.get(0).size();
					dades.addSeries(serie);
					double standard = 0.0f;
					double standardf;
					
					if(alg != "Louvain"){
						for(int i = 0; i < dadesinfo.get(0).size(); i++){
							 standard = Math.sqrt(Math.pow((dadesinfo.get(0).get(i).getFirst()/1000000 - mitja),2));						 
							 desviationp.add(dades.getSeries(0).getX(i).doubleValue(), (dades.getSeries(0).getY(i).doubleValue() + standard));
							 desviationn.add(dades.getSeries(0).getX(i).doubleValue(), (dades.getSeries(0).getY(i).doubleValue() - standard));
						 }
					}
					else{
						for(int i = 0; i < dadesinfo.get(0).size(); i++){
							 standard = Math.sqrt(Math.pow((dadesinfo.get(0).get(i).getFirst()/100 - mitja),2));						 
							 desviationp.add(dades.getSeries(0).getX(i).doubleValue(), (dades.getSeries(0).getY(i).doubleValue() + standard));
							 desviationn.add(dades.getSeries(0).getX(i).doubleValue(), (dades.getSeries(0).getY(i).doubleValue() - standard));
						 }
					}
					 
	
					 if(!desviationp.isEmpty() && !desviationn.isEmpty()){
						 dades.addSeries(desviationp);
						 renderer.setSeriesPaint(1, new Color(255,78,78));
						 renderer.setSeriesStroke(1, new BasicStroke(1.0f));
						 dades.addSeries(desviationn);
						 renderer.setSeriesPaint(2, new Color(2,78,78));
						 renderer.setSeriesStroke(2, new BasicStroke(1.0f));
					 }
					 
					 
				}
				else{
					
					XYSeries serie = new XYSeries("Girvan-Newman");
					XYSeries serie1 = new XYSeries("Clique");
					XYSeries serie2 = new XYSeries("Louvain");
					for(int x1 = 0; x1 < dadesinfo.size(); x1++){
						for(int i = 0; i < dadesinfo.get(x1).size(); i++){
							double n = dadesinfo.get(x1).get(i).getFirst()/1000000;
							if(x1 == 0)	serie.add(dadesinfo.get(x1).get(i).getSecond().doubleValue(),n);							
							if(x1 == 1)serie1.add(dadesinfo.get(x1).get(i).getSecond().doubleValue(),n);
							else if(x1 == 2) serie2.add(dadesinfo.get(x1).get(i).getSecond().doubleValue(),dadesinfo.get(x1).get(i).getFirst()/100);
						}
					}
					int count = 0;
					if(!serie.isEmpty()){
						dades.addSeries(serie);
						count++;
					}
					
					if(!serie1.isEmpty()) {
						dades.addSeries(serie1);
						renderer.setSeriesPaint(count, Color.GREEN);
						count++;
					}
					if(!serie2.isEmpty()){
						dades.addSeries(serie2);
						renderer.setSeriesPaint(count, Color.BLUE);
					}				
				}
				chart = ChartFactory.createXYLineChart(alg,"Nodes","Temps",(XYDataset)dades,PlotOrientation.VERTICAL,true,false,false);
				chart.setBackgroundPaint(Color.white);
				plot =  chart.getXYPlot(); 
				
				
				//PER TAL DE CANVIAR EL FORMAT DE LES Y
				//DecimalFormat newFormat = new DecimalFormat("0.00");
				
				XYPlot xyPlot = chart.getXYPlot();
				ValueAxis domainAxis = xyPlot.getDomainAxis();

				
				//((NumberAxis) domainAxis).setNumberFormatOverride(newFormat);
				//((NumberAxis) domainAxis).setTickUnit(new NumberTickUnit(0.1f));
				domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
				plot.setDomainAxis(domainAxis);
				
				//color de la primera serie i tamany de la linia
				renderer.setSeriesPaint(0, Color.RED);				
				renderer.setSeriesStroke(0, new BasicStroke(1.0f));
				plot.setRenderer(renderer);
				
				
				panel = new ChartPanel(chart);
				panel.setBounds(5, 10, 500, 450);
				 
				grafica.add(panel);
				grafica.repaint();
				
				
			 }
			else
			 {
				grafica.repaint();
			 }
		} 
		else if(algoritme && forma.equals("Taula") && tipus){
			 
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
			 
			 if(!alg.equals("Tots") && !dadesinfo.get(0).isEmpty()){
				 String[] ColumNames = {
						 "Temps","Nodes"
				 };
				 Object[][] fields = new String[dadesinfo.get(0).size()][2];	 
				 for(int i = 0; i< dadesinfo.get(0).size(); i++){
					 if(alg != "Louvain"){
						 Double n = dadesinfo.get(0).get(i).getFirst()/1000000;
						 fields[i][0] = n.toString();
						 fields[i][1] = dadesinfo.get(0).get(i).getSecond().toString();
					 }
					 else{
						 Double n = dadesinfo.get(0).get(i).getFirst()/100;
						 fields[i][0] = n.toString();
						 fields[i][1] = dadesinfo.get(0).get(i).getSecond().toString();
					 }			
				 }
				 JTable table = new JTable(fields,ColumNames);
				 grafica.removeAll();
				 table.setBounds(10,10,500,400);
				 
				 grafica.add(table,BorderLayout.CENTER);
				 //JScrollPane tableContainer = new JScrollPane(table);			     
				 //grafica.add(tableContainer,BorderLayout.CENTER);
			     grafica.repaint();
			 }
			 else if(!dadesinfo.get(0).isEmpty()){
				 String[] ColumNames = {
						 "Algorisme","Temps","Nodes"
				 };
				 String[] Algorisme={
						 "Girvan-Newman","Clique","Louvain"
				 };
				 Object[][] fields = new String[dadesinfo.get(0).size() + dadesinfo.get(1).size() + dadesinfo.get(2).size()][3];
				 int count = 0;

				 for(int x1 = 0; x1 < dadesinfo.size(); x1++){
					 for(int i = 0; i< dadesinfo.get(x1).size(); i++){
						 
						 if(x1 != 2){
							 Double n = dadesinfo.get(x1).get(i).getFirst()/1000000;
							 fields[count][0] = Algorisme[x1];
							 fields[count][1] = n.toString();
							 fields[count][2] = dadesinfo.get(x1).get(i).getSecond().toString();
						 }
						 else{
							 Double n = dadesinfo.get(x1).get(i).getFirst()/100;
							 fields[count][0] = Algorisme[x1];
							 fields[count][1] = n.toString();
							 fields[count][2] = dadesinfo.get(x1).get(i).getSecond().toString();
						 }
						 ++count;
					 }
				 }
				 
				 JTable table = new JTable(fields,ColumNames);
				 grafica.removeAll();
				 table.setBounds(10,10,500,400);
				 grafica.add(table,BorderLayout.CENTER);
				 grafica.repaint();
			 }
			 else{
				 JTable table = new JTable();
				 grafica.removeAll();
				 table.setBounds(10,10,500,400);
				 grafica.add(table,BorderLayout.CENTER);
				 grafica.repaint();
				 
			 }
		 }
	}
	public void refresh() {
		// TODO Auto-generated method stub
		
	}		
}
	


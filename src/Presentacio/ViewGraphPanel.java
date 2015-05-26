package Presentacio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.util.VertexShapeFactory;

import javax.swing.JScrollPane;

import org.apache.commons.collections15.Transformer;

import Domini.Pair;

import java.awt.dnd.DragGestureEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.StrokeBorder;

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.SystemColor;

public class ViewGraphPanel extends JPanel 
{
	private static VisualizationViewer vv;
	private static Graph<Pair<String, Integer>, Pair<String, Float>> g; //GRAF de JUNG, no el nostre de DOMINI
	private static SpringLayout<Pair<String, Integer>, Pair<String, Float>> graphLayout;
	private static boolean graphLocked = false;
	private static int layoutOffsetX = 15, layoutOffsetY = 15;
	private static int layoutWidth = 550, layoutHeight = 365;
	private static float currentZoom = 1.0f, zoomStep = 1.05f;
	private static float maxZoom = 2.0f, minZoom = 0.7f;
	private static Set< Pair<String, Integer> > verticesAdded;
	private static Pair<String, Integer> selectedVertex = null;
	private static Set< Pair<String, Float> > edgesVisible;
	private static Pair<ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > , ArrayList< Pair<String, Integer> > > graphCommunities;
	private JButton btnPausar;
	private JLabel labelTitol, labelAutor, labelDuracio, labelAny, labelEstils;
	
	@SuppressWarnings("unchecked")
	public ViewGraphPanel() 
	{
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(20, 20, 750, 463);
		add(panel);

		g = new UndirectedSparseGraph<Pair<String, Integer>, Pair<String, Float>>();
		graphLayout = new SpringLayout<Pair<String, Integer>, Pair<String, Float>>(g);
		graphLayout.setSize( new Dimension(layoutWidth, layoutHeight) );
		vv = new VisualizationViewer(graphLayout);
		vv.setBackground(SystemColor.controlLtHighlight);
		
		vv.addGraphMouseListener(new GraphMouseListener()
		{
			public void graphClicked(Object t, MouseEvent arg1) 
			{
				edgesVisible.clear();
				
				Pair<String, Integer> vertex = (Pair<String, Integer>)t;
				if(selectedVertex != null && selectedVertex.equals(vertex)) 
				{
					selectedVertex = null;
					clearSongData();
					return;
				}
				
				selectedVertex = vertex;
				setSongData(vertex.getFirst());

				ArrayList<Pair<String, Float>> edges = new ArrayList<Pair<String, Float>>(g.getIncidentEdges(vertex));
				for(Pair<String, Float> e : edges)
				{
					edgesVisible.add(e);
				}
				
				vv.invalidate();
			}

			public void graphPressed(Object t, MouseEvent arg1) {
			}
			
			public void graphReleased(Object t, MouseEvent arg1) 
			{
				clearSongData();
			}
		});

		vv.addMouseWheelListener(new MouseWheelListener() 
		{
			public void mouseWheelMoved(MouseWheelEvent e) 
			{
				float delta = e.getWheelRotation();
				if(delta < 0) currentZoom *= Math.abs(delta) * zoomStep;
				else currentZoom /= delta * zoomStep;
				if(currentZoom < minZoom) currentZoom = minZoom;
				else if(currentZoom > maxZoom) currentZoom = maxZoom;
				applyZoom(currentZoom);
			}
		});
		vv.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		vv.setBounds(75, 5, 600, 400);

		panel.setLayout(null);
		
		panel.add(vv);
		
		btnPausar = new JButton("Pausar animacio");
		btnPausar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPausar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				triggerGraphAnimation();
			}
		});
		btnPausar.setBounds(75, 416, 250, 23);
		panel.add(btnPausar);
		
		JButton btnRedibuixarGraf = new JButton("Redibuixar graf");
		btnRedibuixarGraf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRedibuixarGraf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				redrawGraphInstant();
			}
		});
		btnRedibuixarGraf.setBounds(425, 416, 250, 23);
		panel.add(btnRedibuixarGraf);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(101, 470, 590, 96);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titol:");
		lblNewLabel.setBounds(28, 12, 70, 15);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setBounds(28, 39, 70, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estils:");
		lblNewLabel_2.setBounds(222, 12, 58, 15);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Any:");
		lblNewLabel_3.setBounds(302, 39, 58, 15);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duracio:");
		lblNewLabel_4.setBounds(468, 39, 70, 15);
		panel_1.add(lblNewLabel_4);
		
		labelAutor = new JLabel("----");
		labelAutor.setBounds(86, 12, 124, 15);
		panel_1.add(labelAutor);
		
		labelTitol = new JLabel("----");
		labelTitol.setBounds(86, 39, 124, 15);
		panel_1.add(labelTitol);
		
		labelAny = new JLabel("----");
		labelAny.setBounds(372, 39, 70, 15);
		panel_1.add(labelAny);
		
		labelEstils = new JLabel("----");
		labelEstils.setBounds(292, 12, 286, 15);
		panel_1.add(labelEstils);
		
		labelDuracio = new JLabel("----");
		labelDuracio.setBounds(550, 38, 108, 15);
		panel_1.add(labelDuracio);
		applyZoom(1.0);
		
		verticesAdded = new HashSet< Pair<String, Integer> >();
		edgesVisible =  new HashSet< Pair<String, Float> >();
	}
	
	public static void setCurrentGraph(Pair<ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > , ArrayList< Pair<String, Integer> > > graphCommunities)
	{
		/*
		 * El graf es una llista d'adjacencies:
		 * Array(
		 * 			Pair( "A", Array("B", "C", "D"),
		 * 			Pair( "X", Array("X", "Y", "A", ...)),
		 * 			...
		 * 		)
		 */
		
		ViewGraphPanel.graphCommunities = graphCommunities;
		
		g = new UndirectedSparseGraph();
		graphLayout.setGraph(g);
	
		ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > graph = graphCommunities.getFirst();
		ArrayList< Pair<String, Integer> > communities = graphCommunities.getSecond();
		final int numCommunities = communities.size();

		verticesAdded.clear();
		for(Pair< String, ArrayList< Pair<String, Float> > > adjacencies : graph)
		{
			Pair<String, Integer> vertex = new Pair<String, Integer>();
			String nomVertex = adjacencies.getFirst();
			vertex.setFirst(nomVertex); //Posem el nom del vertex
			vertex.setSecond(0);
			
			//Afegim comunitats
			for(Pair<String, Integer> com : communities)
			{
				String author = nomVertex.split(",")[0].trim();
				String title = nomVertex.split(",")[1].trim();
				String authorCom = com.getFirst().split(",")[0].trim();
				String titleCom = com.getFirst().split(",")[1].trim();
				
				if(author.equals(authorCom) && title.equals(titleCom))
				{
					Integer indexComunitat = com.getSecond();
					vertex.setSecond(indexComunitat);
				}
			}
			verticesAdded.add(vertex);
			g.addVertex(vertex);
		}
		
		for(Pair<String, Integer> vertex : verticesAdded)
		{
			String nomVertex = vertex.getFirst();
			
			//Afegim edges
			for(Pair< String, ArrayList< Pair<String, Float> > > adjacencies : graph)
			{
				String author = nomVertex.split(",")[0].trim();
				String title = nomVertex.split(",")[1].trim();
				String authorN = adjacencies.getFirst().split(",")[0].trim();
				String titleN = adjacencies.getFirst().split(",")[1].trim();
				
				if(author.equals(authorN) && title.equals(titleN))
				{
					//Hemos encontrado el vertice en la lista de adyacencias
					//La recorremos y anadimos sus edges
					for(Pair<String, Float> edge : adjacencies.getSecond())
					{
						String nomVertexAdjacent = edge.getFirst();
						Float pesEdge = edge.getSecond();
						Pair<String, Integer> adjVertex = new Pair<String, Integer>();
						
						//Busquem el adjacent vertex a la llista de vertices
						for(Pair<String, Integer> v : verticesAdded) 
							if(v.getFirst().equals(nomVertexAdjacent)) { adjVertex = v; break;}

						Pair<String, Float> edgePair = new Pair<String, Float>();
						edgePair.setFirst(nomVertex + nomVertexAdjacent);
						edgePair.setSecond(pesEdge);
						g.addEdge(edgePair, vertex, adjVertex);
					}
				}
			}
		}
		
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
		
 		//Creamos colores para cada comunidad
 		final ArrayList<Color> communityColors = new ArrayList<Color>();
 		communityColors.add(Color.RED);
 		communityColors.add(Color.GREEN);
 		communityColors.add(Color.BLUE);
 		communityColors.add(Color.YELLOW);
 		communityColors.add(Color.ORANGE);
 		communityColors.add(Color.WHITE);
 		communityColors.add(Color.PINK);
 		communityColors.add(Color.CYAN);
 		communityColors.add(Color.MAGENTA);
 		communityColors.add(Color.GRAY);
 		int n = communityColors.size();
 		for(int i = n; i < numCommunities; ++i) communityColors.add( Color.getHSBColor(new Random().nextFloat(), 1.0f, 0.7f) );
 		
 	    Transformer<Pair<String, Integer>, Paint> vertexColor = new Transformer<Pair<String, Integer>, Paint>() 
 	    {
 	        public Paint transform(Pair<String, Integer> v) 
 	        {
 	        	int c = v.getSecond();
 	        	if(c == -1) return new Color(0.0f, 0.0f, 0.0f, 0.0f);
 	            return communityColors.get(c);
 	        }
 	    };
	    
	    vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
 	    
	     vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Pair<String, Float>, String>() 
	     {
	       public String transform(Pair<String, Float> e) 
	       {
	    	   float weight = ((float)((int)(e.getSecond() * 100)))/100.0f;
	    	   if(edgesVisible.contains(e)) return String.valueOf(weight);
	    	   else return "";
	       }
	     });
	     vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<Pair<String, Float>, Paint>() 
    	     {
		       public Paint transform(Pair<String, Float> e) 
		       {
		    	   if(edgesVisible.contains(e)) return Color.BLACK;
		    	   else return Color.LIGHT_GRAY;
		       }
		     });

	     vv.getRenderContext().setEdgeStrokeTransformer(new Transformer<Pair<String, Float>, Stroke>() 
    	     {
		       public Stroke transform(Pair<String, Float> e) 
		       {
		    	   if(edgesVisible.contains(e)) return  (Stroke) new BasicStroke(2.0f);
		    	   else return (Stroke) new BasicStroke(1.0f);
		       }
		     });
	}

	public void refreshPauseButton()
	{
		if(graphLocked) btnPausar.setText("Continuar animacio");
		else btnPausar.setText("Pausar animacio");
	}
	
	public void triggerGraphAnimation()
	{
		if(!graphLocked) {graphLayout.lock(true); graphLocked = true; }
		else { graphLayout.lock(false); graphLocked = false; }
		refreshPauseButton();
	}
	
	public void onEnterPanel()
	{
		currentZoom = 1.0f;
		redrawGraphInstant();
		//labelTitol.setText("----");
	}
	
	public void redrawGraphInstant()
	{
		resetLayout();

		applyZoom(3.0);
	    int initialGraphSteps = 100;
		for(int i = 0; i < initialGraphSteps; ++i) graphLayout.step();
		graphLayout.lock(false);
		graphLocked = false;
		applyZoom(1.0);
		for(int i = 0; i < initialGraphSteps; ++i) graphLayout.step();
		selectedVertex = null;
		edgesVisible.clear();
		
		refreshPauseButton();
	}
	
	public void applyZoom(double scale)
	{
		currentZoom = (float) scale;
		final int newLayoutWidth = (int) (layoutWidth * 1.0f/currentZoom), 
				  newLayoutHeight = (int) (layoutHeight * 1.0f/currentZoom);
		final int newLayoutOffsetX = (int) (layoutOffsetX * 1.0f/currentZoom), 
				  newLayoutOffsetY = (int) (layoutOffsetY * 1.0f/currentZoom);
		
		graphLayout.setSize( new Dimension(newLayoutWidth, newLayoutHeight) );
		
		MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
		modelTransformer.setScale(scale, scale, new Point2D.Double(0, 0));
		modelTransformer.setTranslate(newLayoutOffsetX,  newLayoutOffsetY);
	}
	
	public void resetLayout() 
	{
		graphLayout = new SpringLayout<Pair<String, Integer>, Pair<String, Float>>(g);
		applyZoom(currentZoom);
		graphLocked = false;
		graphLayout.setForceMultiplier(0.01f*g.getVertexCount());
		
		vv.setGraphLayout(graphLayout);
		refreshPauseButton();
	}
	
	private void clearSongData() {
		labelTitol.setText("----");
		labelAutor.setText("----");
		labelEstils.setText("----");
		labelDuracio.setText("----");
		labelAny.setText("----");
	}
	
	private void setSongData(String songName) {
		String author = songName.split(", ")[0].trim();
		String title = songName.split(", ")[1].trim();
		labelTitol.setText(author);
		labelAutor.setText(title);
		
		ArrayList<String> styles= PresentationManager.getSongStyles(author, title);
		String stylesStr = "";
		for (String style : styles) {
			stylesStr += style + ", ";
		}
		labelEstils.setText(stylesStr);
		labelDuracio.setText(String.valueOf(PresentationManager.getSongDuration(author, title)));
		labelAny.setText(String.valueOf(PresentationManager.getSongYear(author, title)));
	}
}

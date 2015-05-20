package Presentacio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

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

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class ViewGraphPanel extends JPanel 
{
	private static VisualizationViewer vv;
	private static Graph<Pair<String, Integer>, String> g; //GRAF de JUNG, no el nostre de DOMINI
	private static SpringLayout<Pair<String, Integer>, String> graphLayout;
	private static boolean graphLocked = false;
	private static int layoutOffsetX = 15, layoutOffsetY = 15;
	private static int layoutWidth = 550, layoutHeight = 365;
	private static float currentZoom = 1.0f, zoomStep = 1.05f;
	private static float maxZoom = 2.0f, minZoom = 0.7f;
	private JLabel labelNodeName;
	private JButton btnPausar;
	
	public ViewGraphPanel() 
	{
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(20, 20, 750, 463);
		add(panel);

		g = new UndirectedSparseGraph<Pair<String, Integer>, String>();
		graphLayout = new SpringLayout<Pair<String, Integer>, String>(g);
		graphLayout.setSize( new Dimension(layoutWidth, layoutHeight) );
		vv = new VisualizationViewer(graphLayout);
		
		vv.addGraphMouseListener(new GraphMouseListener()
		{
			public void graphClicked(Object t, MouseEvent arg1) 
			{
				Pair<String, Integer> vertex = (Pair<String, Integer>)t;
				labelNodeName.setText(vertex.getFirst());
			}

			public void graphPressed(Object t, MouseEvent arg1) {
			}
			
			public void graphReleased(Object t, MouseEvent arg1) 
			{
				labelNodeName.setText("-----");
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
		btnPausar.setBounds(50, 416, 161, 23);
		panel.add(btnPausar);
		
		JButton btnRedibuixarGrafanimacio = new JButton("Redibuixar graf (animacio)");
		btnRedibuixarGrafanimacio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRedibuixarGrafanimacio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				animateGraph();
			}
		});
		btnRedibuixarGrafanimacio.setBounds(221, 416, 218, 23);
		panel.add(btnRedibuixarGrafanimacio);
		
		JButton btnRedibuixarGraf = new JButton("Redibuixar graf (instantaniament)");
		btnRedibuixarGraf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRedibuixarGraf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				redrawGraphInstant();
			}
		});
		btnRedibuixarGraf.setBounds(449, 416, 250, 23);
		panel.add(btnRedibuixarGraf);
		
		labelNodeName = new JLabel("");
		labelNodeName.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelNodeName.setBounds(256, 488, 452, 20);
		add(labelNodeName);
		
		JLabel lblNodeSeleccionat = new JLabel("Canco seleccionada:");
		lblNodeSeleccionat.setAlignmentX(1.0f);
		lblNodeSeleccionat.setAlignmentY(1.0f);
		lblNodeSeleccionat.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNodeSeleccionat.setBounds(87, 488, 157, 20);
		add(lblNodeSeleccionat);
		applyZoom(1.0);
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
		
		g = new UndirectedSparseGraph();
		graphLayout.setGraph(g);
	
		ArrayList< Pair< String, ArrayList< Pair<String, Float> > > > graph = graphCommunities.getFirst();
		ArrayList< Pair<String, Integer> > communities = graphCommunities.getSecond();
		final int numCommunities = communities.size();

		ArrayList< Pair<String, Integer> > verticesAdded = new ArrayList< Pair<String, Integer> >();
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

						String nomEdge = nomVertex + nomVertexAdjacent;
						g.addEdge(nomEdge, vertex, adjVertex);
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
 	            return communityColors.get(c);
 	        }
 	    };
	    
	    vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
	    //vv.getRenderContext().setVertexShapeTransformer(vertexSize);
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
		animateGraph();
	}
	
	public void animateGraph()
	{
		resetLayout();
		refreshPauseButton();
	}
	
	public void redrawGraphInstant()
	{
		resetLayout();

	    int initialGraphSteps = 100;
		for(int i = 0; i < initialGraphSteps; ++i) graphLayout.step();
		graphLayout.lock(true);
		graphLocked = true;
		refreshPauseButton();
	}
	
	public void applyZoom(double scale)
	{
		int newLayoutWidth = (int) (layoutWidth * 1.0f/currentZoom), 
			newLayoutHeight = (int) (layoutHeight * 1.0f/currentZoom);
		int newLayoutOffsetX = (int) (layoutOffsetX * 1.0f/currentZoom), 
			newLayoutOffsetY = (int) (layoutOffsetY * 1.0f/currentZoom);
			
		graphLayout.setSize( new Dimension(newLayoutWidth, newLayoutHeight) );
		
		MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
		modelTransformer.setScale(scale, scale, new Point2D.Double(0, 0));
		modelTransformer.setTranslate(newLayoutOffsetX,  newLayoutOffsetY);
	}
	
	public void resetLayout() 
	{
		graphLayout = new SpringLayout<Pair<String, Integer>, String>(g);
		applyZoom(currentZoom);
		graphLayout.reset();
		graphLayout.initialize();
		graphLayout.lock(false);
		graphLocked = false;
		vv.setGraphLayout(graphLayout);
		vv.invalidate();
		refreshPauseButton();
	}
}

package Presentacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
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
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.util.VertexShapeFactory;

import javax.swing.JScrollPane;

import org.apache.commons.collections15.Transformer;

public class ViewGraphPanel extends JPanel 
{
	private VisualizationViewer vv;
	private static Graph g; //GRAF de JUNG, no el nostre de DOMINI
	
	public ViewGraphPanel() 
	{
		setLayout(null);

		JPanel scrollPane = new JPanel();
		scrollPane.setBounds(20, 20, 750, 550);
		add(scrollPane);
		
		SpringLayout graphLayout = new SpringLayout(g);
		graphLayout.setSize( new Dimension(550, 450) );
		vv = new VisualizationViewer(graphLayout);
		
		MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
		modelTransformer.setTranslate(20, 20);
		
		scrollPane.add(vv);
	}
	
	public void setCurrentGraph(ArrayList<ArrayList<String>> stringGraph,
			  					ArrayList<ArrayList<String>> comunitats)
	{
		Graph g = new UndirectedSparseGraph();
		String v1 =  "A", v2 = "B";
		g.addVertex(v1);
		g.addVertex(v2);
		for(int i = 0 ; i<20; ++i) g.addVertex("kjasdjk");
		
		g.addEdge(new Object(), v1, v2);
		
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
		//vv.getRenderContext().setVertexStrokeTransformer(Verte);
	
	    // Transformer maps the vertex number to a vertex property
	    Transformer<String,Paint> vertexColor = new Transformer<String,Paint>() {
	        public Paint transform(String str) 
	        {
	            return Color.getHSBColor(new Random().nextFloat() * 1, 
	            		new Random().nextFloat() * 1, 
	            		new Random().nextFloat() * 1);
	        }
	    };
	    
	    Transformer<String,Shape> vertexSize = new Transformer<String,Shape>(){
	        public Shape transform(String str){
	            Ellipse2D circle = new Ellipse2D.Double(-15, -15, 30, 30);
	            // in this case, the vertex is twice as large
	            if(str.equals("A")) return AffineTransform.getScaleInstance(2, 2).createTransformedShape(circle);
	            else return circle;
	        }
	    };
	    vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
	    //vv.getRenderContext().setVertexShapeTransformer(vertexSize);
	}
	
	public void refresh() 
	{

	}
}

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

import Domini.Pair;

public class ViewGraphPanel extends JPanel 
{
	private static VisualizationViewer vv;
	private static Graph g; //GRAF de JUNG, no el nostre de DOMINI
	private static SpringLayout graphLayout;
	
	public ViewGraphPanel() 
	{
		setLayout(null);

		JPanel scrollPane = new JPanel();
		scrollPane.setBounds(20, 20, 750, 550);
		add(scrollPane);

		g = new UndirectedSparseGraph();
		graphLayout = new SpringLayout(g);
		graphLayout.setSize( new Dimension(550, 450) );
		vv = new VisualizationViewer(graphLayout);
		
		MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
		modelTransformer.setTranslate(20, 20);
		
		scrollPane.add(vv);
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
		
		/*
		for(Pair< String, ArrayList< Pair<String, Float> > > adjacencies : graph)
		{
			ArrayList<Pair<String, Integer>> vertex = g.getVertices();
			for(Pair<String, Float> e : adjacencies.getSecond());
			{
				g.addEdge(new Pair<String, Float>(vertex.getFirst() + e.getFirst(), e.getSecond()), vertex, );
			}
		}
		*/
		
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
		//vv.getRenderContext().setVertexStrokeTransformer(Verte);
	
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
	 	    
	    
	    Transformer<Pair<String, Integer>, Shape> vertexSize = new Transformer<Pair<String, Integer>, Shape>(){
	        public Shape transform(Pair<String, Integer> v)
	        {
	            Ellipse2D circle = new Ellipse2D.Double(-15, -15, 30, 30);
	            // in this case, the vertex is twice as large
	            return AffineTransform.getScaleInstance(2, 2).createTransformedShape(circle);
	        }
	    };
	    vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
	    //vv.getRenderContext().setVertexShapeTransformer(vertexSize);
	}
	
	public void refresh() 
	{

	}
}

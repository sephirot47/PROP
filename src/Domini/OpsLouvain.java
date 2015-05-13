package Domini;
import java.util.Set;
import java.util.Vector;
 
public class OpsLouvain {
       
               
        public static Vector<Pair<Integer, Float>> neighbors(Graph<Node> g, int i){
                Node [] n = new Node[g.getAllNodes().size()];
                g.getAllNodes().toArray(n);
                Set<Node> s = g.getAdjacentNodesTo(n[i]);
                Vector <Pair<Integer, Float>> v = new Vector<Pair<Integer, Float>>();
                for(Node n2 : s){
                        for(int j = 0; j < n.length; j++){
                                if(n[j] == n2) v.add(new Pair<Integer, Float>(j, g.getEdge(n[i], n2).getWeight()));                    
                        }
                }
                return v;
        }
       
        public static float nb_selfloops(Graph<Node> g, Node n){
                if(g.getAdjacentNodesTo(n).contains(n)) return g.getEdge(n, n).getWeight();
                else return 0;
        }
       
        public static float weighted_degree(Graph<Node> g, Node n){
                Set<Node> s = g.getAdjacentNodesTo(n);
                float w = 0;
                for(Node n2 : s) w += g.getEdge(n, n2).getWeight();
                return w;
        }
       
        public static float getTotalWeight(Graph<Node> g){
                Set<Node> s = g.getAllNodes();
                float w = 0;
                for(Node n : s) w += weighted_degree(g, n);
                return w;
        }
       
}
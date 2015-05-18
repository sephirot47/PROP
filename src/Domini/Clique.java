package Domini;
 
import java.util.*;

 

public class Clique extends Algorithm
{
    public Solution getSolution(Graph g) throws Exception 
    {
        long before = System.nanoTime();
 
        Solution s;
        DJSets ds = new DJSets(0);
        HashMap<Edge, Integer> edgeIndexes = new HashMap<Edge, Integer>();
 
        // We get all the edges
        List<Edge> edges = new ArrayList<Edge>(g.getAllEdges());
 
        ArrayList<Pair<Edge, Pair<Node, Node>>> edgesInfo = new ArrayList<Pair<Edge, Pair<Node, Node>>>();
        for (Edge e : edges) {
            Pair<Node, Node> p = (g.getNodesConnectedBy(e));
            edgesInfo.add(new Pair<Edge, Pair<Node, Node>>(e, g.getNodesConnectedBy(e)));
            g.removeEdge(e);
        }
        int k = 3;
        phase1(g, edgesInfo, k, ds, edgeIndexes);
        s = buildSolution(edgeIndexes, ds, g);
        long after = System.nanoTime();
        s.setTime(after - before);
        return s;
    }
 
    private static void phase1(Graph<Node> g, ArrayList<Pair<Edge, Pair<Node, Node>>> edges, int k, DJSets ds, HashMap<Edge, Integer> edgeIndexes) throws Exception {
        k = 3;
        for (Pair<Edge, Pair<Node, Node>> p : edges) {
            Edge e = p.getFirst();
            Pair<Node, Node> nodes = p.getSecond();
            if (e == null) throw new Exception("Phase 1 found a null edge.");
            else {
                Node v1 = nodes.getFirst();
                Node v2 = nodes.getSecond();
                g.addEdge(v1, v2, e);
                if (g.getAdjacentNodesTo(v1).size() >= k - 1 && g.getAdjacentNodesTo(v2).size() >= k - 1) {
                    // It may release a clique. Let's find common neighbours of its nodes.
                    // Collect all neighbours of both v1 and v2
                    Set<Node> NV1 = g.getAdjacentNodesTo(v1);
                    Set<Node> NV2 = g.getAdjacentNodesTo(v2);
                    Set<Node> commonNeighbours = new HashSet<Node>();
                    for (Node n : NV1) {
                        if (NV2.contains(n)) {
                            // n is a common neighbour
                            commonNeighbours.add(n);
                        }
                    }
                    // "We found " + commonNeighbours.size() + " common neighbours.\n"
                    for (Node n : commonNeighbours) {
                        // We take advantage of k being 3.
                        phase2(g, new Node[] {n, v1, v2}, ds, edgeIndexes);
                    }
                }
            }
        }
    }
 
    private static void phase2(Graph<Node> g, Node[] threeClique, DJSets ds, HashMap<Edge, Integer> edgeIndexes) throws Exception {
        int k = 3;
        if(threeClique.length != k) throw new IllegalArgumentException("Pahse 2 received a non 3-clique.");
        Edge[] edges = new Edge[k];
        edges[0] = g.getEdge(threeClique[0], threeClique[1]);
        edges[1] = g.getEdge(threeClique[0], threeClique[2]);
        edges[2] = g.getEdge(threeClique[1], threeClique[2]);
 
        if (!edgeIndexes.containsKey(edges[0])) { edgeIndexes.put(edges[0], edgeIndexes.size()); ds.add(); }
        if (!edgeIndexes.containsKey(edges[1])) { edgeIndexes.put(edges[1], edgeIndexes.size()); ds.add(); }
        if (!edgeIndexes.containsKey(edges[2])) { edgeIndexes.put(edges[2], edgeIndexes.size()); ds.add(); }
        if (ds.size() != edgeIndexes.size()) {
            //System.out.print("Mida ds: " + ds.size() + ". Mida edgeIndexes: " + edgeIndexes.size()+ "\n");
            throw new Exception("Inconsistency: DJSets of different size than the edgeIndexes.");
        }
 
        // Every (2)-clique (there are 3) belong to the same community, so we merge them
        int rootA = ds.find((edgeIndexes.get(edges[0])));
        int rootB = ds.find((edgeIndexes.get(edges[1])));
        if (rootA != rootB) ds.union(rootA, rootB);
 
        rootA = ds.find((edgeIndexes.get(edges[0])));
        rootB = ds.find(edgeIndexes.get(edges[2]));
        if (rootA != rootB) ds.union(rootA, rootB);
 
        /* Uncomment for debugging purposes
        for (Edge e : edgeIndexes.keySet()) {
            Node a = (Node) g.getNodesConnectedBy(e).getFirst();
            Node b = (Node) g.getNodesConnectedBy(e).getSecond();
 
            System.out.print("The edge " + a.getId() + " - " + b.getId() + " is in :" + ds.getParent(i) +"\n");
            System.out.print(b.getId() + "is in " + ds.find(i)+"\n");
        }
        */
    }
 
    private static Solution buildSolution(HashMap<Edge, Integer> edgeIndexes, DJSets ds, Graph g) {
        Solution s = new Solution();
        HashMap<Integer, Community> comms = new HashMap<Integer, Community>();
        for (Edge e : edgeIndexes.keySet()) {
            Community c;
            int commNum = ds.find(edgeIndexes.get(e));
            if (comms.containsKey(commNum)) c = comms.get(commNum);
            else { c = new Community(); comms.put(commNum, c); }
            c.addNode((Node) g.getNodesConnectedBy(e).getFirst());
            c.addNode((Node) g.getNodesConnectedBy(e).getSecond());
        }
        for (Community c : comms.values()) {
            s.addCommunity(c);
        }
        return s;
    }
}
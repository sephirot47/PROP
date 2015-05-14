package Domini;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;
 
public class Louvain extends Algorithm 
{
        private int[] n2cFinal;
        private Node[] initialNodes;
        private int level;
        private class LouvainCom{
                private Graph<Node> gc;
                private int size;
                private int[] n2c;//node to community
                private float[] in;
                private float[] tot;
                private Node[] node;
               
                LouvainCom(Graph <Node> graph){
                        this.gc=graph;
                        node = new Node[gc.getAllNodes().size()];
                        gc.getAllNodes().toArray(node);
                        size=node.length;
                        n2c=new int[size];
                        in= new float[size];
                        tot= new float[size];
                        for(int i=0;i<size;++i){
                                n2c[i]=i;
                                in[i]= OpsLouvain.nb_selfloops(gc, node[i]);
                                tot[i]= OpsLouvain.weighted_degree(gc,node[i]);
                        }
                }
               
                public void remove(int node,int comm,double linksToComm){
                        tot[comm] -=  OpsLouvain.weighted_degree(gc,this.node[node]);
                        in[comm] -= 2*linksToComm +  OpsLouvain.nb_selfloops(gc,this.node[node]);
                        n2c[node]=-1;
                }
               
                public void insert(int node,int comm,double linksToComm){
                        tot[comm] +=  OpsLouvain.weighted_degree(gc,this.node[node]);
                        in[comm] += 2*linksToComm + OpsLouvain.nb_selfloops(gc,this.node[node]);
                        n2c[node]=comm;
                       
                }
               
                public double modularityGain(int node,int comm, double linksToComm,double wDegree){
                        double totcom=tot[comm];
                        double degcom=wDegree;
                        double m= OpsLouvain.getTotalWeight(gc);
                        double linkscom=linksToComm;
                        double r=(linkscom-totcom*degcom/m);
                        return r;
                }
               
                public int neighComm(int node,int[] veins,double[] weights){
                       
                        for (int i=0;i<size;++i) weights[i]=-1.0;
                        Vector<Pair<Integer,Float>> p=OpsLouvain.neighbors(gc,node);
                        int degreeN=gc.getAdjacentNodesTo(this.node[node]).size();
                        veins[0]=n2c[node];
                        weights[veins[0]]=0;
                        int nvecinos=1;
                        for(int i=0;i<degreeN;++i){
                                int neigh=p.get(i).getFirst();
                                int neighCom=n2c[neigh];
                                double neighW=p.get(i).getSecond();
                                if(neigh!=node){
                                        if(weights[neighCom]==-1){
                                                weights[neighCom]=0.0;
                                                veins[nvecinos]=neighCom;
                                                ++nvecinos;
                                        }
                                        weights[neighCom]+=neighW;
                                }
                        }
                        return nvecinos;
                }
               
                double modularity(){
                        double q=0;
                        double m=OpsLouvain.getTotalWeight(gc);
                        for(int i=0;i<size;++i){
                                if(tot[i]>0) q+= (in[i]/m) -(tot[i]/m)*(tot[i]/m);
                        }
                        return q;
                }
               
                public Graph<Node> partition2graph(){
                        if (level==0)n2cFinal=n2c;
                        Graph<Node> gf2=new Graph<Node>();
                        ArrayList <Integer> communities= new ArrayList<Integer>();
                        //per a cada node, faig add de la seva comunitat al arraylist communities(per l'implementacio de l'add, no tindre repetides)
                        for(int i=0;i<size;++i){
                                communities.add(n2c[i]);
                        }
                       
                        double [][] comToCom= new double[size][size];
                        //Per a cada comunitat creo un node al nou graf
                        for (int i=0;i<size;++i){
                                //inicialitzacions per millorar rendiment
                                for(int j=0;j<size;++j) comToCom[i][j]=0;
                        }
                        //per cada node, agafo la suma de edges que van a ell i acumulo la suma a la comunitat pertinent si els dos nodes son de la mateixa comunitat
                        //si no, acumulo la suma en el array comToCom que, per la comunitat i acumula la suma cap a la comunitat j
                        for(int i=0;i<size;++i){
                                int comi=n2c[i];
                                for(int j=i;j<size;++j){
                                        if(gc.getEdge(node[i], node[j])!=null){
                                        if(comi==n2c[j]){
                                                comToCom[comi][comi]+= gc.getEdge(node[i] , node[j] ).getWeight();
                                        }
                                        else comToCom[comi][n2c[j]] += gc.getEdge(node[i] , (Node)node[j]).getWeight();
                                }
                                }
                               
                        }
                        int nodeDesti=0;
                        HashMap<Integer,Integer> c2n=new HashMap<Integer,Integer>();
                        for(int i=0;i<size;++i){
                                if(c2n.get(n2c[i])==null){
                                        c2n.put(n2c[i],nodeDesti);
                                        ++nodeDesti;
                                        gf2.addNode( new NodeLouvain("com: "+nodeDesti));
                                }
                               
                        }
 
                        //track dels nodes inicials
                        int numeroComunitats=c2n.size();
                        Node[] nodesNew= new Node[numeroComunitats];
                        gf2.getAllNodes().toArray(nodesNew);
                       
                                for(int i=0;i<n2cFinal.length;++i){
                                        n2cFinal[i]=c2n.get(n2cFinal[i]);
                                }
                       
                       
                       
                        //Per a cada node del nou graf, li poso el edge corresponent segons elsvectors abans calculats
                       
                        Edge e= new Edge();
                        for(int i=0;i<numeroComunitats;++i){
                                //para[i][j] comToCom
                                for(int j=i;j<numeroComunitats;++j){
                                        if(comToCom[i][j]!=0){
                                                int idnode1= c2n.get(i);
                                                int idnode2=c2n.get(j);
                                                e=new Edge();
                                                e.setWeight((float)comToCom[i][j] );
                                                gf2.addEdge(nodesNew[idnode1], nodesNew[idnode2], e);
                                        }
                                }
                        }
                        return gf2;
                }
               
                public boolean oneLevel(){
                        boolean improvement=false;
                        int moves=0;
                        int passes=0;
                        double newMod=modularity();
                        double mod=newMod;
                        // reordenem el vector d'ids de nodes de forma aleatoria per tal de tenir millor resultats en els calculs
                        int [] random= new int[size];
                        Random r=new Random();
                        //ponemos a cada posicion i, la propia i para lujego hacer un shuffle entre ella en posiciones random del vector
                        for(int i=0;i<size;++i)random[i]=i;
                        for(int i=0;i<size;++i){
                                int rpos= r.nextInt(size);
                                int aux= random[i];
                                random[i]=random[rpos];
                                random[rpos]=aux;
                        }
                        mod=-newMod-1;
                        moves=1;
                        //itera mientras haya ganancia de modularidad (newMod-mod>0)
 
                        while(moves>0 && newMod-mod>0){
                                mod=newMod;
                                moves=0;
                                ++passes;
                                for(int i=0;i<size;++i){
                                        int nodeid=random[i];
                                        int comunitat=n2c[nodeid];
                                        double weightDegree=OpsLouvain.weighted_degree(gc,node[nodeid]);
                                        //buscamos los links hacia otras comunidades, cogiendo su peso
                                        int[] veins=new int[size];
                                        double[] weights= new double[size];
                                        int nvecinos= neighComm(nodeid,veins,weights);
                                        //quitamos el nodo de su comunidad actual
                                        remove(nodeid,comunitat,weights[comunitat]);
                                        //Buscamos la mejor comunidad entre sus vecinos
                                        int bestCommunity=comunitat;
                                        double linksMillor=0.0;
                                        double bestIncrease=0.0;
                                        for(int j=0;j<nvecinos;++j){
                                                int vei=veins[j];
 
                                               
                                                double millora=modularityGain(nodeid, vei, weights[vei],weightDegree);
                                                if(millora>bestIncrease){
                                                        bestCommunity=vei;
                                                        linksMillor=weights[vei];
                                                        bestIncrease=millora;
                                                }
                                        }
                                        //insertem el node a la millor comunitat trobada
                                        insert(nodeid,bestCommunity,linksMillor);
                                        if(bestCommunity!=comunitat){
                                                ++moves;
                                                improvement=true;
                                        }
                                       
                                }
                                double totalTot=0.0;
                                double totalIn=0.0;
                                for(int i=0;i<tot.length;++i){
                                        totalTot+=tot[i];
                                        totalIn+=in[i];
                                }
                                newMod=modularity();
                               
                        }
                        return improvement;
                }
               
               
        }
        public Louvain() {
               
        }
 
        @Override
        public Solution getSolution(Graph g) throws Exception
        {
                long startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
                initialNodes= new Node[g.getAllNodes().size()];
                n2cFinal= new int[g.getAllNodes().size()];
                g.getAllNodes().toArray(initialNodes);
                LouvainCom c=new LouvainCom(g);
                boolean improvement=true;
                double mod=c.modularity();
                double newMod;
                level=0;
                while(improvement){
                        improvement=c.oneLevel();
                        newMod=c.modularity();
                        g=c.partition2graph();
                        c=new LouvainCom(g);
                        mod=newMod;
                        ++level;
                }
 
                long genTime = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() - startTime;
                Solution s=new Solution();
                s.setAlg('L');
                s.setTime(genTime);
                NodeLouvain[] comunitatsFinals=new NodeLouvain[g.getAllNodes().size()];
                g.getAllNodes().toArray(comunitatsFinals);
                Community[] p= new Community[comunitatsFinals.length];
                for(int i=0;i<p.length;++i) p[i]=new Community();
                for(int i=0;i<n2cFinal.length;++i){
                        p[n2cFinal[i]].addNode(initialNodes[i]);
                }
                for(int i=0;i<p.length;++i)s.addCommunity(p[i]);
               
                return s;
        }
 
}
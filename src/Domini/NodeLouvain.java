package Domini;
 
import java.util.ArrayList;
 
public class NodeLouvain extends Node 
{
        private String s;
        private ArrayList<Integer> nodes;
        public NodeLouvain(){
                s="comunitat";
                nodes=new ArrayList<Integer>();
        }
        public NodeLouvain(String s) {
                this.s=s;
                nodes=new ArrayList<Integer>();
        }
 
        @Override
        public String getId() {
                return s;
        }
 
}
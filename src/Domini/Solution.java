package Domini;
 
/**
 * @author Ruben Marias
 */
 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
 
public class Solution {
 
    private ArrayList<Community> communities;
    private double time;
    private int memory;
    private char alg;
    private String id;
 
    /**
     * Constructor class
     */
    public Solution() {
        communities = new ArrayList<Community>();
        Calendar C = Calendar.getInstance();
        double pid = C.get(Calendar.YEAR)*Math.pow(10,9) + C.get(Calendar.DAY_OF_YEAR)*Math.pow(10, 6) +
                C.get(Calendar.HOUR_OF_DAY)*Math.pow(10, 4) + C.get(Calendar.MINUTE)*Math.pow(10,2) + C.get(Calendar.SECOND);
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        id = String.valueOf(df.format(pid));
    }
 
    /**
     * @param time Value to be set as time taken to obtain the solution
     * @throws IllegalArgumentException If @param is not valid
     */
    public void setTime(double time) throws IllegalArgumentException {
        if (time <= 0) throw new IllegalArgumentException();
        this.time = time;
    }
 
    /**
     * @return Time taken to obtain the solution
     */
    public double getTime() {
        return time;
    }
 
    /**
     * @param id Identifier to be assigned to the solution
     */
    public void setId(String id) {
        this.id = id;
    }
 
    /**
     * @return The identifier of the solution
     */
    public String getId() {
        return id;
    }
 
    /**
     * @param alg The algorithm used to obtain the solution. L for Louvain, C for Clique and G for Girvan-Newman
     */
    public void setAlg(char alg) {
        this.alg = alg;
    }
 
    /**
     * @return The algorithm used to obtain the solution. L for Louvain, C for Clique and G for Girvan-Newman
     */
    public char getAlg() {
        return alg;
    }
 
 
    /**
     *
     * @param memory The memory used to obtain the solution
     * @throws IllegalArgumentException If @param is not valid
     */
    public void setMemory(int memory) throws IllegalArgumentException {
        if (memory <= 0) throw new IllegalArgumentException();
        this.memory = memory;
    }
 
    /**
     * @return The memory used to obtain the solution
     */
    public int getMemory() {
        return memory;
    }
 
    /**
     * @return Number of communities that form the solution
     */
    public int getNumCommunities() {
        return communities.size();
    }
 
    /**
     * @return Number of nodes that form the solution
     */
    public int getNumNodes() {
        int numNodes = 0;
        for (int i = 0; i < communities.size(); ++i) numNodes += communities.get(i).getNumberOfNodes();
        return numNodes;
    }
 
    /**
     * @return The communities that form the solution.
     */
    public ArrayList<Community> getCommunities() {
        return communities;
    }
 
    /**
     * @param C Community to be removed
     */
    public void removeCommunity(Community C) {
        boolean r = communities.remove(C);
    }
 
    /**
     * @param C Community to be added to the solution
     */
    public void addCommunity(Community C) {
        boolean r = communities.add(C);
    }
}
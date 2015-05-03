package Domini;

import java.util.ArrayList;
import java.util.Calendar;

public class Solution {
	private ArrayList<Community> communities;
	private double time;
	private int memory;
	private String algorithm;
	private int id;

	public Solution() {}

	public void setTime(double time) throws IllegalArgumentException {
		if (time <= 0)
			throw new IllegalArgumentException();
		this.time = time;
	}

	/* Get the time taken to obtain the solution */
	public double getTime() {
		return time;
	}

	public double getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// alg = C, G, L
	public void setAlg(String alg) {
		this.algorithm = alg;
	}

	public String getAlg() {
		return algorithm;
	}

	/*
	 * Set the time taken to obtain the solution. memory must be a positive
	 * number
	 */
	public void setMemory(int memory) throws IllegalArgumentException {
		if (memory <= 0)
			throw new IllegalArgumentException();
		this.memory = memory;
	}

	/* Get the time taken to obtain the solution */
	public int getMemory() {
		return memory;
	}

	/* Get the number of communities that form the solution */
	public int getNumCommunities() {
		return communities.size();
	}

	/* Get the number of nodes that form the solution */
	public int getNumNodes() {
		int numNodes = 0;
		for (int i = 0; i < communities.size(); ++i)
			numNodes += communities.get(i).getNumberOfNodes();
		return numNodes;
	}

	public ArrayList<Community> getCommunities() {
		return communities;
	}

	public void removeCommunity(Community C) {
		boolean r = communities.remove(C);
	}

	public void addCommunity(Community C) {
		boolean r = communities.add(C);
	}
}

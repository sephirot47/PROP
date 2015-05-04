package Domini;

import java.util.ArrayList;

public class Solution 
{
	protected ArrayList<Community> communities;
	protected double time;
	protected String algorithm, id;

	public Solution()
	{
		communities = new ArrayList<Community>();
		time = 0.0;
		algorithm = id = "";
	}
	
	public Solution(ArrayList<Community> communities, double time, String algorithm, String id)
	{
		this.communities = communities;
		this.time = time;
		this.algorithm = algorithm;
		this.id = id;
	}

	public void setTime(double time)
	{
		this.time = time;
	}

	public double getTime() 
	{
		return time;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public void setAlg(String alg) 
	{
		this.algorithm = alg;
	}

	public String getAlg() 
	{
		return algorithm;
	}

	/* Get the number of communities that form the solution */
	public int getNumCommunities() 
	{
		return communities.size();
	}

	public int getNumNodes() 
	{
		int n = 0;
		for (int i = 0; i < communities.size(); ++i)
		{
			n += communities.get(i).getNumberOfNodes();
		}
		return n;
	}

	public ArrayList<Community> getCommunities() 
	{
		return communities;
	}

	public void removeCommunity(Community C) 
	{
		communities.remove(C);
	}

	public void addCommunity(Community C) 
	{
		communities.add(C);
	}
}

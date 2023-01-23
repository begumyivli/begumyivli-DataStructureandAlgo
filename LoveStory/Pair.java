
public class Pair {
	private String nodeID;
	private int weight;
	public Pair(String nodeID, int weight)  {
		this.setNodeID(nodeID);
		this.setWeight(weight);
	}
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}

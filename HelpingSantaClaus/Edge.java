
public class Edge implements Comparable<Edge>{
	private Vertex u;
	private Vertex v;
	private int flow;
	private int capacity;
	public Edge(Vertex u, Vertex v, int flow, int capacity)  {
		this.u = u;
		this.v = v;
		this.setFlow(flow);
		this.setCapacity(capacity);
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Vertex getV() {
		return v;
	}
	@Override
	public int compareTo(Edge o) {
		if(this.getV().getHeight() < o.getV().getHeight()) {
			return -1;
		}
		else if(this.getV().getHeight() > o.getV().getHeight()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vertex {
	private final int ID;
	private int height;
	private int excess;
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private HashMap<Vertex, Edge> allEdge = new HashMap<Vertex, Edge>();
	
	public Vertex(int ID, int height, int excess)  {
		this.ID = ID;
		this.setHeight(height);
		this.setExcess(excess);
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getExcess() {
		return excess;
	}
	public void setExcess(int excess) {
		this.excess = excess;
	}
	public int getID() {
		return ID;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	public HashMap<Vertex, Edge> getAllEdge() {
		return allEdge;
	}
	public void setAllEdge(HashMap<Vertex, Edge> allEdge) {
		this.allEdge = allEdge;
	}
}

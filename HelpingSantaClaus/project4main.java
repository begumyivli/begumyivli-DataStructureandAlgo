import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class project4main {
	static Map<Vertex, ArrayList> graph = new HashMap<Vertex, ArrayList>();
	static ArrayList<Vertex> GreenTrain = new ArrayList<Vertex>();
	static ArrayList<Vertex> RedTrain = new ArrayList<Vertex>();
	static ArrayList<Vertex> GreenDeer = new ArrayList<Vertex>();
	static ArrayList<Vertex> RedDeer = new ArrayList<Vertex>();
	static ArrayList<Vertex> Green = new ArrayList<Vertex>();
	static ArrayList<Vertex> Red = new ArrayList<Vertex>();
	static ArrayList<Vertex> Deer = new ArrayList<Vertex>();
	static ArrayList<Vertex> Train = new ArrayList<Vertex>();
	static ArrayList<Vertex> Vehicle = new ArrayList<Vertex>();
	static ArrayList<Vertex> bagList = new ArrayList<Vertex>();
	static ArrayDeque<Vertex> active = new ArrayDeque<Vertex>();
	static ArrayList<Vertex> relabel = new ArrayList<Vertex>();
	static Vertex source;
	static Vertex sink;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		//input reading start
		int numOfGreenTrain = in.nextInt();
		String[] GreenTrainCapacity = new String[numOfGreenTrain];
		in.nextLine();
		if (numOfGreenTrain != 0) {
			String str = in.nextLine().trim();
			GreenTrainCapacity = str.split(" ", numOfGreenTrain);
		}
		
		int numOfRedTrain = in.nextInt();
		String[] RedTrainCapacity = new String[numOfRedTrain];
		in.nextLine();
		if (numOfRedTrain != 0) {
			String str = in.nextLine().trim();
			RedTrainCapacity = str.split(" ", numOfRedTrain);
		}
		
		int numOfGreenDeer = in.nextInt();
		String[] GreenDeerCapacity = new String[numOfGreenDeer];
		in.nextLine();
		if (numOfGreenDeer != 0) {
			String str = in.nextLine().trim();
			GreenDeerCapacity = str.split(" ", numOfGreenDeer);
		}
		
		int numOfRedDeer = in.nextInt();
		String[] RedDeerCapacity = new String[numOfRedDeer];
		in.nextLine();
		if (numOfRedDeer != 0) {
			String str = in.nextLine().trim();
			RedDeerCapacity = str.split(" ", numOfRedDeer);
		}
		//input reading for vehicles finish
		int numOfBags = in.nextInt();
		int totalVertex = numOfGreenTrain+numOfRedTrain+numOfGreenDeer+numOfRedDeer+numOfBags+2;
		source = new Vertex(0,totalVertex,0); 
		sink = new Vertex(totalVertex-1,0,0);
		graph.put(source, source.getEdges());
		graph.put(sink, sink.getEdges());
		
		int gtid = numOfBags+1;
		for (String i: GreenTrainCapacity) {
			Vertex myvertex = new Vertex(gtid,1,0); //green tren creating and adding an edge from train to sink 
			GreenTrain.add(myvertex);
			Green.add(myvertex);
			Train.add(myvertex);
			Vehicle.add(myvertex);
			int capacity = Integer.parseInt(i);
			Edge myedge = new Edge(myvertex, sink, 0, capacity);
			myvertex.getEdges().add(myedge);
			myvertex.getAllEdge().put(sink, myedge);
			graph.put(myvertex, myvertex.getEdges());
			gtid++;
		}
		
		for (String i: RedTrainCapacity) {
			Vertex myvertex = new Vertex(gtid,1,0); //red tren creating and adding an edge from train to sink 
			RedTrain.add(myvertex);
			Red.add(myvertex);
			Train.add(myvertex);
			Vehicle.add(myvertex);
			int capacity = Integer.parseInt(i);
			Edge myedge = new Edge(myvertex, sink, 0, capacity);
			myvertex.getEdges().add(myedge);
			myvertex.getAllEdge().put(sink, myedge);
			graph.put(myvertex, myvertex.getEdges());
			gtid++;
		}
		
		for (String i: GreenDeerCapacity) {
			Vertex myvertex = new Vertex(gtid,1,0); //green reindeer creating and adding an edge from deer to sink 
			GreenDeer.add(myvertex);
			Green.add(myvertex);
			Deer.add(myvertex);
			Vehicle.add(myvertex);
			int capacity = Integer.parseInt(i);
			Edge myedge = new Edge(myvertex, sink, 0, capacity);
			myvertex.getEdges().add(myedge);
			myvertex.getAllEdge().put(sink, myedge);
			graph.put(myvertex, myvertex.getEdges());
			gtid++;
		}
		
		for (String i: RedDeerCapacity) {
			Vertex myvertex = new Vertex(gtid,1,0); //red reindeer creating and adding an edge from deer to sink 
			RedDeer.add(myvertex);
			Red.add(myvertex);
			Deer.add(myvertex);
			Vehicle.add(myvertex);
			int capacity = Integer.parseInt(i);
			Edge myedge = new Edge(myvertex, sink, 0, capacity);
			myvertex.getEdges().add(myedge);
			myvertex.getAllEdge().put(sink, myedge);
			graph.put(myvertex, myvertex.getEdges());
			gtid++;
		}
		//bag input reading
		in.nextLine();
		String[] bags = new String[numOfBags*2];
		if(numOfBags!=0) {
		String str = in.nextLine().trim();
		bags = str.split(" ", numOfBags*2);
		}
		int i = 0;
		int totalGift = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int bd = 0;
		int be = 0;
		int cd = 0;
		int ce = 0;
		int b_id = 0;
		int c_id = 0;
		int d_id = 0;
		int e_id = 0;
		int bd_id = 0;
		int be_id = 0;
		int cd_id = 0;
		int ce_id = 0;
		
		for(i = 0; i < numOfBags; i++) {
			String type = bags[i*2];
			int gift = Integer.parseInt(bags[i*2+1]);
			totalGift += gift;
			if(type.equals("b")) {
				if(b==0) {
					b_id=i+1;
				}
				b += gift;
				continue;
			}
			if(type.equals("c")) {
				if(c==0) {
					c_id=i+1;
				}
				c += gift;
				continue;
			}
			if(type.equals("d")) {
				if(d==0) {
					d_id=i+1;
				}
				d += gift;
				continue;
			}
			if(type.equals("e")) {
				if(e==0) {
					e_id=i+1;
				}
				e += gift;
				continue;
			}
			if(type.equals("bd")) {
				if(bd==0) {
					bd_id=i+1;
				}
				bd += gift;
				continue;
			}
			if(type.equals("be")) {
				if(be==0) {
					be_id=i+1;
				}
				be += gift;
				continue;
			}
			if(type.equals("cd")) {
				if(cd==0) {
					cd_id=i+1;
				}
				cd += gift;
				continue;
			}
			if(type.equals("ce")) {
				if(ce==0) {
					ce_id=i+1;
				}
				ce += gift;
				continue;
			}
			if(gift==0) {
				continue;
			}
			//vertex creating which includes "a"
			Vertex myvertex = new Vertex(i+1,2,gift);
			Edge myedge = new Edge(source, myvertex, gift, gift);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, gift);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			//adding edge from bag to vehicle and back edge from vehicle to bag
			switch (type) {
			case "a":
				for(Vertex v: Vehicle) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "ab":
				for(Vertex v: Green) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "ac":
				for(Vertex v: Red) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "ad":
				for(Vertex v: Train) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "ae":
				for(Vertex v: Deer) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "abd":
				for(Vertex v: GreenTrain) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "abe":
				for(Vertex v: GreenDeer) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "acd":
				for(Vertex v: RedTrain) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
				
			case "ace":
				for(Vertex v: RedDeer) {
					Edge myedge2 = new Edge(myvertex, v, 0, 1);
					Edge backedge = new Edge(v, myvertex, 1, 1);
					myvertex.getEdges().add(myedge2);
					v.getEdges().add(backedge);
					myvertex.getAllEdge().put(v, myedge2);
					v.getAllEdge().put(myvertex, backedge);
				}
				break;
			}
		}
		//creating bag vertex which does not include "a" and adding edges
		if(b!=0) {
			Vertex myvertex = new Vertex(b_id,2,b);
			Edge myedge = new Edge(source, myvertex, b, b);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, b);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: Green) {
				Edge myedge2 = new Edge(myvertex, v, 0, b);
				Edge backedge = new Edge(v, myvertex, b, b);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(c!=0) {
			Vertex myvertex = new Vertex(c_id,2,c);
			Edge myedge = new Edge(source, myvertex, c, c);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, c);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: Red) {
				Edge myedge2 = new Edge(myvertex, v, 0, c);
				Edge backedge = new Edge(v, myvertex, c, c);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(d!=0) {
			Vertex myvertex = new Vertex(d_id,2,d);
			Edge myedge = new Edge(source, myvertex, d, d);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, d);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: Train) {
				Edge myedge2 = new Edge(myvertex, v, 0, d);
				Edge backedge = new Edge(v, myvertex, d, d);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(e!=0) {
			Vertex myvertex = new Vertex(e_id,2,e);
			Edge myedge = new Edge(source, myvertex, e, e);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, e);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: Deer) {
				Edge myedge2 = new Edge(myvertex, v, 0, e);
				Edge backedge = new Edge(v, myvertex, e, e);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(bd!=0) {
			Vertex myvertex = new Vertex(bd_id,2,bd);
			Edge myedge = new Edge(source, myvertex, bd, bd);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, bd);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: GreenTrain) {
				Edge myedge2 = new Edge(myvertex, v, 0, bd);
				Edge backedge = new Edge(v, myvertex, bd, bd);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(be!=0) {
			Vertex myvertex = new Vertex(be_id,2,be);
			Edge myedge = new Edge(source, myvertex, be, be);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, be);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: GreenDeer) {
				Edge myedge2 = new Edge(myvertex, v, 0, be);
				Edge backedge = new Edge(v, myvertex, be, be);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(cd!=0) {
			Vertex myvertex = new Vertex(cd_id,2,cd);
			Edge myedge = new Edge(source, myvertex, cd, cd);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, cd);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: RedTrain) {
				Edge myedge2 = new Edge(myvertex, v, 0, cd);
				Edge backedge = new Edge(v, myvertex, cd, cd);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		if(ce!=0) {
			Vertex myvertex = new Vertex(ce_id,2,ce);
			Edge myedge = new Edge(source, myvertex, ce, ce);
			source.getEdges().add(myedge);
			source.getAllEdge().put(myvertex, myedge);
			Edge back = new Edge(myvertex, source, 0, ce);
			myvertex.getEdges().add(back);
			myvertex.getAllEdge().put(source, back);
			bagList.add(myvertex);
			active.addFirst(myvertex);
			graph.put(myvertex, myvertex.getEdges());
			for(Vertex v: RedDeer) {
				Edge myedge2 = new Edge(myvertex, v, 0, ce);
				Edge backedge = new Edge(v, myvertex, ce, ce);
				myvertex.getEdges().add(myedge2);
				v.getEdges().add(backedge);
				myvertex.getAllEdge().put(v, myedge2);
				v.getAllEdge().put(myvertex, backedge);
			}
		}
		//active contains nodes which has excess gift
		while(!active.isEmpty()) {
			Vertex u = active.poll();
			int old = u.getHeight();
			if(relabel.size() == Vehicle.size()) { //because sink is not available anymore
				break;
			}
			unload(u);
			if(u.getHeight() > old) { //still node has excess value
				if(bagList.contains(u)) {
					active.addFirst(u);
				}
				else if(Vehicle.contains(u)) {
					active.addLast(u);
				}
			}
		}
		out.println(totalGift-sink.getExcess());
	}
	
	public static void relabel(Vertex u) { //if node has excess value and its height is not enough, this method increases it 
		int height = Integer.MAX_VALUE;
		for (Edge e: u.getEdges()) {
			if(e.getV().getHeight() < height && e.getCapacity()-e.getFlow() > 0) {
				height = e.getV().getHeight();
			}
		}
		u.setHeight(height+1);
		if(Vehicle.contains(u) && !relabel.contains(u)) {
			relabel.add(u);
		}
	}
	
	public static void push(Vertex u, Edge e) { //this method pushes excess value from u to edge e's v
		int send = Math.min(u.getExcess(), e.getCapacity()-e.getFlow());
		u.setExcess(u.getExcess()-send);
		e.getV().setExcess(e.getV().getExcess()+send);
		e.setFlow(e.getFlow()+send);
		if((!e.getV().equals(sink)) && (!e.getV().equals(source)) && !active.contains(e.getV())){
			active.add(e.getV());
		}
		if(e.getV().getAllEdge().get(u)!=null) {
			e.getV().getAllEdge().get(u).setFlow(e.getV().getAllEdge().get(u).getFlow()-send);
		}
		
	}
	
	public static void unload(Vertex u) { //this method decreases node's excess value, it pushes or relable
		int edgenum = 0;
		while(u.getExcess() > 0) {
			if(edgenum < u.getEdges().size()) {
				Edge e = u.getEdges().get(edgenum);
				if(e.getCapacity()-e.getFlow() > 0 && u.getHeight() > e.getV().getHeight()) {
					push(u, e);
				}
				else {
					edgenum++;
				}
		    }
			else {
				relabel(u);
				edgenum=0;
			}
		}
	}
}

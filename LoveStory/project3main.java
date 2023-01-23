import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class project3main {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		Integer myInf = Integer.MAX_VALUE;
		int time = in.nextInt();
		int city = in.nextInt();
		
		in.nextLine();
		String str = in.nextLine().trim();
		String[] arrOfStr = str.split(" ", 3);
		
		String mecnunID = arrOfStr[0];
		String leylaID = arrOfStr[1];
		// i created distanceMap, adjacencyMap for Djikstra's algorithm
		// and honeyDistMap, honeyAdjMap, boolMap for Prim's algorithm
		Map<String, Integer> distanceMap = new HashMap<String, Integer>();
		Map<String, ArrayList<Pair>> adjacencyMap = new HashMap<String, ArrayList<Pair>>();
		Map<String, Integer> honeyDistMap = new HashMap<String, Integer>();
		Map<String, ArrayList<Pair>> honeyAdjMap = new HashMap<String, ArrayList<Pair>>();
		Map<String, Boolean> boolMap = new HashMap<String, Boolean>();
		
		for (int j = 0; j < city; j++) {
			String str2 = in.nextLine().trim();
			String[] arrOfStr2 = str2.split(" ", city*2);
			String myvertex = arrOfStr2[0];
			// here, i read file and if city's first letter is c, i put it in distanceMap
			// i created an adjacency arraylist for every vertex, and i put Pair objects which
			// contains neighbor vertex's id and the weight between them
			if(myvertex.charAt(0) == 'c') {
				distanceMap.put(myvertex, myInf);
				if(!myvertex.equals(leylaID)) {
					ArrayList<Pair> myList = new ArrayList<Pair>();
					for(int i = 1; i < arrOfStr2.length; i=i+2) {
						String neighbor = arrOfStr2[i];
						int weight = Integer.parseInt(arrOfStr2[i+1]);
						Pair p = new Pair(neighbor, weight);
						myList.add(p);
					}
					adjacencyMap.put(myvertex, myList);
				}
				else {
					// if the city is leyla's city, i put it in honeyDistMap and boolMap
					honeyDistMap.put(leylaID, myInf);
					boolMap.put(leylaID, false);
					if(honeyAdjMap.get(myvertex) == null) {
						ArrayList<Pair> myList = new ArrayList<Pair>();
						honeyAdjMap.put(myvertex, myList);
					}
					for(int i = 1; i < arrOfStr2.length; i=i+2) {
						String neighbor = arrOfStr2[i];
						int weight = Integer.parseInt(arrOfStr2[i+1]);
						if(neighbor.charAt(0) == 'c') {
							continue;
						}
						Pair p = new Pair(neighbor, weight);
						honeyAdjMap.get(myvertex).add(p);
						if(honeyAdjMap.get(neighbor) == null) {
							ArrayList<Pair> myList2 = new ArrayList<Pair>();
							Pair p2 = new Pair(myvertex, weight);
							myList2.add(p2);
							honeyAdjMap.put(neighbor, myList2);
						}
						else {
							Pair p2 = new Pair(myvertex, weight);
							honeyAdjMap.get(neighbor).add(p2);
						}
					}
				}
			}
			// if the city's first letter is d, i put it in honeyDistMap and boolMap
			else if(myvertex.charAt(0) == 'd') {
				honeyDistMap.put(myvertex, myInf);
				boolMap.put(myvertex, false);
				if(honeyAdjMap.get(myvertex) == null) {
					ArrayList<Pair> myList = new ArrayList<Pair>();
					honeyAdjMap.put(myvertex, myList);
				}
				for(int i = 1; i < arrOfStr2.length; i=i+2) {
					String neighbor = arrOfStr2[i];
					int weight = Integer.parseInt(arrOfStr2[i+1]);
					Pair p = new Pair(neighbor, weight);
					honeyAdjMap.get(myvertex).add(p);
					if(honeyAdjMap.get(neighbor) == null) {
						ArrayList<Pair> myList2 = new ArrayList<Pair>();
						Pair p2 = new Pair(myvertex, weight);
						myList2.add(p2);
						honeyAdjMap.put(neighbor, myList2);
					}
					else {
						Pair p2 = new Pair(myvertex, weight);
						honeyAdjMap.get(neighbor).add(p2);
					}
				}
			}
		}
		Map<String, String> parentMap = new HashMap<String, String>(); //neighbour, parent
		Queue<String> DjikstraPQ = new LinkedList<String>();
		//Djikstra start
		distanceMap.replace(mecnunID, 0);
		DjikstraPQ.add(mecnunID);
		while(!DjikstraPQ.isEmpty()) {
			String mynode = DjikstraPQ.poll();
			int distance = distanceMap.get(mynode);
			if(!mynode.equals(leylaID)) { //bcs leyla node hasn't adjacency node "c" cities
				ArrayList<Pair> myList = adjacencyMap.get(mynode);
				for(Pair entry : myList) {
				    String neighbour = entry.getNodeID();
				    int weight = entry.getWeight();
				    if(distanceMap.get(neighbour) > distance + weight) { // if there is a shortest path to source node it is updated
				    	distanceMap.replace(neighbour, distance+weight);
				    	parentMap.put(neighbour, mynode);
				    	DjikstraPQ.add(neighbour);
				    }
				}
			}
		}
		//Djikstra end
		String path = "";
		if(parentMap.get(leylaID) == null) { // if leyla node hasn't a parent there is no path from mecnun to leyla
			path = "-1";
			out.println(path);
			out.println("-1");
		}
		else {
			path = leylaID;
			String parent = leylaID;
			while(!parent.equals(mecnunID)) {
				path = parentMap.get(parent) + " "+ path;
				parent = parentMap.get(parent);
			}
			if (distanceMap.get(leylaID) > time) { // mecnun cannot reach leyla in her father's permitted time
				out.println(path);
				out.println("-1");
			}
			else {
				//Prim start
				PriorityQueue<Pair> pairs = new PriorityQueue<Pair>(11, new PairComparator());
				honeyDistMap.replace(leylaID, 0);
				Pair myp = new Pair(leylaID, 0);
				pairs.add(myp);
				while(!pairs.isEmpty()) {
					Pair mypair = pairs.poll();
					String mynode = mypair.getNodeID();
					boolMap.replace(mynode, true);
					ArrayList<Pair> myList = honeyAdjMap.get(mynode);
					for(Pair neighbor : myList) {
						String neighborID = neighbor.getNodeID();
						int weight = neighbor.getWeight();
						if(boolMap.get(neighborID) == false && honeyDistMap.get(neighborID) > weight) { // if a node is not in MST and there is a shorter path
							honeyDistMap.replace(neighborID, weight);                                  // it is updated
							Pair myp2 = new Pair(neighborID, weight);
							pairs.add(myp2);
						}
					}
				}
				//Prim end
				if(honeyDistMap.values().contains(myInf)) { // unconnected graph
					out.println(path);
					out.println(-2);
				}
				else {
				int yol = 0;
				for(int i : honeyDistMap.values()) {
					yol = yol + i;
				}
				out.println(path);
				out.println(yol*2); //tax paid
				}
			}
		}
	}
}

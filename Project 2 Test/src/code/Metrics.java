package code;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Metrics {
	static double percentage;
	static long totalSCC;
	static HashMap<Long, ArrayList<Vertex>>  list; //list of SCC and their components
	
	public static double getDensity(DirectedGraph graph) {
		double arcs = (double) graph.numArcs();
		int denominator = graph.numVertices() * (graph.numVertices() - 1);
		double answer = arcs/denominator;
		BigDecimal bd = new BigDecimal(Double.toString(answer));
		//round to 3 decimal places
		bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		return Double.valueOf(bd.doubleValue());
	}
	public static long getMinInDegree(DirectedGraph graph) {
		long min = 1000000000; //biggest long possible
		Iterator<Vertex> iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int inDegree = graph.inDegree(next);
			if (inDegree < min) {
				min = inDegree;
			}
		}
		return min;
	}
	public static long getMaxInDegree(DirectedGraph graph) {
		Iterator<Vertex> iterator = graph.vertices();
		long max = 0;
		while(iterator.hasNext()){
			Vertex next = iterator.next();
			int inDegree = graph.inDegree(next);
			if (inDegree > max) {
				max = inDegree;
			}
		}
		return max;
	}
	public static long getMinOutDegree(DirectedGraph graph) {
		long min = 1000000000; //biggest long possible
		Iterator<Vertex> iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int outDegree = graph.outDegree(next);
			if (outDegree < min) {
				min = outDegree;
			}
		}
		return min;
	}
	public static long getMaxOutDegree(DirectedGraph graph) {
		Iterator<Vertex> iterator = graph.vertices();
		long max = 0;
		while(iterator.hasNext()){
			Vertex next = iterator.next();
			int outDegree = graph.outDegree(next);
			if (outDegree > max) {
				max = outDegree;
			}
		}
		return max;
	}
	public static void createTranspose(DirectedGraph graph) {
		Iterator<Arc> iterator = graph.arcs();
		while (iterator.hasNext()) {
			Arc next = iterator.next();
			graph.reverseDirection(next);
		}
	}
	//gets everything SCC in this run
	public static void runSCC(DirectedGraph graph) {
		DirectedGraph newGraph = DFS.runDFS(graph);
		Metrics.createTranspose(newGraph);
		DirectedGraph sccGraph = DFS.runDFSWithSCC(newGraph);
		list = new HashMap<Long, ArrayList<Vertex>>();
		Iterator<Vertex> iterator = sccGraph.vertices();
		
		long scc = 0;
		
		while(iterator.hasNext()){
			Vertex vertex = iterator.next();
			long currentSCC = (long) sccGraph.getAnnotation(vertex, "scc");
			if (currentSCC > scc) {
				scc = currentSCC;
			}
			if (list.containsKey(currentSCC)){
				ArrayList<Vertex> vertexList = list.get(currentSCC);
				vertexList.add(vertex);
			}
			else {
				ArrayList<Vertex> newList = new ArrayList<Vertex>();
				newList.add(vertex);
				list.put(currentSCC, newList);
			}
		}
		totalSCC = scc;
		Collection<ArrayList<Vertex>> listOfVertices = list.values();
		Iterator<ArrayList<Vertex>> listIterator = listOfVertices.iterator();
		ArrayList<Vertex> max = new ArrayList<Vertex>();
		while (listIterator.hasNext()) {
			 ArrayList<Vertex> nextList = listIterator.next();
			 if (nextList.size() > max.size()) {
				 max = nextList;
			 }
		}
		double answer = (double) max.size()/graph.numVertices();
		BigDecimal bd = new BigDecimal(Double.toString(answer));
		//round to 3 decimal places
		bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		percentage = Double.valueOf(bd.doubleValue()*100);
		
	}
	
	//these three methods only return correct values if runSCC is run first
	public static long getNumberOfSCC() {
		return totalSCC;
	}
	public static double getPercentLargestSCC() {
		return percentage;
	}
	public static List<ArrayList<Vertex>> sortedSCC() {
		List<ArrayList<Vertex>> vertices = new ArrayList<ArrayList<Vertex>>(list.values());
		Collections.sort(vertices, new VertexListComparable());
		//give each SCC an arbitrary number
		return vertices;
	}
}

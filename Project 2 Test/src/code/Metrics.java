package code;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Metrics {
	
	public static double getDensity(DirectedGraph graph) {
		double arcs = (double) graph.numArcs();
		int denominator = graph.numVertices() * (graph.numVertices() - 1);
		double answer = arcs/denominator;
		BigDecimal bd = new BigDecimal(Double.toString(answer));
		//round to 3 decimal places
		bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		return Double.valueOf(bd.doubleValue());
	}
	//[min, max, ave]
	public static Object[] getInDegreeStats(DirectedGraph graph) {
		Object[] array = new Object [3];
		long min = 1000000000; //biggest long possible
		long max = 0;
		long runningTotal = 0;
		Iterator<Vertex> iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int inDegree = graph.inDegree(next);
			if (inDegree < min) {
				min = inDegree;
			}
			if (inDegree > max) {
				max = inDegree;
			}
			runningTotal = runningTotal + inDegree;
		}		
		double answer = (double) runningTotal / graph.numVertices();
		BigDecimal bd = new BigDecimal(Double.toString(answer));
		//round to 3 decimal places
		bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		
		array[0] = min;
		array[1] = max;
		array[2] = Double.valueOf(bd.doubleValue());
		return array;
	}
	//[min, max, ave]
	public static Object[] getOutDegreeStats(DirectedGraph graph) {
		Object[] array = new Object[3];
		long min = 1000000000; //biggest long possible
		long max = 0;
		long runningTotal = 0;
		Iterator<Vertex> iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int outDegree = graph.outDegree(next);
			if (outDegree < min) {
				min = outDegree;
			}
			if (outDegree > max) {
				max = outDegree;
			}
			runningTotal = runningTotal + outDegree;
		}
		double answer = (double) runningTotal / graph.numVertices();
		BigDecimal bd = new BigDecimal(Double.toString(answer));
		//round to 3 decimal places
		bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		
		array[0] = min;
		array[1] = max;
		array[2] = Double.valueOf(bd.doubleValue());
		return array;
	}
	public static void createTranspose(DirectedGraph graph) {
		Iterator<Arc> iterator = graph.arcs();
		while (iterator.hasNext()) {
			Arc next = iterator.next();
			graph.reverseDirection(next);
		}
	}
	//gets everything SCC in this run
	//[number of SCC, percent vertices in largest SCC, list of sorted SCC]
	public static Object[] runSCC(DirectedGraph graph) {
		Object[] sCCStats = new Object[3];
		HashMap<Long, ArrayList<Vertex>> list = new HashMap<Long, ArrayList<Vertex>>();
		long scc = 0;
		long totalSCC;
		DirectedGraph newGraph = DFS.runDFS(graph);
		Metrics.createTranspose(newGraph);
		DirectedGraph sccGraph = DFS.runDFSWithSCC(newGraph);
		Iterator<Vertex> iterator = sccGraph.vertices();
		
		//create a hash of SCC to size
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
		
		//get the max SCC size
		Collection<ArrayList<Vertex>> listOfVertices = list.values();
		Iterator<ArrayList<Vertex>> listIterator = listOfVertices.iterator();
		ArrayList<Vertex> max = new ArrayList<Vertex>();
		while (listIterator.hasNext()) {
			 ArrayList<Vertex> nextList = listIterator.next();
			 if (nextList.size() > max.size()) {
				 max = nextList;
			 }
		}
		
		//get the percentage of the largest SCC
		double answer = (double) max.size()/graph.numVertices();
		BigDecimal bd = new BigDecimal(Double.toString(answer));
		//round to 3 decimal places
		bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		double percentage = Double.valueOf(bd.doubleValue()*100);
		
		//create a list of sortedSCC
		List<ArrayList<Vertex>> vertices = new ArrayList<ArrayList<Vertex>>(list.values());
		Collections.sort(vertices, new VertexListComparable());
		
		sCCStats[0] = totalSCC;
		sCCStats[1] = percentage;
		sCCStats[2] = vertices;
		return sCCStats;
	}
}

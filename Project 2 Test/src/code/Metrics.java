package code;

import java.math.BigDecimal;
import java.util.Iterator;

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
	
	public static long getSCC(DirectedGraph graph) {
		long scc = 0;
		return scc;
	}
}

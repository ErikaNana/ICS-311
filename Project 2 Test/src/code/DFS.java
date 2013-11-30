package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DFS {
	static int time;
	static int scc;
	
	public static DirectedGraph runDFS(DirectedGraph graph) {
		Iterator<Vertex>iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			graph.setAnnotation(next, "color", "white");
			graph.setAnnotation(next, "parent", null);
		}
		time = 0;
		iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (graph.getAnnotation(next, "color").equals("white")) {
				DFSVisit(graph,next);
			}
		}
		return graph;
	}
	
	public static void DFSVisit(DirectedGraph graph, Vertex vertex) {
		time = time + 1;
		graph.setAnnotation(vertex, "discover", time);
		graph.setAnnotation(vertex, "color", "gray");
		Iterator<Vertex> iterator = graph.outAdjacentVertices(vertex);
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (graph.getAnnotation(next, "color").equals("white")) {
				graph.setAnnotation(next, "parent", vertex);
				DFSVisit(graph,next);
			}
		}
		graph.setAnnotation(vertex, "color", "black");
		time = time + 1;
		graph.setAnnotation(vertex, "finish", time);
	}
	
	public static DirectedGraph runDFSWithSCC(DirectedGraph graph) {
		//get a list of the vertices ordered by finish time
		HashMap<Vertex, BTree<Arc>> collection = graph.getATree();
		List<Vertex> vertices = new ArrayList<Vertex>(collection.keySet());
		Collections.sort(vertices, new VertexComparable());
		for (Vertex vertex: vertices) {
			graph.setAnnotation(vertex, "color", "white");
			graph.setAnnotation(vertex, "parent", null);
		}
		time = 0;
		scc = 0;
		for (Vertex vertex: vertices) {
			if(graph.getAnnotation(vertex, "color").equals("white")) {
				scc = scc + 1;
				DFSVisitWithSCC(graph, vertex);
			}
		}
		return graph;
	}
	public static void DFSVisitWithSCC(DirectedGraph graph, Vertex vertex) {
		time = time + 1;
		graph.setAnnotation(vertex, "discover", time);
		graph.setAnnotation(vertex, "color", "gray");
		graph.setAnnotation(vertex, "scc", scc);
		Iterator<Vertex> iterator = graph.outAdjacentVertices(vertex);
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (graph.getAnnotation(next, "color").equals("white")) {
				graph.setAnnotation(next, "parent", vertex);
				DFSVisitWithSCC(graph,next);
			}
		}
		graph.setAnnotation(vertex, "color", "black");
		time = time + 1;
		graph.setAnnotation(vertex, "finish", time);
	}
}

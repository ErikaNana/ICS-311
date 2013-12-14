package code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BFS {
	
	public static HashMap<Vertex,Integer> runBFS(DirectedGraph graph, Vertex start) {
		/* When the algorithm is finisehd we are left with an array that 
		 * contains the distances to every vertex int he component of the 
		 * network that contains s (and every vertex in every other component
		 * that has unknown distance*/
		Vertex[] queue = new Vertex[graph.numVertices()];
		HashMap<Vertex,Integer> distances = new HashMap<Vertex,Integer>();
		int read = 0;
		int write = 1;
		
		//initialize
		queue[0] = start;
		Iterator<Vertex> vertices = graph.vertices();
		while (vertices.hasNext()) {
			Vertex vertex = vertices.next();
			if (vertex == start) {
				distances.put(start, 0);
			}
			else{
				distances.put(vertex, -1);
			}
		}
		//run through the algorithm
		while (read != write) {
			Vertex currentRead = queue[read];
			read++;
			int distance = distances.get(currentRead);
			HashMap<Vertex, HashSet<Vertex>> aList = graph.getAList().getMap();
			HashSet<Vertex> neighbors = aList.get(currentRead);
			Iterator<Vertex> iterator = neighbors.iterator();
			while (iterator.hasNext()) {
				Vertex neighbor = iterator.next();
				//check if distance is known
				if (distances.get(neighbor) == -1) {
					int neighborDistance = distance + 1;
					distances.put(neighbor, neighborDistance);
					queue[write] = neighbor;
					write++;
				}
			}
			
		}
		return distances;
	}
}

package code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class AList {
	
	HashMap<Vertex,HashSet<Vertex>> outVertices;
	int numOfEdges;
	
	public AList() {
		outVertices = new HashMap<Vertex,HashSet<Vertex>>();
	}
	
	public void addVertex(Vertex vertex) {
		//add start vertex to the HashMaps
		if (!outVertices.containsKey(vertex)) {
			HashSet<Vertex> tree = new HashSet<Vertex>();
			outVertices.put(vertex,tree);
		}
	}
	public void addEdge(Vertex start, Vertex end) {
		//this assumes that vertices to be connected already exist in the adj. tree

		if (outVertices.containsKey(start)) {
			//System.out.println("add end vertex " + end + " to " + start);
			HashSet<Vertex>set = outVertices.get(start);
			set.add(end);
			numOfEdges++;
		}
	}
	public void deleteVertex(Vertex vertex) {
		//remove vertex and update 
		if (outVertices.containsKey(vertex)) {
			//check if that vertex has outgoing edges
			numOfEdges = numOfEdges - outVertices.get(vertex).size();
			outVertices.remove(vertex);
			//get the tree from each value in HashMap and delete the vertex from that tree
			for (HashSet<Vertex> tree: outVertices.values()) {
				if (tree.contains(vertex)){
					tree.remove(vertex);
					numOfEdges--;
				}
			}
		}
	}
	
	public void deleteEdge(Vertex start, Vertex end) {
		HashSet<Vertex> outSet = outVertices.get(start);
		outSet.remove(end);
		numOfEdges--;
	}
	public int getOutDegree(Vertex vertex) {
		HashSet<Vertex> set = outVertices.get(vertex);
		return set.size();
	}
	
	public int getInDegree(Vertex vertex) {
		int counter = 0;
		Set<Vertex> keys = outVertices.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			HashSet<Vertex> set = outVertices.get(i.next());
			if(set.contains(vertex)) {
				counter++;
			}
		}
		return counter;
	}
	
	public HashMap<Vertex,HashSet<Vertex>> getMap() {
		return outVertices;
	}
	
	public int getNumOfEdges() {
		return numOfEdges;
	}
	
	public int getNumOfVertices() {
		return outVertices.size();
	}
}
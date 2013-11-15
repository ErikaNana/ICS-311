package code;

import java.util.HashMap;



public class AList {
	
	HashMap<Vertex,BTree<Vertex>> map;
	int numOfEdges;
	HashMap<Vertex,Integer> inDegree;
	
	public AList() {
		map = new HashMap<Vertex,BTree<Vertex>>();
		inDegree = new HashMap<Vertex,Integer>();
		numOfEdges = 0;
	}
	
	public void addVertex(Vertex vertex) {
		//add start vertex to the HashMap
		if (!map.containsKey(vertex)) {
			BTree<Vertex> tree = new BTree<Vertex>();
			map.put(vertex,tree);
			inDegree.put(vertex, 0);
		}
	}
	public void addEdge(Vertex start, Vertex end) {
		//this assumes that vertices to be connected already exist in the adj. tree

		if (map.containsKey(start)) {
			//System.out.println("add end vertex " + end + " to " + start);
			BTree<Vertex>tree = map.get(start);
			tree.insert(end, null);
			//update the tree
			map.put(start, tree);
			inDegree.put(end,inDegree.get(end)+1); 
			numOfEdges++;
		}
	}
	public void deleteVertex(Vertex vertex) {
		//remove vertex and update BTrees and HashMap
		if (map.containsKey(vertex)) {
			//check if that vertex has outgoing edges
			numOfEdges = numOfEdges - map.get(vertex).size();
			map.remove(vertex);
			//get the tree from each value in HashMap and delete the vertex from that tree
			for (BTree<Vertex> tree: map.values()) {
				if (!tree.isEmpty()) {
					Vertex found = (Vertex) tree.searchForValue(vertex);
					System.out.println();
					if (found != null) {
						tree.delete(found);	
						numOfEdges--;
					}		
				}
			}
		}
	}
	
	public void deleteEdge(Vertex start, Vertex end) {
		BTree<Vertex> tree = map.get(start);
		tree.delete(end);
		numOfEdges--;
	}
	public int getOutDegree(Vertex vertex) {
		BTree<Vertex> tree = map.get(vertex);
		return tree.size();
	}
	
	public int getInDegree(Vertex vertex) {
		return inDegree.get(vertex);
	}
	
	public HashMap<Vertex,BTree<Vertex>> getMap() {
		return map;
	}
	
	public int getNumOfEdges() {
		return numOfEdges;
	}
	
	public int getNumOfVertices() {
		return map.size();
	}
}
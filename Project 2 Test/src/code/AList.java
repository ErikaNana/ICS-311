package code;

import java.util.HashMap;



public class AList {
	
	HashMap<Vertex,BTree<Vertex>> outVertices;
	int numOfEdges;
	HashMap<Vertex,BTree<Vertex>> inVertices;
	
	public AList() {
		outVertices = new HashMap<Vertex,BTree<Vertex>>();
		inVertices = new HashMap<Vertex,BTree<Vertex>>();
	}
	
	public void addVertex(Vertex vertex) {
		//add start vertex to the HashMaps
		if (!outVertices.containsKey(vertex)) {
			BTree<Vertex> tree = new BTree<Vertex>();
			outVertices.put(vertex,tree);
		}
	}
	public void addEdge(Vertex start, Vertex end) {
		//this assumes that vertices to be connected already exist in the adj. tree

		if (outVertices.containsKey(start)) {
			//System.out.println("add end vertex " + end + " to " + start);
			BTree<Vertex>tree = outVertices.get(start);
			tree.insert(end, null);
			//update the trees
			outVertices.put(start, tree);
			if (inVertices.containsKey(end)) {
				BTree<Vertex> inTree = inVertices.get(end);
				inTree.insert(start, null);
				inVertices.put(end, inTree);
			}
			else {
				BTree<Vertex> inTree = new BTree<Vertex>();
				inTree.insert(start, null);
				inVertices.put(end,inTree);
			}
			numOfEdges++;
		}
	}
	public void deleteVertex(Vertex vertex) {
		//remove vertex and update BTrees and HashMap
		if (outVertices.containsKey(vertex)) {
			//check if that vertex has outgoing edges
			numOfEdges = numOfEdges - outVertices.get(vertex).size();
			outVertices.remove(vertex);
			//get the tree from each value in HashMap and delete the vertex from that tree
			for (BTree<Vertex> tree: outVertices.values()) {
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
		if (inVertices.containsKey(vertex)) {
			inVertices.remove(vertex);
			//get the tree from each value in HashMap and delete the vertex from that tree
			for (BTree<Vertex> tree: inVertices.values()) {
				if (!tree.isEmpty()) {
					Vertex found = (Vertex) tree.searchForValue(vertex);
					System.out.println();
					if (found != null) {
						tree.delete(found);	
					}		
				}
			}
		}
	}
	
	public void deleteEdge(Vertex start, Vertex end) {
		System.out.println("start:  " + start);
		System.out.println("end:  " + end);
		BTree<Vertex> outTree = outVertices.get(start);
		outTree.delete(end);
		BTree<Vertex> inTree = inVertices.get(end);
		System.out.println("tree size:  " + inTree.size());
		System.out.println("root:  " + inTree.getRoot());
		inTree.delete(start);
		numOfEdges--;
	}
	public int getOutDegree(Vertex vertex) {
		BTree<Vertex> tree = outVertices.get(vertex);
		return tree.size();
	}
	
	public int getInDegree(Vertex vertex) {
		return inVertices.get(vertex).size();
	}
	
	public HashMap<Vertex,BTree<Vertex>> getMap() {
		return outVertices;
	}
	
	public int getNumOfEdges() {
		return numOfEdges;
	}
	
	public int getNumOfVertices() {
		return outVertices.size();
	}
	public BTree<Vertex>getInVertices(Vertex vertex){
		return inVertices.get(vertex);
	}
	public BTree<Vertex>getOutVertices(Vertex vertex){
		return outVertices.get(vertex);
	}
}
package code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;



public class AList {
	
	HashMap<Vertex,BTree<Vertex>> map = null;
	int numOfEdges;
	
	public AList() {
		map = new HashMap<Vertex,BTree<Vertex>>();
		numOfEdges = 0;
	}
	
	public void addVertex(Vertex vertex) {
		//add start vertex to the HashMap
		if (!map.containsKey(vertex)) {
			BTree<Vertex> tree = new BTree<Vertex>();
			map.put(vertex,tree);
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
			numOfEdges++;
		}
	}
	public void deleteVertex(Vertex vertex) {
		//remove vertex and update BTrees and HashMap
		if (map.containsKey(vertex)) {
			System.out.println("deleting vertex");
			map.remove(vertex);
			//get the tree from each value in HashMap and delete the vertex from that tree
			for (BTree<Vertex> tree: map.values()) {
				if (!tree.isEmpty()) {
					tree.delete(vertex);					
				}
			}
		}
	}
	
	//searches the vertex by string
	public Vertex getVertex(Object key) {
		Set<Vertex> keys = map.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex currentKey = i.next();
			if (Utils.compareValue(currentKey.toString(), (String)key) == Utils.EQUAL){
				return currentKey;
			}
		}
		return null;
	}
	
	public boolean checkEdgeExist(Vertex source, Vertex end) {
		return false;
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
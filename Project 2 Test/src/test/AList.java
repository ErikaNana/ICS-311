package test;

import java.util.HashMap;


public class AList {
	
	HashMap<Vertex,BTree<Vertex>> map = null;
	
	public AList() {
		map = new HashMap<Vertex,BTree<Vertex>>();
	}
	
	public void addStartVertex(Vertex vertex) {
		//add start vertex to the HashMap
		if (!map.containsKey(vertex)) {
			System.out.println("doesn't contain " + vertex);
			BTree<Vertex> tree = new BTree<Vertex>();
			map.put(vertex,tree);
		}
	}
	public void addEndVertex(Vertex start, Vertex end) {
		//add end vertex to the BTree

		BTree<Vertex> tree;
		if (map.containsKey(start)) {
			System.out.println("add end vertex " + end + " to " + start);
			System.out.println("contains key " + start);
			tree = map.get(start);
			tree.insert(end, null);
			System.out.println("size now:  " + tree.size());
			tree.inorderTreeWalk(tree.getRoot());
		}
		//also add an edge from end to start
		if (map.containsKey(end)) {
			tree = map.get(end);
			tree.insert(start, null);
		}
		else { //if end vertex is not in the tree
			System.out.println("in else clause");
			addStartVertex(end);
			tree = map.get(end); //this should be empty
			tree.insert(start, null);
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
	public HashMap<Vertex,BTree<Vertex>> getMap() {
		return map;
	}
}
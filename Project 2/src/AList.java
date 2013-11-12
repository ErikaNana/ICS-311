

import java.util.HashMap;


public class AList {
	
	HashMap<Vertex,BTree<String>> map = null;
	
	public AList() {
		map = new HashMap<Vertex,BTree<String>>();
	}
	
	public void addStartVertex(Vertex vertex) {
		//add start vertex to the HashMap
		if (!map.containsKey(vertex)) {
			BTree<String> tree = new BTree<String>();
			map.put(vertex,tree);
		}
	}
	public void addEndVertex(Vertex start, Vertex end) {
		//add end vertex to the BTree
		if (map.containsKey(start)) {
			BTree<String> tree = map.get(start);
			tree.insert(end.getValue(), null);
		}
	}
	public void deleteVertex(Vertex vertex) {
		//remove vertex and update BTrees and HashMap
		if (map.containsKey(vertex)) {
			System.out.println("here!");
			map.remove(vertex);
			//get the tree from each value in HashMap and delete the vertex from that tree
			for (BTree<String> tree: map.values()) {
				if (!tree.isEmpty()) {
					tree.delete(vertex.getValue());					
				}
			}
		}
		
	}
	public HashMap<Vertex,BTree<String>> getMap() {
		return map;
	}
}
package bst;

import java.util.ArrayList;

public class TestBST {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		BTree<String> tree = new BTree<String>();
		ArrayList<String> list = new ArrayList<String>();
		list.add("f");
		list.add("d");
		list.add("a");
		list.add("e");
		list.add("c");
		list.add("b");
		
		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			BNode<String> node = new BNode<String>(key);
			tree.insert(key, node);
			//root
/*			if (node.getParent() != null)
				System.out.println("parent is:  " + node.getParent().getKey());
			System.out.println("");*/
		}
		System.out.println("size of tree:  " + tree.size());
		tree.inorderTreeWalk(tree.getRoot());
		System.out.println("");
		//System.out.println(((BNode<String>) tree.search("5")).getKey());
		System.out.println("--------------------------------");
		System.out.println("minimum:  " +((BNode<String>) tree.minimum()).getKey());
		System.out.println("maximum:  " + ((BNode<String>) tree.maximum()).getKey());
		System.out.println("sucessor: " + ((BNode<String>) tree.successor("c")).getKey());
		System.out.println("predecessor:  " + ((BNode<String>) tree.predecessor("c")).getKey());
		
		//delete 
		System.out.println("deleting f");
		tree.delete("f");
		tree.inorderTreeWalk(tree.getRoot());
		System.out.println("");
		System.out.println("size of tree now:  " + tree.size());
		System.out.println("print root:  " + tree.getRoot().getKey());
	}

}

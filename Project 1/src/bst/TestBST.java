package bst;

public class TestBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BTree<String> tree = new BTree<String>();
		
		for (int i = 0; i < 5; i++) {
			BNode<String> node = new BNode<String>("" + i);
			tree.insert("" + i, node);
		}
		tree.inorderTreeWalk(tree.getRoot());
	}

}

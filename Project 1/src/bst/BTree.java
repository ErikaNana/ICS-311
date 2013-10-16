package bst;

import main.DynamicSet;

public class BTree<Type> implements DynamicSet<Type>{
	private int size = 0;
	private BNode<Type> root = null;
	
	private static final int GREATER = 1;
	private static final int LESSER = 2;
	private static final int EQUAL = 3;
	
	public BTree() {
		
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	BNode<Type> getRoot(){
		return root;
	}
	public void inorderTreeWalk(BNode<Type> node) {
		if (node != null) {
			inorderTreeWalk(node.getLeftChild());
			System.out.println(node.getKey() + " ");
			inorderTreeWalk(node.getRightChild());
		}
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public void insert(Type key, Object e) {
		
		@SuppressWarnings("unchecked")
		BNode<Type> node = (BNode<Type>) e;
		node.setKey(key);
		
		if (root == null) {
			root = node;
			System.out.println("root is null");
		}
		else {
			BNode<Type> current = root;
			BNode<Type> parent;
			
			while (true) {
				parent = current;
				System.out.println("next iteration");
				System.out.println("Parent is current:  " + current.getKey());
				System.out.println("key is:  " + key);
				int compare = compareValue(key, current);
				if (compare == LESSER) {
					System.out.println("compare is lesser");
					current = current.getLeftChild();
					if (current == null) {
						System.out.println("left current is null");
						parent.setLeftChild(node);
						return;
					}
					System.out.println("new current is:  " + current.getKey());
				}
				else {
					current = current.getRightChild();
					System.out.println("key is greater than current");
					if (current == null) {
						parent.setRightChild(node);
						System.out.println("right current is null!");
						return;
					}
				}
			}
		}
/*		if (node == null) {
			node = new BNode<Type>(key);
			return;
		}
		//assume that keys and values are strings
		int compare = ((String) key).compareTo((String) node.getKey());
		switch(compare) {
			case EQUAL: return;
			case LESSER: insert(key,node.getLeftChild());
			case GREATER:
		}*/
	}

	@Override
	public void delete(Type key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object search(Type key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object minimum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object maximum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object successor(Type key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object predecessor(Type key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* Helper method that compares strings*/
	public int compareValue(Type value, BNode<Type> node) {
		System.out.println("COMPARE VALUE");
		//assume that list is of String type
		int compare = ((String) value).compareTo((String) node.getKey());
		if (compare > 0) {
			System.out.println("GREATER");
			return GREATER;
		}
		else if (compare == 0) {
			System.out.println("EQUAL");
			return EQUAL;
		}
		System.out.println("LESSER");
		return LESSER;
	}

}

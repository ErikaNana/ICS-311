package bst;

import main.DynamicSet;

/** Operations based on psuedocode found in the book*/

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
			System.out.print(node.getKey() + " ");
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
			size++;
		}
		else {
			BNode<Type> current = root;
			BNode<Type> parent;
			
			while (true) {
				parent = current;
				int compare = compareValue(key, current);
				if (compare == LESSER) {
					current = current.getLeftChild();
					if (current == null) {
						parent.setLeftChild(node);
						//set the parent pointers
						parent.getLeftChild().setParent(parent);
						size++;
						return;
					}
				}
				else {
					current = current.getRightChild();
					if (current == null) {
						parent.setRightChild(node);
						//set parent pointer
						parent.getRightChild().setParent(parent);
						size++;
						return;
					}
				}
			}
		}
	}

	@Override
	public void delete(Type key) {
		//search for the key to delete
		@SuppressWarnings("unchecked")
		BNode<Type> node = (BNode<Type>) this.search(key);
		
		if (node.getLeftChild() == null) {
			transplant(node, node.getRightChild());
		}
		else if (node.getRightChild() == null) {
			transplant(node, node.getLeftChild());
		}
		else  {
			System.out.println("in else");
			BNode<Type>y = minimum(node.getRightChild());
			if (y.getParent() != node) {
				transplant(y,y.getRightChild());
				y.setRightChild(node.getRightChild());
				y.getRightChild().setParent(y);
			}
			transplant(node,y);
			y.setLeftChild(node.getLeftChild());
			y.getLeftChild().setParent(y);
		}
		size--;
	}

	@Override
	public Object search(Type key) {
		//matches at the root
		BNode<Type> current = root;
		
		while (true) {
			int compare = compareValue(key, current);
			if (compare == LESSER) {
				current = current.getLeftChild();
				if (current == null) {
					System.out.println("Not in the tree");
					return null;
				}
			}
			else if (compare == GREATER) {
				current = current.getRightChild();
				if (current == null) {
					System.out.println("Not in the tree");
					return null;
				}
			}
			else {
				return current;
			}
		}
	}
	/*Gets the minimum of the whole tree*/
	@Override
	public Object minimum() {
		BNode<Type> temp = root;
		if (root == null) {
			return null;
		}
		else {
			while (temp.getLeftChild() != null) {
				temp = temp.getLeftChild();
			}
			return temp;
		}
	}
	/*Gets the maximum of the whole tree*/
	@Override
	public Object maximum() {
		BNode<Type> temp = root;
		if (root == null) {
			return null;
		}
		else {
			while(temp.getRightChild() != null) {
				temp = temp.getRightChild();
			}
			return temp;
		}
	}
	/*Returns null if can't be found*/
	@Override
	public Object successor(Type key) {
		@SuppressWarnings("unchecked")
		//search the tree for this node
		BNode<Type> node = (BNode<Type>) this.search(key);
		BNode<Type> successor = null;
		
		if (node.getRightChild() != null) {
			return minimum(node.getRightChild());
		}
		else {
			successor = node.getParent();
			while (successor != null && node == successor.getRightChild()) {
				node = successor;
				successor = successor.getParent();
			}
		}
		return successor;
	}
	/*Returns null if can't be found*/
	@Override
	public Object predecessor(Type key) {
		@SuppressWarnings("unchecked")
		BNode<Type> node = (BNode<Type>) this.search(key);
		BNode<Type> predecessor = null;
		
		if (node.getLeftChild() != null) {
			return maximum(node.getLeftChild());
		}
		else {
			predecessor = node.getParent();
			while(predecessor != null && node == predecessor.getLeftChild()) {
				node = predecessor;
				predecessor = predecessor.getParent();
			}
			return predecessor;
		}
	}
	
	/* Helper method that compares strings.  Assumes that keys are strings*/
	public int compareValue(Type value, BNode<Type> node) {
		
		int compare = ((String) value).compareTo((String) node.getKey());

		if (compare > 0) {
			return GREATER;
		}
		else if (compare == 0) {
			return EQUAL;
		}
		return LESSER;
	}
	
	/* Helper method that gets the minimum of a subtree */
	public BNode<Type> minimum(BNode<Type> node){
		while (node.getLeftChild() != null) {
			node = node.getLeftChild();
		}
		return node;
	}
	
	/* Helper method that gets the maximum of a subtree */
	public BNode<Type> maximum(BNode<Type> node){
		while (node.getRightChild() != null) {
			node = node.getRightChild();
		}
		return node;
	}
	
	/*Helper method for delete*/
	public void transplant(BNode<Type> u, BNode<Type> v) {
		if (u.getParent() == null) //u is the root of its subtree
				setRoot(v);
		else if (u == u.getParent().getLeftChild())
			u.getParent().setLeftChild(v);
		else u.getParent().setRightChild(v);
		if (v != null)
			v.setParent(u.getParent());
	}
	/*Sets the value of the root*/
	public void setRoot(BNode<Type> node) {
		root = node;
	}
}

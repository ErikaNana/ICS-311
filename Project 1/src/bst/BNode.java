package bst;

public class BNode<Type>{
	private Type key = null;
	private BNode<Type> leftChild = null;
	private BNode<Type> rightChild = null;
	private BNode<Type> parent = null;
	
	public BNode(Type key) {
		this.key = key;
	}
	/*********************** GETTERS ********************************************/
	public Type getKey() {
		return key;
	}
	
	public BNode<Type> getParent() {
		return parent;
	}
	
	public BNode<Type> getLeftChild() {
		return leftChild;
	}
	
	public BNode<Type> getRightChild() {
		return rightChild;
	}
	
	/*********************** SETTERS ********************************************/
	public void setKey(Type key) {
		this.key = key;
	}

	public void setRightChild(BNode<Type> right) {
		this.rightChild = right;
	}
	public void setLeftChild(BNode<Type> left) {
		this.leftChild = left;
	}
	public void setParent(BNode<Type> parent) {
		this.parent = parent;
	}
}
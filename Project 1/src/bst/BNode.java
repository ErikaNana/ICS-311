package bst;

/**
 * The Class BNode.
 *
 * @author Erika Nana
 * @param <Type> the generic type
 */
public class BNode<Type>{
	
	/** The key. */
	private Type key = null;
	
	/** The left child. */
	private BNode<Type> leftChild = null;
	
	/** The right child. */
	private BNode<Type> rightChild = null;
	
	/** The parent. */
	private BNode<Type> parent = null;
	
	/**
	 * Instantiates a new b node.
	 *
	 * @param key the value of the node
	 */
	public BNode(Type key) {
		this.key = key;
	}
	
	/**
	 * ********************* GETTERS *******************************************.
	 *
	 * @return the key
	 */
	public Type getKey() {
		return key;
	}
	
	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public BNode<Type> getParent() {
		return parent;
	}
	
	/**
	 * Gets the left child.
	 *
	 * @return the left child
	 */
	public BNode<Type> getLeftChild() {
		return leftChild;
	}
	
	/**
	 * Gets the right child.
	 *
	 * @return the right child
	 */
	public BNode<Type> getRightChild() {
		return rightChild;
	}
	
	/**
	 * ********************* SETTERS *******************************************.
	 *
	 * @param key the new key
	 */
	public void setKey(Type key) {
		this.key = key;
	}

	/**
	 * Sets the right child.
	 *
	 * @param right the new right child
	 */
	public void setRightChild(BNode<Type> right) {
		this.rightChild = right;
	}
	
	/**
	 * Sets the left child.
	 *
	 * @param left the new left child
	 */
	public void setLeftChild(BNode<Type> left) {
		this.leftChild = left;
	}
	
	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(BNode<Type> parent) {
		this.parent = parent;
	}
}
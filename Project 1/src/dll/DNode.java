package dll;
// TODO: Auto-generated Javadoc
/* Constructs a node for the doubly-linked list. 
 * @author Erika Nana
 */
/**
 * The Class DNode.
 *
 * @author Erika Nana
 * @param <Type> the generic type
 */
public class DNode<Type> {
	
	/** The value of the node. */
	private Type value = null;
	
	/** The node after this node. */
	private DNode<Type> next = null;
	
	/** The node before this node. */
	private DNode<Type> prev = null;
	
	/**
	 * Instantiates a new node.
	 */
	public DNode(){ //constructor
	}
	
	/**
	 * Instantiates a new node.
	 *
	 * @param value The value of the new node
	 */
	public DNode (Type value){
		this.value = value;
	}

	/**
	 * Gets the value of the node.
	 *
	 * @return The value of the node.
	 */
	public Type getValue() {
		return value;
	}

	/**
	 * Sets the value of the node.
	 *
	 * @param value The value to be set.
	 */
	public void setValue(Type value) {
		this.value = value;
	}

	/**
	 * Gets the next node.
	 *
	 * @return The next node.
	 */
	public DNode<Type> getNext() {
		return next;
	}

	/**
	 * Sets the next node.
	 *
	 * @param next The node to be set.
	 */
	public void setNext(DNode<Type> next) {
		this.next = next;
	}

	/**
	 * Gets the previous node.
	 *
	 * @return The previous node.
	 */
	public DNode<Type> getPrev() {
		return prev;
	}

	/**
	 * Sets the previous node.
	 *
	 * @param prev The node to be set as the previous node.
	 */
	public void setPrev(DNode<Type> prev) {
		this.prev = prev;
	}
	
	/**
	 * Connects this node to the next node.
	 *
	 * @param next The node to connect to.
	 */
	public void connectNext (DNode<Type> next){
		this.setNext(next); //set current node working with
		//to the next node = current object
		if (next != null){
			next.setPrev(this); // set the previous of next to current object
		}
	}
	
	/**
	 * Connects this node to the previous node.
	 *
	 * @param prev The node to connect to.
	 */
	public void connectPrev (DNode<Type> prev){
		this.setPrev(prev); 
		if (prev != null){
			prev.setNext(this); //current object prev
			//set the next node of previous to the previous node
		}
	}
}

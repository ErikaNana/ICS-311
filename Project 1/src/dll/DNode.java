package dll;
/* Constructs a node for the doubly-linked list. 
 * @author Erika Nana
 */
public class DNode<Type> {
	private Type value = null;
	private DNode<Type> next = null;
	private DNode<Type> prev = null;
	
	public DNode(){ //constructor
	}
	
	public DNode (Type value){ //constructor with value as parameter
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Type getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Type value) {
		this.value = value;
	}

	/**
	 * @return the next
	 */
	public DNode<Type> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(DNode<Type> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public DNode<Type> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(DNode<Type> prev) {
		this.prev = prev;
	}
	
	public void connectNext (DNode<Type> next){
		this.setNext(next); //set current node working with
		//to the next node = current object
		if (next != null){
			next.setPrev(this); // set the previous of next to current object
		}
	}
	
	public void connectPrev (DNode<Type> prev){
		this.setPrev(prev); 
		if (prev != null){
			prev.setNext(this); //current object prev
			//set the next node of previous to the previous node
		}
	}
}

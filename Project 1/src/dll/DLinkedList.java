package dll;
/* Constructs a doubly-linked list. 
 * @author Erika Nana
 */
public class DLinkedList<Type> {
	private DNode<Type> head = null;
	private int size = 0;
	
	 /**adds an item to the end of the list
	  * */
	public void add (Type value){

		DNode<Type> newNode = new DNode<Type>(value);
		if (this.isEmpty()) { 
			head = newNode;
		}
		else {
			DNode<Type> temp = head;
			while (temp.getNext() != null) { 
				temp = temp.getNext();
			}
			temp.setNext(newNode); 
			newNode.setPrev(temp); 
		}
		size++;
	}
	 /**inserts an item to the specified index
	  * */
	public void insert (Type value, int index) {
		DNode<Type> newNode = new DNode<Type>(value);
		if (index ==0) {
			newNode.connectNext(head);
			head = newNode;
		}
		else {
			if (index >= size) {
				add(value);
				return;
			}
			DNode<Type> temp = head;
			int i = 0;
			for (;i < index && temp.getNext() != null; i++) {
				temp = temp.getNext();
			}
			if (i == index) {
				//newNode should be inserted before temp node
				temp.getPrev().connectNext(newNode);
				newNode.connectNext(temp);
			}
		}
		size++;
	}
	 /**removes an item at a specified index.
	  * */
	public void remove (int index) throws NodeCountException {
	    if ((index < 0) || (index > size)) {
	        throw new NodeCountException ("invalid index " + index
	            + ": only 1.." + size + " items are available");
	      }

		if (index == 0) {
			head = head.getNext();
		}
		else {
			DNode<Type> temp = head;
			int i = 0;
			for(; i < index && temp.getNext() != null; i++) {
				if ((temp.getNext() == null) && (i == index)) {
					temp.getPrev().setNext(null);
					temp.getPrev().connectNext(null);
					break;
				}
				temp = temp.getNext();
			}
			if (i == index) {
				temp.getPrev().setNext(temp.getNext());
				temp.getPrev().connectNext(temp.getNext());//set value of temp
			}
		}
		size--;
	}
	 /**traverses the list and looks for the specified String name
	  * and returns the index.
	  * */
	public int traverse (String name) {
		int i = 0;
		String value = "";
		value = head.getValue().toString();
		
		if (value.equals(name)) {
			return 0;
			
		}
		else {
			DNode<Type> temp = head;

			for(i = 1; i < size + 1 && temp.getNext() != null; i++) {
				temp = temp.getNext();
				value = temp.getValue().toString();
				if (value.equals(name)) {
					temp.getPrev().setNext(temp.getNext());
					break;
				}
			}
		}
		return i;
	}
	 /**checks to see if the list is empty.
	  * */
	public boolean isEmpty() {
		return head == null;
	}
	 /**provides a String representation of the list.
	  * */
	public String toString() {
		DNode<Type> temp = head;
		String answer = "";
		while(temp != null) {
			answer = answer + temp.getValue().toString()+ " ";
			temp = temp.getNext();
		}
		return answer;
	}
}

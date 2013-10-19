package dll;

import utils.Utils;
import main.DynamicSet;

/* Constructs a sorted doubly-linked list. 
 * @author Erika Nana
 */
public class DLinkedList<Type> implements DynamicSet<Type> {
	
	private DNode<Type> head = null;
	private int size = 0;
	private DNode<Type> empty = null; //for null pointer
	
	/* Constructor */
	public DLinkedList(){
		empty = new DNode<Type>(); //for null pointer
	}
	/* Checks to see if the list is empty*/
	public boolean isEmpty() {
		return head == null;
	}
	/* Provides a String representation of the list*/
	public String toString() {
		DNode<Type> temp = head;
		String answer = "";
		while(temp != null) {
			answer = answer + temp.getValue() + "\n";
			temp = temp.getNext();
		}
		return answer;
	}
	
	@Override
	public int size() {
		return size;
	}
	@Override
	public void insert(Type value, Object e) {
		DNode<Type> temp = head;
		DNode<Type> newNode = new DNode<Type>(value);
		newNode.setValue(value);
		boolean insert = false; //check to see if inserted
		
		if (size == 0) {
			head = newNode;
		}
		if (size == 1) {
			int compare = Utils.compareValue((String)value, (String) head.getValue());
			if (compare == Utils.GREATER || compare == Utils.EQUAL) {
				//add the thing after
				head.connectNext(newNode);
			}
			else { //add the thing before
				head = newNode;
				head.connectNext(temp);
			}
		}
		else {
			//compare the values
			if (size  > 1) {
				while (temp.getNext()!= null) {
					int compare = Utils.compareValue((String)value, (String) temp.getValue());
					if (compare == Utils.LESSER) {
						//special case if the head
						if (temp == head) {

							head = newNode;
							head.connectNext(temp);
							temp.connectPrev(head);
						}
						else { //connect it before the node
							temp.getPrev().connectNext(newNode);
							temp.connectPrev(newNode);
							newNode.connectNext(temp);
						}
						insert = true;
						break;	
					}
					temp = temp.getNext();
				}
				//reached the end of the list
				if (!insert) {
					//check the last node
					if (Utils.compareValue((String) value, (String) temp.getValue()) == Utils.LESSER) {
						temp.getPrev().connectNext(newNode);
						temp.connectPrev(newNode);
						newNode.connectNext(temp);
					}
					else {
						temp.connectNext(newNode);
					}	
				}
			}
		}
		size++;
		
	}
	@Override
	public void delete(Type key) {
		DNode<Type> temp = head;
		while (temp != null) {
			if (temp.getValue().equals(key)) {
				//adjust the pointers
				if (temp == head) {//deleting the head
					head = null;
					size--;
					break;
				}
				temp.getPrev().connectNext(temp.getNext());
				size--;
			}
			temp = temp.getNext();
		}
	}
	@Override
	public Object search(Type key) {
		DNode<Type> temp = head;
		while (temp != null) {
			//find the node
			if (temp.getValue().equals(key)) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}
	@Override
	public Object minimum() {
		return head;
	}
	@Override
	public Object maximum() {
		DNode<Type> temp = head;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		return temp;
	}
	@Override
	public Object successor(Type key) {
		@SuppressWarnings("unchecked")
		DNode<Type>temp = (DNode<Type>) search(key);
		//handle null pointer exceptions
		if (temp.getNext() == null) {
			return empty;
		}
		return temp.getNext();
	}
	@Override
	public Object predecessor(Type key) {
		@SuppressWarnings("unchecked")
		DNode<Type>temp = (DNode<Type>) search(key);
		//handle null pointer exceptions
		if (temp.getPrev() == null) {
			return empty;
		}
		else {
			return temp.getPrev();
		}
	}
	
}

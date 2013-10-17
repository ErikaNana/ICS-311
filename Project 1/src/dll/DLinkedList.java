package dll;

import main.DynamicSet;
//fix null pointer exceptions (with predecessor), use maybe sentinels

/* Constructs a sorted doubly-linked list. 
 * @author Erika Nana
 */
public class DLinkedList<Type> implements DynamicSet<Type> {
	
	private DNode<Type> head = null;
	private int size = 0;
	
	private static final int GREATER = 1;
	private static final int LESSER = 2;
	private static final int EQUAL = 3;
	
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
			answer = answer + temp.getValue().toString()+ " ";
			temp = temp.getNext();
		}
		return answer;
	}
	
	/* Helper method that compares strings*/
	public int compareValue(Type value, DNode<Type> node) {
		System.out.println("COMPARE VALUE");
		//assume that list is of String type
		int compare = ((String) value).compareTo((String) node.getValue());
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
	@Override
	public int size() {
		return size;
	}
	@Override
	public void insert(Type value, Object e) {
		DNode<Type> temp = head;
		@SuppressWarnings("unchecked")
		DNode<Type> newNode = (DNode<Type>) e;
		newNode.setValue(value);
		boolean insert = false; //check to see if inserted
		
		if (size == 0) {
			System.out.println("size 0");
			head = newNode;
		}
		if (size == 1) {
			System.out.println("size 1");
			int compare = compareValue(value, head);
			if (compare == GREATER || compare == EQUAL) {
				//add the thing after
				head.connectNext(newNode);
			}
			else { //add the thing before
				System.out.println("less than in size 1");
				head = newNode;
				head.connectNext(temp);
			}
		}
		else {
			//compare the values
			if (size  > 1) {
				System.out.println("bigger than 1");
				while (temp.getNext()!= null) {
					System.out.println("size:  " + size);
					System.out.println("temp is:  " + temp.getValue());
					int compare = compareValue(value,temp);
					if (compare == LESSER) {
						//special case if the head
						if (temp == head) {
							System.out.println("temp is the head");
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
						System.out.println("inserting before the lesser");
						System.out.println("temp:  "+ temp.getValue());
						break;	
					}
					temp = temp.getNext();
				}
				//reached the end of the list
				if (!insert) {
					System.out.println("end of the list");
					//check the last node
					if (compareValue(value, temp) == LESSER) {
						temp.getPrev().connectNext(newNode);
						temp.connectPrev(newNode);
						newNode.connectNext(temp);
					}
					else {
						temp.connectNext(newNode);
					}
					System.out.println("temp:  " + temp.getValue());	
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

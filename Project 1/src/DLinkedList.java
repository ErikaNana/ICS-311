/*
 * Copyright (c) 2013, Erika Nana
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Project 1 nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Erika Nana ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Erika Nana BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/** 
 * Allows the user to run tests on the three data structures at one time.
 * Also supports individual operations on the data structures.
 * 
 * @author Erika Nana
 */
/**
 * Constructs a sorted doubly-linked list. 
 *
 * @author Erika Nana
 * @param <Type> the generic type
 */
public class DLinkedList<Type> implements DynamicSet<Type> {
	
	/** The head. */
	private DNode<Type> head = null;
	
	/** The tail.  */
	private DNode<Type> tail = null;
	
	/** The size. */
	private int size = 0;
	
	/** The empty node, used to handle null pointer exceptions. */
	private DNode<Type> empty = null; 
	
	/**
	 * Instantiates a new doubly linked list.
	 */
	public DLinkedList(){
		empty = new DNode<Type>(); //for null pointer
	}

	/**
	 * Checks if is empty.
	 *
	 * @return True, if is empty
	 */
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
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#size()
	 */
	
	public int size() {
		return size;
	}
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#insert(java.lang.Object, java.lang.Object)
	 */
	
	public void insert(Type value, Object e) {
		DNode<Type> temp = head;
		DNode<Type> newNode = new DNode<Type>(value);
		newNode.setValue(value);
		boolean insert = false; //check to see if inserted
		
		if (size == 0) {
			head = newNode;
			tail = newNode;
		}
		if (size == 1) {
			int compare = Utils.compareValue((String)value, (String) head.getValue());
			if (compare == Utils.GREATER || compare == Utils.EQUAL) {
				//add the thing after
				head.connectNext(newNode);
				tail = newNode;
			}
			else { //add the thing before
				head = newNode;
				head.connectNext(temp);
				tail = temp;
				
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
						tail = newNode;
					}	
				}
			}
		}
		size++;
	}
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#delete(java.lang.Object)
	 */
	
	public void delete(Type key) {
		DNode<Type> temp = head;
		while (temp != null) {
			if (temp.getValue().equals(key)) {
				//adjust the pointers
				if (temp == head) {//deleting the head
					head = head.getNext();
					size--;
					break;
				}
				if (temp == tail) {
					tail = temp.getPrev();
				}
				temp.getPrev().connectNext(temp.getNext());
				size--;
				break;
			}
			temp = temp.getNext();
		}
	}
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#search(java.lang.Object)
	 */
	
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
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#minimum()
	 */
	
	public Object minimum() {
		return head;
	}
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#maximum()
	 */
	
	public Object maximum() {
		return tail;
	}
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#successor(java.lang.Object)
	 */
	
	public Object successor(Type key) {
		@SuppressWarnings("unchecked")
		DNode<Type>temp = (DNode<Type>) search(key);
		//handle null pointer exceptions
		if (temp.getNext() == null) {
			return empty;
		}
		return temp.getNext();
	}
	
	/* (non-Javadoc)
	 * @see main.DynamicSet#predecessor(java.lang.Object)
	 */
	
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

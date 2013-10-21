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
 * Constructs a node for the doubly-linked list.
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

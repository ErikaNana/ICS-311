package code;
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
 *     * Neither the name of Extra Credit nor the
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
 * Implementation of heap sort
 * 
 * @author Erika Nana
 * 
 */

import java.util.ArrayList;

public class Heap implements Sort {
	ArrayList<String> array;
	int largest;
	
	public Heap(ArrayList<String> array) {
		this.array = array;
	}
	
	public String getParent(int index) {
		if (index == 1) {
			return null;
		}
		return array.get(index/2);
	}
	
	public String getLeftChild(int index) {
		long position = 2*index;
		if (position > array.size()) {
			return null;
		}
		return array.get(2*index-1);
	}
	
	public String getRightChild(int index) {
		long position = 2*index + 1;
		if (position > array.size()) {
			return null;
		}
		return array.get(2*index);
	}

	public void maxHeapify(int index, int size) {
		int left = 2*index;
		int right = 2*index + 1;
		if ((left <= size) && (array.get(left-1).compareTo(array.get(index-1)) > 0)){
			largest = left;
		}
		else {
			largest = index;
		}
		if ((right <= size) && (array.get(right -1).compareTo(array.get(largest-1)) > 0)){
			largest = right;
		}
		if (largest != index) {
			String temp = array.get(index-1);
			//swaps
			array.set(index-1, array.get(largest-1));
			array.set(largest-1, temp);
			this.maxHeapify(largest, size);		
		}
	}
	public void buildMaxHeap() {
		//take placeholder into account
		int size = array.size();
		for (int i = size/2; i > 0; i--) {
			this.maxHeapify(i,size);
		}
	}
	public void printHeap() {
		for (int i = 1; i < array.size(); i++) {
			System.out.println("node:  " + array.get(i));
			int left = 2*i;
			int right = 2*i + 1;
				if (left >= array.size()) {
					System.out.println("     no left child");
				}
				else {
					System.out.println("     left child:  " + array.get(left));
				}
				if (right >= array.size()) {
					System.out.println("     no right child");
				}
				else {
					System.out.println("     right child:  " + array.get(right));
				}
			
		}
	}
	
	public ArrayList<String> getArray(){
		return array;
	}
	//these methods only return correct values after sort is called
	public String getFirstValue() {
		return array.get(0);
	}
	
	public String getLastValue() {
		return array.get(array.size()-1);
	}
	
	public void sort(int p, int r) {
		this.buildMaxHeap();
		int size = array.size();
		for (int i = size; i > 1; i--) {
			//swap
			String temp = array.get(i-1);
			array.set(i-1,array.get(0));
			array.set(0, temp);
			size = size - 1;
			this.maxHeapify(1,size);
		}
	}
}

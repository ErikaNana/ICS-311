package heap;

import java.util.ArrayList;

//this starts at 1 not 0
public class Heap {
	ArrayList<String> array;
	
	public Heap(ArrayList<String> array) {
		this.array = array;
	}
	
	public String getParent(int index) {
		if (index == 1) {
			return null;
		}
		return array.get((int)Math.floor((double)index/2));
	}
	
	public String getLeftChild(int index) {
		long position = 2*index;
		if (position >= array.size()) {
			return null;
		}
		return array.get(2*index);
	}
	
	public String getRightChild(int index) {
		long position = 2*index + 1;
		if (position >= array.size()) {
			return null;
		}
		return array.get(2*index + 1);
	}
	public void printHeap() {
		for (int i = 1; i < array.size(); i++) {
			System.out.println("node:  " + array.get(i));
			int left = i + 1;
			int right = i + 2;
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
}

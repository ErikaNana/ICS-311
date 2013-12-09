package code;


import java.util.ArrayList;


//this starts at 1 not 0
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
/*		if ((left <= size) && (Utils.compare(array.get(left-1), array.get(index-1))) == 1){*/
/*			System.out.println("left:  " + array.get(left-1));
			System.out.println("index:  " + array.get(index-1));
			System.out.println("    left is bigger than index");*/
			largest = left;
		}
		else {
			largest = index;
		}
		if ((right <= size) && (array.get(right -1).compareTo(array.get(largest-1)) > 0))
/*		if ((right <= size) && (Utils.compare(array.get(right-1), array.get(largest-1))) == 1)*/{
			largest = right;
/*			System.out.println("right is:  " + array.get(right-1));
			System.out.println("    right is bigger than largest");*/
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
	
	//unused method from interface
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

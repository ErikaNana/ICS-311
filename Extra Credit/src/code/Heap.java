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

	public void maxHeapify(int index, int size) {
		int left = 2*index;
		int right = 2*index + 1;

		//adjust for placeholder in array
		if ((left <= size) && (Utils.compare(array.get(left), array.get(index))) == 1){
			largest = left;
		}
		else {
			largest = index;
		}
		if ((right <= size) && (Utils.compare(array.get(right), array.get(largest))) == 1){
			largest = right;
		}
		if (largest != index) {
			String temp = array.get(index);
			//swaps
			array.set(index, array.get(largest));
			array.set(largest, temp);
			this.maxHeapify(largest, size);		
		}
	}
	public void buildMaxHeap() {
		//take placeholder into account
		int size = array.size() - 1;
		for (int i = (int)Math.floor((double)size/2); i > 0; i--) {
			this.maxHeapify(i,size);
		}
	}
	public void sort() {
		this.buildMaxHeap();
		int size = array.size() - 1;
		for (int i = size; i > 1; i--) {
			//swap
			String temp = array.get(i);
			array.set(i,array.get(1));
			array.set(1, temp);
			size = size - 1;
			this.maxHeapify(1,size);
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
		return array.get(1);
	}
	
	public String getLastValue() {
		return array.get(array.size()-1);
	}
	
	//unused method from interface
	public void sort(int p, int r) {		
	}
}

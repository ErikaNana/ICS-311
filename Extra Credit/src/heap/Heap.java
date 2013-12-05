package heap;

import java.util.ArrayList;

//this starts at 1 not 0
public class Heap {
	ArrayList<Node> array;
	
	public Heap(ArrayList<Node> array) {
		this.array = array;
	}
	
	public Node getParent(int index) {
		if (index == 1) {
			return null;
		}
		return array.get((int)Math.floor((double)index/2));
	}
	
	public Node getLeftChild(int index) {
		long position = 2*index;
		if (position >= array.size()) {
			return null;
		}
		return array.get(2*index);
	}
	
	public Node getRightChild(int index) {
		long position = 2*index + 1;
		if (position >= array.size()) {
			return null;
		}
		return array.get(2*index + 1);
	}
}

package code;

import java.util.ArrayList;

public class Quick implements Sort {
	ArrayList<String> array;
	
	public Quick(ArrayList<String> array) {
		this.array = array;
	}
	
	public void sort(int p, int r) {
		if (p < r) {
			int q = partition(p,r);
			sort(p,q-1);
			sort(q+1,r);
		}
	}
	
	public int partition(int p, int r) {
		String value = array.get(r-1);
		int i = p - 2; //adjust for indexing at 0
		for (int j = p-1; j < r-1; j++) {
			if ((Utils.compare(array.get(j), value) == -1) || (Utils.compare(array.get(j), value) == -1)) {
				i = i + 1;
				exchange(i,j);
			}
		}
		exchange(i+1,r-1);
		return i + 2; //adjust for indexing at 0
	}
	
	public void exchange(int first, int second) {
		String temp = array.get(first);
		array.set(first, array.get(second));
		array.set(second, temp);
	}
	
	//only returns correct values if sort is called first
	public ArrayList<String> getSortedArray(){
		return array;
	}
	
	public String getFirstValue() {
		return array.get(0);
	}
	
	public String getlastValue() {
		return array.get(array.size()-1);
	}
	
	//unused sort from interface
	public void sort() {
	}
}

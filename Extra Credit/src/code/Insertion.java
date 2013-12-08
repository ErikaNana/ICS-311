package code;

import java.util.ArrayList;

public class Insertion {

	ArrayList<String> array;
	
	public Insertion(ArrayList<String> array) {
		this.array = array;
	}
	
	public void sort() {
		for (int j = 1; j < array.size(); j++) {
			int i = 0;
			String key = "";
			key = array.get(j);
			i = j -1;
			while ((i >= 0) && (Utils.compare(array.get(i), key) == 1)) {
				array.set(i+1, array.get(i));
				i = i - 1;
			}
			array.set(i+1, key);
		}
	}
	
	//returns sorted array only after sort is called
	public ArrayList<String> returnSortedArray(){
		return array;
	}
}

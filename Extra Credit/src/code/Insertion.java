package code;

import java.util.ArrayList;

public class Insertion implements Sort {

	ArrayList<String> array;

	public Insertion(ArrayList<String> array) {
		this.array = array;
	}
	//these methods only returns correct values if sort is called first
	public ArrayList<String> returnSortedArray(){
		return array;
	}
	public String getFirstValue() {
		return array.get(0);
	}
	
	public String getLastValue() {
		return array.get(array.size()-1);
	}
	
	public void sort(int p, int r) {
		for (int j = 1; j < array.size(); j++) {
			int i = 0;
			String key = "";
			key = array.get(j);
			i = j -1;
			while ((i >= 0) && (array.get(i).compareToIgnoreCase(key) > 0)){
/*			while ((i >= 0) && (Utils.compare(array.get(i), key) == 1)) {*/
				array.set(i+1, array.get(i));
				i = i - 1;
			}
			array.set(i+1, key);
		}
	}
}

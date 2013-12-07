package code;

import java.util.ArrayList;
import java.util.Collections;


public class Merge {
	ArrayList<String> array;
	ArrayList<String> result;
	
	public Merge(ArrayList<String> array) {
		this.array = array;
		//initialize results array so can set in merge
		result = new ArrayList<String>(Collections.nCopies(array.size(), ""));
	}

	public void merge(int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;

		ArrayList<String> left = new ArrayList<String>();
		ArrayList<String> right = new ArrayList<String>();

		for (int i = 0; i < n1; i++) {
			left.add(i, array.get(p+i-1));
		}
		for (int j = 0; j < n2; j++) {
			right.add(j, array.get(q+j));
		}
		left.add(n1,"inf");
		right.add(n1,"inf");
		
		int i = 0;
		int j = 0;
		
		for (int k = p-1; k < r; k++) {
			if ((Utils.compare(left.get(i), right.get(j)) == -1) || (Utils.compare(left.get(i), right.get(j)) == 0)) {
				result.set(k, left.get(i));
				i = i + 1;
			}
			else {
				result.set(k, right.get(j));
				j = j + 1;
			}
		}
	}
	public void sort(int p, int r) {
		if (p < r) {
			int q = (p + r)/2;
			this.sort(p, q);
			this.sort(q+1, r);
			this.merge(p, q, r);
		}
	}
	
	public ArrayList<String> returnSortedArray(){
		return result;
	}
}
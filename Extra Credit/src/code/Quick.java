package code;

import java.util.ArrayList;
	
public class Quick implements Sort{
	//private int[] array;
	private int size;
	ArrayList<String> array;
	
	public Quick (ArrayList<String> values) {
	  // check for empty or null array
	  if (values ==null || values.size() == 0){
	    return;
	  }
	  this.array = values;
	  size = values.size();
	}
	
	private void quicksort(int low, int high) {
	  int i = low, j = high;
	  
	  // Get the pivot element from the middle of the list
	  String pivot = array.get(low + (high-low)/2);
	
	  // Divide into two lists
	  while (i <= j) {
	    // If the current value from the left list is smaller then the pivot
	    // element then get the next element from the left list
		  while (array.get(i).compareTo(pivot) < 0) {
	      i++;
	    }
	    // If the current value from the right list is larger then the pivot
	    // element then get the next element from the right list
		  while (array.get(j).compareTo(pivot) > 0) {
	      j--;
	    }
	
	    // If we have found a values in the left list which is larger then
	    // the pivot element and if we have found a value in the right list
	    // which is smaller then the pivot element then we exchange the
	    // values.
	    // As we are done we can increase i and j
	    if (i <= j) {
	      exchange(i, j);
	      i++;
	      j--;
	    }
	  }
	  // Recursion
	  if (low < j)
	    quicksort(low, j);
	  if (i < high)
	    quicksort(i, high);
	}
	
	private void exchange(int i, int j) {
		String temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}
	
	public void sort(int p, int r) {
		quicksort(0, size - 1);
	}
	
	public String getFirstValue() {
		return array.get(0);
	}
	
	public String getLastValue() {
		return array.get(array.size()-1);
	}
	
	public ArrayList<String> getSortedArray(){
		return array;
	}
}

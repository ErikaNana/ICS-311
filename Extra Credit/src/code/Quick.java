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
 * Quick sort implementation
 * 
 * @author Original Author          Lars Vogel
 * @author Derivative Author        Erika Nana
 * 
 */
import java.util.ArrayList;
	
public class Quick implements Sort{
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

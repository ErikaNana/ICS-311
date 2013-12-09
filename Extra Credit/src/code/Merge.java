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
 * Implementation of merge sort
 * 
 * @author Erika Nana
 * 
 */
import java.util.ArrayList;

public class Merge implements Sort {
	ArrayList<String> array;
	
	public Merge(ArrayList<String> array) {
		this.array = array;
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
		right.add(n2,"inf");
		
		//changed from psuedocode to start from 0
		int i = 0;
		int j = 0;
		for (int k = p-1; k < r; k++) {
			if (left.get(i).compareTo(right.get(j)) <=0) {
				array.set(k, left.get(i));
				i = i + 1;
			}
			else {
				array.set(k, right.get(j));
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
	
	//returns correct values only if sort is called first
	public ArrayList<String> returnSortedArray(){
		return array;
	}
	
	public String getFirstValue() {
		return array.get(0);
	}
	
	public String getLastValue() {
		return array.get(array.size()-1);
	}
}

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
 * Implementation of insertion sort
 * 
 * @author Erika Nana
 * 
 */
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

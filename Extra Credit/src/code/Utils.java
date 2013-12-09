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
 * Provides helper functions for displaying output.
 * 
 * @author Erika Nana
 * 
 */
public class Utils {	
    /**
     * Prints the divider
     */
    public static void printDivider() {
            for(int i = 0; i < 80; i++) {
                    System.out.print("-");
            }
            System.out.println("");
    }
	//run the sort 10 times, and return an array of all the times
	public static void sortAndPrint(Sort sort, int p, int r,String title) {
		Object [] results = new Object [3];
		double runningTotal = 0;
		for (int i = 0; i < 10; i++) {
			double start = System.nanoTime();
			Sort inputSort = sort;
			inputSort.sort(p, r);
			if (i == 0) { //do this only once
				results[1] = inputSort.getFirstValue();
				results[2] = inputSort.getLastValue();
			}
			inputSort = null;
			double end = System.nanoTime();
			runningTotal = runningTotal + (end - start);
		}
		results[0] = runningTotal/10;
		//print the results
		String leftAlignFormat = "%-9s: %14.2f ns; First Key: ";
		System.out.println();
		System.out.format(leftAlignFormat,title, (Double) results[0]);
		System.out.printf("%-7s", (String) results[1]);
		System.out.print("; Last Key: ");
		System.out.printf("%-7s", (String) results[2]);
	}
}

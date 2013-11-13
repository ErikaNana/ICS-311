package test;
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
 *     * Neither the name of Project 1 nor the
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
 * Allows the user to run tests on the three data structures at one time.
 * Also supports individual operations on the data structures.
 * 
 * @author Erika Nana
 */
/**
 * Includes static helper methods.
 *
 * @author Erika Nana
 * @param <Type> the generic type
 */
public class Utils<Type> {
	
	/**
	 * The Enum Command: used to emulate a switch on the inputs.
	 */
	public enum Command{
		runtest, insert, search, delete, pred, succ, min, max, loadNew, quit;
	}
	
	/*used for the compareValue method*/
	
	public static final int GREATER = 1;
	public static final int LESSER = 2;
	public static final int EQUAL = 3;
	
	//These are used to determine the operations for runtest
	public static final int INSERT = 4;
	public static final int SUCCESSOR = 5;
	public static final int PREDECESSOR = 6;
	public static final int SEARCH = 7;
	public static final int MINIMUM = 8;
	public static final int MAXIMUM = 9;
	
	/**
	 * Helper method that compares strings
	 *
	 * @param value The string
	 * @param nodeValue The node value to be compared
	 * @return The result of the comparison
	 */
	public static int compareValue(String value, String nodeValue) {
		int compare = (value.compareToIgnoreCase(nodeValue));
		//check if string has numbers, if it does compare as integers
		if (value.matches("\\d")) {
			int newValue = Integer.parseInt(value);
			int newNodeValue = Integer.parseInt(nodeValue);
			if (newValue > newNodeValue) {
				return GREATER;
			}
			else if (newValue == newNodeValue) {
				return EQUAL;
			}
			else {
				return LESSER;
			}
		}
		if (compare > 0) {
			return GREATER;
		}
		else if (compare == 0) {
			return EQUAL;
		}
		return LESSER;
	}
	
	/**
	 * Outputs the minimum, maximum and average times of the operations on a set.
	 *
	 * @param set The set that is operated on
	 * @param input The array to be used as input
	 * @param operation The operation to be performed
	 * @return An array of times
	 */
	public static double[] testSet(DynamicSet<String> set, String[] input, int operation) {
		double [] outputArray = new double [3];
		double sum = 0;
		double min = 1000000000000000000000000000.0;
		double max = 0;
		
		for (String word: input) {
			double start = System.nanoTime();
			//operation is based on input
			switch (operation) {
				case INSERT: set.insert(word,null); break; 
				case SEARCH: set.search(word); break;
				case SUCCESSOR: set.successor(word); break;
				case PREDECESSOR: set.predecessor(word); break;
			}
			
			double end = System.nanoTime();
			double time = end - start;
			if (time < min) {
				min = time;
			}
			if (time > max) {
				max = time;
			}
			sum = sum + time;
		}
		outputArray[0] = min;
		outputArray[1] = max;
		outputArray[2] = sum/input.length;
		return outputArray;
	}
	
	/**
	 * Tests the the time it takes to get the minimum or the maximum value of a set
	 *
	 * @param set The set to be operated on
	 * @param operation The operation to be performed
	 * @return The time it takes for the operation to be completed
	 */
	public static double testMinMax(DynamicSet<String> set, int operation) {
		double start = System.nanoTime();
		if (operation == MINIMUM) {
			set.minimum();
		}
		if (operation == MAXIMUM) {
			set.maximum();
		}
		double end = System.nanoTime();
		return end - start;
	}
	
	/**
	 * Checks to see if a command is valid.
	 *
	 * @param test The input to be tested
	 * @return true, if is a Command
	 */
	public static boolean isEnum(String test) {

	    for (Command c : Command.values()) {
	        if (c.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Prints the divider for the runtest table.
	 */
	public static void printDivider() {
		for(int i = 0; i < 154; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	
	/**
	 * Prints a cell of the runtest table.
	 *
	 * @param input The array of values to be printed.
	 */
	public static void printCell(double[] input) {
		System.out.printf("%-4.2e/%-4.2e/%-4.2e", input[0], input[1], input[2]);
	}
	
	/**
	 * Prints the row of the runtest table.
	 *
	 * @param set the set
	 * @param array the array
	 * @param randomValues the random values
	 */
	public static void printRow(DynamicSet<String> set, String[] array, String[] randomValues) {
		double[] insert = testSet(set, array, Utils.INSERT);
		double[] search = testSet(set, randomValues, Utils.SEARCH);
		double[] pred = testSet(set, randomValues, Utils.PREDECESSOR);
		double[] succ = testSet(set, randomValues, Utils.SUCCESSOR);
		double min = testMinMax(set, Utils.MINIMUM);
		double max = testMinMax(set, Utils.MAXIMUM);
		
		printCell(insert);
		System.out.print(" | ");
		printCell(search);
		System.out.print(" | ");
		printCell(pred);
		System.out.print(" | ");
		printCell(succ);
		System.out.print(" | ");
		System.out.printf("%-4.2e", min);
		System.out.print(" | ");
		System.out.printf("%-4.2e", max);
		System.out.print(" | \n");
		printDivider();
	}
}

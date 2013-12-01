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
		analyzeGraph, quit, runSCC;
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
		for(int i = 0; i < 72; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	
	public static void printTable(DirectedGraph graph, String name) {
		int vertices = graph.numVertices();
		int arcs = graph.numArcs();
		Object[] inDegreeStats = Metrics.getInDegreeStats(graph);
		Object[] outDegreeStats = Metrics.getOutDegreeStats(graph);
		Object [] sccStats = Metrics.runSCC(graph);
		double density = Metrics.getDensity(graph);
		System.out.println("");
		printDivider();
		System.out.printf("%-10s",name + "\n"); //left justified
		printDivider();
		System.out.printf("%-10s", "|V| = " + vertices);
		System.out.println("");
		System.out.printf("%-10s", "|A| = " + arcs);
		System.out.println();
		System.out.printf("%-10s", "Density = " + density);
		System.out.println("");
		System.out.printf("%-20s", "Degree Distribution: ");
		System.out.printf("%10s", "minimum");
		System.out.printf("%10s", "average");
		System.out.printf("%10s", "maximum");
		System.out.println("");
		System.out.printf("%15s", "inDegree");
		System.out.printf("%10d", inDegreeStats[0]);
		System.out.printf("%15.3f", inDegreeStats[2]);
		System.out.printf("%5d", inDegreeStats[1]);
		System.out.println("");
		System.out.printf("%16s", "outDegree");
		System.out.printf("%9d", outDegreeStats[0]);
		System.out.printf("%15.3f", outDegreeStats[2]);
		System.out.printf("%5d", outDegreeStats[1]);
		System.out.println("");
		System.out.printf("%-20s", "Number of Strongly Connected Components:");
		System.out.printf("%5d",sccStats[0]);
		System.out.println("");
		System.out.printf("%-20s", "Perecent Vertices in Largest Strongly Connected Component:");
		System.out.printf("%8.3f",sccStats[1]);
		System.out.println("\n");
	}
	
	public static void printSCCTable(DirectedGraph graph, String name) {
		
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
/*		double[] insert = testSet(set, array, Utils.INSERT);
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
		System.out.print(" | \n");*/
		printDivider();
	}
}

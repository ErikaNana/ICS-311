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
 *     * Neither the name of Project 3 nor the
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
import java.util.HashMap;
import java.util.Iterator;

/**
 * Provides helper methods for output.
 *
 * @param <Type> the generic type
 * @author Erika Nana
 */
public class Utils<Type> {
	
	/**
	 * The Enum Command: used to emulate a switch on the inputs.
	 */
	public enum Command{
		
		/** The analyze graph. */
		analyzeGraph, 
 /** The quit. */
 quit;
	}
	
	/*used for the compareValue method*/
	
	/** The Constant GREATER. */
	public static final int GREATER = 1;
	
	/** The Constant LESSER. */
	public static final int LESSER = 2;
	
	/** The Constant EQUAL. */
	public static final int EQUAL = 3;
	
	/**
	 * Helper method that compares strings.
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
	
	/**
	 * Prints the table for output
	 *
	 * @param graph the graph
	 * @param name the name of the graph
	 */

	public static void printTable(DirectedGraph graph, String name) {
		int vertices = graph.numVertices();
		int arcs = graph.numArcs();
		Object[] inDegreeStats = Metrics.getInDegreeStats(graph);
		Object[] outDegreeStats = Metrics.getOutDegreeStats(graph);
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
		System.out.printf("%10d", inDegreeStats[1]);
		System.out.println("");
		System.out.printf("%16s", "outDegree");
		System.out.printf("%9d", outDegreeStats[0]);
		System.out.printf("%15.3f", outDegreeStats[2]);
		System.out.printf("%10d", outDegreeStats[1]);
		System.out.println("");
		
		double reciprocity = Metrics.getReciprocity(graph);
		double degCorr = Metrics.getDegreeCorrelation(graph);
		double clustCoeff = Metrics.getClusteringCoefficient(graph);
		double [] geoStats = Metrics.getGeoStats(graph);
		System.out.printf("%-15s %5.4f", "Reciprocity: ", reciprocity);
		System.out.println("");
		System.out.printf("%-15s %5.4f", "Degree Correlation: ", degCorr);
		System.out.println("");
		System.out.printf("%-15s %5.4f", "Clustering Coefficient: ", clustCoeff);
		System.out.println("");
		System.out.printf("%-15s %5.4f", "Mean Geodesic Distance ", geoStats[0]);
		System.out.println("");
		System.out.printf("%-10s %5.4f", "Diameter: ", geoStats[1]);
		System.out.println("");
	}

	/**
	 * Gets the average and max path length given a HashMap of distances
	 *
	 * @param distances the distances
	 * @return the average and max
	 */
	public static double[] getAverageAndMax(HashMap<Vertex,Integer> distances) {
		double[] stats = new double[2];
		double sumOfPaths = 0;
		double maximum = 0;

		Iterator<Vertex> iterator = distances.keySet().iterator();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int distance = distances.get(next);
			if (distance > 0) { //only get valid path lengths
				sumOfPaths = sumOfPaths + distance;			
			}
			if (distance > maximum) {
				maximum = distance;
			}
		}
		stats[0] = sumOfPaths;
		stats[1] = maximum;
		return stats;
	}
}

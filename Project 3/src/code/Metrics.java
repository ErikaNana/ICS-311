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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Computes all of the output required for graphs.
 *  @author Erika Nana
 */
public class Metrics {
	
	/**
	 * Gets the density of the graph.
	 *
	 * @param graph the graph
	 * @return the density
	 */
	public static double getDensity(DirectedGraph graph) {
		double arcs = (double) graph.numArcs();
		int denominator = graph.numVertices() * (graph.numVertices() - 1);
		return arcs/denominator;
	}
	
	/**
	 * Gets the in degree statistics [min, max, ave].
	 *
	 * @param graph the graph
	 * @return the in degree statistics
	 */
	public static Object[] getInDegreeStats(DirectedGraph graph) {
		Object[] array = new Object [3];
		long min = 1000000000; //biggest long possible
		long max = 0;
		long runningTotal = 0;
		Iterator<Vertex> iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int inDegree = graph.inDegree(next);
			if (inDegree < min) {
				min = inDegree;
			}
			if (inDegree > max) {
				max = inDegree;
			}
			runningTotal = runningTotal + inDegree;
		}		
		
		array[0] = min;
		array[1] = max;
		array[2] = (double) runningTotal / graph.numVertices();
		return array;
	}
	/**
	 * Gets the out degree statistics [min, max, ave].
	 *
	 * @param graph the graph
	 * @return the out degree statistics
	 */
	public static Object[] getOutDegreeStats(DirectedGraph graph) {
		Object[] array = new Object[3];
		long min = 1000000000; //biggest long possible
		long max = 0;
		long runningTotal = 0;
		Iterator<Vertex> iterator = graph.vertices();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			int outDegree = graph.outDegree(next);
			if (outDegree < min) {
				min = outDegree;
			}
			if (outDegree > max) {
				max = outDegree;
			}
			runningTotal = runningTotal + outDegree;
		}
		
		array[0] = min;
		array[1] = max;
		array[2] = (double) runningTotal / graph.numVertices();
		return array;
	}
	
	/**
	 * Creates the transpose of the graph.
	 *
	 * @param graph the graph
	 */
	public static void createTranspose(DirectedGraph graph) {
		Iterator<Arc> iterator = graph.arcs();
		while (iterator.hasNext()) {
			Arc next = iterator.next();
			graph.reverseDirection(next);
		}
	}

	/**
	 * Gets the average path and maximum path of the while graph
	 * stats[0] = average path, stats[1] = maximum path
	 *
	 * @param graph the graph
	 * @return the stats to calculate geodesic metrics
	 */
	public static double[] getGeoStats(DirectedGraph graph) {
		double[] stats = new double[2];
		double averagePath = 0;
		double maximum = 0;
		int numOfPaths = 0;
		//get the maximum and average
		Iterator<Vertex> vertices = graph.vertices();
		while (vertices.hasNext()) {
			Vertex vertex = vertices.next();
			//get the stats from run on current vertex
			HashMap<Vertex, Integer> distances = BFS.runBFS(graph, vertex);
			Iterator<Vertex> iterator = distances.keySet().iterator();
			//don't need to count the path from source to itself
			int possiblePaths = graph.numVertices()-1;
			while (iterator.hasNext()) {
				Vertex next = iterator.next();
				//get the number of possible paths
				if (distances.get(next) < 0) {
					possiblePaths = possiblePaths - 1;
				}
			}
			numOfPaths = numOfPaths + possiblePaths;
			double [] output = Utils.getAverageAndMax(distances);
			averagePath = averagePath + output[0];
			double outputMax = output[1];
			//update maximum
			if (outputMax > maximum) {
				maximum = outputMax;
			}
		}
		averagePath = averagePath/numOfPaths;
		stats[0] = averagePath;
		stats[1] = maximum;
		return stats;
	}
	
	/**
	 * Gets the reciprocity.
	 *
	 * @param graph the graph
	 * @return the reciprocity
	 */
	public static double getReciprocity(DirectedGraph graph) {
		HashMap<Vertex, HashSet<Vertex>> outVertices = graph.aList.getMap();
		Iterator<Vertex>vertices = outVertices.keySet().iterator();
		double counter = 0;
		while (vertices.hasNext()) {
			Vertex next = vertices.next();
			HashSet<Vertex> endpoints = outVertices.get(next);
			//check endpoints for reciprocated edges 
			Iterator<Vertex>endIterator = endpoints.iterator();
			while (endIterator.hasNext()) {
				Vertex endpoint = endIterator.next();
				HashSet<Vertex> endpointsOfEndpoint = outVertices.get(endpoint);
				//if they are not reciprocated, add one for both vertices
				if (endpointsOfEndpoint.contains(next)) {
					counter++;
				}
			}
		}
		return counter/graph.numArcs();
	}
	
	/**
	 * Calculates the degree correlation
	 * 
	 * @param graph the graph
	 * @return the degree correlation
	 */
	public static double getDegreeCorrelation(DirectedGraph graph) {
		graph.setUndirectedDegree();
		HashMap<Vertex, HashSet<Vertex>> outVertices = graph.aList.getMap();
		Iterator<Vertex> vertices = outVertices.keySet().iterator();
		long s1 = 0;
		long s2 = 0;
		long s3 = 0;
		
		while (vertices.hasNext()) {
			Vertex next = vertices.next();
			//System.out.println("vertex:  " + next + " degree:  " + next.getUndirectedDegree());
			s1 = s1 + next.getUndirectedDegree();
			s2 = (int) (s2 + Math.pow(next.getUndirectedDegree(), 2));
			s3 = (int) (s3 + Math.pow(next.getUndirectedDegree(), 3));
		}
		long se = 0;
		ArrayList<Arc> arcs = graph.aList.getUndirectedEdges();
		for (Arc arc: arcs) {
			int product = arc.getStartVertex().getUndirectedDegree() * arc.getEndVertex().getUndirectedDegree();
			se = se + product;
		}
		se = 2*se;

		double s2Squared = (double) Math.pow(s2,2);
		double numerator = (s1*se) - s2Squared;
		double denominator = (double) ((s1 * s3) - Math.pow(s2, 2));
		return numerator/denominator;
	}

	/**
	 * Gets the clustering coefficient from 
	 * 
	 * # of triangles / # of possible triangles
	 *
	 * @param graph the graph
	 * @return the clustering coefficient
	 */
	public static double getClusteringCoefficient(DirectedGraph graph) {
		double triangles = 0;
		double possibleTriangles = 0;
		//iterate through all vertices
		Iterator<Vertex> vertices = graph.vertices();
		while (vertices.hasNext()) {
			Vertex vertex = vertices.next();
			//get adjacency list for current vertex
			 ArrayList<Vertex> adjVertices = graph.getUndirectedAdjVertices(vertex);
			 for (Vertex adj: adjVertices) {
				 ArrayList<Vertex> adjToSecondPoint = graph.getUndirectedAdjVertices(adj);
				 //remove self loop
				 adjToSecondPoint.remove(vertex);
				 for (Vertex thirdPoint: adjToSecondPoint) {
					 possibleTriangles++;
					 if (adjVertices.contains(thirdPoint)) {
						 triangles++;
					 }
				 }
			 }
		}
		return triangles/possibleTriangles;
	}
}

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
import java.util.HashSet;
import java.util.Iterator;

/**
 * BFS implementation
 * @author Erika Nana
 */
public class BFS {
	/**
	 * Run BFS.
	 *
	 * @param graph the graph 
	 * @param source the source vertex
	 * @return the hash map of vertices to distances.  returns -1 if vertex is unreachable
	 */
	public static HashMap<Vertex,Integer> runBFS(DirectedGraph graph, Vertex source) {
		Vertex[] queue = new Vertex[graph.numVertices()];
		HashMap<Vertex,Integer> distances = new HashMap<Vertex,Integer>();
		int read = 0;
		int write = 1;
		
		//initialize
		queue[0] = source;
		Iterator<Vertex> vertices = graph.vertices();
		while (vertices.hasNext()) {
			Vertex vertex = vertices.next();
			if (vertex == source) {
				distances.put(source, 0);
			}
			else{
				distances.put(vertex, -1);
			}
		}
		//run through the algorithm
		while (read != write) {
			Vertex currentRead = queue[read];
			read++;
			int distance = distances.get(currentRead);
			HashMap<Vertex, HashSet<Vertex>> aList = graph.getAList().getMap();
			HashSet<Vertex> neighbors = aList.get(currentRead);
			Iterator<Vertex> iterator = neighbors.iterator();
			while (iterator.hasNext()) {
				Vertex neighbor = iterator.next();
				//check if distance is known
				if (distances.get(neighbor) == -1) {
					int neighborDistance = distance + 1;
					distances.put(neighbor, neighborDistance);
					queue[write] = neighbor;
					write++;
				}
			}
			
		}
		return distances;
	}
}

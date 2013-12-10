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
import java.util.Iterator;

import junit.framework.TestCase;
import code.DFS;
import code.DirectedGraph;
import code.Vertex;

/**
 * Shows output for a sample DFS run.
 * @author Erika Nana
 */
public class DFSTest extends TestCase {
	
	/** The graph. */
	DirectedGraph graph = new DirectedGraph();
	
	/**
	 * Instantiates a new dFS test.
	 *
	 * @param name the name
	 */
	public DFSTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Vertex a = graph.insertVertex("a");
		Vertex b = graph.insertVertex("b");
		Vertex c = graph.insertVertex("c");
		Vertex d = graph.insertVertex("d");
		Vertex e = graph.insertVertex("e");
		Vertex f = graph.insertVertex("f");
		Vertex g = graph.insertVertex("g");
		Vertex h = graph.insertVertex("h");
		
		//add edges
		graph.insertArc(a,b);
		graph.insertArc(a,d);
		graph.insertArc(a,g);
		graph.insertArc(b,g);
		graph.insertArc(b,e);
		graph.insertArc(c,b);
		graph.insertArc(c,e);
		graph.insertArc(c,h);
		graph.insertArc(h,g);
		graph.insertArc(g,f);
		graph.insertArc(f,a);
		graph.insertArc(d,g);
		graph.insertArc(d,f);
		graph.insertArc(e,g);
	}
	
	
	/**
	 * Test DFS.
	 */
	public void testDFS() {
		DirectedGraph newGraph = DFS.runDFS(graph);
		Iterator<Vertex> iterator = newGraph.vertices();
		while(iterator.hasNext()) {
			Vertex next = iterator.next();
			System.out.println("vertex:  " + next);
			System.out.println("     color: " + graph.getAnnotation(next, "color"));
			System.out.println("     parent: " + graph.getAnnotation(next, "parent"));
			System.out.println("     discover: " + graph.getAnnotation(next, "discover"));
			System.out.println("     finish: " + graph.getAnnotation(next, "finish"));
		}
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

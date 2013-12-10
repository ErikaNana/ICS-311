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
import code.DirectedGraph;
import code.VNAParser;
import junit.framework.TestCase;
import code.Vertex;

/**
 * Tests the functionality of the VNAParser.java class.
 * @author Erika Nana
 */
public class ParserTest extends TestCase {
	
	/** The file name. */
	String fileName = "SCC-Test.vna";
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Test SCC.
	 */
	public void testSCC() {
		//test vertices
		DirectedGraph graph = VNAParser.generateGraph(fileName);
		assertEquals(41, graph.numVertices());
		assertEquals("1", graph.getVertex("1").getKey());
		//assertNull(graph.getVertex("1").getData());
		
		//test arcs
		assertEquals(67, graph.numArcs());
		Vertex start = graph.getVertex("56");
		Vertex end = graph.getVertex("58");
		//Arc arc = graph.getArc(start, end);
		//assertEquals("1", arc.getData());
		end = graph.getVertex("32");
		assertEquals(null,graph.getArc(start, end));
	}
	
	/**
	 * Test blog input file.
	 */
	public void testBlog() {
		fileName = "political-blogs.vna";
		DirectedGraph graph = VNAParser.generateGraph(fileName);
		assertEquals(1490, graph.numVertices());
		assertEquals(19025, graph.numArcs());
	}
	
	/**
	 * Test wikipedia input file.
	 */
/*	public void testWikipedia() {
		fileName = "wiki-Vote.vna";
		DirectedGraph graph = VNAParser.generateGraph(fileName);
		assertEquals(7115, graph.numVertices());
		assertEquals(103689, graph.numArcs());
	}*/
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import code.AList;
import code.Vertex;

/**
 * Tests the functionality of the adjacency list.
 * @author Erika Nana
 */
public class AListTest extends TestCase {
	
	/** The a list. */
	AList aList;
	
	/** The map. */
	HashMap<Vertex,HashSet<Vertex>> map;
	
	/**One vertex */
	code.Vertex one = new code.Vertex("1");
	
	/** The two. */
	code.Vertex two = new code.Vertex("2");
	
	/** The three. */
	code.Vertex three = new code.Vertex("3");
	
	/** The four. */
	code.Vertex four = new code.Vertex("4");
	
	/**Vertices for testing project 3 methods*/
	Vertex a;
	Vertex b;
	Vertex c;
	Vertex d;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap<Vertex,HashSet<Vertex>>();
		aList = new AList();
		HashSet<Vertex> set = new HashSet<Vertex>();
		map.put(one, set);
		map.put(two, set);
		map.put(three, set);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		aList = null;
	}
	
	/**
	 * Test add start vertex.
	 */
	public void testAddStartVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertTrue("has value one", checkMap.containsKey(one));
		assertTrue("has value two", checkMap.containsKey(two));
		assertTrue("has value three", checkMap.containsKey(three));
	}
	
	/**
	 * Test delete just start vertex.
	 */
	public void testDeleteJustStartVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.deleteVertex(one);
		aList.deleteVertex(two);
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertNotNull(checkMap);
		assertTrue(checkMap.size() == 1);
		assertTrue("last node is 3", checkMap.containsKey(three));
	}
	
	/**
	 * Test delete vertex.
	 */
	public void testDeleteVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		assertEquals(3, aList.getNumOfVertices());
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		aList.deleteVertex(one);
		assertEquals(2, aList.getNumOfVertices());
		
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertEquals(null, checkMap.get(one));
		assertEquals(1, checkMap.get(two).size());
		assertTrue(checkMap.get(three).isEmpty());
		assertEquals(0, checkMap.get(three).size());
	}
	
	/**
	 * Test add edge.
	 */
	public void testAddEdge() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		Set<Vertex> keys = checkMap.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex key = i.next();
			HashSet<Vertex> tree = checkMap.get(key);
			assertNotNull(tree);
			assertEquals(1, tree.size());
		}
		assertEquals("[2]", checkMap.get(one).toString());
		assertEquals("[3]", checkMap.get(two).toString());
		assertEquals("[1]", checkMap.get(three).toString());
		assertEquals(3, aList.getNumOfEdges());
	}
	
	/**
	 * Test delete methods.
	 */
	public void testDeleteMethods() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		aList.deleteEdge(two,three);
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertTrue(checkMap.get(two).isEmpty());
		assertEquals(2,aList.getNumOfEdges());
	}
	
	public void createSmallGraph() {
		a = new Vertex("a");
		b = new Vertex("b");
		c = new Vertex("c");
		d = new Vertex("d");
		aList.addVertex(a);
		aList.addVertex(b);
		aList.addVertex(c);
		aList.addVertex(d);
		
		aList.addEdge(a, b);
		aList.addEdge(b, a);
		aList.addEdge(c, d);
		aList.addEdge(d, c);
		aList.addEdge(c, a);
		aList.addEdge(b, d);
	}

/*	public void testUndirectedDegree() {
		createSmallGraph();
		assertEquals(1, aList.getUndirectedDegree(a));
		assertEquals(1, aList.getUndirectedDegree(b));
		assertEquals(1, aList.getUndirectedDegree(c));
		assertEquals(1, aList.getUndirectedDegree(d));
	}
	
	public void testUndirectedDegreeTwo() {
		createSmallGraph();
		aList.addEdge(a,c);
		assertEquals(2, aList.getUndirectedDegree(a));
		assertEquals(1, aList.getUndirectedDegree(b));
		assertEquals(2, aList.getUndirectedDegree(c));
		assertEquals(1, aList.getUndirectedDegree(d));
		//assertEquals(-0.5, aList.getDegreeCorrelation());
	}*/
}

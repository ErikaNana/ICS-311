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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import junit.framework.TestCase;
import code.Arc;
import code.BFS;
import code.DirectedGraph;
import code.Metrics;
import code.VNAParser;
import code.Vertex;

// TODO: Auto-generated Javadoc
/**
 * Tests the required output of the graphs.
 * @author Erika Nana
 */
public class MetricsTest extends TestCase {
	
	/** The graph. */
	DirectedGraph graph;
	
	/**
	 * Instantiates a new metrics test.
	 *
	 * @param name the name
	 */
	public MetricsTest(String name) {
		super(name);
		//graph = VNAParser.generateGraph("SCC-Test.vna");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Test density.
	 */
	public void testDensity() {
		graph = VNAParser.generateGraph("SCC-Test.vna");
		double answer = Metrics.getDensity(graph);
		BigDecimal bd = new BigDecimal(Double.toString(answer));
        //round to 3 decimal places
        bd = bd.setScale(3, BigDecimal.ROUND_CEILING);
		assertEquals(0.041, Double.valueOf(bd.doubleValue()));
	}
	
	/**
	 * Test in degree.
	 */
	public void testInDegree() {
		graph = VNAParser.generateGraph("SCC-Test.vna");
		Object[] array = Metrics.getInDegreeStats(graph);
		//cast the wrapper class, then cast it to primitive
		assertEquals((long) 0, (long) (Long) array[0]);
		assertEquals((long) 4, (long) (Long) array[1]);
	}
	
	/**
	 * Test out degree.
	 */
	public void testOutDegree() {
		graph = VNAParser.generateGraph("SCC-Test.vna");
		Object[] array = Metrics.getOutDegreeStats(graph);
		assertEquals((long) 0, (long) (Long) array[0]);
		assertEquals((long) 4, (long) (Long) array[1]);
	}
	
	/**
	 * Test transpose.
	 */
	public void testTranspose() {
		System.out.println("Transpose test");
		graph = new DirectedGraph();
		Vertex one = graph.insertVertex("one");
		Vertex two = graph.insertVertex("two");
		Vertex three = graph.insertVertex("three");
		graph.insertArc(one, two, "three");
		graph.insertArc(two, three);
		graph.insertArc(three, one);
		
		HashSet<Arc> check = new HashSet<Arc>();
		Metrics.createTranspose(graph);
		Iterator<Arc> iterator = graph.arcs();
		while(iterator.hasNext()) {
			Arc arc = iterator.next();
			check.add(arc);
			System.out.println("arc:  " + arc.getStartVertex() + "," + arc.getEndVertex());
		}
		assertEquals(one, graph.getArc(two, one).getEndVertex());
		assertEquals("three", graph.getArc(two, one).getData());
		assertNotNull(graph.getArc(three, two));
		assertEquals(1, graph.inDegree(one));
		assertEquals(1, graph.inDegree(two));
		assertEquals(1, graph.inDegree(two));
		assertEquals(1, graph.outDegree(one));
		assertEquals(1, graph.outDegree(two));
		assertEquals(1, graph.outDegree(two));
	}	
	
	/**
	 * Test metricss for celegansneural.vnas
	 */
	public void testMetrics3() {
		System.out.println("Metric 3 test");
		graph = VNAParser.generateGraph("celegansneural.vna");
		double stats [] = Metrics.getGeoStats(graph);

		assertEquals(0.18071147126607687, Metrics.getClusteringCoefficient(graph));
		assertEquals(-0.1631992103157044, Metrics.getDegreeCorrelation(graph));
		assertEquals(0.16801705756929639, Metrics.getReciprocity(graph));
		assertEquals(3.9918839808408726, stats[0]);
		assertEquals(14.0,stats[1]);
	}
	
	/**
	 * Test metrics for a simple graph
	 */
	public void testMetrics2() {
		graph = new DirectedGraph();
		Vertex one = graph.insertVertex("one");
		Vertex two = graph.insertVertex("two");
		Vertex three = graph.insertVertex("three");
		Vertex four = graph.insertVertex("four");

		graph.insertArc(one, two);
		graph.insertArc(one, three);
		graph.insertArc(three,four);
		
		graph.insertArc(two, one);
		graph.insertArc(three, one);
		graph.insertArc(four,three);
		graph.insertArc(four, two);
		graph.insertArc(two, three);

		assertEquals(-0.6666666666666666d,Metrics.getDegreeCorrelation(graph));
		assertEquals(0.75, Metrics.getClusteringCoefficient(graph));
		assertEquals(0.75, Metrics.getReciprocity(graph));
		assertEquals(2, one.getUndirectedDegree());
		assertEquals(3, two.getUndirectedDegree());
		assertEquals(3, three.getUndirectedDegree());
		assertEquals(2, four.getUndirectedDegree());
	}
	
	/**
	 * Test BFS
	 */
	public void testBFS() {
		graph = new DirectedGraph();
		Vertex a = graph.insertVertex("a");
		Vertex b = graph.insertVertex("b");
		Vertex c = graph.insertVertex("c");
		Vertex d = graph.insertVertex("d");
		graph.insertArc(a, b);
		graph.insertArc(a, c);
		graph.insertArc(b, d);
		HashMap<Vertex, Integer> distances = new HashMap<Vertex,Integer>();
		distances = BFS.runBFS(graph, a);
		Iterator<Vertex> iterator = distances.keySet().iterator();
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (next == a) {
				assertEquals((Integer)0, (Integer) distances.get(next));
			}
			if (next == b) {
				assertEquals((Integer)1, (Integer) distances.get(next));
			}
			if (next == c) {
				assertEquals((Integer)1, (Integer) distances.get(next));
			}
			if (next == d) {
				assertEquals((Integer)2, (Integer) distances.get(next));
			}
		}
		Metrics.getGeoStats(graph);
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		System.out.println("-----------------------------");
		super.tearDown();
	}
}

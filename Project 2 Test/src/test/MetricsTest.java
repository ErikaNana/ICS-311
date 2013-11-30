package test;

import java.util.HashSet;
import java.util.Iterator;

import junit.framework.TestCase;
import code.Arc;
import code.DirectedGraph;
import code.Metrics;
import code.VNAParser;
import code.Vertex;

public class MetricsTest extends TestCase {
	DirectedGraph graph;
	public MetricsTest(String name) {
		super(name);
		graph = VNAParser.generateGraph("SCC-Test.vna");
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	public void testDensity() {
		assertEquals(0.041, Metrics.getDensity(graph));
	}
	public void testInDegree() {
		assertEquals(0, Metrics.getMinInDegree(graph));
		assertEquals(4, Metrics.getMaxInDegree(graph));
	}
	public void testOutDegree() {
		assertEquals(4, Metrics.getMaxOutDegree(graph));
		assertEquals(0, Metrics.getMinOutDegree(graph));
	}
	public void testTranspose() {
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
		}
		assertEquals(one, graph.getArc(two, one).getEndVertex());
		assertEquals("three", graph.getArc(two, one).getData());
	}
/*	public void testSCC() {
		DirectedGraph newGraph = DFS.runDFS(graph);
		newGraph = Metrics.createTranspose(newGraph);
		DirectedGraph answerGraph = DFS.runDFSWithSCC(newGraph);
		assertEquals(12, Metrics.getSCC(answerGraph));
	}*/
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

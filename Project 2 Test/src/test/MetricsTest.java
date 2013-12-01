package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
		Object[] array = Metrics.getInDegreeStats(graph);
		//cast the wrapper class, then cast it to primitive
		assertEquals((long) 0, (long) (Long) array[0]);
		assertEquals((long) 4, (long) (Long) array[1]);
	}
	public void testOutDegree() {
		Object[] array = Metrics.getOutDegreeStats(graph);
		assertEquals((long) 0, (long) (Long) array[0]);
		assertEquals((long) 4, (long) (Long) array[1]);
	}
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
	@SuppressWarnings("unchecked")
	public void testSCC() {
		System.out.println("SCC test");
		Object array[] = Metrics.runSCC(graph);
		assertEquals((long) 12, (long) (Long) array[0]);
		System.out.println("percentage:  " + array[1]);
		List<ArrayList<Vertex>> list = (List<ArrayList<Vertex>>) array[2];
		long counter = 0;
		for (ArrayList<Vertex> vertexList: list) {
			System.out.println("scc:  " + counter + "  size:  " + vertexList.size());
			for (Vertex vertex: vertexList) {
				System.out.println("    vertex:  " + vertex);
			}
			counter++;
		}
	}
	protected void tearDown() throws Exception {
		System.out.println("-----------------------------");
		super.tearDown();
	}
	
}

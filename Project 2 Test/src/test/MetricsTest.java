package test;

import junit.framework.TestCase;
import code.DirectedGraph;
import code.Metrics;
import code.VNAParser;

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
	public void testSCC() {
		assertEquals(12, Metrics.getSCC(graph));
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

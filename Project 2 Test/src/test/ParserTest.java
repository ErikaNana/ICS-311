package test;

import junit.framework.TestCase;
import code.DirectedGraph;
import code.VNAParser;
import code.Vertex;

public class ParserTest extends TestCase {
	String fileName = "SCC-Test.vna";
	
	protected void setUp() throws Exception {
		super.setUp();
	}
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
	
	public void testBlog() {
		fileName = "political-blogs.vna";
		DirectedGraph graph = VNAParser.generateGraph(fileName);
		assertEquals(1490, graph.numVertices());
		assertEquals(19025, graph.numArcs());
	}
	
	public void testWikipedia() {
		fileName = "wiki-Vote.vna";
		DirectedGraph graph = VNAParser.generateGraph(fileName);
		assertEquals(7115, graph.numVertices());
		assertEquals(103689, graph.numArcs());
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

package test;

import junit.framework.TestCase;
import code.Arc;
import code.DirectedGraph;
import code.Vertex;

public class DirectedGraphTest extends TestCase {
	Vertex one;
	Vertex two;
	Vertex three;
	Arc arcOne;
	Arc arcTwo;
	Arc arcThree;
	DirectedGraph graph;
	
	protected void setUp() throws Exception {
		graph = new DirectedGraph();
		one = graph.insertVertex("one");
		two = graph.insertVertex("two");
		three = graph.insertVertex("three");
		arcOne = graph.insertArc(one, two);
		arcTwo = graph.insertArc(two, three);
		arcThree = graph.insertArc(three, one);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGettingAndSetting() {
		Arc arcFour = graph.insertArc(three,two);
		assertEquals(one, graph.getVertex(one));
		assertEquals(arcOne,graph.getArc(one, two));
		assertEquals(arcFour,graph.getArc(three,two));
		assertEquals(3,graph.numVertices());
		assertEquals(4,graph.numArcs());
		assertEquals(one,graph.origin(arcOne));
		assertEquals(two,graph.destination(arcOne));
	}
	
	public void testGetAndSetData() {
		graph.insertArc(three, two, "hi");
		Arc arc = graph.getArc(three, two);
		Vertex another = graph.insertVertex("five", "ho");
		assertEquals("hi", graph.getArcData(arc));
		assertEquals("ho", graph.getVertexData(another));
		graph.setArcData(arc, "hola");
		graph.setVertexData(another, "bonjour");
		assertEquals("hola", graph.getArcData(arc));
		assertEquals("bonjour", graph.getVertexData(another));
		assertEquals(null, graph.getVertexData(one));
	}
	
	public void testInDegreeOutDegree() {
		graph.insertArc(three, two, "hi");
		assertEquals(2,graph.inDegree(two));
		assertEquals(1,graph.inDegree(one));
		assertEquals(1,graph.inDegree(three));
		assertEquals(1,graph.outDegree(two));
		assertEquals(1,graph.outDegree(one));
		assertEquals(2,graph.outDegree(three));
	}
	
	public void testDeleteMethods() {
		
	}
}

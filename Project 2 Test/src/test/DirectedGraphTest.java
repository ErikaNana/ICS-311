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
	
	public void testDeleteVertex() {
		//test the alist and graph		
		Vertex found = (Vertex) graph.removeVertex(one);
		assertEquals(2, graph.getAList().getNumOfVertices());
		assertEquals(1, graph.getAList().getNumOfEdges());
		assertEquals(2,graph.numVertices());
		assertEquals(1,graph.numArcs());
		assertEquals(one, found);
	}
	
	public void testDeleteArc() {
		Arc arc = (Arc) graph.removeArc(arcOne);
		assertEquals(arc,arcOne);
		assertEquals(2, graph.getAList().getNumOfEdges());
		assertEquals(2, graph.numArcs());
		assertEquals(3, graph.getAList().getNumOfVertices());
		assertEquals(3,graph.numVertices());
	}
	public void testReverseArc() {
		graph.reverseDirection(arcOne);
		assertEquals(2, graph.outDegree(two));
		assertEquals(2, graph.inDegree(one));
		Vertex four = graph.insertVertex("four");
		Arc arc = new Arc(three,four,"what");
		graph.reverseDirection(arc);
		assertEquals("what",graph.getArc(four, three).getData());
	}
	public void testAnnotations() {
		//test set annotations
		graph.setAnnotation(one, "Parent", "Color");
		Arc arc = graph.getArc(one, two);
		graph.setAnnotation(arcOne,"Color", "Parent");
		
		//make sure that they are still the same arc after modification
		assertEquals(arc,arcOne);
		
		//test get annotations
		assertEquals("Parent", graph.getAnnotation(one,"Parent"));
		assertNull(one.getAnnotation("WHAT"));
		assertEquals("Color", graph.getAnnotation(arcOne,"Color"));
		assertNull(graph.getAnnotation(two,"WHAT"));
		
		//test remove annotations
		Object removeGraph = graph.removeAnnotation(arcOne, "Color");
		assertNull(graph.getAnnotation(arcOne, "Color"));
		assertEquals(removeGraph, "Color");
		Object removeVertex = graph.removeAnnotation(one, "Parent");
		assertNull(graph.getAnnotation(one, "Parent"));
		assertEquals(removeVertex,"Parent");
		
		//reset
		graph.setAnnotation(one, "Parent", "Color");
		graph.setAnnotation(arcOne, "Color", "Parent");
		graph.clearAnnotations(one);
		assertNull(graph.getAnnotation(one, "Parent"));
		assertNull(graph.getAnnotation(one, "Color"));
		graph.clearAnnotations(arcOne);
		assertNull(graph.getAnnotation(arcOne, "Parent"));
		assertNull(graph.getAnnotation(arcOne, "Color"));
		
		//test general functionality
		assertEquals(3,graph.numVertices());
		assertEquals(3,graph.numArcs());
	}
}

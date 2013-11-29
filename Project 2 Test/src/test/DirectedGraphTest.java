package test;

import java.util.Iterator;

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
		assertEquals("ho, bonjour", graph.getVertexData(another));
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
		Arc arcFour = graph.insertArc(three, four, "what");
		graph.reverseDirection(arcFour);
		assertEquals("what",graph.getArc(four, three).getData());
	}
	public void testVertexIterators() {
		Iterator<Vertex> allVertices = graph.vertices();
		int counter = 0;
		//test all vertices
		while (allVertices.hasNext()){
			Vertex currentKey = allVertices.next();
/*			System.out.println("current key:  " + currentKey);*/
			assertNotNull(graph.getVertex(currentKey));
			counter++;
		}
		assertEquals(3, counter);
		
		counter = 0;
		Iterator<Vertex> inVerticesTwo = graph.inAdjacentVertices(two);
		while (inVerticesTwo.hasNext()){
			Vertex currentKey = inVerticesTwo.next();
			assertEquals(one,graph.getVertex(currentKey));
			counter++;
		}
		assertEquals(1, counter);
		
		counter = 0;
		Iterator<Vertex> inVerticesOne = graph.inAdjacentVertices(one);
		while (inVerticesOne.hasNext()){
			Vertex currentKey = inVerticesOne.next();
			assertEquals(three,graph.getVertex(currentKey));
			counter++;
		}
		assertEquals(1, counter);
		
		counter = 0;
		Iterator<Vertex> inVerticesThree = graph.inAdjacentVertices(three);
		while (inVerticesThree.hasNext()){
			Vertex currentKey = inVerticesThree.next();
			assertEquals(two,graph.getVertex(currentKey));
			counter++;
		}
		assertEquals(1, counter);
		
		//test outVertices
		counter = 0;
		Iterator<Vertex> outVerticesOne = graph.outAdjacentVertices(one);
		while (outVerticesOne.hasNext()){
			Vertex currentKey = outVerticesOne.next();
			assertEquals(two,graph.getVertex(currentKey));
			counter++;
		}
		assertEquals(1, counter);
	}
	public void testAnnotations() {
		//test set annotations
		graph.setAnnotation(one, "Parent", "two");
		Arc arc = graph.getArc(one, two);
		graph.setAnnotation(arcOne,"Color", "blue");
		
		//make sure that they are still the same arc after modification
		assertEquals(arc,arcOne);
		
		//test get annotations
		assertEquals("two", graph.getAnnotation(one,"Parent"));
		assertNull(one.getAnnotation("WHAT"));
		assertEquals("blue", graph.getAnnotation(arcOne,"Color"));
		assertNull(graph.getAnnotation(two,"WHAT"));
		
		//test remove annotations
		Object removeGraph = graph.removeAnnotation(arcOne, "Color");
		assertNull(graph.getAnnotation(arcOne, "Color"));
		assertEquals(removeGraph, "blue");
		Object removeVertex = graph.removeAnnotation(one, "Parent");
		assertNull(graph.getAnnotation(one, "Parent"));
		assertEquals(removeVertex,"two");
		
		//reset
		graph.setAnnotation(one, "Parent", "two");
		graph.setAnnotation(arcOne, "Color", "blue");
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

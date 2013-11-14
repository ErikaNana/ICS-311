package test;

import junit.framework.TestCase;
import code.AList;
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
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConstructingAndAdding() {
		graph.insertArc(one, two);
		graph.insertArc(two, three);
		graph.insertArc(three, one);
		
		AList graphList = graph.getAList();
		//make sure adjacency list is working
		assertEquals(3, graphList.getNumOfVertices());
		assertEquals(3,graphList.getNumOfEdges());
		//make sure graph is working
		assertEquals(3,graph.numVertices());
		assertEquals(3,graph.numArcs());
	}
	
	public void testGettingAndSetting() {
		arcOne = graph.insertArc(one, two);
		arcTwo = graph.insertArc(two, three);
		arcThree = graph.insertArc(three, one);
		assertEquals(one, graph.getVertex(one));
		assertEquals(arcOne,graph.getArc(one, two));
	}
}

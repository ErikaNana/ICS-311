package test;

import junit.framework.TestCase;
import code.AList;
import code.DirectedGraph;
import code.Vertex;

public class DirectedGraphTest extends TestCase {
	Vertex one;
	Vertex two;
	Vertex three;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConstructingAndAdding() {
		DirectedGraph graph = new DirectedGraph();
		one = graph.insertVertex("one");
		two = graph.insertVertex("two");
		three = graph.insertVertex("three");
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
}

package test;

import java.util.Iterator;

import junit.framework.TestCase;
import code.DFS;
import code.DirectedGraph;
import code.Vertex;

public class DFSTest extends TestCase {
	DirectedGraph graph = new DirectedGraph();
	
	public DFSTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Vertex a = graph.insertVertex("a");
		Vertex b = graph.insertVertex("b");
		Vertex c = graph.insertVertex("c");
		Vertex d = graph.insertVertex("d");
		Vertex e = graph.insertVertex("e");
		Vertex f = graph.insertVertex("f");
		Vertex g = graph.insertVertex("g");
		Vertex h = graph.insertVertex("h");
		
		//add edges
		graph.insertArc(a,b);
		graph.insertArc(a,d);
		graph.insertArc(a,g);
		graph.insertArc(b,g);
		graph.insertArc(b,e);
		graph.insertArc(c,b);
		graph.insertArc(c,e);
		graph.insertArc(c,h);
		graph.insertArc(h,g);
		graph.insertArc(g,f);
		graph.insertArc(f,a);
		graph.insertArc(d,g);
		graph.insertArc(d,f);
		graph.insertArc(e,g);
	}
	
	
	public void testDFS() {
		//had to manually draw it out, but so far it matches the sample done in the podcast 14E kinda
		DirectedGraph newGraph = DFS.runDFS(graph);
		Iterator<Vertex> iterator = newGraph.vertices();
		while(iterator.hasNext()) {
			Vertex next = iterator.next();
/*			System.out.println("vertex:  " + next);
			System.out.println("     color: " + graph.getAnnotation(next, "color"));
			System.out.println("     parent: " + graph.getAnnotation(next, "parent"));
			System.out.println("     discover: " + graph.getAnnotation(next, "discover"));
			System.out.println("     finish: " + graph.getAnnotation(next, "finish"));*/
		}
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;
import code.AList;
import code.Vertex;

public class AListTest extends TestCase {
	AList aList;
	HashMap<Vertex,HashSet<Vertex>> map;
	code.Vertex one = new code.Vertex("1");
	code.Vertex two = new code.Vertex("2");
	code.Vertex three = new code.Vertex("3");
	code.Vertex four = new code.Vertex("4");
	
	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap<Vertex,HashSet<Vertex>>();
		aList = new AList();
		HashSet<Vertex> set = new HashSet<Vertex>();
		map.put(one, set);
		map.put(two, set);
		map.put(three, set);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		aList = null;
	}
	
	public void testAddStartVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertTrue("has value one", checkMap.containsKey(one));
		assertTrue("has value two", checkMap.containsKey(two));
		assertTrue("has value three", checkMap.containsKey(three));
	}
	
	public void testDeleteJustStartVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.deleteVertex(one);
		aList.deleteVertex(two);
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertNotNull(checkMap);
		assertTrue(checkMap.size() == 1);
		assertTrue("last node is 3", checkMap.containsKey(three));
	}
	public void testDeleteVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		assertEquals(3, aList.getNumOfVertices());
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		aList.deleteVertex(one);
		assertEquals(2, aList.getNumOfVertices());
		
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertEquals(null, checkMap.get(one));
		assertEquals(1, checkMap.get(two).size());
		assertTrue(checkMap.get(three).isEmpty());
		assertEquals(0, checkMap.get(three).size());
	}
	public void testAddEdge() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		Set<Vertex> keys = checkMap.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex key = i.next();
			HashSet<Vertex> tree = checkMap.get(key);
			assertNotNull(tree);
			assertEquals(1, tree.size());
		}
		assertEquals("[2]", checkMap.get(one).toString());
		assertEquals("[3]", checkMap.get(two).toString());
		assertEquals("[1]", checkMap.get(three).toString());
		assertEquals(3, aList.getNumOfEdges());
	}
	
	public void testDeleteMethods() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		aList.deleteEdge(two,three);
		HashMap<Vertex, HashSet<Vertex>> checkMap = aList.getMap();
		assertTrue(checkMap.get(two).isEmpty());
		assertEquals(2,aList.getNumOfEdges());
	}

}

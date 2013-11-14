package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import code.AList;
import code.BTree;
import code.Vertex;

import junit.framework.TestCase;

public class AListTest extends TestCase {
	AList aList;
	HashMap<Vertex,BTree<Vertex>> map;
	code.Vertex one = new code.Vertex("1");
	code.Vertex two = new code.Vertex("2");
	code.Vertex three = new code.Vertex("3");
	code.Vertex four = new code.Vertex("4");
	
	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap<Vertex,BTree<Vertex>>();
		aList = new AList();
		BTree<Vertex> tree = new BTree<Vertex>();
		System.out.println("set up");
		System.out.println("***********");
		map.put(one, tree);
		map.put(two, tree);
		map.put(three, tree);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		aList = null;
		System.out.println("-------------------");
	}
	
	public void testAddStartVertex() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
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
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		assertNotNull(checkMap);
		System.out.println("checkMap size:  " + checkMap.size());
		assertTrue(checkMap.size() == 1);
		Set<Vertex> keys = checkMap.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex key = i.next();
			System.out.println("key:  " + key);
		}
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
		
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		assertEquals(null, checkMap.get(one));
		assertEquals("3", checkMap.get(two).getRoot().toString());
		assertEquals(1, checkMap.get(two).size());
		assertNull(checkMap.get(three).getRoot());
		assertEquals(0, checkMap.get(three).size());
	}
	public void testAddEdge() {
		aList.addVertex(one);
		aList.addVertex(two);
		aList.addVertex(three);
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		Set<Vertex> keys = checkMap.keySet();
		System.out.println("keys in hashmap");
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			Vertex key = i.next();
			System.out.println("key:  " + key);
			BTree<Vertex> tree = checkMap.get(key);
			assertNotNull(tree);
			assertEquals(1, tree.size());
		}
		assertEquals("2", checkMap.get(one).getRoot().toString());
		assertEquals("3", checkMap.get(two).getRoot().toString());
		assertEquals("1", checkMap.get(three).getRoot().toString());
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
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		assertEquals(null,checkMap.get(two).getRoot());
		assertEquals(2,aList.getNumOfEdges());
	}

}

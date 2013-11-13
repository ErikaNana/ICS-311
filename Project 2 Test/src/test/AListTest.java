package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

public class AListTest extends TestCase {
	AList aList;
	HashMap<Vertex,BTree<Vertex>> map;
	test.Vertex one = new test.Vertex("1");
	test.Vertex two = new test.Vertex("2");
	test.Vertex three = new test.Vertex("3");
	test.Vertex four = new test.Vertex("4");
	
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
	
/*	public void testAddStartVertex() {
		aList.addStartVertex(one);
		aList.addStartVertex(two);
		aList.addStartVertex(three);
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		assertTrue("has value one", checkMap.containsKey(one));
		assertTrue("has value two", checkMap.containsKey(two));
		assertTrue("has value three", checkMap.containsKey(three));
	}*/
	
/*	public void testDeleteJustStartVertex() {
		aList.addStartVertex(one);
		aList.addStartVertex(two);
		aList.addStartVertex(three);
		aList.deleteVertex(one);
		aList.deleteVertex(two);
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		assertNotNull(checkMap);
		System.out.println("checkMap size:  " + checkMap.size());
		assertTrue(checkMap.size() == 1);
		Set<Vertex> keys = checkMap.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			String key = i.next().getValue();
			System.out.println("key:  " + key);
		}
		assertTrue("last node is 3", checkMap.containsKey(three));
	}*/
	public void testDeleteVertex() {
		aList.addStartVertex(one);
		aList.addStartVertex(two);
		aList.addStartVertex(three);
		aList.addEdge(one, two);
		aList.addEdge(two, three);
		aList.addEdge(three, one);
		
		aList.deleteVertex(one);
		
		HashMap<Vertex, BTree<Vertex>> checkMap = aList.getMap();
		assertEquals(null, checkMap.get(one));
		assertEquals("3", checkMap.get(two).getRoot().toString());
		assertEquals(1, checkMap.get(two).size());
		assertEquals("2", checkMap.get(three).getRoot().toString());
		assertEquals(1, checkMap.get(three).size());
	}
	public void testAddEdge() {
		aList.addStartVertex(one);
		aList.addStartVertex(two);
		aList.addStartVertex(three);
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
			assertEquals(2, tree.size());
		}
		assertEquals("2", checkMap.get(one).getRoot().toString());
		assertEquals("3", checkMap.get(one).getRoot().getRightChild().toString());
		assertEquals("1", checkMap.get(two).getRoot().toString());
		assertEquals("3", checkMap.get(two).getRoot().getRightChild().toString());
		assertEquals("2", checkMap.get(three).getRoot().toString());
		assertEquals("1", checkMap.get(three).getRoot().getLeftChild().toString());
	}
}

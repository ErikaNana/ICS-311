package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

public class AListTest extends TestCase {
	AList aList;
	HashMap<Vertex,BTree<Vertex>> map;
	test.Vertex one = new test.Vertex("one");
	test.Vertex two = new test.Vertex("two");
	test.Vertex three = new test.Vertex("three");
	
	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap<Vertex,BTree<Vertex>>();
		aList = new AList();
		BTree<Vertex> tree = new BTree<Vertex>();
		test.Vertex one = new test.Vertex("one");
		test.Vertex two = new test.Vertex("two");
		test.Vertex three = new test.Vertex("three");
		map.put(one, tree);
		map.put(two, tree);
		map.put(three, tree);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		aList = null;
	}
	
	public void testAddStartVertex() {
		aList.addStartVertex(one);
		aList.addStartVertex(two);
		aList.addStartVertex(three);
		HashMap<Vertex, BTree<String>> checkMap = aList.getMap();
		assertTrue("has value one", checkMap.containsKey(one));
		assertTrue("has value two", checkMap.containsKey(two));
		assertTrue("has value three", checkMap.containsKey(three));
	}
	
	public void testDeleteVertex() {
		aList.addStartVertex(one);
		aList.addStartVertex(two);
		aList.addStartVertex(three);
		aList.deleteVertex(one);
		aList.deleteVertex(two);
		HashMap<Vertex, BTree<String>> checkMap = aList.getMap();
		assertNotNull(checkMap);
		System.out.println("checkMap size:  " + checkMap.size());
		assertTrue(checkMap.size() == 1);
		Set<Vertex> keys = checkMap.keySet();
		for (Iterator<Vertex> i = keys.iterator(); i.hasNext();) {
			String key = i.next().getValue();
			System.out.println("key:  " + key);
		}
		assertTrue("last node is 3", checkMap.containsKey(three));
	}
}

package testFiles;



import java.util.ArrayList;

import junit.framework.TestCase;
import code.Heap;

public class HeapTest extends TestCase {
	Heap heap;
	ArrayList<String> test;
	
	public HeapTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		test = new ArrayList<String>();
		super.setUp();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	//uses example from the notes
	public void testBuildMaxHeap() {
		test.add("4");
		test.add("1");
		test.add("3");
		test.add("2");
		test.add("16");
		test.add("9");
		test.add("10");
		test.add("14");
		test.add("8");
		test.add("7");
		heap = new Heap(test);
		heap.buildMaxHeap();
		assertNull(heap.getParent(1));
		assertEquals("14", heap.getLeftChild(1));
		assertEquals("10", heap.getRightChild(1));
		assertEquals("8", heap.getLeftChild(2));
		assertEquals("7", heap.getRightChild(2));
		assertEquals("9", heap.getLeftChild(3));
		assertEquals("3", heap.getRightChild(3));
		assertEquals("2", heap.getLeftChild(4));
		assertEquals("4", heap.getRightChild(4));
		assertEquals("1", heap.getLeftChild(5));
		assertNull(heap.getRightChild(5));
		assertNull(heap.getLeftChild(6));
		assertNull(heap.getRightChild(6));
		assertNull(heap.getLeftChild(7));
		assertNull(heap.getRightChild(7));
	}
	
	//uses example from the notes
	public void testMaxHeapify() {
		test.add("16");
		test.add("4");
		test.add("10");
		test.add("14");
		test.add("7");
		test.add("9");
		test.add("3");
		test.add("2");
		test.add("8");
		test.add("1");
		heap = new Heap(test);
		heap.maxHeapify(2,test.size());
		assertNull(heap.getParent(1));
		assertEquals("14", heap.getLeftChild(1));
		assertEquals("10", heap.getRightChild(1));
		assertEquals("8", heap.getLeftChild(2));
		assertEquals("7", heap.getRightChild(2));
		assertEquals("9", heap.getLeftChild(3));
		assertEquals("3", heap.getRightChild(3));
		assertEquals("2", heap.getLeftChild(4));
		assertEquals("4", heap.getRightChild(4));
		assertEquals("1", heap.getLeftChild(5));
		assertNull(heap.getRightChild(5));
		assertNull(heap.getLeftChild(6));
		assertNull(heap.getRightChild(6));
		assertNull(heap.getLeftChild(7));
		assertNull(heap.getRightChild(7));
	}
	
	public void testParentLeftRight() {
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
	
		test.add(a);
		test.add(b);
		test.add(c);
		test.add(d);
		heap = new Heap(test);
		assertNull(heap.getParent(1));
		assertEquals(b, heap.getLeftChild(1));
		assertEquals(c, heap.getRightChild(1));
		assertEquals(d, heap.getLeftChild(2));
		assertNull(heap.getRightChild(2));
		assertNull(heap.getLeftChild(3));
		assertNull(heap.getLeftChild(3));
	}
	
	public void testStrings() {
		test.add("a");
		test.add("b");
		test.add("c");
		test.add("d");
		heap = new Heap(test);
		assertNull(heap.getParent(1));
		assertEquals("b", heap.getLeftChild(1));
		assertEquals("c", heap.getRightChild(1));
		assertEquals("d", heap.getLeftChild(2));
		assertNull(heap.getRightChild(2));
		assertNull(heap.getLeftChild(3));
		assertNull(heap.getRightChild(3));
		assertNull(heap.getLeftChild(4));
		assertNull(heap.getRightChild(4));
	}
	public void testSortTwo() {
		test.add("16");
		test.add("4");
		test.add("10");
		test.add("14");
		test.add("7");
		test.add("9");
		test.add("3");
		test.add("2");
		test.add("8");
		test.add("1");
		heap = new Heap(test);
		heap.sort();
		assertNull(heap.getParent(1));
		assertEquals("2", heap.getLeftChild(1));
		assertEquals("3", heap.getRightChild(1));
		assertEquals("4", heap.getLeftChild(2));
		assertEquals("7", heap.getRightChild(2));
		assertEquals("8", heap.getLeftChild(3));
		assertEquals("9", heap.getRightChild(3));
		assertEquals("10", heap.getLeftChild(4));
		assertEquals("14", heap.getRightChild(4));
		assertEquals("16", heap.getLeftChild(5));
		assertNull(heap.getRightChild(5));
		assertNull(heap.getLeftChild(6));
		assertNull(heap.getRightChild(6));
		assertNull(heap.getLeftChild(7));
		assertNull(heap.getRightChild(7));
	}
	public void testSort() {
		test.add("7");
		test.add("4");
		test.add("3");
		test.add("1");
		test.add("2");
		heap = new Heap(test);
		heap.sort();
		assertNull(heap.getParent(1));
		assertEquals("2", heap.getLeftChild(1));
		assertEquals("3", heap.getRightChild(1));
		assertEquals("4", heap.getLeftChild(2));
		assertEquals("7", heap.getRightChild(2));
		assertEquals("7", heap.getLastValue());
		assertEquals("1", heap.getFirstValue());
	}
}

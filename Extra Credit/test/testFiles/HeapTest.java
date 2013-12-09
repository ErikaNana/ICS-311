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
		test.add("d");
		test.add("a");
		test.add("c");
		test.add("b");
		test.add("p");
		test.add("i");
		test.add("j");
		test.add("n");
		test.add("h");
		test.add("g");
		heap = new Heap(test);
		heap.buildMaxHeap();
		assertNull(heap.getParent(1));
		assertEquals("n", heap.getLeftChild(1));
		assertEquals("j", heap.getRightChild(1));
		assertEquals("h", heap.getLeftChild(2));
		assertEquals("g", heap.getRightChild(2));
		assertEquals("i", heap.getLeftChild(3));
		assertEquals("c", heap.getRightChild(3));
		assertEquals("b", heap.getLeftChild(4));
		assertEquals("d", heap.getRightChild(4));
		assertEquals("a", heap.getLeftChild(5));
		assertNull(heap.getRightChild(5));
		assertNull(heap.getLeftChild(6));
		assertNull(heap.getRightChild(6));
		assertNull(heap.getLeftChild(7));
		assertNull(heap.getRightChild(7));
	}
	
	//uses example from the notes
	public void testMaxHeapify() {
		test.add("p");
		test.add("d");
		test.add("j");
		test.add("n");
		test.add("g");
		test.add("i");
		test.add("c");
		test.add("b");
		test.add("h");
		test.add("a");
		heap = new Heap(test);
		heap.maxHeapify(2,test.size());
		assertNull(heap.getParent(1));
		assertEquals("n", heap.getLeftChild(1));
		assertEquals("j", heap.getRightChild(1));
		assertEquals("h", heap.getLeftChild(2));
		assertEquals("g", heap.getRightChild(2));
		assertEquals("i", heap.getLeftChild(3));
		assertEquals("c", heap.getRightChild(3));
		assertEquals("b", heap.getLeftChild(4));
		assertEquals("d", heap.getRightChild(4));
		assertEquals("a", heap.getLeftChild(5));
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
		test.add("a");
		test.add("b");
		test.add("c");
		test.add("d");
		test.add("e");
		test.add("f");
		test.add("g");
		test.add("h");
		test.add("i");
		test.add("j");
		heap = new Heap(test);
		heap.sort(0,0);
		assertNull(heap.getParent(1));
		assertEquals("b", heap.getLeftChild(1));
		assertEquals("c", heap.getRightChild(1));
		assertEquals("d", heap.getLeftChild(2));
		assertEquals("e", heap.getRightChild(2));
		assertEquals("f", heap.getLeftChild(3));
		assertEquals("g", heap.getRightChild(3));
		assertEquals("h", heap.getLeftChild(4));
		assertEquals("i", heap.getRightChild(4));
		assertEquals("j", heap.getLeftChild(5));
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
		heap.sort(0,0);
		assertNull(heap.getParent(1));
		assertEquals("2", heap.getLeftChild(1));
		assertEquals("3", heap.getRightChild(1));
		assertEquals("4", heap.getLeftChild(2));
		assertEquals("7", heap.getRightChild(2));
		assertEquals("7", heap.getLastValue());
		assertEquals("1", heap.getFirstValue());
	}
}

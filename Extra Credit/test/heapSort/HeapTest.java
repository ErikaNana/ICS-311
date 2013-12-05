package heapSort;

import heap.Heap;

import java.util.ArrayList;

import junit.framework.TestCase;

public class HeapTest extends TestCase {
	Heap heap;
	String a = "a";
	String b = "b";
	String c = "c";
	String d = "d";
	
	public HeapTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

		ArrayList<String> test = new ArrayList<String>();
		String placeholder = "";
		test.add(placeholder);
		test.add(a);
		test.add(b);
		test.add(c);
		test.add(d);
		heap = new Heap(test);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void createHeapTest() {
		
	}
	public void emptyHeapTest() {
		
	}
	public void buildMaxHeapTest() {
	}
	
	//use example from the notes
	public void maxHeapifyTest() {
		ArrayList<String> test = new ArrayList<String>();
		String placeholder = new String("");
	}
	
	public void testParentLeftRight() {
		assertNull(heap.getParent(1));
		assertEquals(b, heap.getLeftChild(1));
		assertEquals(c, heap.getRightChild(1));
		assertEquals(d, heap.getLeftChild(2));
		assertNull(heap.getRightChild(2));
		assertNull(heap.getLeftChild(3));
		assertNull(heap.getLeftChild(3));
		heap.printHeap();
	}
}

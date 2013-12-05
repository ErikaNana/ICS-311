package heapSort;

import heap.Heap;
import heap.Node;

import java.util.ArrayList;

import junit.framework.TestCase;

public class HeapTest extends TestCase {
	Heap heap;
	Node a = new Node("a");
	Node b = new Node("b");
	Node c = new Node("c");
	Node d = new Node("d");
	
	public HeapTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

		ArrayList<Node> test = new ArrayList<Node>();
		Node placeholder = new Node("");
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
	
	public void maxHeapifyTest() {
		
	}
	
	public void testParentLeftRight() {
		assertNull(heap.getParent(1));
		assertEquals(b, heap.getLeftChild(1));
		assertEquals(c, heap.getRightChild(1));
		assertEquals(d, heap.getLeftChild(2));
		assertNull(heap.getRightChild(2));
		assertNull(heap.getLeftChild(3));
		assertNull(heap.getLeftChild(3));
	}
}

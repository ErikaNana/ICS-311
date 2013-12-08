package testFiles;

import java.util.ArrayList;

import junit.framework.TestCase;
import code.Insertion;

public class InsertionTest extends TestCase {
	ArrayList<String> test;
	
	public InsertionTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test = new ArrayList<String>();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testOrderedArray() {
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		test.add("5");
		Insertion insertion = new Insertion(test);
		insertion.sort();
		ArrayList<String> array = insertion.returnSortedArray();
		assertEquals("1", array.get(0));
		assertEquals("2", array.get(1));
		assertEquals("3", array.get(2));
		assertEquals("4", array.get(3));
		assertEquals("5", array.get(4));
	}
	
	public void testUnorderedArray() {
		test.add("6");
		test.add("1");
		test.add("3");
		test.add("4");
		test.add("8");
		test.add("2");
		test.add("5");
		test.add("7");
		Insertion insertion = new Insertion(test);
		insertion.sort();
		ArrayList<String> array = insertion.returnSortedArray();
		assertEquals("1", array.get(0));
		assertEquals("2", array.get(1));
		assertEquals("3", array.get(2));
		assertEquals("4", array.get(3));
		assertEquals("5", array.get(4));
		assertEquals("6", array.get(5));
		assertEquals("7", array.get(6));
		assertEquals("8", array.get(7));
	}
}

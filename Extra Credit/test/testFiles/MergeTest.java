package testFiles;

import java.util.ArrayList;

import junit.framework.TestCase;
import code.Merge;

public class MergeTest extends TestCase {

	public MergeTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testMergeSortOrdered() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		Merge merge = new Merge(test);
		merge.sort(1, 4);
		ArrayList<String> array = merge.returnSortedArray();
		assertEquals("1", array.get(0));
		assertEquals("2", array.get(1));
		assertEquals("3", array.get(2));
		assertEquals("4", array.get(3));
	}
	public void testMergeSort() {
		System.out.println("TESTING");
		ArrayList<String> test = new ArrayList<String>();
		test.add("20");
		test.add("80");
		test.add("40");
		test.add("25");
		Merge merge = new Merge(test);
		merge.sort(1, 4);
		ArrayList<String> array = merge.returnSortedArray();
		assertEquals("20", array.get(0));
		assertEquals("25", array.get(1));
		assertEquals("30", array.get(2));
		assertEquals("40", array.get(3));
		assertEquals("60", array.get(4));
		assertEquals("80", array.get(5));
	}

}

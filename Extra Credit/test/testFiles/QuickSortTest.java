package testFiles;

import java.util.ArrayList;

import junit.framework.TestCase;
import code.Quick;
import code.Utils;

public class QuickSortTest extends TestCase {
	ArrayList<String> test;
	
	public QuickSortTest(String name) {
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
		Quick sort = new Quick(test);
		sort.sort(1, 3);
		ArrayList<String> array = sort.getSortedArray();
		assertEquals("1", array.get(0));
		assertEquals("2", array.get(1));
		assertEquals("3", array.get(2));
	}
	
	public void testUnorderedArray() {
		test.add("1");
		test.add("5");
		test.add("3");
		Quick sort = new Quick(test);
		sort.sort(1, 3);
		ArrayList<String> array = sort.getSortedArray();
		assertEquals("1", array.get(0));
		assertEquals("3", array.get(1));
		assertEquals("5", array.get(2));
	}
	
	//test example from the book
	public void testBookArray() {
		test.add("2");
		test.add("8");
		test.add("7");
		test.add("1");
		test.add("3");
		test.add("5");
		test.add("6");
		test.add("4");
		Quick sort = new Quick(test);
		sort.sort(1, 8);
		ArrayList<String> array = sort.getSortedArray();
		assertEquals("1", array.get(0));
		assertEquals("2", array.get(1));
		assertEquals("3", array.get(2));
		assertEquals("4", array.get(3));
		assertEquals("5", array.get(4));
		assertEquals("6", array.get(5));
		assertEquals("7", array.get(6));
		assertEquals("8", array.get(7));
		assertEquals("1", sort.getFirstValue());
		assertEquals("8", sort.getlastValue());
		System.out.println("printing for quicksort test");
		System.out.println(Utils.testRunTime(sort, 1, 8, Utils.QUICK_SORT));
	}
}

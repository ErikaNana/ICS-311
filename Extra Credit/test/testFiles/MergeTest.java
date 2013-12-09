package testFiles;
/*
 * Copyright (c) 2013, Erika Nana
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Extra Credit nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Erika Nana ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Erika Nana BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/** 
 * Tests for merge sort
 * 
 * @author Erika Nana
 * 
 */
import java.util.ArrayList;

import junit.framework.TestCase;
import code.Merge;

public class MergeTest extends TestCase {
	ArrayList<String> test;
	
	public MergeTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test = new ArrayList<String>();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testMergeSortOrdered() {
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
		test.add("20");
		test.add("80");
		test.add("40");
		test.add("25");
		test.add("30");
		test.add("60");
		Merge merge = new Merge(test);
		merge.sort(1, 6);
		ArrayList<String> array = merge.returnSortedArray();
		assertEquals("20", array.get(0));
		assertEquals("25", array.get(1));
		assertEquals("30", array.get(2));
		assertEquals("40", array.get(3));
		assertEquals("60", array.get(4));
		assertEquals("80", array.get(5));
		assertEquals("20", merge.getFirstValue());
		assertEquals("80", merge.getLastValue());
	}

}

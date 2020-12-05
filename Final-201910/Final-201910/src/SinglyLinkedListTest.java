import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class SinglyLinkedListTest {


	SinglyLinkedList emptyS, s1;
	
	@Before
	public void setUp() throws Exception {
		this.emptyS = new SinglyLinkedList();
		this.s1 = new SinglyLinkedList();
	}
	
	/*
	 * Populates the list with the items in the given array, in the order
	 * they're given in the array.
	 */
	private void populateList(SinglyLinkedList list, Integer[] items) {
		for (int k = 0; k < items.length; k++) {
			list.addAtHead(items[k]);
		} // end for
	}

	//*********************************************************
	// Test cases for findAnIntAndSwapItToFront
	//*********************************************************
	
	@Test
	public void test1() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		this.s1.findAnIntAndSwapItToFront(10);
		assertEquals("[10->20->30->40->50]", this.s1.toString());
	}
	
	@Test
	public void test2() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		this.s1.findAnIntAndSwapItToFront(20);
		assertEquals("[20->10->30->40->50]", this.s1.toString());
	}
	
	@Test
	public void test3() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		this.s1.findAnIntAndSwapItToFront(40);
		assertEquals("[40->20->30->10->50]", this.s1.toString());
	}
	
	@Test
	public void test4() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		this.s1.findAnIntAndSwapItToFront(50);
		assertEquals("[50->20->30->40->10]", this.s1.toString());
	}
	
	@Test
	public void test5() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		this.s1.findAnIntAndSwapItToFront(60);
		assertEquals("[10->20->30->40->50]", this.s1.toString());
	}
	
	@Test
	public void test6() {
		this.emptyS.findAnIntAndSwapItToFront(50);
		assertEquals("[]", this.s1.toString());
	}
	
	//*********************************************************
	// Test cases for returnValueInMiddleOfList
	//*********************************************************
	
	@Test
	public void test7() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		assertEquals(30, this.s1.returnValueInMiddleOfList());
	}
	
	@Test
	public void test8() {
		populateList(this.s1, new Integer[] {40,30,20,10});
		assertEquals(-1, this.s1.returnValueInMiddleOfList());
	}
	
	@Test
	public void test9() {
		populateList(this.s1, new Integer[] {});
		assertEquals(-1, this.s1.returnValueInMiddleOfList());
	}
	
	//*********************************************************
	// Test cases for cutOutNodesFrom55To65
	//*********************************************************

	@Test
	public void test10() {
		// 1 startNode, endNode pair
		// Appearing in the middle of the list
		populateList(this.s1, new Integer[] {44,65,63,61,59,57,55,91});
		this.s1.cutOutNodesFrom55To65();
		assertEquals("[91->55->65->44]", this.s1.toString());
	}
	
	@Test
	public void test11() {
		// 2 startNode, endNode pairs
		// Both appearing in the middle of the list
		populateList(this.s1, new Integer[] {88,65,93,55,22,65,7,55,5});
		this.s1.cutOutNodesFrom55To65();
		assertEquals("[5->55->65->22->55->65->88]", this.s1.toString());
	}
	
	@Test
	public void test12() {
		// 1 startNode, endNode pair
		// A second startNode and with a missing endNode
		// Both appearing in the middle of the list
		populateList(this.s1, new Integer[] {88,1,93,55,22,65,7,55,5});
		this.s1.cutOutNodesFrom55To65();
		assertEquals("[5->55->65->22->55]", this.s1.toString());
	}
	
	@Test
	public void test13() {
		// 1 startNode and missing endNode
		// Appearing at the beginning of the list
		populateList(this.s1, new Integer[] {-1,5,57,55});
		this.s1.cutOutNodesFrom55To65();
		assertEquals("[55]", this.s1.toString());
	}
	
	@Test
	public void test14() {
		// 1 startNode, endNode pair, with no nodes in between
		// Appearing at the end of the list
		populateList(this.s1, new Integer[] {65,55,57,52});
		this.s1.cutOutNodesFrom55To65();
		assertEquals("[52->57->55->65]", this.s1.toString());
	}
	
	@Test
	public void test15() {
		// 0 startNode, endNode pairs
		// Non-empty list
		populateList(this.s1, new Integer[] {700,457});
		this.s1.cutOutNodesFrom55To65();
		assertEquals("[457->700]", this.s1.toString());
	}
	
	@Test
	public void test16() {
		// 0 startNode, endNode pairs
		// Empty list
		this.emptyS.cutOutNodesFrom55To65();
		assertEquals("[]", this.s1.toString());
	}
}

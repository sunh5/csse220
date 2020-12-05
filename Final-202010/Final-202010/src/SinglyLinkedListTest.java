

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
		for (int k = items.length-1; k >= 0; k--) {
			list.addAtHead(items[k]);
		} // end for
	}
	
	private SinglyLinkedList makeNew(Integer[] items) {
		SinglyLinkedList newList = new SinglyLinkedList();
		for (int k = items.length-1; k >= 0; k--) {
			newList.addAtHead(items[k]);
		}
		return newList;
	}

	
	@Test
	public void testReverseAndSkip() {
		populateList(this.s1, new Integer[] {50,40,30,20,10});
		SinglyLinkedList newList = this.s1.reverseAndSkip();
		assertEquals("[10->30->50]", newList.toString());
	
		this.s1 = makeNew(new Integer[] {50, 40, 30, 20, 10, 4, 3, 2, 1});
		newList = this.s1.reverseAndSkip();
		assertEquals("[1->3->10->30->50]", newList.toString());
	
		this.s1 = makeNew(new Integer[] {50});
		newList = this.s1.reverseAndSkip();
		assertEquals("[50]", newList.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
		newList = this.s1.reverseAndSkip();
		assertEquals("[9->7->5->3->1]", newList.toString());
	
		this.s1 = makeNew(new Integer[] {9, 8, 7, 6, 5, 4, 3, 2, 1});
		newList = this.s1.reverseAndSkip();
		assertEquals("[1->3->5->7->9]", newList.toString());
	}
	
	
	
	@Test
	public void testRemoveAllBetween() {
		this.s1 = makeNew(new Integer[] {1, 2, 3, 4, 5, 6, 2, 7, 8, 9});
		this.s1.removeAllBetween(2);
		assertEquals("[1->2->2->7->8->9]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 2, 7, 8, 9});
		this.s1.removeAllBetween(2);
		assertEquals("[1->2->2->7->8->9]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {9, 2, 3, 4, 5, 6, 2, 7, 8, 9});
		this.s1.removeAllBetween(9);
		assertEquals("[9->9]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 3, 4, 5, 6, 2, 7, 8, 9});
		this.s1.removeAllBetween(5);
		assertEquals("[1->2->3->4->5]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 3, 4, 5, 6, 2, 7, 8, 9});
		this.s1.removeAllBetween(1);
		assertEquals("[1]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 3, 4, 5, 6, 9, 8, 9});
		this.s1.removeAllBetween(9);
		assertEquals("[1->2->3->4->5->6->9->9]", this.s1.toString());
	}
	
	
	
	@Test
	public void testMoveAllToBack() {
		this.s1 = makeNew(new Integer[] {1, 2, 3, 2, 4, 5, 6, 2, 8, 9});
		this.s1.moveAllToBack(2);
		assertEquals("[1->3->4->5->6->8->9->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 3, 4, 5, 6, 8, 9});
		this.s1.moveAllToBack(2);
		assertEquals("[1->3->4->5->6->8->9]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 3, 2, 4, 5, 6, 2, 8, 9, 2});
		this.s1.moveAllToBack(2);
		assertEquals("[1->3->4->5->6->8->9->2->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {2, 3, 4, 5, 1});
		this.s1.moveAllToBack(2);
		assertEquals("[3->4->5->1->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {2, 2, 2, 2, 2, 1, 3});
		this.s1.moveAllToBack(2);
		assertEquals("[1->3->2->2->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {2, 2, 2, 2, 2, 2, 2});
		this.s1.moveAllToBack(2);
		assertEquals("[2->2->2->2->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {2, 2, 2, 2, 2, 2, 2, 1});
		this.s1.moveAllToBack(2);
		assertEquals("[1->2->2->2->2->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 2, 2, 2});
		this.s1.moveAllToBack(2);
		assertEquals("[1->2->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 2, 2, 2, 2, 2, 2, 3});
		this.s1.moveAllToBack(2);
		assertEquals("[1->3->2->2->2->2->2->2->2]", this.s1.toString());
	
		this.s1 = makeNew(new Integer[] {1, 2, 3, 4, 5});
		this.s1.moveAllToBack(6);
		assertEquals("[1->2->3->4->5]", this.s1.toString());
	}
}

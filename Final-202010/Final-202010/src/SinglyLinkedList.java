import sll.SinglyLinkedList;


public class SinglyLinkedList {
	private class Node {
		public Integer data;
		public Node next;

		public Node(Integer data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node head;

	// Do not change constructor
	public SinglyLinkedList() {
		head = null;
	}

	// Do not change addAtHead
	public void addAtHead(int x) {
		Node p = new Node(x, this.head);
		this.head = p;
	}

	// Do not change toStringHelper
	private String toStringHelper(Node p) {
		if (p == null) {
			return "";
		} else {
			return p.data + ((p.next != null) ? "->" : "") + toStringHelper(p.next);
		} // end if
	}

	// Do not change toString
	@Override
	public String toString() {
		return "[" + toStringHelper(head) + "]";
	}

	/**
	  * TODO
	  * You are to return a new SinglyLinkedList that has all data
	  * in reverse order to the list with every other value skipped.
	  * 
	  * For example
	  * this = 1->2->3->4->5->null
	  * you should return a list: 5->3->1->null
	  * 
	  * You can assume there is always an odd number of Nodes in the list for simplicity.
	  * 
	  * If the list is empty, return an empty list
	  * 
	  */
	public SinglyLinkedList reverseAndSkip() {
//		SinglyLinkedList list = new SinglyLinkedList();
		if (this.head.next == null) return this;
		Node current = this.head;
		Node newCurrent  = null;
		Node next = this.head.next.next;
		while(current != null) {
			current.next = newCurrent;
			newCurrent = current;
			current = next;
			if (next.next == null) {
				next = null;
			}else if (next.next != null){
			next = next.next.next;
			}
		}
		return this;
	}

	/**
	* TODO
	* YOu are to remove all Nodes between the two that contain the value given as the
	* parameter.  If the value only appears once, you should remove all Nodes after
	* the first instance.
	* 
	* For example:
	* this = 1->2->5->4->2->3->null
	* between = 2
	* You should change the list so that it is
	* 1->2->2->3->null
	* 
	* 
	* As another example, if the value only appears once
	* this = 2->4->6->8->10->null
	* between = 6
	* You should change the list so that it is
	* 2->4->6->null
	* 
	* For simplicity, the value is guaranteed to be in the list at least once.
	* 
	* @param between - the value to find and remove nodes between the two instances of the value
	* 
	* @return nothing
	* 
	*/
	public void removeAllBetween(int between) {
		
	}

	/** TODO
	 * You are to move every instance of a Node with the value toMove to the back
	 * of the list.
	 * 
	 * For example:
	 * this = 1->2->3->2->4->5->2->7->null
	 * toMove = 2
	 * the list when finished should be 1->3->4->5->7->2->2->2->null
	 * 
	 * If the value is not in the list, or if the list is empty, it should remain unchanged.
	 * 
	 * @param toMove - the value you are to move every instance of to the back of the list
	 * 
	 * @return nothing
	 */
	public void moveAllToBack(int toMove) {
	}
}

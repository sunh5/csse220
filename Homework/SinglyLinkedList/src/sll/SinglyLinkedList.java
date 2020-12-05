package sll;

/**
 * This class provides a basic implementation of a singly linked list. It's
 * motivated by the implementation in Big Java, 5e, ch. 16.1.
 * 
 * @author TODO <YOUR_NAME> on <DATE>.
 */
public class SinglyLinkedList implements ILinkedList {

	/**
	 * Note that in addition to a head (first) pointer
	 * this list contains a last pointer.  Be sure to
	 * BOTH first and last up to date in all your code.
	 */
	private ListNode first;
	private ListNode last;

	/**
	 * These are needed for the test code.
	 */
	@Override
	public IListNode getFirst() {
		return first;
	}

	@Override
	public IListNode getLast() {
		return last;
	}
	
	@Override
	public void setFirst(IListNode first){
		this.first = (ListNode) first;
	}
	
	@Override
	public void setLast(IListNode last){
		this.last = (ListNode) last;
	}
	
	/**
	 * Constructs a new, empty linked list.
	 */
	public SinglyLinkedList() {
		this.first = null;
		this.last = null;
	}
	

	@Override
	public String toString() {
		if (this.first == null) {
			return "[]";
		}
		String result = "[";
		ListNode current = this.first;
		//while (current != this.last) {
		while (current.next != null) {
			result += (current.element + ", ");
			current = current.next;
		}
		result += current.element + "] first=["+this.first.getElement()+"] last=["+this.last.getElement()+"]";
		return result;
	}
	
	/**
	 *  WARNING: add(Integer element) must be completed before OTHER tests can pass!
	 *  
	 * 	Make sure to complete this method FIRST before trying the other ones.
	 *  
	 *  This method should add the given element to the end of this list.
	 */
	@Override
	public void add(Integer element) {
		if (this.first == null) {
			this.first = new ListNode(element, null);
			this.last = this.first;
			return;
		}
		
		this.last.next = new ListNode(element, null);
		this.last = this.last.next;
	}

	
	/**
	 * WARNING: add(Integer element) must be completed before this test can pass!
	 * 
	 *  This method should return the number of elements in the list.
	 */
	@Override
	public Integer size() {
		int result = 0;
		if (this.first == null) return 0;
		ListNode current = this.first;
		while(current != null) {
			result++;
			current = current.next;
		}
		return result;
	}
	
	
	/**
	 *  This method should insert the given element at the given index in the list.
	 *  This DOES NOT replace the element at an index, but inserts an element. 
	 *   
	 *  If you are passed an invalid index you should throw an IndexOutOfBoundsException.
     *  
     *  Note that this inserts a value at the specific index NOT a value.
     *  Please also note that it is LEGAL to insert at the END of the list.
     *  
     *  Examples:
     *  [2, 6]          ->    insertAtIndex(1, 7)    ->   [2, 7, 6]
     *  [2, 7, 6]       ->    insertAtIndex(0, 8)    ->   [8, 2, 7, 6]
     *  [8, 2, 7, 6]    ->    insertAtIndex(4, 9)    ->   [8, 2, 7, 6, 9]
     *   ^  ^  ^  ^  ^
     *   |  |  |  |  |
     *   0  1  2  3  4 <- indices 
	 */
	@Override
	public void insertAtIndex(int index, Integer element) throws IndexOutOfBoundsException {
		// TODO 03 Implement the insertAtIndex(int index, int value) method.
		if(index>this.size() || index < 0 ){
			throw new IndexOutOfBoundsException();
		}
		else if ( index == this.size()) {
			this.add(element);
		}else if (index == 0) {
			this.first = new ListNode (element, this.first);
		}
		else {
			ListNode prev = this.first;
			ListNode current = this.first.next;
			ListNode las = this.last;
			int i = 1;
			while(i != index) {
				current = current.next;
				prev = prev.next;
				i++;
			}
			prev.next = new ListNode(element,current);
			
		}
		
	}
	
	
	/**
	 * 	This method should return true if the list contains the given element
	 *  and false if it does not.
	 * 
	 */
	@Override
	public boolean contains(Integer element) {
		if (this.first == null) return false;
		ListNode current = this.first;
		while(current != null) {
			if (current.element == element) return true;
			current = current.next;
		}
		return false;
	}

	
	/**
	 *  This method should attempt to remove the FIRST occurrence of the
	 *  given element in the list.
	 *  
	 *  If the element is found and removed, return true
	 *  otherwise if the element is not found, then return false.
	 */
	@Override
	public boolean remove(Integer element) {
		// TODO 05 Implement the remove(Integer element) method
		if (this.first == null) return false;
		if (this.first.element == element) {
			this.first = this.first.next;
			return true;
		}
		ListNode pre = this.first;
		ListNode current = this.first.next;
		while(current.element != element) {
			current = current.next;
			pre = pre.next;
			if (current == null) return false;
		}
		pre.next = current.next;
		ListNode lis = this.first;
		ListNode lis2 = this.first.next;
		while(lis2 != null) {
			lis = lis.next;
			lis2 = lis2.next;
		}
		this.last = lis;
		return true;
	}
	
	
	/**
	 *  This method should return the index of the FIRST occurrence of the 
	 *  given element, OR -1 if the element is not found in the list.
	 * 
	 */
	@Override
	public int indexOf(Integer element) {
		ListNode current = this.first;
		int index = 0;
		while(current!= null){
			if(current.element == element){
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	
	/**
	 * Replaces the element at the given index with the new element.
	 * RETURNS the OLD element.
	 * 
	 * The method should throw an IndexOutOfBoundsException if an invalid index is 
	 * provided.
	 */
	@Override
	public Integer set(int index, Integer element) throws IndexOutOfBoundsException {
		if(index < 0 || index > this.size()-1){
			throw new IndexOutOfBoundsException();
		}
		Integer output = null;
		ListNode current = this.first;
		ListNode previous = null;
		int i = 0;
		if(index == 0){
			output = this.first.element;
			current = new ListNode(element,current.next);
			this.first = current;
			return output;
		}
		while(i!=index){
			i++;
			previous = current;
			current = current.next;
		}
		if(index == this.size()-1){
			output = this.last.element;
			ListNode theOne = new ListNode(element,null);
			previous.next = theOne;
			this.last = theOne;
			return output;
		}
		output = current.element;
		ListNode add =new ListNode(element,current.next);
		previous.next = add;
		
	
		return output;
	}
		
}

package sll.algorithms;

import sll.ILinkedList;
import sll.IListNode;
import sll.ListNode;
import sll.SinglyLinkedList;

// done: complete sll.SinglyLinkedList first, then use it to complete these problems.
public class Algorithms {
	/**
	 * 
	 * Write a function that takes an array of integers and returns a linked list.
	 * The linked list should contain only the integers in the original list that
	 * begin with the number 1.
	 * 
	 * So arraylistOf1s({123,456,1, 21}) yields [123, 1]
	 */
	public static SinglyLinkedList arraylistOf1s(int[] integers) {
		SinglyLinkedList list = new SinglyLinkedList();
		if (integers.length != 0) {
			for (int i = 0; i < integers.length; i++) {
				String str = String.valueOf(integers[i]);
				if (str.charAt(0) == '1') {
					list.add(integers[i]);
				}
			}
		}

		return list;
	}

	/**
	 * Takes a list of numbers in sorted (ascending) order and a number to add.
	 * 
	 * Adds the number in the correct place in the sorted list.
	 * 
	 * so insertIntoSorted([1,5,9],6) yields [1,5,6,9]
	 * 
	 */
	public static void insertIntoSorted(ILinkedList list, int number) {
		if (list.size() != 0) {
			int i = 0;
			IListNode current = list.getFirst();
			while (current != null && current.getElement() < number) {
				i++;
				current = current.getNext();
			}
			list.insertAtIndex(i, number);
			return;
//			if (i == 0) list.getFirst() = list.insertAtIndex(i, number);
		}
		list.add(number);
	}

	/**
	 * Takes a linked list and removes all numbers longer than 3 digits from the
	 * list.
	 * 
	 * So removeLongNumbers([1,1000,3,99999,999]) yields [1, 3, 999]
	 */
	public static void removeLongNumbers(ILinkedList list) {
		if (list.size() == 1) {
			if (list.getFirst().getElement()/1000 >= 1) {
				list.setFirst(null);
			}
		}
		IListNode current = list.getFirst();
		while(current != null) {
			
			if(current.getElement() / 1000 >= 1) list.remove(current.getElement());
			current = current.getNext();
		}
		
	}

	/**
	 * Returns whether the given linked list is sorted in increasing order.
	 * 
	 * So checkSorted([1,2,3]) yields true. checkSorted ([1,3,2]) yields false.
	 * checkSorted ([]) yields true.
	 */
	public static boolean checkSorted(ILinkedList list) {
		if (list.size() == 0) return true;
		IListNode current = list.getFirst();
		IListNode next = list.getFirst().getNext();
		while(next != null) {
			if(current.getElement() > next.getElement()) return false;
			current = current.getNext();
			next = next.getNext();
		}
		return true;
	}

	/**
	 * Duplicates all elements of the list.
	 * 
	 * So doubleList([1, 2, 3]) yields [1, 1, 2, 2, 3, 3].
	 */
	public static void doubleList(ILinkedList list) {
		if (list.size() != 0) {
			IListNode current = list.getFirst();
			int i = 0;
			while(current != null) {
				int ele = current.getElement();
				current = current.getNext();
				list.insertAtIndex(i, (Integer)ele);
				i = i+2;
			}
		}
	}

	/**
	 * Returns whether the list represents a Fibonacci sequence.
	 * 
	 * Recall: fib(i) = fib(i-1) + fib(i-2)
	 * 
	 * [0,1] is too short to be a fibonacci sequence. [1,2,3] is a fibonacci
	 * sequence because 3 = 2 + 1. [0,1,1] is a fibonacci sequence because 1 = 1 +
	 * 0. [1,4,5,9,14] is a fibonacci sequence.
	 */
	public static boolean isFibonacciSequence(ILinkedList list) {
		if (list.size() >= 3) {
			IListNode one = list.getFirst();
			IListNode two = list.getFirst().getNext();
			IListNode three = list.getFirst().getNext().getNext();
			int i = 0;
			while(three != null) {
				if (three.getElement() != two.getElement()+one.getElement()) return false;
				one = one.getNext();
				two = two.getNext();
				three = three.getNext();
				i++;
			}
			return true;
		}
		return false;
	}

	/**
	 * Gets the value of the ith node.
	 * 
	 * If i==0, return the value of the given node.
	 * 
	 * If you are passed an invalid index you should throw an
	 * IndexOutOfBoundsException.
	 * 
	 */
	public static Integer recursiveGet(int index, IListNode iListNode) {
		if (index < 0 || iListNode == null) throw new IndexOutOfBoundsException();
		if (index == 0) return iListNode.getElement();
		return recursiveGet(index-1, iListNode.getNext());

	}

}

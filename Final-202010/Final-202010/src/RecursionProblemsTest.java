

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RecursionProblemsTest {

	@Test
	public void test1() {
		int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] toFind = {1, 2, 3};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {2, 3, 4};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {3, 4, 5};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {4, 5, 6};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {5, 6, 7};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {6, 7, 8};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {7, 8, 9};
		assertTrue(RecursionProblems.findIn(input, toFind));
	}
	
	@Test
	public void test2() {
		int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] toFind = {1, 3, 5};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {1, 5, 9};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {7, 9};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {5, 7, 9};
		assertTrue(RecursionProblems.findIn(input, toFind));
	}
	
	@Test
	public void test3() {
		int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] toFind = {10, 20, 30};
		assertFalse(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {9, 5, 1};
		assertFalse(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {8, 9, 10};
		assertFalse(RecursionProblems.findIn(input, toFind));
	}
	
	@Test
	public void test4() {
		int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] toFind = {1};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {6};
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[] {9};
		assertTrue(RecursionProblems.findIn(input, toFind));
	}
	
	@Test
	public void test5() {
		int[] input = new int[100];
		int[] toFind = new int[100];
		ArrayList<Integer> toFindAL = new ArrayList<Integer>();
		
		for(int i = 0; i < 100; i++) {
			input[i] = i;
			toFind[i] = i;
		}
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		toFind = new int[5];
		int index = 0;
		for(int i = 0; i < 100; i++) {
			if(i%9 == 0)
				toFind[index++] = i;
			if(index >= 5)
				break;
		}
		assertTrue(RecursionProblems.findIn(input, toFind));
		
		int[] temp = new int[6];
		for(int i = 0; i < 5; i++) {
			temp[i] = toFind[i];
		}
		temp[5] = 10000;
		assertFalse(RecursionProblems.findIn(input, temp));
	}
}

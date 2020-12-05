

import static org.junit.Assert.*;

import org.junit.Test;

public class RecursionProblemsTest {

	@Test
	public void test1() {
		// Move 'X' at location 4 to front
		String testS1 = "abcaXbyzX";
		String result = RecursionProblems.findAndMoveCharToFront(testS1, 'X');
		assertEquals("XabcabyzX", result);
	}
	
	@Test
	public void test2() {
		// Move '1' at location zero to front
		String testS2 = "1,2,3,4,5";
		String result = RecursionProblems.findAndMoveCharToFront(testS2, '1');
		assertEquals(testS2, result);
	}
	
	@Test
	public void test3() {
		// Move '5' at location 9 to front
		String testS3 = "1,2,3,4,5";
		String result = RecursionProblems.findAndMoveCharToFront(testS3, '5');
		assertEquals("51,2,3,4,", result);
	}
	
	@Test
	public void test4() {
		// Nothing moves because 'Q' is not found
		String testS4 = "abcaXbyzX";
		String result = RecursionProblems.findAndMoveCharToFront(testS4, 'Q');
		assertEquals(testS4, result);
	}
	
	@Test
	public void test5() {
		// Nothing moves because 'R' is not found
		String testS5 = "";
		String result = RecursionProblems.findAndMoveCharToFront(testS5, 'R');
		assertEquals(testS5, result);
	}

}

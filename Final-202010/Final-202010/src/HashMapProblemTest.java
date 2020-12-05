

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HashMapProblemTest {

	@Test
	public void test1() {	
		HashMap<String,String> map = new HashMap<String, String>();
		
		assertEquals(1, HashMapProblem.longestChain(map));
		
		map.put("A","B");
		
		assertEquals(2, HashMapProblem.longestChain(map));
		
		map.put("C","D");
		
		assertEquals(2, HashMapProblem.longestChain(map));
		
		map.put("B","C");
		
		assertEquals(4, HashMapProblem.longestChain(map));
		
		map.put("D","E");
		
		assertEquals(5, HashMapProblem.longestChain(map));
		
		map.put("X","Y");
		map.put("Y","Z");
		
		assertEquals(5, HashMapProblem.longestChain(map));
		
		map.put("Z","B");
		
		assertEquals(7, HashMapProblem.longestChain(map));
	}

}

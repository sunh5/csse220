

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HashMapProblem {
	
	/* TODO
	 * 
	 * Imagine a hashmap that represents dependencies between
	 * servers.  So if the pair "A" -> "B" is in the map, that
	 * means that A depends on B.  In this architecture, a server
	 * can only depend on one other server at most (and maybe depends 
	 * on none, in which case it's not in the map).  You can also
	 * assume there are no dependency cycles.
	 * 
	 * Our function computes the length of the longest dependency 
	 * chain.
	 * 
	 * So if the map looks like this:
	 * 
	 * C -> D
	 * A -> B
	 * B -> C
	 * E -> F
	 * 
	 * The longest dependency chain is A->B->C->D so they function will
	 * return 4.
	 * 
	 * Another example:
	 * 
	 * A -> B
	 * C -> D
	 * E -> F
	 * 
	 * Longest dependency chain is 2.
	 * 
	 * An empty map is considered to have a dependency chain of 1, because surely 
	 * there is a server somewhere right? 
	 */
	public static int longestChain(HashMap<String, String> map) {
		if (map.keySet().size() ==  0) return 1;
		int max = 0;
		boolean hasKey = true;
		
		for (String key : map.keySet()) {
			int count = 2;
			String val = map.get(key);
			hasKey = map.containsKey(map.get(key));
			while (hasKey == true) {
				count++;
				hasKey = helper(map, val);
				val = map.get(val);
			}
			if (max < count) max = count;
		}
		System.out.println(max);
		return max;
	}
	
	public static boolean helper(HashMap<String, String> map, String key) {
		if (map.containsKey(map.get(key))) {
			return true;
		}
		return false;
	}
}

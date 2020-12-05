

public class RecursionProblems {
	
	/*
	 * findAndMoveCharToFront(s, c) - takes String s and char c and
	 * returns a new String with c moved from its original location
	 * to the front of the String, all other characters in s remain the same
	 * 
	 * If c does not appear in s, then the original String s is returned	 
	 * If multiple copies of c appear in s, then the c with the smallest
	 * index is moved to the front
	 * 
	 * Don't use indexOf or other string methods that lets you search a string 
	 * for a particular value
	 * 
	 * You may want to use a helper operation to solve this problem
	 * 
	 * Examples: findAndMoveCharToFront("abcaXbyzX", 'X') produces "XabcabyzX"
	 * See JUnit test scripts for more examples
	 */
	
	static String findAndMoveCharToFront(String s, char c) {
		return helper(s,0,c);
	}
	public static String helper(String s, int index, char c) {
		if(!s.contains(Character.toString(c))) {
			return s;
		}
		if (s.charAt(0) == c) return s;
		if(s.charAt(index)==c) {
			s = Character.toString(s.charAt(index))+s.substring(0,index)+s.substring(index+1);
			return s;
		}
		else {
			return helper(s,index+1,c);
		}
	}

}

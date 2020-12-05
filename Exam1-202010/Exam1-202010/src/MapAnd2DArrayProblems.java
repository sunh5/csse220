import java.util.ArrayList;
import java.util.HashMap;

public class MapAnd2DArrayProblems {
	
	/**
	 * Takes an 2d array consisting of of 1s and 0s 
	 * representing a game board
	 * 
	 * Returns the length of the longest consecutive sequence of 1s
	 * in either rows or columns
	 * 
	 * Eg.
	 *    [[0,1,0,0,0],
	 *     [0,1,1,1,0]] returns 3
	 *     
	 *    [[1,0,0,0,1],
	 *     [0,1,1,0,1],
	 *     [1,1,0,1,1],
	 *     [1,0,1,1,0]] returns 3
	 *     
	 *    [[1,0,0,0,1]
	 *     [0,1,1,0,1],
	 *     [1,1,0,1,0],
	 *     [1,0,1,1,0]] returns 2      
	 *
	 * 	  [[1,0,0,0,1]
	 *     [0,1,1,0,1],
	 *     [1,1,0,1,0],
	 *     [1,0,1,1,1]] returns 3      
	 */
	public static int longest1Run(int [][] board) {
		int max = 0;
		int zeros = 0;
		for (int i = 0; i < board.length; i++) {
			int sum = 1;
			for (int j = 1; j < board[0].length; j++) {
				if (board[i][j] == 1) 
					if (board[i][j] == board[i][j-1]) {
						sum++;
					}
				if (sum > max) max = sum;
			}
		}
		
		for (int i = 0; i < board[0].length; i++) {
			int sum = 1;
			for (int j = 1; j < board.length; j++) {
				//System.out.print(board[j][i]+" ");
				//if (board[j][i] == 0) zeros+=1;
				if (board[j][i] == 1) 
					if (board[j][i] == board[j-1][i]) {
						sum++;
						
					}
				if (sum > max) max =sum;
			}
		}
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) {
					zeros++;
				}
				if (zeros == (board.length*board[0].length)) return 0;
			}
		}
		System.out.print(zeros+" ");
		return max;
	}
	
	/**
	 * The given represents the results of a survey where students 
	 * listed who they were dating.  The key is the student who 
	 * answered the survey and the value is the name of their significant 
	 * other.  If a student said they weren't dating anyone, their value 
	 * will be "SINGLE". 
	 * 
	 * Assume all names are unique and included in the map.  Find all students 
	 * who said they were dating someone, but that someone didn't say they were
	 * dating them.  Note that SINGLE people should never be in the result list, 
	 * and neither should the special value "SINGLE".
	 * 
	 * Example Map: 
	 * { "Alice" => "Bill", 
	 * "Bill" => "Alice", 
	 * "Steve" => "Alice",
	 * "Pat" => "SINGLE",
	 * "Sam" => "Pat" }
	 * Should return ["Steve", "Sam"]       
	 *   
	 * 
	 * @param map 
	 * @param valueToFind
	 * @return key in map associated with valueToFind, or -1 if no key associated with valueToFind
	 */
	public static ArrayList<String> findUnexpectedSingles(HashMap<String,String> map) {
		ArrayList<String> output = new ArrayList<>();
		for (String key : map.keySet()) {
			String datePartner = "";
			String datePartner2 = "";
			if (!map.get(key).equals("SINGLE")) {
				datePartner = map.get(key);
				for (String key2 : map.keySet()) {
					if (key2.equals(datePartner)) {
						if (!key.equals(map.get(key2))) {
							output.add(key);
						}
					}
				}
			}
			//else output.add(key);
		}
		return output;		
	}
}

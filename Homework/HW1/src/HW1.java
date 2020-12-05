/*
 * This is a very small assignment.  It's mostly to show that you can
 * check out and check in code successfully.
 */
public class HW1 {

	/**
	 * This method takes in 4 separate integers. The first two comprise the first fraction
	 * as the numerator and denominator, respectively.  The third and fourth integers 
	 * comprise the second fraction.
	 * 
	 * This method returns the decimal result of adding the two fractions
	 * 
	 * Example
	 * 
	 * addFraction(1,2,1,4) returns 0.75
	 */
	public static double addFraction(int num1, int den1, int num2, int den2) {
		
		double a = (double)num1 / (double)den1;
		double b = (double)num2 / (double)den2;
		double sum = a + b;
		return sum;
	}
	
	/**
	 * Takes a string that is all Ts or Fs.
	 * 
	 * T stands for touchdown and is worth 7 points.
	 * F stands for fieldgoal and is worth 3 points.
	 * 
	 * Returns the total score.
	 * 
	 * For example:
	 * "TT" returns 14
	 * "FTF" returns 13
	 * "FFFF" returns 12
	 * "" returns 0;
	 *  
	 * 
	 * @param a string only Ts and Fs
	 * @return a score
	 */
	public static int footballScore(String input) {
		int i = 0;
		int sum = 0;
		for (i = 0; i < input.length(); i++) {
			if (input.charAt(i) == 'F')
				sum +=3;
			else
				sum +=7;
		}
		return sum;
	}
	
}//end class HW1

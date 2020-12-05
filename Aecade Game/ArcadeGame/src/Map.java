import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Map {
	private char[][] currentMap;
	private int level;
	private String file;
	
	public Map (int gameLevel){
		this.level = gameLevel;
		this.file = "Level"+level+".txt";
		// initialize the map information to a 2D array
		this.currentMap = new char[15][20];
		// use scanner to search for file first 
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(this.file));			
		} catch(FileNotFoundException e) {
			System.out.println(this.file + "does not exist.");
		}
		// put character array into a row
		int row = 0;
		while (scanner.hasNext()) {
			currentMap[row] = scanner.nextLine().toCharArray();
			row ++;
		}

	}
	public char[][] getMap(){
		return this.currentMap;
	}
}

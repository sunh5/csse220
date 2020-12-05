import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	/*
	 * Read files from folder
	 * Construct arraylist to store level information
	 */
	protected ArrayList<Integer> rocks = new ArrayList<>();
	protected ArrayList<Integer> pookas = new ArrayList<>();
	protected ArrayList<Integer> fygars = new ArrayList<>();
	protected ArrayList<Integer> tunnels = new ArrayList<>();
	
	public Level() {
		readFile(1);

	}
	public void readFile(int level) {
		/*
		 * read information from file
		 */
		this.rocks = new ArrayList<>();
		this.pookas = new ArrayList<>();
		this.fygars = new ArrayList<>();
		this.tunnels = new ArrayList<>();
		String filename = "Levelfiles/level " + level + ".txt";
		
		try {
			FileReader file = new FileReader(filename);
			Scanner s = new Scanner(file);
			int r = 0;
			while (s.hasNext()) {
				String line = s.nextLine();
				if (line.startsWith("Rock")) {
					for (String splited : line.substring(5).split(",")) {
						 int i = Integer.parseInt(splited);
						 this.rocks.add(i);
					}
				} else if (line.startsWith("Tunnel")) {
					for (String splited : line.substring(7).split(",")) {
						 int i = Integer.parseInt(splited);
						 this.tunnels.add(i);
					}
				} else if (line.startsWith("Pooka")) {
					for (String splited : line.substring(6).split(",")) {
						 int i = Integer.parseInt(splited);
						 this.pookas.add(i);
					}
				} else if (line.startsWith("Fygar")) {
					for (String splited : line.substring(6).split(",")) {
						 int i = Integer.parseInt(splited);
						 this.fygars.add(i);
					}
				}
				r += 1;
			}
			file.close();
		} catch (FileNotFoundException e1) {
		} catch (IOException e2) {
		}

	}

	
}

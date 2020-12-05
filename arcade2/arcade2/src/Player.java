import javax.swing.JLabel;

public class Player {
	/*
	 * Also known as gamedata, restore gamedata in this class
	 * In chrge of change in life, score and monsternum
	 */
	protected int scores;
	protected int previousscore;
	private int lifecount;
	protected int monsternum;
	private JLabel label;

	public Player() {
		this.lifecount = 3;
//		this.hero = new Hero(450, 450);
//		this.level = 0;
		this.scores = 0;
		this.previousscore = 0;
		this.label=new JLabel("Score:" + this.scores);
		this.monsternum=0;
		
	}
	
	public void addPoints(int point){
		this.scores += point;
		this.label.setText("Score:" + this.scores);
	}
	
	public int getPoints() {
		return this.scores;
	}
	
	public int getHeroLives() {
		return this.lifecount;
	}
	public void die(){
		this.previousscore = scores;
		this.scores = 0;
	}
	
	public void monsterkilled(){
		this.monsternum -= 1;
	}
}

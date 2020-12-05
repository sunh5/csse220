import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GameWorld implements Temporal{
	/*
	 * Where game run
	 */
	private final int width;
	private final int height;
	private ArrayList<GameObject> objects;
	private Hero hero;
	private Level level;
	protected int currentLevel;
	private GameFrame frame;
	private Listener lisener;
	protected ArrayList<GameObject> objectsToRemove;
	protected Player player;
	private ArrayList<Monster> monsters;
	private boolean isFirst;

	public GameWorld() {
		this.width = 800;
		this.height = 800;
		this.objects=new ArrayList<>();
		this.currentLevel=1;
		this.objectsToRemove=new ArrayList<>();
		this.player=new Player();
		this.monsters=new ArrayList<>();
	    this.isFirst=true;
	}
	
	public void addMonster(ArrayList<Integer> pookas,ArrayList<Integer> fygars){
		/*
		 * Add monsters according to levelfile
		 */
		for(int i = 1; i<pookas.size(); i+=2){
			Monster monster=new Pookas(pookas.get(i-1),pookas.get(i), this);
			this.objects.add(monster);
			this.monsters.add(monster);
			this.player.monsternum+=1;
		}
		for(int i = 1; i<fygars.size(); i+=2){
			Monster monster=new Fygars(fygars.get(i-1),fygars.get(i), this);
			this.objects.add(monster);
			this.monsters.add(monster);
			this.player.monsternum+=1;
		}
	}
	
	public void addRock(ArrayList<Integer> rocks){
		/*
		 * Add rock from levelfile
		 */
		for(int i = 1; i<rocks.size(); i+=2){
			GameObject rock=new Rock(rocks.get(i-1),rocks.get(i), this);
			this.objects.add(rock);
		}
	}
	
	public void addTunnel(ArrayList<Integer> tunnels){
		/*
		 * Add tunnel from level file
		 */
		for(int i = 3; i<tunnels.size(); i+=4){
			Rectangle2D.Double tunnel = new Rectangle2D.Double(tunnels.get(i-3), tunnels.get(i-2),tunnels.get(i-1),tunnels.get(i));
			this.getHero().dugs.addAll(convertRect(tunnel));
		}
		
	}
	
	private ArrayList<Rectangle2D> convertRect(Rectangle2D.Double tunnel) {
		
		ArrayList<Rectangle2D> result = new ArrayList<>();
		double x = tunnel.getMinX();
		double y = tunnel.getMinY();
		while(x<=tunnel.getMaxX()-40){
			Rectangle2D square = new Rectangle2D.Double(x, y, 40, 40);
			result.add(square);
			x+=8;
		}
		return result;
	}

	public void startGame(){
		this.objects.clear();
		this.hero=new Hero(408,400, this);
		this.level = new Level();
		if(this.isFirst){
			this.lisener = new Listener(this, this.hero);
			this.isFirst=false;
		}else{
			this.lisener.setHero(this.hero);
		}
		this.frame.addKeyListener(this.lisener);
		this.objects.add(this.hero);
		this.addTunnel(this.level.tunnels);
		this.addRock(this.level.rocks);
		this.addMonster(this.level.pookas, this.level.fygars);
	}
	public void changeLevel(){
		this.objects.clear();
		this.hero=new Hero(408,400, this);
		this.level.readFile(this.currentLevel);
		this.lisener.setHero(this.hero);
		this.objects.add(this.hero);
		this.addTunnel(this.level.tunnels);
		this.addRock(this.level.rocks);
		this.addMonster(this.level.pookas, this.level.fygars);
	}


	public void handleCollision(GameObject object){
			for(GameObject object1: this.objects){
				if(!object.equals(object1)){
					object.collideWith(object1);
				}
			}
		}
	
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub.
		for (GameObject o : this.objects){
			o.updatePosition();
			handleCollision(o);
		}
		this.drawFruit();
		updatelevel();
		
	}

	private void updatelevel() {
		if(this.player.monsternum==0){
			nextLevel();
		}
		if(this.currentLevel>=4){
			this.hero.imageName="images/newhero.png";
			for(Monster monster : this.monsters){
				if(monster.monsterType.equals("Pookas")){
					monster.monsterType="Tiejiayong";
				}else{
					monster.monsterType="Pika";
				}
			}
		}
		
	}

	private void drawFruit() {
		if(this.player.scores==400&&this.currentLevel >= 4){

				GameObject fruit=new Fruit(408,400,this,"");
				this.objects.add(fruit);
			}
		if(this.player.scores==400&&this.currentLevel < 4){
				GameObject fruit=new Fruit(408,400,this,"fruit");
				this.objects.add(fruit);
			}
	}

	@Override
	public void die() {
		/*
		 * When hero dies
		 */
		if(this.getHero().lifeCount==0){
			this.player.previousscore=this.player.scores;
			this.gameover();
		}else{
		this.getHero().x=408;
		this.getHero().y=400;
		for(Monster monster : this.monsters){
			monster.repos();
			}
		}
		}
	
	
	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}
	
	public void drawBackground(Graphics2D g2){
		Rectangle2D.Double box=new Rectangle2D.Double(0, 0, this.width, 100);
		Rectangle2D.Double dirt=new Rectangle2D.Double(0, 100, this.width, this.height-100);
		g2.setColor(Color.BLACK);
		g2.fill(box);
		g2.setColor(new Color(139,69,19));
		g2.fill(dirt);
	}
	
	public void drawObjects(Graphics2D g2){
		for(Rectangle2D dug : this.getHero().dugs){
			g2.setColor(Color.black);
			g2.fill(dug);
		}
		for(GameObject object : this.objects){
			object.drawOn(g2);
		}
		for(GameObject obj : this.objectsToRemove){
			this.objects.remove(obj);
		}
		this.getHero().drawPump(g2);
		this.drawLabel(g2);
	}
	
	public void drawLabel(Graphics2D g2){
		g2.setColor(new Color(255, 255, 255));
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 42));
//		if (this.player.getLevel() != 0) {
			g2.drawString("points: " + this.player.scores, 42, 42);
			g2.drawString("lives: " + this.getHero().lifeCount, 284, 42);
			g2.drawString("level: " + this.currentLevel, 284, 82);
//		}
//		if (this.Level == 0) {
			g2.drawString("last game score " + this.player.previousscore, 450, 42);
//		}
	}
	
	public Hero getHero(){
		return this.hero;
	}

	public void nextLevel(){
		this.currentLevel+=1;
		this.changeLevel();
	}
	
	public void previousLevel(){
		this.currentLevel-=1;
		this.changeLevel();
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.frame = gameFrame;
	}

	public void gameover() {
		this.objects=new ArrayList<>();
		this.objectsToRemove=new ArrayList<>();
		this.getHero().dugs=new ArrayList<>();
		this.objectsToRemove=new ArrayList<>();
		this.monsters=new ArrayList<>();
		this.player=new Player();
		this.currentLevel=1;
		this.isFirst=false;
		this.startGame();
//		this.frame.restart();
		
	}

}

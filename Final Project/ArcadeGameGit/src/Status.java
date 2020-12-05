import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Status extends JPanel implements Temporal{

	private GameComponent gc;
	private int blood;
	private int scores;
	private BufferedImage bloodImage;
	private BufferedImage expImage;
	private BufferedImage statusImage;


	public Status(GameComponent gc){

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000,60));

		this.gc = gc;
		this.scores = 0;
		this.blood = 3;
		//this.duration = 0;
		// load images
		try {
			this.bloodImage = ImageIO.read(new File("heart.png"));
			this.expImage = ImageIO.read(new File("star.png"));
			this.statusImage = ImageIO.read(new File("status.png"));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file ");
		}
		
		// Create a timer to trigger the repaint of components.
				Timer repaintTimer = new Timer(1, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						repaint();
					}
				});
				repaintTimer.start();
	}
	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(this.statusImage, null,0,0);
		// draw the score bar
		this.scores = gc.getExp();
		for (int i =0; i<this.scores;i++) {
			g2.drawImage(this.expImage, null, 10 + i*(this.expImage.getWidth()+5), 10);
		}

		//draw the blood status
		this.blood = gc.getBlood();
		for (int i = 0; i<this.blood; i++) {
			g2.drawImage(this.bloodImage, null, 990 - (i+1)*(this.bloodImage.getWidth())-10, 10);
		}
		
		g2.setColor(Color.white);
		Font tr = new Font("Comic Sans MS", Font.BOLD, 32);
		g2.setFont(tr);

		long duration = (this.gc.getDuraction()) / 1000;
		long seconds = duration % 60;
		long minutes = duration / 60;
		g2.drawString("Time " + Long.toString(minutes) + ":" + Long.toString(seconds),400,40);
		
	}
	
	@Override
	public void timePassed() {

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return false;
	}

}

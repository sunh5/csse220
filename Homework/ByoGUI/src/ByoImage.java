import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ByoImage extends ByoComponent {
	
	private String imageName;
	private Image image;
	private int imageHeight;
	private int imageWidth;
	
	public ByoImage(String filename) {
		try {
		    this.image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			throw new RuntimeException("Could not load image file " + filename);
		}
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.imageHeight = bimg.getHeight();
		this.imageWidth = bimg.getWidth();
	}
	
	
	@Override
	public int getPreferredWidth() {
		
		return this.imageWidth;
	}

	@Override
	public int getPreferredHeight() {
		// TODO Auto-generated method stub
		return this.imageHeight;
	}

	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.drawImage(this.image, getX(), getY(), getWidth(), getHeight()+6, null);
	}

}

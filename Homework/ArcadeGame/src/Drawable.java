import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.util.ArrayList;

public interface Drawable {
	Shape getShape();
	Color getColor();
	Image getImage();
	int getX();
	int getY();
}

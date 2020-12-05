import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public interface Collidable {
	 void isCollide(Rectangle2D.Double Rect, ArrayList<Element> elements);

//	void isCollide();
}

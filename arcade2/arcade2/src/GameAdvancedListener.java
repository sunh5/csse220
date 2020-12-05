import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameAdvancedListener implements ActionListener {

	private GameWorld world;
	private GameFrame frame;

	public GameAdvancedListener(GameFrame frame) {
		// TODO Auto-generated constructor stub.
		this.frame=frame;
		this.world = frame.getWorld();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub.
		this.world = this.frame.getWorld();
		this.world.timePassed();
	}

}

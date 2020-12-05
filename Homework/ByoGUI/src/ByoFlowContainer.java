import java.awt.Graphics2D;
import java.util.ArrayList;

public class ByoFlowContainer extends ByoContainer {
//	ArrayList<ByoComponent> childs = new ArrayList<ByoComponent>();

//	public ByoFlowContainer(ByoButton button) {
//		this.buttons.add(button);
//		this.computeChildPositions(button);
//	}
//	
	@Override
	public int getPreferredWidth() {
		int sum = 0;
		for (ByoComponent flow : childs) {
			sum += flow.getPreferredWidth();
		}
		return sum;
	}

	@Override
	public int getPreferredHeight() {
		int max = 0;
		for (ByoComponent flow : childs) {
			if (flow.getPreferredWidth() > max) {
				max = flow.getPreferredHeight();
			}

		}
		return max;
	}

	@Override
	public void computeChildPositions() {
		// TODO Auto-generated method stub
		int[] xCoor = new int[this.childs.size()];
		int sum = this.getX();
		for (int i = 0; i < this.childs.size(); i++) {
			xCoor[i] = sum;
			sum += this.childs.get(i).getPreferredWidth();
		}
		for (int i = 0; i < childs.size(); i++) {
			childs.get(i).setPosition(xCoor[i], this.getY(), this.childs.get(i).getPreferredWidth(),
					this.childs.get(i).getPreferredHeight());
		}

	}

//	@Override
//	public void computeChildPositions(ByoComponent child) {
//		// TODO Auto-generated method stub
//		
//	}

}

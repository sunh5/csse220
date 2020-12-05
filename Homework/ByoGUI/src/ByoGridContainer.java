
public class ByoGridContainer extends ByoContainer {
	private int row;
	private int col;
	private int prefWidth;
	private int prefHeight;

	public ByoGridContainer(int i, int j, int r, int c) {
		this.prefWidth = i;
		this.prefHeight = j;
		this.row = r;
		this.col = c;
	}

	@Override
	public int getPreferredWidth() {
		// TODO Auto-generated method stub
		return this.prefWidth;
	}

	@Override
	public int getPreferredHeight() {
		// TODO Auto-generated method stub
		return this.prefHeight;
	}

	@Override
	public void computeChildPositions() {

		int ind = 0;
		for (int i = 0; i < this.col; i++) {
			for (int j = 0; j < this.row; j++) {
				this.childs.get(ind).setPosition((this.getX() + (this.getPreferredWidth() / this.col) * i),
						(this.getY() + (this.getPreferredHeight() / this.row) * j), this.getPreferredWidth() / this.col,
						this.getPreferredHeight() / this.row);
				ind++;
			}
		}
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 3; j++) {
//				this.childs.get(ind).setPosition((this.getX() + (this.prefWidth / this.col)*j),
//						(this.getY() + (this.prefHeight / this.row)*i), this.prefWidth / this.col,
//						this.prefHeight / this.row);
//				ind++;
//			}
//		}
	}
}

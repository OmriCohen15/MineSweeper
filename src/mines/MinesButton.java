package mines;

import javafx.scene.control.Button;

// button class, given i and j //
public class MinesButton extends Button {
	private int i, j;
	private boolean rightClick = true;

	public MinesButton(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public boolean isRightClick() {
		return rightClick;
	}

	public void setRightClick(boolean rightClick) {
		this.rightClick = rightClick;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}

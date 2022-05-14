package mines;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Mines {
	private int height, width;
	private Place mat[][];
	private boolean isFirst = true; //Flag indicate if its the first call to open in order to initialize all the places neighbores
	private boolean showAll=false;
	
	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		mat = new Place[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mat[i][j] = new Place();
			}
		}
		//The loop will add mines randomly to the matrix.
		for(int j=0; j<numMines; j++) {
			Random rand = new Random();
			List<Point> arr = new ArrayList<>();
			int x = rand.nextInt(height-1);
			int y = rand.nextInt(width-1);
			if((arr.contains(new Point(x, y))))
				j--;
			else
				arr.add(new Point(x, y));
			addMine(x, y);
		}
	}
	
	
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	private class Place{
		private boolean isOpen = false; //flag to indicate if the place is open
		private boolean isMine = false; //flag to indicate if the place have mine
		private boolean isFlag = false; //flag to indicate if the place have flag
		private int numOfMines = 0;
		private List<Point> arrNeighbors = new ArrayList<>(); //list contains all the neighbors 
		private String c = ".";
		
		public Place() {
		}
		
		private void myNeighbors(int i, int j){ //Method will init list of points represent all the current place neighbors
			int x,y;
			for (x=i-1; x <= i+1; x++) {
				for (y=j-1; y <= j+1; y++) {
					if(x>=0 && y>=0 && x<height && y<width) {
						if(!(x==i && y==j)) { //If the curren neighbore is the current place dont add it
							arrNeighbors.add(new Point(x, y));
							/*Run over the place's neighbors and checks if they are mines, 
							if so incrreament the counter of mines around the place*/
							if(mat[x][y].isMine)
								mat[i][j].numOfMines++;
						}
					}
					
				}
			}
		}
		
		@Override
		public String toString() {
			if(!showAll)
				return c;
			else if(isMine)
				return "X";
			else if(numOfMines != 0)
				return numOfMines + "";
			else 
				return " ";
			
		}
	}
	
	private class Point{ //Class represents point (i, j)
		private int i,j;
		
		private Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		private int getI() {
			return i;
		}

		private int getJ() {
			return j;
		}
		
	}
	
	private boolean isInMatrix(int i, int j) { //The function checks if i and j are in boundries.
		if(i>=0 && j>=0 && i<height && j<width) return true;
		return false;
	}
	
	public boolean addMine(int i, int j) {
		if(!isInMatrix(i, j)) return false; //Return false in case the place is not in matrix or there are too many mines
		mat[i][j].isMine = true; 
		return true;
	}
	
	private void initNeighbors() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mat[i][j].myNeighbors(i, j);
			}
		}	
	}
	
	public boolean open(int i, int j) {
		if(isFirst) {
			initNeighbors();
			isFirst = false;
		}
		if(mat[i][j].isMine) return false; //If the place a mine, cant open it and return false
		else if(mat[i][j].isOpen) return true; //If the place already opened
		else if(mat[i][j].isFlag) return true; //If the place is flag, do nothing
			
		else {
			mat[i][j].isOpen = true;

			if (mat[i][j].numOfMines == 0) {
				for (Point point : mat[i][j].arrNeighbors) //If there are no mines around the place recursively opens his neighbors 
					open(point.getI(), point.getJ());
			}	
		}
		//Changes the string value of the place according to the terms
		if(mat[i][j].isMine) mat[i][j].c = "X";
		else if (mat[i][j].numOfMines == 0) mat[i][j].c = " ";
		else mat[i][j].c = "" + mat[i][j].numOfMines;
		return true;
	}
	
	public void toggleFlag(int x, int y) {
		if(!mat[x][y].isOpen) { //If the place already open, dont change the flage value
			mat[x][y].isFlag = !mat[x][y].isFlag;
			if(mat[x][y].isFlag)
				mat[x][y].c = "F";
			else {
				mat[x][y].c = ".";
			}
		}
	}
	
	public boolean isDone() {
		for (Place[] row : mat) {  //Checks if all the places in matrix are open (not including mines)
			for (Place element : row) {
				if(!element.isMine && !(element.isOpen))
					return false;	
			}
		}
		return true;
	}
	
	public String get(int i, int j) {
		return mat[i][j].toString();
	}
	
	@Override
	public String toString() {
		int i=0, j=0;
		StringBuilder st = new StringBuilder();
		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
				st.append(get(i ,j));
			}
			st.append("\n");
		}	
		return st.toString();
	}
	
	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	public static void main(String[] args) {
		Mines m = new Mines(3, 4, 0);
		m.addMine(0, 1);
		m.addMine(2, 3);
		m.open(2, 0);
		System.out.println(m);
		m.toggleFlag(0, 1);
		System.out.println(m);
	}
}
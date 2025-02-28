// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

public class TetrisGrid {

	private boolean[][] grid;
	private int width;
	private int height;

	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
		this.width = grid.length;
		this.height = grid[0].length;
	}


	/**
	 * Does row-clearing on the grid.
	 */
	public void clearRows() {
		int rowsCleared = 0;
		for (int y = 0; y < height; y++) {
			if (isRowFull(y)) {
				clearRow(y);
				rowsCleared++;
				y--;
			}
		}
	}

	private boolean isRowFull(int y) {
		for (int x = 0; x < width; x++) {
			if (!grid[x][y]) {
				return false;
			}
		}
		return true;
	}

	private void clearRow(int clearedRowY) {
		for (int y = clearedRowY + 1; y < height; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y - 1] = grid[x][y];
			}
		}

		for (int x = 0; x < width; x++) {
			grid[x][height - 1] = false;
		}
	}

	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid;
	}

	public static void main(String[] args) {
		boolean[][] grid1 = new boolean[][] {
				{false, false, false},
				{true, false, true},
				{true, true, false}
		};
		TetrisGrid tetrisGrid1 = new TetrisGrid(grid1);
		tetrisGrid1.clearRows();
		System.out.println("Test Case 1:");
		printGrid(tetrisGrid1.getGrid());

	}

	private static void printGrid(boolean[][] grid) {
		for (int y = grid[0].length - 1; y >= 0; y--) { // Print from top to bottom
			for (int x = 0; x < grid.length; x++) {
				System.out.print((grid[x][y] ? "true  " : "false ") );
			}
			System.out.println();
		}
	}
}
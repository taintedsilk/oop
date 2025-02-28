public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}

	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int minRow = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxCol = Integer.MIN_VALUE;
		boolean found = false;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == ch) {
					found = true;
					minRow = Math.min(minRow, i);
					maxRow = Math.max(maxRow, i);
					minCol = Math.min(minCol, j);
					maxCol = Math.max(maxCol, j);
				}
			}
		}

		if (!found) {
			return 0;
		}

		return (maxRow - minRow + 1) * (maxCol - minCol + 1);
	}

	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int count = 0;
		int rows = grid.length;
		int cols = grid[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (isPlus(i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	private boolean isPlus(int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
			return false;
		}
		char centerChar = grid[r][c];
		int upLength = getBranchLength(r, c, -1, 0, centerChar);
		int downLength = getBranchLength(r, c, 1, 0, centerChar);
		int leftLength = getBranchLength(r, c, 0, -1, centerChar);
		int rightLength = getBranchLength(r, c, 0, 1, centerChar);

		if (rightLength >= 1 &&
                upLength == downLength && downLength == leftLength && leftLength == rightLength) {
			return true;
		}
		return false;
	}

	private int getBranchLength(int r, int c, int dr, int dc, char centerChar) {
		int length = 0;
		int currentRow = r + dr;
		int currentCol = c + dc;
		while (currentRow >= 0 && currentRow < grid.length && currentCol >= 0 && currentCol < grid[0].length && grid[currentRow][currentCol] == centerChar) {
			length++;
			currentRow += dr;
			currentCol += dc;
		}
		return length;
	}

	public static void main(String[] args) {
		// Test charArea
		char[][] grid1 = {
				{'a', 'b', 'c', 'd'},
				{'a', ' ', 'c', 'b'},
				{'x', 'b', 'c', 'a'}
		};
		CharGrid charGrid1 = new CharGrid(grid1);
		System.out.println("charArea tests:");
		System.out.println("Area of 'a': " + charGrid1.charArea('a'));
		System.out.println("Area of 'c': " + charGrid1.charArea('c'));
		System.out.println("Area of 'b': " + charGrid1.charArea('b'));
		System.out.println("Area of 'd': " + charGrid1.charArea('d'));
		System.out.println("Area of 'x': " + charGrid1.charArea('x'));
		System.out.println("Area of ' ': " + charGrid1.charArea(' '));
		System.out.println("Area of 'z': " + charGrid1.charArea('z'));

		System.out.println("------------------");

		// Test countPlus
		char[][] grid2 = {
				{' ', ' ', 'p', ' ', ' ', ' '},
				{' ', ' ', 'p', ' ', ' ', ' '},
				{'p', 'p', 'p', 'p', 'p', ' '},
				{' ', ' ', 'p', ' ', ' ', ' '},
				{' ', ' ', 'p', ' ', 'k', ' '},
				{'z', 'z', 'z', 'k', 'k', 'k'},
				{' ', ' ', 'x', 'x', 'k', 'y'}
		};
		CharGrid charGrid2 = new CharGrid(grid2);
		System.out.println("countPlus tests:");
		System.out.println("Count of '+': " + charGrid2.countPlus());
	}
}
package game.maps;


/**
 * This class represents a map of the game, which is a grid-world labeled with rows and columns.
 * Every map in FEH is 8 X 6
 */
public abstract class Map {
	private enum Tile {
		NON_TILE, TILE, TREE, DEFENSE, BLOCK_1, BLOCK_2;
	}
	private static final int ROW = 8;
	private static final int COL = 6;
	protected static final int[][] NONE = {};
	
	private final Tile[][] tiles;
	
	/**
	 * Creates a new map given array of coordinates for each of the special tiles (tiles that are not plain field).
	 * Each array should be composed of coordinate pairs [x, y]
	 * @param nonTiles
	 * @param trees
	 * @param defenses
	 * @param block1s
	 * @param block2s
	 */
	protected Map(int[][] nonTiles, int[][] trees, int[][] defenses, int[][] block1s, int[][] block2s)  {
		tiles = new Tile[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				tiles[i][j] = Tile.TILE;
			}
		}
		
		setTiles(nonTiles, Tile.NON_TILE);
		setTiles(trees, Tile.TREE);
		setTiles(defenses, Tile.DEFENSE);
		setTiles(block1s, Tile.BLOCK_1);
		setTiles(block2s, Tile.BLOCK_2);
	}
	
	/**
	 * Helper method for the constructor, sets all tiles in the given coordinates to t
	 * @param coordinates
	 * @param t
	 */
	private void setTiles(int[][] coordinates, Tile t) {
		for (int[] coordinate : coordinates) {
			tiles[coordinate[0]][coordinate[1]] = t;
		}
	}
	
	/**
	 * Given the row and column, returns whether a unit can stand on it
	 * @param row
	 * @param col
	 * @param isFlier whether the unit can fly
	 * @return True iff the location is a tile, tree, or defense, or if the location is non-tile but the unit is
	 * a flier. Returns false otherwise, including when the point is outside the map.
	 */
	public boolean canStand(int row, int col, boolean isFlier) {
		if (row < 0 || row >= 8 || col < 0 || col >= 6) {
			return false;
		}
		
		Tile t = tiles[row][col];
		return t == Tile.TILE || t == Tile.TREE || t == Tile.DEFENSE || isFlier && t == Tile.NON_TILE; 
	}
}

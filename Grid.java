import java.util.ArrayList;

public class Grid {

	public static enum STATE { PLAY, PAUSE };
	
	private static ArrayList<ArrayList<Cell>> population = new ArrayList<ArrayList<Cell>>();
	private static int numRows = 50;
	private static int numCols = 50;
	private static STATE state = STATE.PAUSE;
	
	/**
	 * @return the state
	 */
	public static STATE getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public static void setState(STATE state) {
		Grid.state = state;
	}

	/**
	 * @return population of all cells
	 */
	public static ArrayList<ArrayList<Cell>> getPopulation() {
		return population;
	}

	/**
	 * @return the numRows
	 */
	public static int getNumRows() {
		return numRows;
	}

	/**
	 * @return the numCols
	 */
	public static int getNumCols() {
		return numCols;
	}
	
	public static void initGrid() {
		Grid.loadCells();
		Grid.initAlive();
	}
	
	/**
	 * Load the cells into the grid
	 */
	public static void loadCells() {
		// Loop through every grid location to place a cell (numbered from top-left to bottom-right zero-indexed - top left is [0,0])
		int rowCount = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				population.add(new ArrayList<Cell>());
				population.get(rowCount).add(new Cell(i, j));
			}
			rowCount++;
		}
	}
	
	/**
	 * Declare an initial state of alive cells
	 */
	public static void initAlive() {
		
		// Simple Glider
		
		population.get(5).get(5).setAlive(true);
		population.get(6).get(6).setAlive(true);
		population.get(4).get(7).setAlive(true);
		population.get(5).get(7).setAlive(true);
		population.get(6).get(7).setAlive(true);

		
		population.get(8).get(8).setAlive(true);
		population.get(9).get(9).setAlive(true);
		population.get(7).get(10).setAlive(true);
		population.get(8).get(10).setAlive(true);
		population.get(9).get(10).setAlive(true);
		
		
		// Square
		/*
		population.get(15).get(15).setAlive(true);
		population.get(15).get(16).setAlive(true);
		population.get(16).get(15).setAlive(true);
		population.get(16).get(16).setAlive(true);
		*/
		
		// Repeating Bar
		population.get(2).get(3).setAlive(true);
		population.get(2).get(4).setAlive(true);
		population.get(2).get(5).setAlive(true);
		
	}
	
}

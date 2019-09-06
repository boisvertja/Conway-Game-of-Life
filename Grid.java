package logic;
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
	public static STATE getState() { return state; }

	/**
	 * @param state the state to set
	 */
	public static void setState(STATE state) { Grid.state = state; }

	/**
	 * @return population of all cells
	 */
	public static ArrayList<ArrayList<Cell>> getPopulation() { return population; }

	/**
	 * @return the numRows
	 */
	public static int getNumRows() { return numRows; }

	/**
	 * @return the numCols
	 */
	public static int getNumCols() { return numCols; }
	
	/**
	 * Initialize the grid's state
	 */
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
				population.get(rowCount).add(new Cell(j, i));
			}
			rowCount++;
		}
	}
	
	/**
	 * Declare an initial state of alive cells
	 */
	public static void initAlive() {
		
		// Paul Callahan's 5x5 infinite growth
		population.get(25).get(15).setAlive(true);
		population.get(25).get(16).setAlive(true);
		population.get(25).get(17).setAlive(true);
		population.get(25).get(19).setAlive(true);
		population.get(26).get(15).setAlive(true);
		population.get(27).get(18).setAlive(true);
		population.get(27).get(19).setAlive(true);
		population.get(28).get(16).setAlive(true);
		population.get(28).get(17).setAlive(true);
		population.get(28).get(19).setAlive(true);
		population.get(29).get(15).setAlive(true);
		population.get(29).get(17).setAlive(true);
		population.get(29).get(19).setAlive(true);
	}
	
}

import java.util.ArrayList;

public class Cell {
	
	private boolean alive = false;
	private int x;
	private int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param Set the cell's current alive status
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean currentAliveStatus() {
		return alive;
	}
	
	/**
	 * 
	 * @return whether or not the cell is alive
	 */
	public boolean isAlive() {
		// Populate an ArrayList with positions of eight surrounding cells
		int nearbyAliveCells = 0;
		
		// Loop through every cell to search for cells whose positions match the positions surrounding the current cell
		ArrayList<ArrayList<Cell>> population = Grid.getPopulation();
		
		// For every row...
		for (ArrayList<Cell> row : population) {
			
			// For every cell in that row...
			for (Cell cell : row) {
				int x = cell.x;
				int y = cell.y;
				
				// Make sure not including the cell itself
				if (x == this.x && y == this.y) continue;
				
				// If the current cell is within one 'x' coordinate of the main cell...
				if (x == this.x - 1 || x == this.x || x == this.x + 1)
					
					// If the current cell is within one 'y' coordinate of the main cell...
					if (y == this.y - 1 || y == this.y || y == this.y + 1)
						
						// If alive, found an alive surrounding cell
						if (cell.currentAliveStatus())
							nearbyAliveCells++;
			}
		}
		
		
		// Determine if the number of surrounding cells allows the cell to be alive
		if (!alive && nearbyAliveCells == 3) // New birth
			return (alive = true);
		
		if (alive == true && nearbyAliveCells <= 1) // Death by isolation
			return (alive = false);
		
		if (alive == true && nearbyAliveCells >= 4) // Death by overcrowding
			return (alive = false);
		
		if (alive == true && (nearbyAliveCells == 2 || nearbyAliveCells == 3)) // Survival
			return (alive = true);
		else
			return (alive = false);
	}
}

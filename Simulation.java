package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.Cell;
import logic.Grid;

public class Simulation extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public Simulation() {
		setPreferredSize(new Dimension(Display.WIDTH, Display.HEIGHT));
		setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Light up cells that are alive [green if playing, blue if paused]
		if (Grid.getState() == Grid.STATE.PLAY)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.CYAN);
		
		// Loop through each row in the two-dimensional grid and light-up each cell that is alive
		ArrayList<ArrayList<Cell>> population = Grid.getPopulation();
		
		for (ArrayList<Cell> row : population)
			for (Cell cell : row)
				if (cell.isAlive())
					
					// If the cell is alive, draw it
					g.fillRect(cell.getX() * 10, cell.getY() * 10, 10, 10); // Use a hashtable to quickly navigate to only the ones that are alive rather than looping through every one
		
		try {
			Thread.sleep(5);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		drawGrid(g);
		
		// Update the alive status for every cell for the next generation's display
		if (Grid.getState() == Grid.STATE.PLAY)
			Cell.updateGeneration();
		
		repaint();
	}
	
	/**
	 * Draw grid
	 * @param g
	 */
	protected void drawGrid(Graphics g) {
		// Reset line color and redraw grid
		g.setColor(Color.GRAY);
				
		// Draw rows
		int currNumRow = 0;
		for (int i = 0; i < Grid.getNumRows(); i++) {
			g.drawLine(0, Display.HEIGHT / Grid.getNumRows() * currNumRow, Display.WIDTH, Display.HEIGHT / Grid.getNumRows() * currNumRow);
			currNumRow++;
		}
				
		// Draw columns
		int currNumCol = 0;
		for (int i = 0; i < Grid.getNumCols(); i++) {
			g.drawLine(Display.WIDTH / Grid.getNumCols() * currNumCol, 0, Display.WIDTH / Grid.getNumCols() * currNumCol, Display.HEIGHT);
			currNumCol++;
		}
		g.drawLine(Display.WIDTH / Grid.getNumCols() * currNumCol + 1, 0, Display.WIDTH / Grid.getNumCols() * currNumCol + 1, Display.HEIGHT);
	}
	
}

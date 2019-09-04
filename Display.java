import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Display extends JPanel {

	private static JFrame frame;
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 505;
	private static final int HEIGHT = 500;
	
	public Display() {
		frame = new JFrame();
		setBackground(Color.BLACK);
		frame.getContentPane().add(this);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Game of Life");
		frame.setLocationRelativeTo(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Light up cells that are alive
		g.setColor(Color.GREEN);		
		
		// Loop through each row in the two-dimensional array and light-up each cell that is alive
		ArrayList<ArrayList<Cell>> population = Grid.getPopulation();
		
		for (ArrayList<Cell> row : population)
			for (Cell cell : row)
				if (cell.isAlive())
					
					// Light-up the position of the cell using its position attribute
					g.fillRect(cell.getX() * 10, cell.getY() * 10, 10, 10);
		
		try {
			Thread.sleep(5);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		drawGrid(g);
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
			g.drawLine(0, HEIGHT / Grid.getNumRows() * currNumRow, WIDTH, HEIGHT / Grid.getNumRows() * currNumRow);
			currNumRow++;
		}
				
		// Draw columns
		int currNumCol = 0;
		for (int i = 0; i < Grid.getNumCols(); i++) {
			g.drawLine(WIDTH / Grid.getNumCols() * currNumCol, 0, WIDTH / Grid.getNumCols() * currNumCol, HEIGHT);
			currNumCol++;
		}
	}
	
}

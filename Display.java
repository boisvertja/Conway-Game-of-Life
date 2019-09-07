package display;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import logic.Cell;
import logic.Grid;

public class Display implements KeyListener, MouseListener {

	public static final int WIDTH = 517;
	public static final int HEIGHT = 540;
	private static JFrame frame;
	private static Simulation sim;
	private static Menu menu;
	
	public Display() {
		frame = new JFrame();
		sim = new Simulation();
		menu = new Menu();
		
		frame.setBackground(Color.BLACK);
		frame.getContentPane().add(sim, BorderLayout.CENTER);
		frame.getContentPane().add(menu, BorderLayout.PAGE_START);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Life");
		frame.setLocationRelativeTo(null);
		frame.pack();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		// Exit the application
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Change the 'aliveNextGen' value of the selected cell
		int mouseX = e.getX() - 8;
		int mouseY = e.getY() - 80;
		
		// Locate the cell whose pixel-position contains the mouse's click location - for now, loop through every cell
		ArrayList<ArrayList<Cell>> population = Grid.getPopulation();
		for (ArrayList<Cell> row : population)
			for (Cell cell : row) 
				
				// Determine if the cell contains the mouse's position
				if (mouseX > cell.getX() * 10 && mouseX <= cell.getX() * 10 + 10)
					if (mouseY > cell.getY() * 10 && mouseY <= cell.getY() * 10 + 10)
						 
						// Make it so the user can alternate the cell's 'alive' attribute by repeatedly selecting it
						if (cell.isAlive()) {							
							cell.setAlive(false);	
							cell.setAliveNextGen(false);
						}
						else {
							cell.setAlive(true);
							cell.setAliveNextGen(true);
						}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}

package display;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Display implements KeyListener {

	public static final int WIDTH = 505;
	public static final int HEIGHT = 500;
	private static JFrame frame;
	private static Simulation simMenu;
	
	public Display() {
		frame = new JFrame();
		simMenu = new Simulation();
		
		frame.setBackground(Color.BLACK);
		frame.getContentPane().add(simMenu);
		frame.addKeyListener(this);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Game of Life");
		frame.setLocationRelativeTo(null);
		frame.pack();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		// Play the simulation
		if (e.getKeyCode() == KeyEvent.VK_P && Grid.getState() == Grid.STATE.PAUSE)
			Grid.setState(Grid.STATE.PLAY);
		
		// Pause the simulation
		else if (e.getKeyCode() == KeyEvent.VK_P && Grid.getState() == Grid.STATE.PLAY)
			Grid.setState(Grid.STATE.PAUSE);
		
		// Reset the simulation
		else if (e.getKeyCode() == KeyEvent.VK_R) {
			Grid.setState(Grid.STATE.PAUSE);
			Grid.getPopulation().clear();
			Grid.initGrid();
			Grid.initAlive();
		}
		
		// Exit the application
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}

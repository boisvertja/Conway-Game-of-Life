package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Cell;
import logic.Grid;

public class Menu extends JPanel {
	// Has tabs to select statistics to display (generation, number of alive cells, etc.)
	// Also may have additional tabs for other information / tools
	
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 50;
	private static JLabel generationCount;
	private static JLabel populationCount;
	private static JButton play;
	private static JButton pause;
	private static JButton reset;
	private static JButton positions;
	
	public Menu() {
		setPreferredSize(new Dimension(Display.WIDTH, HEIGHT));
		setBackground(Color.LIGHT_GRAY);
			
		// Set to null in order to place labels at any (x,y) panel position
		this.setLayout(null);
		
		JLabel stats = new JLabel();
		stats.setText("");
		stats.setBounds(1, 1, Display.WIDTH - 100, HEIGHT - 2);
		this.add(stats);
		
		JLabel generationLabel = new JLabel("Generation:");
		generationLabel.setBounds(1, -4, 70, 20);
		this.add(generationLabel);
		
		JLabel populationLabel = new JLabel("Population:");
		populationLabel.setBounds(1, 9, 70, 20);
		this.add(populationLabel);
		
		generationCount = new JLabel();
		generationCount.setText("0");
		generationCount.setBounds(70, -4, Display.WIDTH, 20);
		this.add(generationCount);
		
		populationCount = new JLabel();
		populationCount.setText(String.valueOf(Grid.getPopulation().size()));
		populationCount.setBounds(70, 11, Display.WIDTH, 20);
		this.add(populationCount);
		
		// PLAY =======================================================
		play = new JButton();
		play.setText("Play");
		play.setBounds(1, 28, 70, 22);
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Grid.setState(Grid.STATE.PLAY);	
			}
		});
		this.add(play);
		
		// PAUSE =======================================================		
		pause = new JButton();
		pause.setText("Pause");
		pause.setBounds(75, 28, 70, 22);
		pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Grid.setState(Grid.STATE.PAUSE);
			}
		});
		this.add(pause);
		
		// RESET =======================================================
		reset = new JButton();
		reset.setText("Reset");
		reset.setBounds(149, 28, 70, 22);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Reset the simulation
				Grid.setState(Grid.STATE.PAUSE);
				Grid.getPopulation().clear();
				Grid.initGrid();
				Grid.initAlive();
			}
		});
		this.add(reset);
		
		// POSITIONS =======================================================
		positions = new JButton();
		positions.setText("Positions");
		positions.setBounds(223, 28, 87, 22);
		positions.addActionListener(new ActionListener() {

			// Record positions of all live cells to easily input into 'initAlive' method
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ArrayList<Cell>> population = Grid.getPopulation();
				System.out.println("Copy / Paste output below into Grid class's initAlive()");
				
				for (int i = 0; i < population.size(); i++) {
					for (int j = 0; j < population.get(i).size(); j++) {
						if (population.get(i).get(j).isAlive())
							System.out.println("population.get(" + i + ").get(" + j + ").setAlive(true);");
					}
				}
			}
		});
		this.add(positions);
	}
	
	/**
	 * Update the labels in the statistics bar
	 */
	public static void updateStats() {
		generationCount.setText(String.valueOf(Cell.getGeneration()));
		populationCount.setText(String.valueOf(Cell.getNumAlive()));
	}
}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class MapPanel extends Canvas {

	private static final long serialVersionUID = 1L;
	private MapObject[][] map;
	private final static int GRID_SIZE = 5;
	private List<Point> solutionSet;
	
	/**
	 * Creates a new panel with the given map.
	 * @param map A 2D array of MapObjects to use.
	 */
	public MapPanel(MapObject[][] map) {
		this.map = map.clone();
	}
	
	/**
	 * Add a list of points showing the solution to the array.
	 * @param solution The solution set (of points) to display.
	 */
	public void addSolution(List<Point> solution) {
		solutionSet = solution;
	}
	
	public void update(Graphics g) {
		super.update(g);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				if (map[i][j].isVisited()) {
					g2.setColor(Color.blue);
				}
				else {
					g2.setColor(map[i][j].getColor());
				}
				g2.fillRect(i*GRID_SIZE, j*GRID_SIZE, GRID_SIZE, GRID_SIZE);
			}
		}
		
		g2.setColor(Color.yellow);
		if (solutionSet == null)
			return;
		for (Point p : solutionSet) {
			g2.fillRect(p.x*GRID_SIZE, p.y*GRID_SIZE, GRID_SIZE, GRID_SIZE);
		}
	}
}

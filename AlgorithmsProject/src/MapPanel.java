import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
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
		paint(g);
	}
	
	public void paint(Graphics g) {
		Image image = createImage(this.getSize().width, this.getSize().height);
		Graphics imageGraphics = image.getGraphics();
		
		imageGraphics.setColor(getBackground());
		imageGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
		imageGraphics.setColor(getForeground());
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				if (map[i][j].isVisited()) {
					imageGraphics.setColor(Color.blue);
				}
				else {
					imageGraphics.setColor(map[i][j].getColor());
				}
				imageGraphics.fillRect(i*GRID_SIZE, j*GRID_SIZE, GRID_SIZE, GRID_SIZE);
				
			}
		}
		g.drawImage(image, 0, 0, this);
	}
}

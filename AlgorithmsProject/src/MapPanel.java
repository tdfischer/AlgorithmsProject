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
  
  private int pixels_per_grid = GRID_SIZE;
  /**
   * Creates a new panel with the given map.
   * @param map A 2D array of MapObjects to use.
   */
  public MapPanel(MapObject[][] map, int pixels_per_grid) {
  	this.map = map.clone();
  	if (pixels_per_grid == 0)
  		this.pixels_per_grid = GRID_SIZE;
  	else
  		this.pixels_per_grid = pixels_per_grid;
  }
  
  public void update(Graphics g) {
  	paint(g);
  }
  
  public void paint(Graphics g) {
  	for (int i = 0; i < map.length; ++i) {
  		for (int j = 0; j < map[0].length; ++j) {
  		  g.setColor(map[i][j].getColor());
  			g.fillRect(i*pixels_per_grid, j*pixels_per_grid, pixels_per_grid, pixels_per_grid);
        if (map[i][j].isVisited()) {
          g.setColor(new Color(0,0,255,96));
          g.fillRect(i*pixels_per_grid, j*pixels_per_grid, pixels_per_grid, pixels_per_grid);
        }
  		}
  	}
  }
}

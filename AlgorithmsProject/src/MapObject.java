import java.awt.Color;

/**
 * Base class for the different objects that can appear on a map. Note that the visited() property can only be changed once!
 * Objects can have defined colors for each. The default color is <b><i>magenta</i></b> to show that something is wrong.
 * @author Ian Palencar
 *
 */
public class MapObject implements Comparable<MapObject> {
	
	protected boolean isPathable;
	protected String name;
	protected boolean isWall;
	protected boolean isVisited = false;
  protected boolean isConsidered = false;
	protected Color color = new Color(255, 0, 255);
	protected Point location;
  public double g_score = Double.MAX_VALUE;
  public double h_score = Double.MAX_VALUE;
  public double f_score = Double.MAX_VALUE;
	
	public MapObject() {
		isPathable = false;
		name = "MapObject";
	}
	
	public void setPoint(Point p) {
		location = p;
	}
	
	public Point getPoint() {
		return location;
	}
	
	/**
	 * Returns whether a given object is pathable (passable)
	 * @return
	 */
	public boolean isPathable() {
		return isPathable;
	}

  public void consider() {
    isConsidered = true;
  }
  /**
    * Returns true if a node was considered (compared at all in the open set)
    * @returns true if considered
    */
  public boolean isConsidered() {
    return isConsidered;
  }
	
	/**
	 * Visit an object.
	 */
	public void visit() {
		isVisited = true;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

  public boolean isWall() {
    return isWall;
  }
	
	public Color getColor() {
		return color;
	}
	
	public String toString() {
		return "["+name
				+":isPathable-"
				+(isPathable?"YES":"NO")
				+":isWall-"
				+(isWall?"YES":"NO")
        +"location: ("+location.x+","+location.y+")"
				+"]";
	}

  public int compareTo(MapObject o) {
    if (f_score < o.f_score) {
        return -1;
    }
    if (f_score > o.f_score) {
        return 1;
    }
    return 0;
  }
}

import java.awt.Color;

/**
 * Base class for the different objects that can appear on a map. Note that the visited() property can only be changed once!
 * Objects can have defined colors for each. The default color is <b><i>magenta</i></b> to show that something is wrong.
 * @author Ian Palencar
 *
 */
public class MapObject {
	
	protected boolean isPathable;
	protected String name;
	protected boolean isWall;
	protected boolean isVisited;
	protected Color color = Color.MAGENTA;
	
	public MapObject() {
		isPathable = false;
		name = "MapObject";
	}
	
	/**
	 * Returns whether a given object is pathable (passable)
	 * @return
	 */
	public boolean isPathable() {
		return isPathable;
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
	
	public Color getColor() {
		return color;
	}
	
	public String toString() {
		return "["+name
				+":isPathable-"
				+(isPathable?"YES":"NO")
				+":isWall-"
				+(isWall?"YES":"NO")
				+"]";
	}
}

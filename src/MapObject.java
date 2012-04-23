import java.awt.Color;

/**
 * Base class for the different objects that can appear on a map. Note that the visited() property can only be changed once!
 * Objects can have defined colors for each. The default color is <b><i>magenta</i></b> to show that something is wrong.
 */
public class MapObject implements Comparable<MapObject> {

    protected boolean isPathable;
    protected String name;
    protected boolean isWall;
    protected boolean isVisited = false;
    protected boolean isConsidered = false;
    protected Color color = new Color(255, 0, 255);
    protected Point location;
    protected MapObject parent;
    public double g_score = Double.MAX_VALUE;
    public double h_score = Double.MAX_VALUE;

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

    public double distanceTo(MapObject other) {

        return Math.sqrt(Math.pow((other.location.x - location.x),2) + (Math.pow((other.location.y - location.y),2)));

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
        if (g_score + h_score < o.g_score + o.h_score) {
            return -1;
        }
        if (g_score + h_score > o.g_score + o.h_score) {
            return 1;
        }
        return 0;
    }
}

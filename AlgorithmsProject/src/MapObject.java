import java.awt.Color;


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
	
	public boolean isPathable() {
		return isPathable;
	}
	
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

/**
 * Drop dead simple Point class, for the list saving of solutions.
 * @author Ian Palencar
 *
 */
public class Point {
	int x;
	int y;
	
	/**
	 * Create a point with the given coordinates.
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "["+this.getClass()+":"+x+","+y+"]";
	}
}

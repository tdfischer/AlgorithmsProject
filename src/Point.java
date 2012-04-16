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

  public Point(Point o) {
    x = o.x;
    y = o.y;
  }

  public int distance(Point o) {
    return (int)Math.floor(Math.sqrt(Math.pow((x+o.x),2)+Math.pow((y+o.y),2)));
  }
  
  public String toString() {
  	return "["+this.getClass()+":"+x+","+y+"]";
  }
}

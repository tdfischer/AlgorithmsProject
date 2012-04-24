/**
  * The validity of this class is obviously suspect and
  * as a result should be used by nobody ever. Duh.
  */
public class ManhattanHeuristic implements Heuristic {

  private int d = 1;

  public double calculate(MapObject start, MapObject end) {
    return (d * ((Math.abs(start.location.x-end.location.x) + (Math.abs(start.location.y-end.location.y)))));
  }
  public String getName() {
    return "Manhattan";
  }
}

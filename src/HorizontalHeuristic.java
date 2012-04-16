/**
  * The validity of this class is obviously suspect and
  * as a result should be used by nobody ever. Duh.
  */
@Mark(name="Horizontal")
public class HorizontalHeuristic implements Heuristic {

  public HorizontalHeuristic() {
  }

  public int calculate(MapObject start, MapObject end) {
    return 25*(end.location.x-start.location.x) + (end.location.y-start.location.y);
  }
}

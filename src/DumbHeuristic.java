/**
  * The validity of this class is obviously suspect and
  * as a result should be used by nobody ever. Duh.
  */
@Mark(name="Dumb")
public class DumbHeuristic implements Heuristic {

  public DumbHeuristic() {
  }

  public int calculate(MapObject start, MapObject end) {
    return -(start.location.distance(end.location));
  }
}

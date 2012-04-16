/**
  * The validity of this class is obviously suspect and
  * as a result should be used by nobody ever. Duh.
  */
@Mark(name="Test")
public class TestHeuristic implements Heuristic {

  public TestHeuristic() {
  }

  public int calculate(MapObject start, MapObject end) {
    return start.location.distance(end.location);
  }
}

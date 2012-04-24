/**
  * Interface for heuristics in A*.
  * Just implement this interface and roll your own calculate()!
  */
public interface Heuristic {
  double calculate(MapObject start, MapObject end);
  String getName();
}

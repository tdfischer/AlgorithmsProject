/**
  * Interface for heuristics in A*.
  * Just implement this interface and roll your own calculate()!
  * @author Ian Palencar
  */
public interface Heuristic {
  int calculate(MapObject start, MapObject end);
}

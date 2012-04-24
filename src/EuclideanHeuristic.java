public class EuclideanHeuristic implements Heuristic {
  public double calculate(MapObject start, MapObject end) {
    return Math.sqrt(Math.pow(end.location.y-start.location.y,2)+Math.pow(end.location.x-start.location.x,2));
  }
  public String getName() {
    return "Euclidean";
  }
}

import java.util.*;


public class AStar {

    private MapObject[][] map;
    private Heuristic heuristic;

    public AStar(MapObject[][] map, Heuristic heuristic) {

        this.map = map;
        this.heuristic = heuristic;

    }

    public ArrayList<MapObject> search(MapObject start, MapObject end) {
        return null;
    }
}

import java.util.*;


public class AStar {

    private MapObject[][] map;
    private Heuristic heuristic;

    public AStar(MapObject[][] map, Heuristic heuristic) {

        this.map = map;
        this.heuristic = heuristic;

    }

    public ArrayList<MapObject> search(MapObject start, MapObject end) {

        PriorityQueue<MapObject> openset = new PriorityQueue<MapObject>();
        // Add start to the openset.
        openset.offer(start);
        HashSet<MapObject> closedset = new HashSet<MapObject>();

        while (openset.size() > 0) {

            MapObject current = openset.poll();
            closedset.add(current);
            if (current == end) {
                // Return the path to the start and end.
                return null;
            }

        }

        return null;
    }
}

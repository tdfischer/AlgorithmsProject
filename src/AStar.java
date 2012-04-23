import java.util.*;


public class AStar {

    private MapObject[][] map;
    private Heuristic heuristic;

    public AStar(MapObject[][] map, Heuristic heuristic) {

        this.map = map;
        this.heuristic = heuristic;

    }

    public ArrayList<MapObject> search(MapObject start, MapObject end) {
        int MAP_WIDTH = map.length;
        int MAP_HEIGHT = map[0].length;

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

            // Get the neighbors of the current node.
            ArrayList<MapObject> neighbors = new ArrayList<MapObject>();
            // Up
            if (current.location.y - 1 < 0 && closedset.contains(map[current.location.x][current.location.y-1]))
                neighbors.add(map[current.location.x][current.location.y-1]);
            // Down
            if (current.location.y + 1 > MAP_WIDTH && closedset.contains(map[current.location.x][current.location.y+1))
                neighbors.add(map[current.location.x][current.location.y+1]);
            // Left
            if (current.location.x - 1 < 0 && closedset.contains(map[current.location.x-1][current.location.y]))
                neighbors.add(map[current.location.x-1][current.location.y]);
            // Right
            if (current.location.x + 1 > MAP_HEIGHT && closedset.contains(map[current.location.x+1][current.location.y]))
                neighbors.add(map[current.location.x+1][current.location.y]);

            // Check each neighbor.
        }

        return null;
    }
}

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
            for (MapObject neighbor : neighbors) {
                int cost = current.g_score + movement_cost(current, neighbor);

                // Remove neighbor from openset, because new path is better.
                if (openset.contains(neighbor) && cost <= neighbor.g_score)
                    openset.remove(neighbor);
                // Remove neighbor from closedset, because we found a better path.
                // This should never happen if the heuristic is monotonic.
                if (closedset.contains(neighbor) && cost <= neighbor.g_score))
                    closedset.remove(neighbor);
                // If the neighbor hasn't been in either, add it to open.
                if (!openset.contains(neighbor) && !closedset.contains(neighbor)) {
                    neighbor.g_score = cost;
                    openset.add(neighbor);
                    neighbor.parent = current;
                }
            }
        }

        return null;
    }

    private int movement_cost(MapObject current, MapObject neighbor) {
        return 1;
    }
}

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

        // Set g_score and h_score of all nodes.
        for(int x = 0; x < MAP_WIDTH; ++x) {
            for(int y = 0; y < MAP_HEIGHT; ++y) {
                map[x][y].g_score = Math.abs(x - start.location.x) + Math.abs(y - start.location.y);
                map[x][y].h_score = heuristic.calculate(map[x][y], end);
            }
        }

        PriorityQueue<MapObject> openset = new PriorityQueue<MapObject>();
        // Add start to the openset.
        openset.offer(start);
        HashSet<MapObject> closedset = new HashSet<MapObject>();

        while (openset.peek() != end) {

            MapObject current = openset.poll();
            //added the following line for drawing prettiness.
            current.consider();
            closedset.add(current);

            // Get the neighbors of the current node.
            ArrayList<MapObject> neighbors = new ArrayList<MapObject>();
            // Up
            if (current.location.y - 1 >= 0 && !map[current.location.x][current.location.y-1].isWall())
                neighbors.add(map[current.location.x][current.location.y-1]);
            // Down
            if (current.location.y + 1 < MAP_HEIGHT && !map[current.location.x][current.location.y+1].isWall())
                neighbors.add(map[current.location.x][current.location.y+1]);
            // Left
            if (current.location.x - 1 >= 0 && !map[current.location.x-1][current.location.y].isWall())
                neighbors.add(map[current.location.x-1][current.location.y]);
            // Right
            if (current.location.x + 1 < MAP_WIDTH && !map[current.location.x+1][current.location.y].isWall())
                neighbors.add(map[current.location.x+1][current.location.y]);

            // Check each neighbor.
            for (MapObject neighbor : neighbors) {
                double cost = current.g_score + movement_cost(current, neighbor);

                // Remove neighbor from openset, because new path is better.
                if (openset.contains(neighbor) && cost <= neighbor.g_score)
                    openset.remove(neighbor);
                // Remove neighbor from closedset, because we found a better path.
                // This should never happen if the heuristic is monotonic.
                if (closedset.contains(neighbor) && cost <= neighbor.g_score)
                    closedset.remove(neighbor);
                // If the neighbor hasn't been in either, add it to open.
                if (!openset.contains(neighbor) && !closedset.contains(neighbor)) {
                    neighbor.g_score = cost;
                    openset.add(neighbor);
                    neighbor.parent = current;
                }
            }
        }

        return reconstruct_path(end, start);
    }

    private ArrayList<MapObject> reconstruct_path(MapObject current, MapObject start) {
        ArrayList<MapObject> path = new ArrayList<MapObject>();

        if (current.parent != start) {
            path.addAll(reconstruct_path(current.parent, start));
        }
        path.add(current);
        return path;
    }

    private int movement_cost(MapObject current, MapObject neighbor) {
        return 1;
    }
}

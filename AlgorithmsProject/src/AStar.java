import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;

public class AStar {

	private MapObject[][] map;
  private Heuristic heuristic;

	public AStar(MapObject[][] map, Heuristic heuristic) {
		this.map = map;
    this.heuristic = heuristic;
	}

	public ArrayList<MapObject> search(MapObject start, MapObject end) {
		final int MAP_LENGTH = this.map.length;
		final int MAP_WIDTH = this.map[0].length;
    Point startPoint = start.getPoint();
    Point endPoint = end.getPoint();
		// The set of nodes already evalutated.
		HashSet<MapObject> closedSet = new HashSet<MapObject>();
		// The set of tentative ndoes to be evaluated, initially containing the start node.
		PriorityQueue<MapObject> openSet = new PriorityQueue<MapObject>();
		openSet.add(start);
		// The path of the navigated nodes.
		MapObject[][] came_from = new MapObject[MAP_LENGTH][MAP_WIDTH];

		// g_score: Cost from the start along the best known path.
		// h_score: Cost guessed from the heuristic.
		// f_score: Estimated total cost from start to goal through y.

    map[startPoint.x][startPoint.y].g_score = 0;
    map[startPoint.x][startPoint.y].h_score = heuristic.calculate(start, end);
    map[startPoint.x][startPoint.y].f_score = map[startPoint.x][startPoint.y].g_score + map[startPoint.x][startPoint.y].h_score;

		while (openSet.size() > 0) {
			MapObject current = openSet.poll();
			if (current == end)
				return reconstruct_path(came_from, came_from[end.location.x][end.location.y]);

			closedSet.add(current);
			ArrayList<MapObject> neighbors = new ArrayList<MapObject>();
			// Add the four possible neighbors to the neighbors list to be processed.
			if (current.location.x - 1  >= 0 && !map[current.location.x-1][current.location.y].isWall())
				neighbors.add(this.map[current.location.x - 1][current.location.y]);
			if (current.location.x + 1 < MAP_LENGTH-1 && !map[current.location.x+1][current.location.y].isWall())
				neighbors.add(this.map[current.location.x + 1][current.location.y]);
			if (current.location.y - 1  >= 0 && !map[current.location.x][current.location.y-1].isWall())
				neighbors.add(this.map[current.location.x][current.location.y - 1]);
			if (current.location.y + 1 < MAP_WIDTH-1 && !map[current.location.x][current.location.y+1].isWall())
				neighbors.add(this.map[current.location.x][current.location.y + 1]);
			// Check the neighbors for viable path.
			for (MapObject neighbor : neighbors) {
				boolean tentative_is_better = true;

				// If we've already processed that neighbor, check others.
				if (closedSet.contains(neighbor)) 
					continue;
				int tentative_g_score = map[current.location.x][current.location.y].g_score + 1;
				// If we haven't processed that neighbor, add it to be processed.	
				if (!openSet.contains(neighbor)) {
					openSet.add(neighbor);
				  map[neighbor.location.x][neighbor.location.y].h_score = heuristic.calculate(start, end);
        }
        boolean tenative_is_better = (tentative_g_score < map[neighbor.location.x][neighbor.location.y].g_score);
        if (tentative_is_better) {
          came_from[neighbor.location.x][neighbor.location.y] = current;
          map[neighbor.location.x][neighbor.location.y].g_score = tentative_g_score;
          map[neighbor.location.x][neighbor.location.y].f_score = map[neighbor.location.x][neighbor.location.y].g_score + map[neighbor.location.x][neighbor.location.y].h_score;
         }
      }
    }
    return null;
  }

  private ArrayList<MapObject> reconstruct_path(MapObject[][] came_from, MapObject current_node) {
    if (came_from[current_node.location.x][current_node.location.y] != null) {
      ArrayList<MapObject> p = reconstruct_path(came_from, came_from[current_node.location.x][current_node.location.y]);
      p.add(current_node);
      return p;
    }
    else {
      ArrayList<MapObject> p = new ArrayList<MapObject>();
      p.add(current_node);
      return p;
    }
  }
}

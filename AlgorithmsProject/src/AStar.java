import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;

public abstract class AStar {

	private MapObject[][] map;

	public AStar(MapObject[][] map) {
		this.map = map;
	}

	public ArrayList<MapObject> search(MapObject start, MapObject end, Point startPoint, Point endPoint) {
		final int MAP_LENGTH = this.map.length;
		final int MAP_WIDTH = this.map[0].length;

		// The set of nodes already evalutated.
		HashSet<MapObject> closedSet = new HashSet<MapObject>();
		// The set of tentative ndoes to be evaluated, initially containing the start node.
		PriorityQueue<MapObject> openSet = new PriorityQueue<MapObject>();
		openSet.add(start);
		// The path of the navigated nodes.
		MapObject[][] came_from = new MapObject[MAP_LENGTH][MAP_WIDTH];

		// Cost from the start along the best known path.
		int[][] g_score = new int[MAP_LENGTH][MAP_WIDTH];
		// Cost guessed from the heuristic.
		int[][] h_score = new int[MAP_LENGTH][MAP_WIDTH];
		// Estimated total cost from start to goal through y.
		int[][] f_score = new int[MAP_LENGTH][MAP_WIDTH];

		g_score[startPoint.x][startPoint.y] = 0;
		h_score[startPoint.x][startPoint.y] = this.heuristic(start, end); 
		f_score[startPoint.x][startPoint.y] = g_score[startPoint.x][startPoint.y] + h_score[startPoint.x][startPoint.y];

		while (openSet.size() > 0) {
			MapObject current = openSet.poll();
			if (current == end)
				return reconstruct_path(came_from, came_from[end.location.x][end.location.y]);

			closedSet.add(current);
			ArrayList<MapObject> neighbors = new ArrayList<MapObject>();
			// Add the four possible neighbors to the neighbors list to be processed.
			if (current.location.x - 1 > 0)
				neighbors.add(this.map[current.location.x - 1][current.location.y]);
			if (current.location.x + 1 < MAP_LENGTH)
				neighbors.add(this.map[current.location.x + 1][current.location.y]);
			if (current.location.y - 1 > 0)
				neighbors.add(this.map[current.location.x][current.location.y - 1]);
			if (current.location.y + 1 < MAP_WIDTH)
				neighbors.add(this.map[current.location.x][current.location.y + 1]);
			// Check the neighbors for viable path.
			for (MapObject neighbor : neighbors) {
				boolean tentative_is_better = true;

				// If we've already processed that neighbor, check others.
				if (closedSet.contains(neighbor)) 
					continue;
				int tentative_g_score = g_score[current.location.x][current.location.y] + 1;
				// If we haven't processed that neighbor, add it to be processed.	
				if (!openSet.contains(neighbor)) {
					openSet.add(neighbor);
					h_score[neighbor.location.x][neighbor.location.y] = heuristic(neighbor, end);
				}
				else if (tentative_g_score < g_score[neighbor.location.x][neighbor.location.y])
					tentative_is_better = true;
				else
					tentative_is_better = false;

				if (tentative_is_better) {
					came_from[neighbor.location.x][neighbor.location.y] = current;
					g_score[neighbor.location.x][neighbor.location.y] = tentative_g_score;
					f_score[neighbor.location.x][neighbor.location.y] = g_score[neighbor.location.x][neighbor.location.y] + h_score[neighbor.location.x][neighbor.location.y];
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

	// TODO : PriorityQueue Comparator.

	abstract int heuristic(MapObject start, MapObject end);
}

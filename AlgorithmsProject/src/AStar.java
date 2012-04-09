import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class AStar {

	private MapObject[][] map;
	
	public AStar(MapObject[][] map) {
		this.map = map;
	}

	public ArrayList<MapObject> search(MapObject start, MapObject end, Point startPoint, Point endPoint) {
		// The set of nodes already evalutated.
		ArrayList<MapObject> closedSet = new ArrayList<MapObject>();
		// The set of tentative ndoes to be evaluated, initially containing the start node.
		PriorityQueue<MapObject> openSet = new PriorityQueue<MapObject>();
		openSet.add(start);
		// The path of the navigated nodes.
		ArrayList<MapObject> path = new ArrayList<MapObject>();

		final int MAP_LENGTH = this.map.length;
		final int MAP_WIDTH = this.map[0].length;
		// Cost from the start along the best known path.
		int[][] g_score = new int[MAP_LENGTH][MAP_WIDTH];
		// Cost guessed from the heuristic.
		int[][] h_score = new int[MAP_LENGTH][MAP_WIDTH];
		// Estimated total cost from start to goal through y.
		int[][] f_score = new int[MAP_LENGTH][MAP_WIDTH];

		g_score[startPoint.x][startPoint.y] = 0;
		h_score[startPoint.x][startPoint.y] = this.heuristic(start, end); 
		f_score[startPoint.x][startPoint.y] = g_score[startPoint.x][startPoint.y] + h_score[startPoint.x][startPoint.y];

		while(openSet.size() > 0) {
			MapObject current = openSet.poll();
			// TODO : Continue A* search.
		}

		return path;
	}
	
	// TODO : PriorityQueue Comparator.

	abstract int heuristic(MapObject start, MapObject end);
}

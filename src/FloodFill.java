import java.util.List;


/**
 * Super garbage flood fill algorithm that doesn't work too well/at all.
 * @author Ian Palencar
 *
 */
public class FloodFill {
	
	/**
	 * Recurisvely flood fills, starting at (x,y)
	 * @param x The x coordinate to start at.
	 * @param y The y coordinate to start at.
	 * @param previousPoints A list of previous points checked.
	 * @param map The map to search over.
	 * @return A list of Points containing the solution.
	 */
	public static List<Point> floodFill(int x, int y, List<Point> previousPoints, MapObject[][] map) {
		
		if (map[x][y].getClass() == ExitObject.class) {
			return previousPoints;
		}
		
		previousPoints.add(new Point(x,y));
		map[x][y].visit();
		
		List<Point> tempList = null;
		List<Point> resultList = null;
		if (x-1 >= 0 && map[x-1][y].isPathable() && !map[x-1][y].isVisited())
			tempList = floodFill(x-1,y,previousPoints,map);
		if (tempList != null)
			resultList = tempList;
		
		if (x+1 < map.length && map[x+1][y].isPathable() && !map[x+1][y].isVisited())
			tempList = floodFill(x+1,y,previousPoints,map);
		if (tempList != null)
			resultList = tempList;
		
		if (y-1 >= 0 && map[x][y-1].isPathable() && !map[x][y-1].isVisited())
			tempList = floodFill(x,y-1,previousPoints,map);
		if (tempList != null)
			resultList = tempList;
		
		if (y+1 < map[0].length && map[x][y+1].isPathable() && !map[x][y+1].isVisited())
			tempList = floodFill(x,y+1,previousPoints,map);
		if (tempList != null)
			resultList = tempList;
		
		return resultList;
	}
}

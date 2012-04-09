import java.util.Random;


public class MapGenerator {
	
	MapObject[][] map;
	Point entrance;
	public MapGenerator(int width, int height, double probabilityOfWall) {
		
		map = new MapObject[width][height];
		Random random = new Random();
		
		//generate the outer walls
		for (int i = 0; i < map.length; ++i) {
			map[i][0] = new WallObject();
			map[i][map[0].length-1] = new WallObject();
		}
		
		for (int j = 0; j < map[0].length; ++j) {
			map[0][j] = new WallObject();
			map[map.length-1][j] = new WallObject();
		}
		
		for (int i = 1; i < map.length - 1; ++i) {
			for (int j = 1; j < map[0].length -1; ++j) {
				if (random.nextDouble() > probabilityOfWall) {
					map[i][j] = new AirObject();
				}
				else {
					map[i][j] = new WallObject();
				}
			}
		}
		
		boolean entranceAdded = false;
		boolean exitAdded = false;
		
		while (!entranceAdded || !exitAdded) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			
			if (x == 0 || x == width-1 || y == 0 || y == height-1) {
				if (!entranceAdded) {
					entranceAdded = true;
					map[x][y] = new EntranceObject();
					entrance = new Point(x,y);
				}
				else if (!exitAdded) {
					exitAdded = true;
					map[x][y] = new ExitObject();
				}
				else {
					System.err.println("ZOMG WHAT IS THIS I DON'T EVEN");
				}
			}
		}
	}
	
	public MapObject[][] getMap() {
		return map;
	}
	
	public Point getEntrance() {
		return entrance;
	}
}

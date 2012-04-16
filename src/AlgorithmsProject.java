import java.util.List;
import javax.swing.JFrame;
import java.io.File;

public class AlgorithmsProject {
	private static final int OBJECT_WIDTH = 160;
	private static final int OBJECT_HEIGHT = 90;
	private static final double WALL_PERCENTAGE = 0.12;
	private static final int PIXEL_SIZE = 5;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(((OBJECT_WIDTH)*PIXEL_SIZE),((OBJECT_HEIGHT+5)*PIXEL_SIZE));
    //below is for predefined square map
    /*
    MapGenerator mapGen = new MapGenerator();
    MapObject[][] map = mapGen.getMap();

    AStar myAStar = new AStar(map, new ManhattanHeuristic());

    List<MapObject> resultList = myAStar.search(map[mapGen.getEntrance().x][mapGen.getEntrance().y],map[mapGen.getExit().x][mapGen.getExit().y]);
    */


		MapGenerator mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT,WALL_PERCENTAGE);
		MapObject[][] map = mapGen.getMap();
		AStar myAStar = new AStar(map, new ManhattanHeuristic());

		List<MapObject> resultList = null;

		while (resultList == null) {
			resultList = myAStar.search(map[mapGen.getEntrance().x][mapGen.getEntrance().y],map[mapGen.getExit().x][mapGen.getExit().y]);
			if (resultList == null) {
				System.err.println("No path found! Regenerating...");
				mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT, WALL_PERCENTAGE);
				map = mapGen.getMap();
				myAStar = new AStar(map, new ManhattanHeuristic());
			}
		}
    for (MapObject m : resultList) {
			map[m.location.x][m.location.y].visit();
		}
		MapPanel myMapPanel = new MapPanel(map, PIXEL_SIZE);
		frame.add(myMapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

    int openSetCount = 0;
    for (int i = 0; i < map.length; ++i)
      for (int j = 0; j < map[0].length; ++j)
        openSetCount += map[i][j].isConsidered() ? 1 : 0;
    System.out.println("Total size of map: "+map.length*map[0].length);
    System.out.println("Number of points in open set:" + openSetCount);
    System.out.println("Path length: "+resultList.size());
	}
}

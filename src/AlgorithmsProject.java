import java.util.List;
import javax.swing.JFrame;
import java.io.File;

public class AlgorithmsProject {
	private static final int OBJECT_WIDTH = 250;
	private static final int OBJECT_HEIGHT = 250;
	private static final double WALL_PERCENTAGE = 0.08;
	private static final int PIXEL_SIZE = 1;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(((OBJECT_WIDTH)*PIXEL_SIZE),((OBJECT_HEIGHT+5)*PIXEL_SIZE));
    //below is for predefined square map
    /*
    MapGenerator mapGen = new MapGenerator();
    MapObject[][] map = mapGen.getMap();

    AStar myAStar = new AStar(map, new EuclideanHeuristic());

    List<MapObject> resultList = myAStar.search(map[mapGen.getEntrance().x][mapGen.getEntrance().y],map[mapGen.getExit().x][mapGen.getExit().y]);
    */

    MapGenerator mapGen = null;

    if (args.length > 0) {
      System.out.println("map file: " + args[0]);
      mapGen = new MapGenerator(new File(args[0]));
    }
    else
      mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT,WALL_PERCENTAGE);
		MapObject[][] map = mapGen.getMap();
    Heuristic heuristic = new EuclideanHeuristic();
		AStar myAStar = new AStar(map, heuristic);

		List<MapObject> resultList = null;

		while (resultList == null) {
			resultList = myAStar.search(map[mapGen.getEntrance().x][mapGen.getEntrance().y],map[mapGen.getExit().x][mapGen.getExit().y]);
			if (resultList == null) {
				System.err.println("No path found! Regenerating...");
				mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT, WALL_PERCENTAGE);
				map = mapGen.getMap();
				myAStar = new AStar(map, heuristic);
			}
		}
    for (MapObject m : resultList) {
			map[m.location.x][m.location.y].visit();
		}
    if (args.length == 0)
      MapTools.ExportMap(map);
    //System.exit(0); //for bailing without drawing anything, woo.
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

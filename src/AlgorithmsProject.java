import java.util.List;
import javax.swing.JFrame;
import java.io.File;


public class AlgorithmsProject {
	private static final int OBJECT_WIDTH = 160;
	private static final int OBJECT_HEIGHT = 90;
	private static final double WALL_PERCENTAGE = 0.0;
	private static final int PIXEL_SIZE = 5;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(((OBJECT_WIDTH)*PIXEL_SIZE),((OBJECT_HEIGHT+5)*PIXEL_SIZE));
		MapGenerator mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT,WALL_PERCENTAGE);
		//MapGenerator mapGen = new MapGenerator(new File("test.map"));
		MapObject[][] map = mapGen.getMap();
    //AStar myAStar = new AStar(map, new ManhattanHeuristic());
    AStar myAStar = new AStar(map, new DijkstraHeuristic());

    List<MapObject> resultList = null;

    while (resultList == null) {
      resultList = myAStar.search(map[mapGen.getEntrance().x][mapGen.getEntrance().y],map[mapGen.getExit().x][mapGen.getExit().y]);
      if (resultList == null) {
        System.err.println("No path found! Regenerating...");
        mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT, WALL_PERCENTAGE);
        map = mapGen.getMap();
        //myAStar = new AStar(map, new ManhattanHeuristic());
        myAStar = new AStar(map, new DijkstraHeuristic());
      }
    }
    for (MapObject m : resultList) {
      System.out.println("List point: "+m);
      map[m.location.x][m.location.y].visit();
    }
		MapPanel myMapPanel = new MapPanel(map, PIXEL_SIZE);
		frame.add(myMapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

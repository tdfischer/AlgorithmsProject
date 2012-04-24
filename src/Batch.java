import java.io.*;
import java.util.List;
public class Batch {


  public static void main(String[] args) {

    if (args.length == 0) {
      System.err.println("No map specified");
      System.exit(0);
    }


    File f = new File(args[0]);

    Heuristic[] heuristics = {
      new DijkstraHeuristic(),
      new ManhattanHeuristic(),
      new EuclideanHeuristic()
    };

    for (Heuristic h : heuristics) {

      MapGenerator gen = new MapGenerator(f);
      MapObject[][] map = gen.getMap();
      AStar myAStar = new AStar(map, h);

      List<MapObject> results = myAStar.search(map[gen.getEntrance().x][gen.getEntrance().y], map[gen.getExit().x][gen.getExit().y]);

      int openSetCount = 0;

      for (int i = 0; i < map.length; ++i)
        for (int j = 0; j < map[0].length; ++j)
          openSetCount += map[i][j].isConsidered() ? 1 : 0;

      System.out.println("Name: "+h.getName());
      System.out.println("Total area: "+map.length*map[0].length);
      System.out.println("Open set size: "+openSetCount);
      System.out.println("Path length: "+results.size());
      System.out.println("---------------");

    }
  }
}

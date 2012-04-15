import java.util.Random;
import java.io.File;
import java.util.Scanner;


/**
 * The generator class to create random and predefined maps.
 * @author Ian Palencar
 *
 */
public class MapGenerator {
  
  private MapObject[][] map;
  private Point entrance;
  private Point exit;
  private static final String DELIMITER = ",";
  /**
	 * Generates a map based on a given file. The file format is CSV (for easy hand-editing) and takes the form:
	 * a,b #first row, dimensions
	 * c,d,<name> #axb rows, uses the getName() property of the MapObjects.
	 * @param f The file to load from (should be plaintext unless you want Bad Things.
	 */
	public MapGenerator(File f) {
		
		Scanner in;
		try {
			in = new Scanner(f);
			
			String[] lineArgs;
			
			lineArgs = in.nextLine().split(DELIMITER);
			
			int width = Integer.parseInt(lineArgs[0]);
			int height = Integer.parseInt(lineArgs[1]);
			
			map = new MapObject[width][height];
			int x = 0;
			int y = 0;
			MapObject mapObj;
			while (in.hasNextLine()) {
				lineArgs = in.nextLine().split(DELIMITER);
				if (lineArgs.length < 3)
					continue;
				x = Integer.parseInt(lineArgs[0]);
				y = Integer.parseInt(lineArgs[1]);
				
				switch (lineArgs[2]) {
				case "WallObject":
					mapObj = new WallObject();
					break;
				case "AirObject":
					mapObj = new AirObject();
					break;
				case "EntranceObject":
					mapObj = new EntranceObject();
					entrance = new Point(x,y);
					break;
				case "ExitObject":
					mapObj = new ExitObject();
					exit = new Point(x,y);
					break;
				default:
					mapObj = new AirObject();
					break;
				}
				
				mapObj.setPoint(new Point(x,y));
				map[x][y] = mapObj;
			}
		
			in.close();
		}
		catch (Exception e) {
			System.err.println("File not found!");
		}
		
		//so, if points aren't specified fill them with air.
		
		for (int i = 0; i < map.length; ++i)
			for (int j = 0; j < map[0].length; ++j)
				if (map[i][j] == null) {
					map[i][j] = new AirObject();
					map[i][j].setPoint(new Point(i,j));
				}
		
	}
  /**
   * Generates a random map to solve with the given width, height, and probability of a wall per block.
   * @param width The width of the map.
   * @param height The height of the map.
   * @param probabilityOfWall The chance that a wall will be there or not (between 0 and 1).
   */
  public MapGenerator(int width, int height, double probabilityOfWall) {
  	
  	map = new MapObject[width][height];
  	Random random = new Random();
  	
    System.out.println("Generating outer walls...");
  	//generate the outer walls
  	for (int i = 0; i < map.length; ++i) {
  		map[i][0] = new WallObject();
  		map[i][0].setPoint(new Point(i,0));
  		map[i][map[0].length-1] = new WallObject();
  		map[i][map[0].length-1].setPoint(new Point(i,map[0].length-1));
  	}
  	
  	for (int j = 0; j < map[0].length; ++j) {
  		map[0][j] = new WallObject();
  		map[0][j].setPoint(new Point(0,j));
  		map[map.length-1][j] = new WallObject();
  		map[map.length-1][j].setPoint(new Point(map.length-1,j));
  	}
  	System.out.println("Generating random data...");
  	for (int i = 1; i < map.length - 1; ++i) {
  		for (int j = 1; j < map[0].length -1; ++j) {
  			if (random.nextDouble() > probabilityOfWall) {
  				map[i][j] = new AirObject();
  				map[i][j].setPoint(new Point(i,j));
  			}
  			else {
  				map[i][j] = new WallObject();
  				map[i][j].setPoint(new Point(i,j));
  			}
        System.out.println("Generated new "+map[i][j]);
  		}
  	}
  	
  

  //add the entrance:
    Point p = generateEntranceOrExit(random);
    map[p.x][p.y] = new EntranceObject();
    map[p.x][p.y].setPoint(new Point(p));
    entrance = new Point(p);
    System.out.println("Added new entrance at "+p.x+","+p.y);
    //add the exit
    p = generateEntranceOrExit(random);
    map[p.x][p.y] = new ExitObject();
    map[p.x][p.y].setPoint(new Point(p));
    exit = new Point(p);
    System.out.println("Added new exit at "+p.x+","+p.y);
  }
  
  //TODO: constructor that creates a preset map from a given file or resource name.

  private Point generateEntranceOrExit(Random r) {
    int x = 0;
    int y = 0;

    if (Math.floor(r.nextDouble()*2) > 0) {
      //set the X value to 0 or length, then set Y to random.
      if (Math.floor(r.nextDouble()*2) > 0)
        x = map.length-1;
      else
        x = 0;

      y = r.nextInt(map[0].length-1);
    }
    else {
      if (Math.floor(r.nextDouble()*2) > 0)
        y = map[0].length-1;
      else
        y = 0;

      x = r.nextInt(map.length-1);
    }
    return new Point(x,y);
  }  
  public MapObject[][] getMap() {
  	return map;
  }
  
  public Point getEntrance() {
  	return entrance;
  }
  
  public Point getExit() {
  	return exit;
  }
}

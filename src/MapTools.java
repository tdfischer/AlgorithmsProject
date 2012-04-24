import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class MapTools {

  public static String DELIMITER = ",";
  public enum MapEnum {
    AirObject,
    WallObject,
    EntranceObject,
    ExitObject
  }

  public static void ExportMap(MapObject[][] map) {
   
    PrintWriter out = null; 
    try {
    FileWriter f = new FileWriter("maps/"+new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date())+".map");
    
    out = new PrintWriter(f);

    out.println(map.length+DELIMITER+map[0].length);

    for (int i = 0; i < map.length; ++i)
      for (int j = 0; j < map[0].length;j++)
        out.println(map[i][j].name);



    }
    catch (Exception e) {}

    finally {if (out != null) out.close();}
  }

  public static MapObject[][] ImportMap(File f) {

    Scanner in = null;
    MapObject[][] map = null;
    try {
      in = new Scanner(f);

      String[] dims = in.nextLine().split(DELIMITER);
      map = new MapObject[Integer.parseInt(dims[0])][Integer.parseInt(dims[1])];

      for (int i = 0; i < map.length; ++i) {
        for (int j = 0; j < map[0].length; ++j) {

          MapObject temp = null;
          String item = in.nextLine();
          if (item.equals("AirObject"))
            temp = new AirObject();
          else if (item.equals("WallObject"))
            temp = new WallObject();
          else if (item.equals("EntranceObject"))
            temp = new EntranceObject();
          else if (item.equals("ExitObject"))
            temp = new ExitObject();
          else
            temp = new AirObject();
          temp.setPoint(new Point(i,j));
          map[i][j] = temp;


        }
      }


    }
    catch (Exception e) {}
    finally {if (in != null) in.close();}

    return map;
  }
}

import java.awt.Color;

/**
 * An object representing the Entrance to a map.
 *
 */
public class EntranceObject extends MapObject {
	
	public EntranceObject() {
		name = "EntranceObject";
		isPathable = true;
		isWall = false;
		color = Color.red;
	}

}

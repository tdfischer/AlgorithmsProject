import java.awt.Color;

/**
 * An object representing a wall (non-passable) map location.
 * @author Ian Palencar
 *
 */
public class WallObject extends MapObject {
	
	public WallObject() {
		isPathable = false;
    isWall = true;
		name = "WallObject";
		color = new Color(63, 63, 63);
	}

}

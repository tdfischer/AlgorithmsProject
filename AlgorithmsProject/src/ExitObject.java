import java.awt.Color;

/**
 * An object representing the exit of a map.
 * @author Ian Palencar
 *
 */
public class ExitObject extends MapObject{
	
	public ExitObject() {
		color = Color.green;
		name = "ExitObject";
		isWall = false;
		isPathable = true;
	}
}

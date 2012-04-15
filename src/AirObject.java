import java.awt.Color;

/**
 * An object representating air/blank space in the map.
 * @author Ian Palencar
 *
 */
public class AirObject extends MapObject {
	
	public AirObject() {
		isPathable = true;
		name = "AirObject";
		color = new Color(208, 208, 189);
	}
}


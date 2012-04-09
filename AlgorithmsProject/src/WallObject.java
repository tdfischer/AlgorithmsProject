import java.awt.Color;


public class WallObject extends MapObject {
	
	public WallObject() {
		isPathable = false;
		name = "WallObject";
		color = Color.black;
	}

}

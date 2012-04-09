import java.awt.Color;


public class ExitObject extends MapObject{
	
	public ExitObject() {
		color = Color.green;
		name = "ExitObject";
		isWall = false;
		isPathable = true;
	}
}

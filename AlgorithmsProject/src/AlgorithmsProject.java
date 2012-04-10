

import javax.swing.JFrame;


public class AlgorithmsProject {
	private static final int OBJECT_WIDTH = 160;
	private static final int OBJECT_HEIGHT = 90;
	private static final double WALL_PERCENTAGE = 0.125;
	private static final int PIXEL_SIZE = 5;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(((OBJECT_WIDTH)*PIXEL_SIZE),((OBJECT_HEIGHT+5)*PIXEL_SIZE));
		MapGenerator mapGen = new MapGenerator(OBJECT_WIDTH, OBJECT_HEIGHT,WALL_PERCENTAGE);
		MapObject[][] map = mapGen.getMap();
		MapPanel myMapPanel = new MapPanel(map, PIXEL_SIZE);
		frame.add(myMapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

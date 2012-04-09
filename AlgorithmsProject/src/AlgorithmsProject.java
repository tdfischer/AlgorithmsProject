

import javax.swing.JFrame;


public class AlgorithmsProject {
	
	public static void main(String[] args) {
		System.out.println("launching program");
		JFrame frame = new JFrame();
		
		frame.setSize(640,480);
		MapGenerator mapGen = new MapGenerator(120,90,0.125);
		MapObject[][] map = mapGen.getMap();
		MapPanel myMapPanel = new MapPanel(map);
		frame.add(myMapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		System.out.println("done showing frame");
	}
}

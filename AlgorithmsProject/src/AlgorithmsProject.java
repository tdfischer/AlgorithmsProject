import java.util.ArrayList;

import javax.swing.JFrame;


public class AlgorithmsProject {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setSize(640,480);
		MapGenerator mapGen = new MapGenerator(120,90,0.125);
		MapObject[][] map = mapGen.getMap();
		MapPanel myMapPanel = new MapPanel(map);
		ArrayList<Point> solutionSet = (ArrayList<Point>) FloodFill.floodFill(mapGen.getEntrance().x, mapGen.getEntrance().y, new ArrayList<Point>(),map);
		System.out.println(solutionSet);
		myMapPanel.addSolution(solutionSet);
		frame.add(myMapPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}

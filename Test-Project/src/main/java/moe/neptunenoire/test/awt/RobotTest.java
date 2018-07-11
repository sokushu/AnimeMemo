package moe.neptunenoire.test.awt;

import java.awt.Robot;

public class RobotTest {
	
	public void robotTest() {
		try {
			Robot robot = new Robot();
			robot.mouseMove(300, 200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}

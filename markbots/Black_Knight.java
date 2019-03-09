package markbots;

import java.awt.Color;

import markbots.intelligence.RobotManager;
import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Black_Knight - a robot by (Adriano C. Polesi)
 */
public class Black_Knight extends AdvancedRobot
{	
	private RobotManager cerebro = null;
	
	public void run() {		 
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);
		setBulletColor(Color.black);
		setScanColor(Color.black);
		
		cerebro = new RobotManager(this);

		// Robot main loop
		while(true) {
			cerebro.keepMoving(this);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		cerebro.onScannedRobot(this, e);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		cerebro.onHitByBullet(this, e);
	}
	
	public void onHitWall(HitWallEvent e) {
		cerebro.onHitWall(this, e);
	}

}

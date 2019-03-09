package markbots.intelligence;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public interface IRobotBehavior {
	
	public void keepMoving(AdvancedRobot robot);
	public void onScannedRobot(AdvancedRobot robot, ScannedRobotEvent e);	
	public void onHitByBullet(AdvancedRobot robot, HitByBulletEvent e);
	public void onHitWall(AdvancedRobot robot, HitWallEvent e);
	
    public void enableBehavior(AdvancedRobot robot);
    public double getDamageRatio();
    public long getTimeElipsed();
}

package markbots.intelligence;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class LineBehavior extends AbstractRobotBehavior {
	
	@Override
	public void keepMoving(AdvancedRobot robot) {
		robot.setAdjustGunForRobotTurn(true);
		robot.setTurnGunLeftRadians(Math.PI);
		robot.ahead(50);
		robot.setTurnGunLeftRadians(Math.PI);
		robot.ahead(50);
		robot.setTurnGunLeftRadians(Math.PI);
		robot.ahead(50);		
		robot.turnRightRadians(Math.PI);
	}

	@Override
	public void onScannedRobot(AdvancedRobot robot, ScannedRobotEvent e) {
		//Target tgt = identifyTarget(e);
		//aimFire(robot, tgt);
		robot.fire(FirePower(e.getDistance(), robot.getEnergy()));
	}

	@Override
	public void onHitByBullet(AdvancedRobot robot, HitByBulletEvent e) {    
		updateDamageRatio(robot, e.getPower());
	    robot.setBack(50);
	    robot.execute();
	}

	@Override
	public void onHitWall(AdvancedRobot robot, HitWallEvent e) {
	    robot.setBack(150);
	    robot.execute();
	}

}

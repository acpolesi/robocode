package markbots.intelligence;

import java.util.ArrayList;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class RobotManager {
	
	private long lastBehaviorchange = 0;
	private IRobotBehavior currentBehavior;
	private ArrayList<IRobotBehavior> behaviorList = new ArrayList<IRobotBehavior>();
	
	public RobotManager(AdvancedRobot robot){
		this.behaviorList.add(new LineBehavior());
		this.behaviorList.add(new SquareBehavior());
		this.behaviorList.add(new CicleBehavior());
		
		this.currentBehavior = this.behaviorList.get(2);
		this.currentBehavior.enableBehavior(robot);
		
		this.lastBehaviorchange = robot.getTime();
	}
	
	public void keepMoving(AdvancedRobot robot) {
		behaviorAnalyse(robot);
		currentBehavior.keepMoving(robot);
	}
	
	public void onScannedRobot(AdvancedRobot robot, ScannedRobotEvent e) {
		currentBehavior.onScannedRobot(robot, e);
	}
	
	public void onHitByBullet(AdvancedRobot robot, HitByBulletEvent e) {
		currentBehavior.onHitByBullet(robot, e);
	}
	
	public void onHitWall(AdvancedRobot robot, HitWallEvent e) {
		currentBehavior.onHitWall(robot, e);
	}
	
	private void behaviorAnalyse(AdvancedRobot robot) {
		if(robot.getTime() - lastBehaviorchange > 100) {
			for (IRobotBehavior robotBehavior : behaviorList) {
				if((currentBehavior != robotBehavior) && (currentBehavior.getDamageRatio() > robotBehavior.getDamageRatio())) {
					robot.out.println("Behavior changed by damage ratio: " + robotBehavior);
					changeBehavior(robot, robotBehavior);
					return;
				} else if((currentBehavior != robotBehavior) 
							&& (currentBehavior.getDamageRatio() == robotBehavior.getDamageRatio()) 
							&& (currentBehavior.getTimeElipsed() < robotBehavior.getTimeElipsed())) {
					robot.out.println("Behavior changed by elipsed time: " + robotBehavior);
					changeBehavior(robot, robotBehavior);
					return;
				}
			}
		}
	}

	private void changeBehavior(AdvancedRobot robot, IRobotBehavior robotBehavior) {
		currentBehavior = robotBehavior;
		currentBehavior.enableBehavior(robot);
		lastBehaviorchange = robot.getTime();		
	}
	
}

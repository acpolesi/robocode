package markbots.intelligence;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.util.HashMap;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public abstract class AbstractRobotBehavior implements IRobotBehavior {
	
    protected long lastUpdate = 0;
    protected long elipsedTime = 1;
    protected double damageTaken = 0;
    protected double damageRatio = 0;    
    
	HashMap<String, Target> targets = new HashMap<String, Target>();
	
    public void enableBehavior(AdvancedRobot robot) {
    	lastUpdate = robot.getTime();
    }
    
    public double getDamageRatio() {
    	return damageRatio;
    }
    
    public long getTimeElipsed() {
    	return elipsedTime;
    }
    
    protected void updateDamageRatio(AdvancedRobot robot, double damage) {
    	long currentTime = robot.getTime(); 
    	long interval = currentTime - lastUpdate;
    	
        lastUpdate = currentTime;
    	
    	damageTaken += damage;
        elipsedTime += interval;
        damageRatio = damageTaken / elipsedTime;
        
        robot.out.println("Damage: " + damage + ", damageTaken: " + damageTaken + ", elipsedTime: " + elipsedTime + ", damageRatio" + damageRatio);
    }	    
	
	protected int FirePower(double enemyDistance, double energy) {
		if (enemyDistance > 150 || energy < 15) {
			return 2;
		} else if (enemyDistance > 50) {
			return 3;		
		} else {
			return 4;
		}
	}
	
	protected double damageValue(double shotPower) {
        double damage = (shotPower * 4);
        if (shotPower > 1) {
            damage += ((shotPower - 1) * 2);
        }
        return damage;
    }
	
	protected Target identifyTarget(ScannedRobotEvent evt) {
		Target tgt;
		
		if (targets.containsKey(evt.getName())) {
			tgt = (Target) targets.get(evt.getName());
		} else {
			tgt = new Target();	
			tgt.name = evt.getName();
			targets.put(tgt.name, tgt);
		}
		
		tgt.distance = evt.getDistance();	
		tgt.speed = evt.getVelocity();
		tgt.scanTime = evt.getTime();
		tgt.bearing = evt.getBearingRadians();
		tgt.heading = evt.getHeadingRadians();
		
		return tgt;		
	}
	
	protected void aimFire(AdvancedRobot robot, Target tgt) {
		double absoluteBearing = robot.getHeading() + tgt.bearing;
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - robot.getGunHeading());
		
		robot.turnGunRight(bearingFromGun);
		if (robot.getGunHeat() == 0) {
			robot.fire(FirePower(tgt.distance, robot.getEnergy()));
		} 
		
		if (bearingFromGun == 0) {
			robot.scan();
		}
				
	}
	
}

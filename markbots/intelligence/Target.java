package markbots.intelligence;

import java.awt.geom.Point2D;

public class Target {
	String name;
	
	public double bearing, heading, speed, x, y, distance, changehead;
	public long scanTime;
	
	public Point2D.Double guessPosition(long moment) {
		double diff = moment - scanTime;
		double newY = y + Math.cos(heading) * speed * diff;
		double newX = x + Math.sin(heading) * speed * diff;
		
		return new Point2D.Double(newX, newY);
	}
}

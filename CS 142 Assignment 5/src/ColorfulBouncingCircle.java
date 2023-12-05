import java.awt.Color;
/*
 * Dustin Feldt
 * CS 142 Assignment 5
 * Colorful Bouncing Circle
 * 
 * This class extends upon pre-existing Circle and Colorful Circle classes
 * to create circles of varying size and color that "bounce" off the boundaries
 * of the graphics window and off other circles.
 */
public class ColorfulBouncingCircle extends ColorfulCircle{
	
	public double xVelocity;
	public double yVelocity;
	public static double width;
	public static double height;

	//the main constructor, which takes all the parameters from Circle and ColorfulCircle
	//but adds velocity values for both x- and y-dimensions
	public ColorfulBouncingCircle(double radius, double centerX, double centerY, 
			Color color, double xVelocity, double yVelocity) {
		
		super(radius, centerX, centerY, color);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		
		
	}
	
	//determines the size of the graphics field
	public static void setPlayingFieldSize(double newWidth, double newHeight) {
		width = newWidth;
		height = newHeight;
	}
	
	//each call of the tick method alters either the circle's center position based on
	//its directional velocities or reverses their velocity if they would exit the
	//playing field
	public void tick() {
		boolean xCheck = getCenterX() + xVelocity < 0 || getCenterX() + xVelocity > width;
		boolean yCheck = getCenterY() + yVelocity < 0 || getCenterY() + yVelocity > height;
		if(xCheck == true) {
			xVelocity = xVelocity * -1;
		}	
		if(yCheck == true) {
			yVelocity = yVelocity * -1;
		}
		if(xCheck == false && yCheck == false) {
			setCenterCoordinates(getCenterX() + xVelocity, getCenterY() + yVelocity);
		}
		
	}
	
	//calls the Overlaps method from Circle, then uses that to determine if the given circle
	//should continue straight or "bounce" off another circle
	@Override
	public boolean overlaps(Circle c) {
		if(super.overlaps(c) == false) {
			return false;
		}
		else {
			if(this.getCenterX() < c.getCenterX() || this.getCenterX() > c.getCenterX()) {
				xVelocity = xVelocity * -1;
			}
			if(this.getCenterY() < c.getCenterY() || this.getCenterY() > c.getCenterY()) {
				yVelocity = yVelocity * -1;
			}
			return true;
		}
		
	}

}

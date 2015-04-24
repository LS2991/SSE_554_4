import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;


public class Player {
	
	private double xPos, yPos;
	private int dX = 3, dY = 3, score = 0;
	private static final int xSize = 15, ySize = 15;
	
	public Player(Rectangle2D environment) {
		
		xPos = environment.getMinX() + 20;
		yPos = Math.floor(environment.getMaxY() / 2);
	}
	
	public void moveLeft(Rectangle2D environment) {	
		xPos -= dX;
	}
	
	public void moveRight(Rectangle2D environment) {
		xPos += dX;
	}
	
	public void moveUp(Rectangle2D environment) {
		if(yPos != environment.getMinY())
			yPos -= dY;
	}
	
	public void moveDown(Rectangle2D environment) {
		if(yPos + ySize != environment.getMaxY())
			yPos += dY;
	}
	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(xPos, yPos, xSize, ySize);
	}
	
	public void paintPlayer(Graphics2D g2) {
		g2.fill(new Ellipse2D.Double(xPos, yPos, xSize, ySize));
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
	///////////////////////////////////////////////////////////
	public int getScore() {
		return score;
	}
	
	public void incrementScore() {
		score ++;
	}
}

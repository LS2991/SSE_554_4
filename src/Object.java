import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;


public class Object {
	
	private double xPos, yPos;
	boolean left,up;
	private int dX = 1, dY = 1;
	private static final int xSize = 15, ySize = 15;
	private int side;
	public int health = 1;
	
	public Object(Rectangle2D environment) {
		side = (int) (Math.random()*3);
		if(side == 0)
		{
			xPos = Math.random()*environment.getMaxX()+20;
			if(xPos > (environment.getMaxX()/2))
			    left = true;
			else
				left = false;
			yPos = environment.getMinY()+20;
		}
		
		else if(side == 1)
		{
			xPos = environment.getMaxX()-20;
			yPos = Math.random()*environment.getMaxY();
			if(yPos > (environment.getMaxX()/2))
			    up = true;
			else
				up = false;
		}
		
		else
		{
			xPos = Math.random()*environment.getMaxX()+20;
			yPos = environment.getMaxY()-20;
			if(xPos > (environment.getMaxX()/2))
				left = true;
			else
				left = false;
		}	
	}
	
	public void move(Rectangle2D environment) {	
		if(side == 0)
		{
			if(left == true)
			{
				xPos = xPos-dX;
				yPos = yPos+dY;
			}
			else
			{
				xPos = xPos+dX;
				yPos = yPos+dY;
			}
		}
		
		else if(side == 1)
		{
			if(up == true)
			{
				xPos = xPos-dX;
				yPos = yPos-dY;
			}
			else
			{
				xPos = xPos-dX;
				yPos = yPos+dY;
			}
		}
		
		else
		{
			if(left == true)
			{
				xPos = xPos-dX;
				yPos = yPos-dY;
			}
			else
			{
				xPos = xPos+dX;
				yPos = yPos-dY;
			}
		}
	}
	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(xPos, yPos, xSize, ySize);
	}
	
	public void paintObject(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fill(new Ellipse2D.Double(xPos, yPos, xSize, ySize));
		
	}
	public void setSpeed(int speed) {
		this.dX = speed;
		this.dY = speed;
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

	public void reset(Rectangle2D environment) {
		side = (int) (Math.random()*3);
		if(side == 0)
		{
			xPos = Math.random()*environment.getMaxX()+20;
			if(xPos > (environment.getMaxX()/2))
			    left = true;
			else
				left = false;
			yPos = environment.getMinY()+20;
		}
		
		else if(side == 1)
		{
			xPos = environment.getMaxX()-20;
			yPos = Math.random()*environment.getMaxY();
			if(yPos > (environment.getMaxX()/2))
			    up = true;
			else
				up = false;
		}
		
		else
		{
			xPos = Math.random()*environment.getMaxX()+20;
			yPos = environment.getMaxY()-20;
			if(xPos > (environment.getMaxX()/2))
				left = true;
			else
				left = false;
		}	
	}

	public boolean intersects(Rectangle2D obj) {
		if(getShape().intersects(obj))
			return true;
		return false;
	}

	public int getSide() {
		// TODO Auto-generated method stub
		return side;
	}

	public boolean intersects2(Shape shapeA, Shape shapeB) 
	{
		Area areaA = new Area(shapeA);
		areaA.intersect(new Area(shapeB));
		return !areaA.isEmpty();
	}

}

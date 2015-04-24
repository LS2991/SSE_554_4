import java.util.ArrayList;


public class ObjectRunnable implements Runnable {

	private GameComponent comp;
	private ArrayList<Object> objects;
	private Object object;
	boolean play = true, collision = false;
	
	public ObjectRunnable(GameComponent comp) {
		object = new Object(comp.getBounds());
		comp.addObject(object);
		comp.paint(comp.getGraphics());
		objects = comp.getObjects();
		
		this.comp = comp;
	}

	public void run() {
		
//		Thread t = new Thread(new Runnable() {
//			public void run() {
//				for
//			}
//		});
		while(play = true)
		{
			while((object.getXPos() + object.getXSize() < comp.getBounds().getMaxX()) 
					&& (object.getXPos() - object.getXSize() > comp.getBounds().getMinX())
					&& (object.getYPos() + object.getYSize() < comp.getBounds().getMaxY())
					&& (object.getYPos() - object.getYSize() > comp.getBounds().getMinY())) 
			{
				try {
					int prevX = (int) object.getXPos();
					int prevY = (int) object.getYPos();
					
					object.move(comp.getBounds());
					for(int i = 0; i < comp.getProjectiles().size(); i++)
						if(object.intersects(comp.getProjectiles().get(i).getShape()))
						{
							comp.getProjectiles().remove(i);
							collision = true;
						}
					if(collision)
					{
						comp.getPlayer().incrementScore();
						break;
					}
					//comp.repaint(prevX, prevY, object.getXSize(), object.getYSize());
					//comp.repaint((int) object.getXPos(), (int) object.getYPos(), object.getXSize(), object.getYSize());
					comp.repaint();
					Thread.sleep(10);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		comp.repaint();
		object.reset(comp.getBounds());
		collision = false;
	}
}
	
	public Object getObject() 
	{
		return object;
	}
}

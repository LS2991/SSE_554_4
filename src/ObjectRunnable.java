import java.util.ArrayList;


public class ObjectRunnable implements Runnable {

	private GameComponent comp;
	private ArrayList<Object> objects;
	private Object object;
	boolean play = true, collision = false, playerhit= false;
	int health = 1;

	public ObjectRunnable(GameComponent comp) {
		object = new Object(comp.getBounds());
		object.setSpeed(comp.difficulty+1);
		if (comp.gamemode == "end")
			object.health = comp.difficulty+1;
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
		while(play == true)
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
					{
						if(object.intersects(comp.getProjectiles().get(i).getShape()))
						{
							comp.getProjectiles().remove(i);
							object.health--;
							break;
						}
					}
					
					if(object.intersects2(comp.getPlayer().getShape(),object.getShape()))
					{
						playerhit = true;
					}
					
					if(playerhit)
					{
						comp.getPlayer().health = comp.getPlayer().health-1;
						System.out.println(comp.getPlayer().health);
						playerhit = false;
						if (comp.getPlayer().health <= 0)
						{
							comp.gameEnded = true;
							comp.endGame();
						}
						break;
					}
					if(object.health==0)
					{
						collision = true;
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
		if(collision)
		{
			//health--;
			comp.getPlayer().incrementScore();
			for(int i = 1; i < 3; i++)
			{
				Runnable r = new SmallerObjectRunnable(comp,object.getXPos(),object.getYPos(),object.getSide(),i);
				Thread t = new Thread(r);
				t.start();
			}
			objects.remove(object);
			play = false;
			break;
		}
		else if(collision == false)
		{
			comp.repaint();
			object.reset(comp.getBounds());
		}
	}
		
}
	
	public Object getObject() 
	{
		return object;
	}
}

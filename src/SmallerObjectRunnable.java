import java.util.ArrayList;


public class SmallerObjectRunnable implements Runnable {

	private GameComponent comp;
	private ArrayList<SmallerObject> objects;
	private SmallerObject object;
	boolean play = true, collision = false, playerhit = false;
	int health;

	public SmallerObjectRunnable(GameComponent comp, double xpos, double ypos, int side, int num) {
		object = new SmallerObject(comp.getBounds(),xpos,ypos,side,num);
		object.setSpeed(comp.difficulty+1);
		comp.addSmallerObject(object);
		comp.paint(comp.getGraphics());
		objects = comp.getSmallerObjects();
		
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
							collision = true;
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
					if(collision)
						break;
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
			comp.getPlayer().incrementScore();
			comp.smalldestroyed++;
			objects.remove(object);
			if(comp.smalldestroyed != 0 && comp.smalldestroyed%2==0)
			{
				Runnable r = new ObjectRunnable(comp);
				Thread t = new Thread(r);
				t.start();
			}
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
	
	public SmallerObject getObject() 
	{
		return object;
	}
}

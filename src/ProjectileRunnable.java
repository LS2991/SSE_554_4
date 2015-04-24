import java.util.ArrayList;


public class ProjectileRunnable implements Runnable {

	private Projectile projectile;
	private GameComponent comp;
	private ArrayList<Projectile> projectiles;
	
	public ProjectileRunnable(GameComponent comp, Player player) {
		projectile = new Projectile(comp.getBounds(), player);
		comp.addProjectile(projectile);
		comp.paint(comp.getGraphics());
		//xxprojectile = comp.getProjectile();
		projectiles = comp.getProjectiles();
		
		this.comp = comp;
	}

	public void run() {
		
//		Thread t = new Thread(new Runnable() {
//			public void run() {
//				for
//			}
//		});
		while(projectile.getXPos() + projectile.getXSize() != comp.getBounds().getMaxX()) {
			try {
				int prevX = (int) projectile.getXPos();
				int prevY = (int) projectile.getYPos();
				
				projectile.moveRight(comp.getBounds());
				comp.repaint(prevX, prevY, projectile.getXSize(), projectile.getYSize());
				comp.repaint((int) projectile.getXPos(), (int) projectile.getYPos(), projectile.getXSize(), projectile.getYSize());
				//comp.repaint();
				//comp.paint(comp.getGraphics());
				Thread.sleep(10);
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		comp.repaint((int) projectile.getXPos(), (int) projectile.getYPos(), projectile.getXSize(), projectile.getYSize());
		projectiles.remove(projectile);
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
}

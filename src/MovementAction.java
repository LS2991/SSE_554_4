import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class MovementAction extends AbstractAction {
	
	private String cmd;
	private boolean upFlag = false, downFlag = false, shootFlag = false;
	
	public MovementAction(String cmd) {
		this.cmd = cmd;
	}
	
	public void actionPerformed(ActionEvent e) {
		GameComponent comp = (GameComponent) e.getSource();
				
		Player p = comp.getPlayer();
		Menu m = comp.getMenu();
		Projectile projectile;
		
		if(cmd.equalsIgnoreCase("W") && p != null) {
			int prevX = (int) p.getXPos();
			int prevY = (int) p.getYPos();
			
			p.moveUp(comp.getBounds());
			comp.repaint(prevX, prevY, p.getXSize(), p.getYSize());
			comp.repaint((int) p.getXPos(), (int) p.getYPos(), p.getXSize(), p.getYSize());
			//comp.paint(comp.getGraphics());
		}
		else if(cmd.equalsIgnoreCase("S") && p != null) {
			int prevX = (int) p.getXPos();
			int prevY = (int) p.getYPos();
			
			p.moveDown(comp.getBounds());
			comp.repaint(prevX, prevY, p.getXSize(), p.getYSize());
			comp.repaint((int) p.getXPos(), (int) p.getYPos(), p.getXSize(), p.getYSize());
			//comp.paint(comp.getGraphics());
		}
		
		else if(cmd.equalsIgnoreCase("Space") && p != null) {
			
			Runnable r = new ProjectileRunnable(comp, p);
			Thread t = new Thread(r);
			t.start();
		}
		else if(cmd.equalsIgnoreCase("M") && m != null) {
			Runnable r = new MenuRunnable(comp, m);
			Thread t = new Thread(r);
			t.start();
		}
		else if(cmd.equalsIgnoreCase("Enter") && m != null) {
			if (m.isDrawing()) {
				int sel = m.getSelected();
				switch (sel) {
					case 0:	comp.toggleScore(); break;
					case 1: break;
					case 2: break;
					case 3: System.exit(0); break;
					default: break;
				}
				m.stopDrawing();
				comp.repaint(0,0,600,600);
			}
		}
		else if(cmd.equalsIgnoreCase("Up") && m != null) {
			if (m.isDrawing()) {
				m.decrementSelection();
				comp.repaint(150,0,400,600);
			}
		}
		else if(cmd.equalsIgnoreCase("Down") && m != null) {
			if (m.isDrawing()) {
				m.incrementSelection();
				comp.repaint(150,0,400,600);
			}
		}
	}

}

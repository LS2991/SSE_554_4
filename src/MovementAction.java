import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
		Menu s = comp.getSubMenu();
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
			if (!comp.gameEnded) {
				Runnable r = new ProjectileRunnable(comp, p);
				Thread t = new Thread(r);
				t.start();
			
				if (comp.gamemode == "eff")
					comp.shots--;
				if (comp.shots <= 0) {
					// END GAME and update scores
					endGame(e);
				}
			}
		}
		else if(cmd.equalsIgnoreCase("M") && m != null) {
			if (m.isDrawing()) {
				m.stopDrawing();
				comp.repaint(150,0,400,600);
			}
			else if (s.isDrawing()){
				s.stopDrawing();
				m.startDrawing();
				comp.repaint(150,0,300,600);
			}
			else {
				Runnable r = new MenuRunnable(comp, m);
				Thread t = new Thread(r);
				t.start();
			}
		}
		else if(cmd.equalsIgnoreCase("Enter") && m != null) {
			if (m.isDrawing()) {
				int sel = m.getSelected();
				switch (sel) {
					case 0:	comp.toggleScore(); break;
					case 1: // Difficulty
						ArrayList<String> l = new ArrayList<String>();
						l.add("Easy");
						l.add("Medium");
						l.add("Hard");
						l.add("Back");
						s = new Menu(150, 0, 300, 600, l);
						s.type = 1;
						comp.addSubMenu(s);
						s.startDrawing();
						break;
					case 2: 
						ArrayList<String> d = new ArrayList<String>();
						d.add("Show Health");
						if (comp.gamemode == "eff")
							d.add("Show Shots Left");
						d.add("Back");
						s = new Menu(150, 0, 300, 600, d);
						s.type = 2;
						comp.addSubMenu(s);
						s.startDrawing();
						break;
					case 3: break;
					case 4: restartGame(e); break;
					case 5: System.exit(0); break;
					default: break;
				}
				m.stopDrawing();
				comp.repaint(0,0,600,600);
			}
			else if (s.isDrawing()) {
				int sel = s.getSelected();
				if (s.type == 1) {
					switch(sel) {
						case 0: comp.difficulty = 0; restartGame(e); break;
						case 1: comp.difficulty = 1; restartGame(e); break;
						case 2: comp.difficulty = 2; restartGame(e); break;
						case 3: break;
						default: break;
					}
					s.stopDrawing();
				}
				if (s.type == 2) {
					switch (sel) {
						case 0:	comp.toggleHealth(); break;
						case 1: 
							if (comp.gamemode == "eff")
								comp.toggleShots();
							break;
						default: break;
					}
					m.startDrawing();
				}
				s.stopDrawing();
			}
		}
		else if(cmd.equalsIgnoreCase("Up") && m != null) {
			if (m.isDrawing()) {
				m.decrementSelection();
				comp.repaint(150,0,300,600);
			}
			if (s.isDrawing()) {
				s.decrementSelection();
				comp.repaint(150,0,300,600);
			}
		}
		else if(cmd.equalsIgnoreCase("Down") && m != null) {
			if (m.isDrawing()) {
				m.incrementSelection();
				comp.repaint(150,0,400,600);
			}
			if (s.isDrawing()) {
				s.incrementSelection();
				comp.repaint(150,0,400,600);
			}
		}
	}
	
	public void restartGame(ActionEvent e) {
		GameComponent comp = (GameComponent) e.getSource();
		Player p = comp.getPlayer();
		Menu m = comp.getMenu();
		Menu s = comp.getSubMenu();
		
		comp.shots = 20;
		comp.gameEnded = false;
		p.resetScore();
		p.health = p.maxHealth;
		s.stopDrawing();
	}
	public void endGame(ActionEvent e) {
		GameComponent comp = (GameComponent) e.getSource();
		Player p = comp.getPlayer();
		
		comp.gameEnded = true;
	}
}

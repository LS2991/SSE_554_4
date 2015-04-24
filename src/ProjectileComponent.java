import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class ProjectileComponent extends JPanel {
	
	private static final int defaultWidth = 600;
	private static final int defaultHeight = 600;
	
	Projectile p;
	
	public void add(Projectile p) {
		this.p = p;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(p != null)
			g2.fill(p.getShape());
	}
	
	public Dimension getPreferedSize() {
		return new Dimension(defaultWidth, defaultHeight);
	}
	
	public Projectile getProjectile() {
		return p;
	}

}

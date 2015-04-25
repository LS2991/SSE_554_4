import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GameComponent extends JPanel{
	
	private static final int defaultWidth = 600;
	private static final int defaultHeight = 600;
	private ArrayList<Player> players = new ArrayList();
	private Player player;
	private Projectile projectile;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Object object;
	private SaveFile savedgame = new SaveFile();
	private ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<SmallerObject> smallobjects = new ArrayList<SmallerObject>();
	private Menu menu = new Menu();
	private Menu submenu = new Menu();
	private boolean showScore = false;
	private boolean showHealth = true;
	
	public void addPlayer(Player p) {
		player = p;
	}
	
	public void addProjectile() {
		projectiles.add(new Projectile(getBounds(), player));
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	public void addObject(Object obj)
	{
		objects.add(obj);
	}
	
	public void addSmallerObject(SmallerObject smallobject) 
	{
		smallobjects.add(smallobject);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
//		for(Player p : players) {
//			g2.fill(p.getShape());
//		}
		if(player != null)
			player.paintPlayer(g2);
		
		if(!objects.isEmpty())
			for(Object obj : objects)
				obj.paintObject(g2);
		
		if(!smallobjects.isEmpty())
			for(SmallerObject obj : smallobjects)
				obj.paintObject(g2);
				
		if(!projectiles.isEmpty()) { 
			for(Projectile projectile : projectiles)
				projectile.paintProjectile(g2);
		}
		
		if (showScore) {
			Font f = new Font("Calibri", Font.BOLD, 16);
			g2.setFont(f);
			g2.setColor(Color.BLACK);
			g2.drawString("Score : " + Integer.toString(player.getScore()), 20, 502);
		}
		if (showHealth) {
			if (player != null) {
				int h = player.health;
				int max = player.maxHealth;
				double perc = Math.min(50,(((double)h/(double)max)*50));
				perc = Math.max(0,perc);
			
				g2.setColor(Color.BLACK);
				g2.fillRect(524, 20, 54, 18);
				g2.setColor(Color.BLUE);
				g2.fillRect(526, 22, ((int)perc), 14);
			}
		}
		
		if (menu != null) {
			menu.paintMenu(g2);
		}
		if (submenu != null) {
			submenu.paintMenu(g2);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(defaultWidth, defaultHeight);
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Projectile getProjectile() {
		return projectile;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public ArrayList<Object> getObjects() 
	{
		return objects;
	}
	
	public ArrayList<SmallerObject> getSmallerObjects() 
	{
		return smallobjects;
	}
	
	public SaveFile getSaveFile() 
	{
		return savedgame;	
	}

	/////////////////////////////////////////
	public void startDrawingMenu() {
		menu.startDrawing();
	}
	
	public void stopDrawingMenu() {
		menu.stopDrawing();
	}
	
	public Menu getMenu() {
		return menu;
	}
	public void addMenu(Menu m) {
		menu = m;
	}
	public Menu getSubMenu() {
		return submenu;
	}
	public void addSubMenu(Menu m) {
		submenu = m;
	}
	public void toggleScore() {
		showScore = !showScore;
	}
	public void toggleHealth() {
		showHealth = !showHealth;
	}


}

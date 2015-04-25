import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameComponent extends JPanel{
	
	private static final int defaultWidth = 600;
	private static final int defaultHeight = 600;
	private ArrayList<Player> players = new ArrayList();
	private Player player;
	private Projectile projectile;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Object object;
	private SaveFile savedgame;
	private ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<SmallerObject> smallobjects = new ArrayList<SmallerObject>();
	private Menu menu = new Menu();
	private Menu submenu = new Menu();
	private boolean showScore = true;
	private boolean showHealth = true;
	private boolean showShots = false;
	public boolean gameEnded = false;
	public int shots = 20;
	public String gamemode;
	public int smalldestroyed = 0;
	public int difficulty = 0;
	public int highScore = 0;
	public double efficiency = 0;
	
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
	
	public void addSaveFile()
	{
		savedgame = new SaveFile();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
//		for(Player p : players) {
//			g2.fill(p.getShape());
//		}
		if (!gameEnded) {
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
				if (player != null) {
					Font f = new Font("Calibri", Font.BOLD, 16);
					g2.setFont(f);
					g2.setColor(Color.BLACK);
					g2.drawString("Score : " + Integer.toString(player.getScore()), 20, 502);
				}
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
			if (showShots) {
				Font f = new Font("Calibri", Font.BOLD, 16);
				g2.setFont(f);
				g2.setColor(Color.BLACK);
				g2.drawString("Shots remaining : " + Integer.toString(shots), 440, 502);
			}
		}
		else {
			if (player != null) {
				Color rectColor = new Color(0f, 0f, 0f, 0.6f);
				Font f = new Font("Calibri", Font.BOLD, 44);
				g2.setColor(rectColor);
				g2.fillRect(0, 210, 600, 150);
				g2.setFont(f);
				g2.setColor(Color.WHITE);
				g2.drawString("GAME OVER", 165, 280);
				f = new Font("Calibri", Font.BOLD, 24);
				g2.setFont(f);
				g2.drawString("Press m to bring up the menu.", 135, 300);
				f = new Font("Calibri", Font.BOLD, 36);
				g2.setFont(f);
				if (gamemode == "eff") {
					efficiency = 100*(double)player.getScore()/(double)20.0;
					switch (difficulty) {
					case 0:
						if (player.getScore() == savedgame.getEfficiencyEasy())
							g2.drawString("Efficiency: " + Double.toString(efficiency) +"%", 165, 340);
						else
							g2.drawString("Efficiency: " + Double.toString(player.getScore()) +"% HighScore: " + savedgame.getEfficiencyEasy() +"%", 45, 340);
						break;
					case 1:
						if (player.getScore() == savedgame.getEfficiencyMedium())
							g2.drawString("Efficiency: " + Double.toString(efficiency) +"%", 165, 340);
						else
							g2.drawString("Efficiency: " + Integer.toString(player.getScore()) +"% HighScore: " + savedgame.getEfficiencyMedium() +"%", 45, 340);
						break;
					case 2:
						if (player.getScore() == savedgame.getEfficiencyHard())
							g2.drawString("Efficiency: " + Double.toString(efficiency) +"%", 165, 340);
						else
							g2.drawString("Efficiency: " + Integer.toString(player.getScore()) +"% HighScore: " + savedgame.getEfficiencyHard() +"%", 45, 340);
						break;
					default: break;
					}
					
				}
				else {
					switch (difficulty) {
					case 0:
						if (player.getScore() == savedgame.getEndlessEasy())
							g2.drawString("HighScore: " + Integer.toString(player.getScore()), 215, 340);
						else
							g2.drawString("Score: " + Integer.toString(player.getScore()) + " HighScore: " + Integer.toString(savedgame.getEndlessEasy()), 45, 340);
						break;
					case 1:
						if (player.getScore() == savedgame.getEndlessMedium())
							g2.drawString("HighScore: " + Integer.toString(player.getScore()), 215, 340);
						else
							g2.drawString("Score: " + Integer.toString(player.getScore()) + " HighScore: " + Integer.toString(savedgame.getEndlessMedium()), 45, 340);
						break;
					case 2:
						if (player.getScore() == savedgame.getEndlessHard())
							g2.drawString("HighScore: " + Integer.toString(player.getScore()), 215, 340);
						else
							g2.drawString("Score: " + Integer.toString(player.getScore()) + " HighScore: " + Integer.toString(savedgame.getEndlessHard()), 45, 340);
						break;
					default: break;
					}
				}
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
	public void toggleShots() {
		showShots = !showShots;
	}
	public void changeObjectSpeeds() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).setSpeed(difficulty+1);
		}
		for (int i = 0; i < smallobjects.size(); i++) {
			smallobjects.get(i).setSpeed(difficulty+1);
		}
	}
	public void updateObjectHealth() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).updateHealth(difficulty+1);
		}
	}
	public void restartGame() {
		Player p = getPlayer();
		
		shots = 20;
		gameEnded = false;
		p.resetScore();
		p.health = p.maxHealth;
		changeObjectSpeeds();
		submenu.stopDrawing();
		menu.stopDrawing();
	}
}

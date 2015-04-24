import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Menu {
	private int xPos, yPos, width, height, selected, maxOptions;
	private ArrayList<String> menuList = new ArrayList<String>();
	private boolean drawing = false;
	
	public Menu() {
		xPos = 150;
		yPos = 0;
		width = 300;
		height = 600;
		
		createMenuList();
	}

	public Menu(int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		selected = 0;
		
		createMenuList();
	}
	
	private void createMenuList() {
		menuList.add("Score");
		menuList.add("Options");
		menuList.add("Exit Menu");
		menuList.add("Exit Game");
		selected = 0;
		
		maxOptions = menuList.size();
	}
	
	public void paintMenu(Graphics2D g2) {
		if (drawing) {
			// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font fontTitle = new Font("Calibri", Font.BOLD, 28);
			Font fontList = new Font("Calibri", Font.BOLD, 18);
			Color rectColor = new Color(0f, 0f, 0f, 0.3f);
			Color selColor = new Color(1f, 1f, 1f, 0.5f);
			
			g2.setColor(rectColor);
			g2.fillRect(xPos, yPos, width, height);
			
			g2.setColor(Color.WHITE);
		    g2.setFont(fontTitle);
		    g2.drawString("Menu", xPos + 20, yPos + 30);
		    g2.setFont(fontList);
			
			for (int i = 0; i < maxOptions; i++) {
				if (selected == i) {
					g2.setColor(selColor);
					g2.fillRect(xPos, yPos + 75 + 20*i, width, 20);
					g2.setColor(Color.BLACK);
					g2.drawString(menuList.get(i), xPos + 20, yPos + 70 + 20*(i + 1));
				}
				else {
					g2.setColor(Color.WHITE);
					g2.drawString(menuList.get(i), xPos + 20, yPos + 70 + 20*(i + 1));
				}
			}
		}
	}
	
	public void incrementSelection() {
		selected++;
		
		if (selected >= maxOptions)
			selected = 0;
	}
	
	public void decrementSelection() {
		selected--;
		
		if (selected < 0)
			selected = maxOptions - 1;
	}
	
	public void startDrawing() {
		drawing = true;
	}
		
	public void stopDrawing() {
		drawing = false;
	}
	
	public boolean isDrawing() {
		return drawing;
	}
	
	public int getSelected() {
		return selected;
	}
}

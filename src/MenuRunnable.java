


public class MenuRunnable implements Runnable {
	private Menu menu;
	private GameComponent comp;
	
	public MenuRunnable(GameComponent comp, Menu m) {
		this.menu = m;
		this.comp = comp;
	}

	public void run() {
		comp.addMenu(menu);
		menu.startDrawing();
		comp.paint(comp.getGraphics());
	}
	
	public Menu getMenu() {
		return menu;
	}
}
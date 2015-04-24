import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.Test;


public class GameUnitTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void addPlayerTest() {
		JFrame frame = new EnvironmentFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		GameComponent comp = ((EnvironmentFrame) frame).getGameComponent();
		
		Player player = new Player(comp.getBounds());

		Runnable r = new PlayerRunnable(comp, player);
		Thread t = new Thread(r);
		t.start();
		
		Player p= ((PlayerRunnable) r).getPlayer();
		assertTrue(p != null);
	}
	
	@Test
	public void addObjectTest() {
		JFrame frame = new EnvironmentFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		GameComponent comp = ((EnvironmentFrame) frame).getGameComponent();
		
		for(int i = 0; i < 5; i++)
		{
			Runnable r = new ObjectRunnable(comp);
			Thread t = new Thread(r);
			t.start();
			
			Object obj = ((ObjectRunnable) r).getObject();
			assertTrue(obj != null);
		}
	}
	
	@Test
	public void addProjectile() {
		JFrame frame = new EnvironmentFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		GameComponent comp = ((EnvironmentFrame) frame).getGameComponent();
		
		Player player = new Player(comp.getBounds());

		Runnable r = new PlayerRunnable(comp, player);
		Thread t = new Thread(r);
		t.start();
		
		Runnable r2 = new ProjectileRunnable(comp, player);
		Thread t2 = new Thread(r2);
		t2.start();
		
		ArrayList<Projectile> projectiles = ((ProjectileRunnable) r2).getProjectiles();
		assertTrue(projectiles != null);
	}
	
	@Test 
	public void movmentTest() {
		JFrame frame = new EnvironmentFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		GameComponent comp = ((EnvironmentFrame) frame).getGameComponent();
		
		Player player = new Player(comp.getBounds());
		
		//double prevX = player.getXPos();
		double prevY = player.getYPos();
		
		player.moveDown(comp.getBounds());
		
		assertTrue(player.getYPos() > prevY);
		
		player.moveUp(comp.getBounds());
		player.moveUp(comp.getBounds());
		
		assertTrue(player.getYPos() < prevY);
	}
	
	@Test
	public void addMenu() {
		JFrame frame = new EnvironmentFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		
		GameComponent comp = ((EnvironmentFrame) frame).getGameComponent();
		Menu m = new Menu();
		
		Runnable r = new MenuRunnable(comp, m);
		Thread t = new Thread(r);
		t.start();
		
		m = ((MenuRunnable) r).getMenu();
		assertTrue(m != null);
	}
	
	@Test
	public void encryptTest() {
		
		CaesarCipher cc = new CaesarCipher(3);
		String in = "ABC";
		String in2 = "xyz";
		
		String encrypted = cc.encrypt(in);
		String encrypted2 = cc.encrypt(in2);
		
		assertTrue(encrypted.equals("DEF"));
		assertTrue(encrypted2.equals("uvw"));
	}
	
	public void decryptTest() {
		
		CaesarCipher cc = new CaesarCipher(3);
		String enc = "DEF";
		String enc2 = "uvw";
		
		String dec = cc.decrypt(enc);
		String dec2 = cc.decrypt(enc2);
		
		assertTrue(dec.equals("ABC"));
		assertTrue(dec2.equals("xyz"));
	}

}

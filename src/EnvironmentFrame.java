import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class EnvironmentFrame extends JFrame implements ActionListener, Observer {
	public GameComponent comp;
	public LoginFrame login;
	Container contentPane;
	JButton existingB, newB;
	boolean authenticated = false;
	public EnvironmentFrame() {
		comp = new GameComponent();
		comp.setFocusable(true);
		setFocusable(true);
		contentPane = getContentPane();
		login = new LoginFrame();
		
		login.registerObserver(this);
		
		existingB = login.getExistingButton();
		existingB.addActionListener(this);
		
		newB = login.getNewButton();
		newB.addActionListener(this);
		
		//login.setFocusable(true);
		//setFocusable(true);
		
		InputMap im = comp.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = comp.getActionMap();
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "W");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "S");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Space");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Up");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "M");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		
		am.put("W", new MovementAction("W"));
		am.put("S", new MovementAction("S"));
		am.put("Space", new MovementAction("Space"));
		am.put("Up", new MovementAction("Up"));
		am.put("Down", new MovementAction("Down"));
		am.put("M", new MovementAction("M"));
		am.put("Enter", new MovementAction("Enter"));
		
		//add(comp, BorderLayout.CENTER);
		add(login, BorderLayout.CENTER);
		
		pack();
	}
	
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	public void addPlayer() {
		comp.addSaveFile();
		comp.getSaveFile().print();
		Player player = new Player(comp.getBounds());
//		comp.addPlayer(player);
//		comp.paint(comp.getGraphics());
		Runnable r = new PlayerRunnable(comp, player);
		Thread t = new Thread(r);
		t.start();

	}
	
	public void addObjects() {
		for(int i = 0; i < 5; i++)
		{
			Runnable r = new ObjectRunnable(comp);
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	public void addProjectile() {
		comp.addProjectile();
		comp.paint(comp.getGraphics());
	}
	
	
	public GameComponent getGameComponent() {
		return comp;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("frame");
		
		String action = e.getActionCommand();
		if(action.equals("existing") && authenticated == true) {
			setPane(comp);
			
			JPanel buttonPanel = new JPanel();
			addButton(buttonPanel, "Start Efficiency Mode", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (comp.gamemode == null) {
						addPlayer();
						addObjects();
						comp.toggleShots();
						comp.gamemode = "eff";
					}
				}
			});
			addButton(buttonPanel, "Start Endless Mode", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (comp.gamemode == null) {
						addPlayer();
						addObjects();
						comp.gamemode = "end";
					}
				}
			});
			
			add(buttonPanel, BorderLayout.SOUTH);
		}
	}
	
public void setPane(JPanel panel) {
        contentPane.removeAll();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setFocusable(true);
		setFocusable(true);
		setPreferredSize((comp.getPreferredSize()));
        pack();
        setVisible(true);
	}

public void update(boolean authenticated) {
	this.authenticated = authenticated;
}

}

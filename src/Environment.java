import java.awt.EventQueue;

import javax.swing.JFrame;


public class Environment {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new EnvironmentFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setFocusable(true);
				frame.setVisible(true);
			}
		});
	}
}

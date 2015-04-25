import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginFrame extends JPanel implements ActionListener, Subject{
	
	JButton existingUser, newUser;
	JTextField username, password;
	JLabel userLabel, passLabel;
	File usersFile, directoryFile;
	int shift = 3;
	public boolean authenticated = false;
	public static String savedfilename;
	CaesarCipher cc;
	ArrayList<Observer> observers;
	
	public LoginFrame() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		observers = new ArrayList<Observer>();
		
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		
		userLabel = new JLabel("Username:");
		c.gridx = 0;
		c.gridy = 0;
		add(userLabel, c);
		
		passLabel = new JLabel("Password:");
		c.gridx = 0;
		c.gridy = 1;
		add(passLabel,c);
		
		c.weightx = 1.0;
		username = new JTextField("", 12);
		username.setPreferredSize(username.getPreferredSize());
		c.gridwidth = 2;
		//c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 1;
		c.gridy = 0;
		add(username, c);
		
		password = new JTextField("", 12);
		
		//c.gridwidth = GridBagConstraints.REMAINDER;

		c.gridx = 1;
		c.gridy = 1;
		add(password, c);
		
		c.weightx = 1.0;
		existingUser = new JButton("Existing User");
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		add(existingUser, c);
		
		newUser = new JButton("New User");
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 3;
		add(newUser, c);
		
		existingUser.addActionListener(this);
		existingUser.setActionCommand("existing");
		
		newUser.addActionListener(this);
		newUser.setActionCommand("new");
		
		cc = new CaesarCipher(shift);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		File user;
		directoryFile = new File("saveDirectory.txt");
		usersFile = new File("usersList.txt");
		try {
			usersFile.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String action = e.getActionCommand();
		
			//String action = e.getActionCommand();
		FileReader fr;// = new FileReader(usersFile.getPath());
		BufferedReader br;// = new BufferedReader(fr);
		String name = "", contents = "";
			try {
				if(action.equals("existing")) {
					fr = new FileReader(directoryFile);
					br = new BufferedReader(fr);
					
					String path = "";
					
					if(!directoryFile.exists()) {
						JOptionPane.showMessageDialog(this, "No save files exist. Please select a save directory.");
						showSaveFileDialog();
					}
					else if(!(new File((path = br.readLine())).exists())) {
						JOptionPane.showMessageDialog(this, "Directory does not exist. Please select a valid directory.");
						showSaveFileDialog();
					}
					
					
					if(!username.getText().equals("") && !password.getText().equals("")) {
						user = null;
						fr = new FileReader(usersFile);
						br = new BufferedReader(fr);
						//fScanner = new Scanner(usersFile);
						if((name = br.readLine()) == null) {
							JOptionPane.showMessageDialog(this, "Username does not exist. Please select new user.");
						}
						else {
							while(name != null) {
								if(name.contains(username.getText())) {
									savedfilename = username.getText();
									user = new File(path + "\\" + username.getText() + ".txt");
									fr = new FileReader(user);
									br = new BufferedReader(fr);
									
									contents = br.readLine();
									//System.out.println("stuff2");
									while(contents != null) {
										if(contents.contains("Password")) {
											StringTokenizer tk = new StringTokenizer(contents);
											tk.nextToken();
											String encrypted = tk.nextToken();
											System.out.println(encrypted);
											String decrypted = cc.decrypt(encrypted);
											System.out.println(decrypted);
											//System.out.println("stuff2");
											if(decrypted.equals(password.getText())) {
												System.out.println("match");
												authenticated = true;
												notifyObserver(authenticated);
												break;
											}
											else {
												JOptionPane.showMessageDialog(this, "Password is incorrect. Please try again.");
												break;
											}
										}
										contents = br.readLine();
									}
								}
								if(authenticated == true)
									break;
								else
									name = br.readLine();
							}
							if(name == null){
								JOptionPane.showMessageDialog(this, "Username does not exist. Please select new user2.");
							}
							
						}
						br.close();
					}
					else {
						JOptionPane.showMessageDialog(this, "Please enter username and password.");
					}
				}
				
				if(action.equals("new")) {
					
					fr = new FileReader(directoryFile);
					br = new BufferedReader(fr);
					
					String path = br.readLine();
					
					if(!directoryFile.exists()) {
						JOptionPane.showMessageDialog(this, "No save files exist. Please select a save directory.");
						showSaveFileDialog();
					}
					
					if(!username.getText().equals("") && !password.getText().equals("")) {
						//CaesarCipher cc = new CaesarCipher(shift);
						user = new File(path + "\\" + username.getText() + ".txt");
						
						System.out.println(password.getText());
						String encrypted = cc.encrypt(password.getText());
						System.out.println(encrypted);
						FileWriter fw = new FileWriter(user, true);
						BufferedWriter bw = new BufferedWriter(fw);
						
						bw.append("Username " + username.getText());
						bw.newLine();
						bw.append("Password " + encrypted);
						bw.newLine();
						bw.append("Color Black");
						bw.newLine();
						bw.append("Endless_easy 0");
						bw.newLine();
						bw.append("Endless_medium 0");
						bw.newLine();
						bw.append("Endless_hard 0");
						bw.newLine();
						bw.append("Efficiency_easy 0");
						bw.newLine();
						bw.append("Efficiency_medium 0");
						bw.newLine();
						bw.append("Efficiency_hard 0");
						bw.close();
						
						
						fw = new FileWriter(usersFile.getAbsoluteFile(), true);
						bw = new BufferedWriter(fw);
						
						bw.append(username.getText());
						bw.newLine();
						bw.close();
						authenticated = true;
						notifyObserver(authenticated);
						
					}
					else {
						JOptionPane.showMessageDialog(this, "Please enter username and password.");
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	private void showSaveFileDialog() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Please Specify Save Game Location");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//File fileToSave = null;
		//String ext = ".db";
		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			if(directoryFile.exists()) {
				directoryFile.delete();
			}
			directoryFile = new File("SaveDirectory.txt");
			FileWriter fw = new FileWriter(directoryFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileChooser.getSelectedFile().getPath());
			bw.close();
		}
		//db.createTable();
		//notifyDatabase(db);
	}
	
	public JButton getExistingButton() {
		return existingUser;
	}
	
	public JButton getNewButton() {
		return newUser;
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	public void notifyObserver(boolean authenticated) {
		for(int x = 0; x < observers.size(); x++) {
			observers.get(x).update(authenticated);
		}
	}
	
//	private void showOpenFileDialog() {
//		JFileChooser fileChooser = new JFileChooser();
//		JFrame frame = new JFrame("fileChooser");
//		fileChooser.setDialogTitle("Specify a file to Open");
//		File fileToOpen = null;
//		int userSelection = fileChooser.showOpenDialog(frame);
//		if (userSelection == JFileChooser.APPROVE_OPTION) {
//			fileToOpen = fileChooser.getSelectedFile();
//			frame.dispose();
//			System.out.println("File Opened: " + fileToOpen.getAbsolutePath());
//		}
//		//db = new Database(fileToOpen.getName());
//		//db.createTable();
//		//notifyDatabase(db);
//	}
}

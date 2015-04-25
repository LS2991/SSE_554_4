import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class SaveFile 
{
	String name, password, color;
	int endlesseasy,endlessmed,endlesshard;
	double effeasy,effmedium,effhard;
	
	public SaveFile() 
	{
		File directoryFile = new File("saveDirectory.txt");
		FileReader fr = null;
		try {
			fr = new FileReader(directoryFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		
		String path = null;
		try {
			path = br.readLine() + "\\" + LoginFrame.savedfilename + ".txt";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File information = new File(path);
		try {
			fr = new FileReader(information);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);
		
		String contents = null;
		try {
			contents = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(contents != null) 
		{
			StringTokenizer tk = new StringTokenizer(contents);
			String type = tk.nextToken();
			if(type.equals("Username"))
				name = tk.nextToken();
			else if(type.equals("Password"))
				password = tk.nextToken();
			else if(type.equals("Color"))
				color = tk.nextToken();
			else if(type.equals("Endless_easy"))
				endlesseasy = Integer.parseInt(tk.nextToken());
			else if(type.equals("Endless_medium"))
				endlessmed = Integer.parseInt(tk.nextToken());
			else if(type.equals("Endless_hard"))
				endlesshard = Integer.parseInt(tk.nextToken());
			else if(type.equals("Efficiency_easy"))
				effeasy = Double.parseDouble(tk.nextToken());			
			else if(type.equals("Efficiency_medium"))
				effmedium = Double.parseDouble(tk.nextToken());	
			else if(type.equals("Efficiency_hard"))
				effhard = Double.parseDouble(tk.nextToken());
			try {
				contents = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print()
	{
		System.out.println("Username " + name);
		System.out.println("Password " + password);
		System.out.println("Color " + color);
		System.out.println("Endless Easy Score " + endlesseasy);
		System.out.println("Endless Medium Score " + endlessmed);
		System.out.println("Endless Hard Score " + endlesshard);
		System.out.println("Efficiency Easy Score " + effeasy);
		System.out.println("Efficiency Medium Score " + effmedium);
		System.out.println("Efficiency Hard Score " + effhard);
	}
	
	public int getEndlessEasy()
	{
		return endlesseasy;
	}
	
	public int getEndlessMedium()
	{
		return endlessmed;
	}
	
	public int getEndlessHard()
	{
		return endlesshard;
	}
	
	public double getEfficiencyEasy()
	{
		return effeasy;
	}
	
	public double getEfficiencyMedium()
	{
		return effmedium;
	}
	
	public double getEfficiencyHard()
	{
		return effhard;
	}
	
	public void setEndlessEasy(int endlesseasy)
	{
		this.endlesseasy =  endlesseasy;
	}
	
	public void setEndlessMedium(int endlessmed)
	{
		this.endlessmed =  endlessmed;
	}
	
	public void setEndlessHard(int endlesshard)
	{
		this.endlesshard = endlesshard;
	}
	
	public void setEfficiencyEasy(double effeasy)
	{
		this.effeasy = effeasy;
	}
	
	public void setEfficiencyMedium(double effmedium)
	{
		this.effmedium = effmedium;
	}
	
	public void setEfficiencyHard(double effhard)
	{
		this.effhard = effhard;
	}
	
	public void Savegame() throws IOException
	{
		File directoryFile = new File("saveDirectory.txt");
		FileReader fr = null;
		try {
			fr = new FileReader(directoryFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		
		String path = null;
		try {
			path = br.readLine() + "\\" + LoginFrame.savedfilename + ".txt";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File information = new File(path);
		FileWriter fw = null;
			 try {
				fw = new FileWriter(information);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.append("Username " + name);
		bw.newLine();
		bw.append("Password " + password);
		bw.newLine();
		bw.append("Color " + color);
		bw.newLine();
		bw.append("Endless_easy " + endlesseasy);
		bw.newLine();
		bw.append("Endless_medium " + endlessmed);
		bw.newLine();
		bw.append("Endless_hard " + endlesshard);
		bw.newLine();
		bw.append("Efficiency_easy " + effeasy);
		bw.newLine();
		bw.append("Efficiency_medium " + effmedium);
		bw.newLine();
		bw.append("Efficiency_hard " + effhard);
		bw.close();
	}
}

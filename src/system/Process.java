package system;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Process {

	public enum state {
		READY, FINISHED, BLOCKED
	};
	
	private int turnaroundTime;
	private int faults;
	private int startframe, endframe;
	private ArrayList<Integer> faultTimes;
	private state processState;
	private String ID;
	private ArrayList<Page> pages;

	/*public ArrayList<Page> readIn(String filename) {
		ArrayList<Page> pages = new ArrayList<Page>();
		Scanner input;
		try {
			input = new Scanner(new FileInputStream(filename));
			while (true) {
				String next = input.nextLine();
				if (next.equalsIgnoreCase("end")) {
					break;
				} else {
					// check if next is a number and add to pages
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return pages;
	}*/

	public Process(String id) {
		ArrayList<Page> p = new ArrayList<Page>();
		pages = p;
		ID = id;
		turnaroundTime = 0;
		faults = 0;
		faultTimes = new ArrayList<Integer>();
		processState = state.READY;
		startframe = endframe = 0;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}
	
	public void addPage(Page p)
	{
		pages.add(p);
	}

	public void setTurnaroundTime(int t) {
		turnaroundTime = t;
	}
	
	public void accessPage(Page p)
	{
		if(pages.contains(p))
			pages.remove(p);
	}
	
	public void addFault() { faults++; }
	public void addFaultTime(int t) {faultTimes.add(t); }
	public int getFaults() { return faults; }
	public ArrayList<Integer> getFaultTimes() { return faultTimes; }
	
	public void setMemorySpace(int start, int end) { startframe = start; endframe = end; }
	
	public int getStartFrame() { return startframe; }
	public int getEndFrame() { return endframe; }
	
	public state getState() { return processState; }
	public int getTurnaroundTime() { return turnaroundTime; }
	public String getID() { return ID; }
	
	public void setState(state s)
	{
		processState = s;
		if(processState == state.BLOCKED) { System.out.println(getID() + " has been blocked"); }
		if(processState == state.READY) { System.out.println(getID() + " is now ready"); }
		if(processState == state.FINISHED) { System.out.println(getID() + " has finished"); }
	}
	
	public String toString() {
		return "This is process " + getID() + ", it is currently: " + getState()
		+ "\nPage requests: " + getPages() + "\n";
	}

}

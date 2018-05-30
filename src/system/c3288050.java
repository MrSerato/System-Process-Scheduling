package system;
import java.util.*;

import system.Process.state;

public class c3288050 {
	public static void main(String[] args)
	{
		ArrayList<Process> processes = new ArrayList<Process>();
		Process p1 = new Process("p1");
		p1.addPage(new Page(1));
		p1.addPage(new Page(3));
		p1.addPage(new Page(2));
		
		Process p2 = new Process("p2");
		p2.addPage(new Page(2));
		p2.addPage(new Page(5));
		p2.addPage(new Page(4));
		p2.addPage(new Page(1));
		
		p1.setMemorySpace(0, 14);
		p2.setMemorySpace(15, 29);
		
		processes.add(p1);
		processes.add(p2);
		//System.out.println(processes);
		
		Memory mainMem = new Memory();
		Dispatcher d = new Dispatcher();
		
		Simulation.runSystem(mainMem, processes, d);
	}
}
	
	

/*
 * Run the system here
 */
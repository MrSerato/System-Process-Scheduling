package system;

import java.util.ArrayList;

import system.Process.state;

public class Simulation {
	public static void runSystem(Memory mem, ArrayList<Process> processes, Dispatcher d) {
		int timeElapsed = 0;
		Process next;
		while (!processes.isEmpty()) {
			timeElapsed++;
			next = d.getNextReady(processes);
			System.out.println("Next process is " + next);

			for (Page p : next.getPages()) {
				timeElapsed++;
				next.accessPage(p);
				System.out.println("Currently accessing page " + p);
				boolean fault = mem.checkFault(p, next.getStartFrame(), next.getEndFrame());
				if (fault == true) {
					next.setState(state.BLOCKED);
					next.addFault();
					next.addFaultTime(timeElapsed);
					mem.getNextAvailableFrame(next.getStartFrame(), next.getEndFrame()).setPage(p);
					System.out.println("Page fault on " + next.getID());
					break;
				}
				if (next.getPages().isEmpty()) {
					System.out.println("Successfully executed " + next.getID());
					next.setTurnaroundTime(timeElapsed);
					System.out.println(
							"Process " + next + " had: " + next.getFaults() + " faults at " + next.getFaultTimes()
									+ " and finished with a turnaround time of " + next.getTurnaroundTime());
					processes.remove(next);
				}

			}
			if (timeElapsed % 2 == 0) {
				for (Process p : processes) {
					p.setState(state.READY);
				}
			}
		}
	}
}

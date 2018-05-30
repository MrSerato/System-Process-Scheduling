package system;

import java.util.*;

import system.Process.state;

public class Dispatcher {
	
	public static void RoundRobin(ArrayList<Process> processes)
	{
		int timeQuantum = 3;
	}
	
	public Process getNextReady(ArrayList<Process> readyQueue)
	{
		Process next = null;
		for(Process p : readyQueue)
		{
			if(p.getState() == state.READY)
			{
				next = p;
				break;
			}
		}
		return next;
	}

}

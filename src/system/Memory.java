package system;

import java.util.*;

public class Memory {

	class Frame {
		private int frameNo;
		private boolean isOccupied;
		private Page page;

		public Frame(int fN, boolean o) {
			frameNo = fN;
			isOccupied = o;
		}

		public void setPage(Page p) {
			page = p;
			isOccupied = true;
		}

		public int getFrameNo() {
			return frameNo;
		}

		public String toString() {
			if (isOccupied)
			{
				return "Frame " + frameNo + ": " + page;
			}
			return "Frame " + frameNo;
		}

		public Page getPage() {
			if (!isOccupied)
				return null;
			else
				return page;
		}
	}

	private ArrayList<Frame> frames;

	public void initiateMemory(ArrayList<Frame> frames) // initiate memory to have 30 frames
	{
		for (int i = 0; i < 30; i++) {
			Frame f = new Frame(i, false);
			frames.add(f);
		}
	}

	public Memory() {
		frames = new ArrayList<Frame>(30);
		initiateMemory(frames);
	}

	public Frame getFrame(int frameNo) {
		return frames.get(frameNo);
	}
	
	public Frame getNextAvailableFrame(int start, int end)
	{
		Frame next = null;
		for(int i = start; i < end; i++)
		{
			if (!frames.get(i).isOccupied)
			{
				next = frames.get(i);
				break;
			}
		}
		return next;
	}

	public Page getFrameContents(int frameNo) {
		if (getIsOccupied(frameNo))
			return getFrame(frameNo).page;
		else
			return null;
	}

	public boolean getIsOccupied(int frameNo) {
		if (getFrame(frameNo).isOccupied)
			return true;
		else
			return false;
	}

	public void addPage(int frame, Page p) {
		getFrame(frame).setPage(p);
		System.out.println("Frame " + getFrame(frame) + " now contains " + p);
	}

	public boolean checkFault(Page p, int start, int end) {
		boolean pageFault = true;
		//System.out.println("Checking for " + p + " in frames " + start + " - " + end);
		for (int i = start; i < end; i++) {
			//System.out.println("Checking frame: " + i);
			//System.out.println("Frame " + frames.get(i) + " contains: " + frames.get(i).getPage());
			if (frames.get(i).isOccupied)
			{
				if(frames.get(i).getPage().getNumber() == p.getNumber())
				pageFault = false;
				//System.out.println("FOUND " + p + " IN MAIN MEM, NO PAGE FAULT");
			}
		}
		return pageFault;
	}

	public String toString() {
		return "This is the main memory: " + frames;
	}
}
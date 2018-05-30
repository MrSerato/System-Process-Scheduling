package system;

/*
 * Basic page class, all processes will contain pages that are essentially
 * instructions to execute. The page class simply contains a number. Pages will be
 * stored in an ArrayList parameter of a process
 */

public class Page {
	private int number;

	public Page(int n) {
		number = n;
	}

	public int getNumber() {
		return number;
	}

	public String toString() {
		return "Page " + getNumber();
	}
}
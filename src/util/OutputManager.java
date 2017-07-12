package util;

public class OutputManager {

	private static OutputManager instance = null;
	private String log;

	private OutputManager() {
	}

	public static OutputManager getInstance() {
		if (instance == null) {
			instance = new OutputManager();
		}
		return instance;
	}

	public void generatesOutput(String outputLine) {
		System.out.print("\n" +outputLine);
		this.log += "\n" +outputLine;

	}
	
	public static void main(String[] args) {
		System.out.print("n\n\nn");
	}

}

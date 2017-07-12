package simulation.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputManager {

	private static OutputManager instance = null;
	private String log;

	private OutputManager() {
		this.log = "";
	}

	public static OutputManager getInstance() {
		if (instance == null) {
			instance = new OutputManager();
		}
		return instance;
	}

	public void generatesOutput(String outputLine) {
		System.out.print("\n" + outputLine);
		this.log += "\n" + outputLine;
	}

	public void logData(String filePath) {
		BufferedWriter logger = null;
		try {
			FileOutputStream fs = new FileOutputStream(filePath, true);
			OutputStreamWriter ow = new OutputStreamWriter(fs);
			logger = new BufferedWriter(ow);
			logger.write(this.log);
			logger.newLine();
			
		} catch (IOException e) {
			e.printStackTrace();

		}finally{
			try {
				logger.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(OutputManager.getInstance().log);
	}

}

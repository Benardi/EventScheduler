package simulation.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

public class OutputManager {
	public static final String EXECUTION_HEADER = "SIMULATION START TIME: ";
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

	public void logData(Timestamp stamp, String filePath) {
		BufferedWriter logger = null;
		try {
			FileOutputStream fs = new FileOutputStream(filePath, true);
			OutputStreamWriter ow = new OutputStreamWriter(fs);
			logger = new BufferedWriter(ow);
			logger.newLine();
			logger.write(EXECUTION_HEADER + stamp.toString());
			logger.newLine();
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

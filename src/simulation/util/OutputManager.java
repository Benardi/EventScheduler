package simulation.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OutputManager {
	public static final String EXECUTION_HEADER = "SIMULATION START TIME: ";
	private static OutputManager instance = null;
	private List<String> logs;

	private OutputManager() {
		this.logs = new ArrayList<String>();
	}

	public static OutputManager getInstance() {
		if (instance == null) {
			instance = new OutputManager();
		}
		return instance;
	}

	public void generatesOutput(String outputLine) {
		// Application no longer feeds the console.
		//System.out.print("\n" + outputLine);
		this.logs.add(outputLine);
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
			for(String line: logs){
				logger.newLine();
				logger.write(line);
			}
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


}

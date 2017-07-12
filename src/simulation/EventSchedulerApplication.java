package simulation;

import java.sql.Timestamp;

import simulation.event.TimeLineHandler;
import simulation.util.OutputManager;

public class EventSchedulerApplication {

	public static void main(String[] args) {
		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		TimeLineHandler th = new TimeLineHandler();
		th.simulates(50);
		OutputManager.getInstance().logData(timeStamp, "SimulationResults.txt");

	}
}

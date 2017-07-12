package simulation;

import java.sql.Timestamp;
import java.time.Instant;

import simulation.event.TimeLineHandler;
import simulation.util.OutputManager;

public class EventSchedulerApplication {

	public static void main(String[] args) {
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		
		TimeLineHandler th = new TimeLineHandler(50);
		th.simulates();
		OutputManager.getInstance().logData(timeStamp, "SimulationResults.txt");

	}
}

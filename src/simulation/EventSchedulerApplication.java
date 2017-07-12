package simulation;

import java.sql.Timestamp;

import simulation.event.EventChronologizer;
import simulation.util.OutputManager;

public class EventSchedulerApplication {

	public static void main(String[] args) {
		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		EventChronologizer th = new EventChronologizer();
		th.simulates(50);
		OutputManager.getInstance().logData(timeStamp, "SimulationResults.txt");

	}
}

package simulation;

import java.sql.Timestamp;
import java.util.Scanner;

import simulation.event.EventChronologizer;
import simulation.util.OutputManager;

public class EventSchedulerApplication {

	public static void main(String[] args) {

		Scanner sc = null;

		try {
			while (true) {
				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
				EventChronologizer th = new EventChronologizer();

				sc = new Scanner(System.in);
				System.out.println("Please insert time of simulation in milliseconds");
				long exectTimeMilli = sc.nextLong();
				th.simulates(exectTimeMilli);
				OutputManager.getInstance().logData(timeStamp, "SimulationResults.txt");
				System.out.println("Would you like to run another simulation? Y/N");
				String answer = sc.next();

				if (answer.toLowerCase().contains("n")) {
					System.out.println("Application terminated");
					break;
				}
			}
		} catch (Exception e) {

		} finally {
			sc.close();

		}

	}
}

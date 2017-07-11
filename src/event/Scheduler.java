package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import client.Client;
import client.ClientClasses;
import util.ProbabilityManager;

public class Scheduler {

	private HashMap<Integer, EventTypes> timeLine;
	private ProbabilityManager randomizer;
	private ArrayList<Client> queue1;
	private ArrayList<Client> queue2;
	private boolean isServerFree;

	public Scheduler() {
		this.timeLine = new HashMap<Integer, EventTypes>();
		this.randomizer = new ProbabilityManager();
		enqueueCustomer(new Client(ClientClasses.Class1));
		enqueueCustomer(new Client(ClientClasses.Class2));

	}
	
	private void scheduleEvent(int timeOfEvent, EventTypes event) {
		this.timeLine.put(timeOfEvent, event);

	}

	private int predictTimeOfEvent(int currentTime, int minimumTime, int maximumTime) {

		return currentTime + this.randomizer.generateValue(minimumTime, maximumTime);

	}

	private void enqueueCustomer(Client customer) {
		if (customer.getGroup() == ClientClasses.Class1) {
			this.queue1.add(customer);
		} else if (customer.getGroup() == ClientClasses.Class2) {
			this.queue2.add(customer);
		}
	}

	private void dequeueCustomer(ClientClasses type) {
		if (type == ClientClasses.Class1) {
			this.queue1.remove(0);
		} else if (type == ClientClasses.Class2) {
			this.queue2.remove(0);
		}

	}

	public boolean isServerFree() {
		return isServerFree;
	}

	public void setServerFree(boolean isServerFree) {
		this.isServerFree = isServerFree;
	}

	public boolean isQueue1Free() {
		return this.queue1.isEmpty();
	}

	public boolean isQueue2Free() {
		return this.queue2.isEmpty();
	}

	

	public void ClientGroup1Influx(int currentTime) {
		scheduleEvent(predictTimeOfEvent(currentTime, 1, 10), EventTypes.ClIENT_GROUP1_INFLUX);

		if (this.isServerFree) {
			this.setServerFree(false);
			scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION);
		} else {
			enqueueCustomer(new Client(ClientClasses.Class1));
		}

	}

	public void ClientGroup2Influx(int currentTime) {

		scheduleEvent(predictTimeOfEvent(currentTime, 1, 5), EventTypes.ClIENT_GROUP1_INFLUX);

		if (this.isServerFree) {
			this.setServerFree(false);
			scheduleEvent(predictTimeOfEvent(currentTime, 1, 5), EventTypes.SERVICE_TERMINATION);
		} else {
			enqueueCustomer(new Client(ClientClasses.Class2));
		}

	}

	public void terminateService(int currentTime) {
		if (isQueue1Free()) {
			if (isQueue2Free()) {
				setServerFree(true);
			} else {
				dequeueCustomer(ClientClasses.Class2);
				scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION);
			}

		} else {
			dequeueCustomer(ClientClasses.Class1);
			scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION);

		}
	}

}

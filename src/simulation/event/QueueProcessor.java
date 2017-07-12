package simulation.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import simulation.client.Customer;
import simulation.client.CustomerClasses;
import simulation.util.OutputManager;
import simulation.util.ProbabilityManager;

public class QueueProcessor {

	private ProbabilityManager randomizer;
	private List<Customer> queue1;
	private List<Customer> queue2;
	private boolean isServerFree;
	private Customer sendoAtendido;

	public QueueProcessor(HashMap<Integer, List<EventTypes>> timeLine) {
		this.randomizer = new ProbabilityManager();
		this.queue1 = new ArrayList<Customer>();
		this.queue2 = new ArrayList<Customer>();
		this.isServerFree = true;

	}

	public void scheduleEvent(int timeOfEvent, EventTypes event, HashMap<Integer, List<EventTypes>> timeLine) {
		List<EventTypes> timeState = timeLine.get(timeOfEvent);
		if (timeState != null) {
			timeState.add(event);
			timeLine.put(timeOfEvent, timeState);
		} else {
			timeState = new ArrayList<EventTypes>();
			timeState.add(event);
			timeLine.put(timeOfEvent, timeState);
		}

	}

	private int predictTimeOfEvent(int currentTime, int minimumTime, int maximumTime) {

		return currentTime + this.randomizer.generateValue(minimumTime, maximumTime);

	}

	private void enqueueCustomer(Customer customer) {
		if (customer.getGroup() == CustomerClasses.Class1) {
			this.queue1.add(customer);
		} else if (customer.getGroup() == CustomerClasses.Class2) {
			this.queue2.add(customer);
		}
	}

	private Customer dequeueCustomer(CustomerClasses type) {
		Customer removed = null;
		this.sendoAtendido = null;
		if (type == CustomerClasses.Class1) {
			removed = this.queue1.get(0);
			this.queue1.remove(0);
		} else if (type == CustomerClasses.Class2) {
			removed = this.queue2.get(0);
			this.queue2.remove(0);
		}

		return removed;

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

	public void ClientGroup1Influx(int currentTime, HashMap<Integer, List<EventTypes>> timeLine) {
		Customer customer = new Customer(CustomerClasses.Class1);
		scheduleEvent(predictTimeOfEvent(currentTime, 1, 10), EventTypes.ClIENT_GROUP1_INFLUX, timeLine);
		
		if (isServerFree) {
			this.setServerFree(false);
			sendoAtendido = customer;
			scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION, timeLine);
		} else {
			enqueueCustomer(customer);
		}

	}

	public void ClientGroup2Influx(int currentTime, HashMap<Integer, List<EventTypes>> timeLine) {
		Customer customer = new Customer(CustomerClasses.Class2);
		scheduleEvent(predictTimeOfEvent(currentTime, 1, 5), EventTypes.ClIENT_GROUP2_INFLUX, timeLine);
		
		if (isServerFree) {
			this.setServerFree(false);
			sendoAtendido = customer;
			scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION, timeLine);
		} else {
			enqueueCustomer(customer);
		}

	}

	public void terminateService(int currentTime, HashMap<Integer, List<EventTypes>> timeLine) {
		if (isQueue1Free()) {
			if (isQueue2Free()) {
				setServerFree(true);
			} else {
				Customer removed = dequeueCustomer(CustomerClasses.Class2);
				scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION, timeLine);
				sendoAtendido = removed;

			}

		} else {
			Customer removed = dequeueCustomer(CustomerClasses.Class1);
			scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION, timeLine);
			sendoAtendido = removed;
		}

	}

	public Customer getSendoAtendido() {
		return sendoAtendido;
	}

	public void generatesOutput() {
		OutputManager outMan = OutputManager.getInstance();
		outMan.generatesOutput("Elementos na Fila 1: " + this.queue1.toString());
		outMan.generatesOutput("Elementos na Fila 2: " + this.queue2.toString());
		
	}

}

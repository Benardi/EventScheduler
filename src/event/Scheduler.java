package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import client.Customer;
import client.CustomerClasses;
import util.ProbabilityManager;

public class Scheduler {

	private HashMap<Integer, EventTypes> timeLine;
	private ProbabilityManager randomizer;
	private List<Customer> queue1;
	private List<Customer> queue2;
	private boolean isServerFree;

	public Scheduler() {
		this.timeLine = new HashMap<Integer, EventTypes>();
		this.randomizer = new ProbabilityManager();
		this.queue1 = new ArrayList<Customer>();
		this.queue2 = new ArrayList<Customer>();
		enqueueCustomer(new Customer(CustomerClasses.Class1));
		enqueueCustomer(new Customer(CustomerClasses.Class2));

	}

	private void scheduleEvent(int timeOfEvent, EventTypes event) {
		this.timeLine.put(timeOfEvent, event);

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

	private void dequeueCustomer(CustomerClasses type) {
		if (type == CustomerClasses.Class1) {
			this.queue1.remove(0);
		} else if (type == CustomerClasses.Class2) {
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
			enqueueCustomer(new Customer(CustomerClasses.Class1));
		}

	}

	public void ClientGroup2Influx(int currentTime) {

		scheduleEvent(predictTimeOfEvent(currentTime, 1, 5), EventTypes.ClIENT_GROUP1_INFLUX);

		if (this.isServerFree) {
			this.setServerFree(false);
			scheduleEvent(predictTimeOfEvent(currentTime, 1, 5), EventTypes.SERVICE_TERMINATION);
		} else {
			enqueueCustomer(new Customer(CustomerClasses.Class2));
		}

	}

	public void terminateService(int currentTime) {
		if (isQueue1Free()) {
			if (isQueue2Free()) {
				setServerFree(true);
			} else {
				dequeueCustomer(CustomerClasses.Class2);
				scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION);
			}

		} else {
			dequeueCustomer(CustomerClasses.Class1);
			scheduleEvent(predictTimeOfEvent(currentTime, 3, 7), EventTypes.SERVICE_TERMINATION);

		}
	}
	
	public void generatesOutput(){
		System.out.println("Elementos na Fila 1: " + this.queue1.toString());
		System.out.println("Elementos na Fila 2: " + this.queue2.toString());
	}
	
	public static void main(String[] args) {
		Scheduler sch = new Scheduler();
		sch.generatesOutput();
		sch.ClientGroup2Influx(0);
		sch.ClientGroup2Influx(1);
		sch.generatesOutput();
		sch.terminateService(2);
		sch.terminateService(3);
		sch.terminateService(4);
		sch.terminateService(5);
		sch.generatesOutput();
	}

}

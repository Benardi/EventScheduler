package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import client.Client;
import client.ClientClasses;

public class Scheduler {

	private HashMap<ClientClasses, Integer> timeLine;
	private ArrayList<Client> queue1;
	private ArrayList<Client> queue2;
	private boolean isServerFree;
	

	public Scheduler() {
		this.timeLine = new HashMap<ClientClasses, Integer>();
		this.queue1.add(new Client(ClientClasses.Class1));
		this.queue2.add(new Client(ClientClasses.Class2));
	}
	
	public boolean isServerFree() {
		return isServerFree;
	}

	public void setServerFree(boolean isServerFree) {
		this.isServerFree = isServerFree;
	}

	public boolean isQueue1Free(){
		return this.queue1.isEmpty();
	}
	
	public boolean isQueue2Free(){
		return this.queue2.isEmpty();		
	}
	
	public static void main(String[] args) {
	}
	


}

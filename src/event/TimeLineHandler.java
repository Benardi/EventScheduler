package event;

import java.util.HashMap;
import java.util.List;


public class TimeLineHandler {
	private HashMap<Integer, List<EventTypes>> timeLine;
	private Scheduler eventHandler;
	private int maxRuns;
	
	public TimeLineHandler() {
		this.timeLine = new HashMap<Integer, List<EventTypes>>();
		this.eventHandler = new Scheduler(this.timeLine);
		this.maxRuns = Integer.MAX_VALUE;
	}
	
	public TimeLineHandler(int maxRuns) {
		this.timeLine = new HashMap<Integer, List<EventTypes>>();
		this.eventHandler = new Scheduler(this.timeLine);
		this.maxRuns = maxRuns;
	}
	
	
	public void simulates(){
		int runs = 0;
		while(runs < this.maxRuns){
			
		}
		
		
	}
	
	

}

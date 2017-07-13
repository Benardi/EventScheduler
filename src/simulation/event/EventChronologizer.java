package simulation.event;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import simulation.util.OutputManager;

public class EventChronologizer {
	private HashMap<Integer, List<EventTypes>> timeLine;
	private EventProcessor eventHandler;

	public EventChronologizer() {
		this.timeLine = new HashMap<Integer, List<EventTypes>>();
		this.eventHandler = new EventProcessor(this.timeLine);
		initializeModel();
	}

	private void initializeModel() {
		this.eventHandler.scheduleEvent(0, EventTypes.ClIENT_GROUP1_INFLUX, this.timeLine);
		this.eventHandler.scheduleEvent(0, EventTypes.ClIENT_GROUP2_INFLUX, this.timeLine);
	}

	public void executesStateOfTime(int stateOfTime) {
		List<EventTypes> lineOfEvents = this.timeLine.get(stateOfTime);
		if (lineOfEvents != null) {

			Iterator<EventTypes> it = lineOfEvents.iterator();

			while (it.hasNext()) {
				EventTypes event = (EventTypes) it.next();
				this.executeEvent(event, stateOfTime);

			}

		}
	}

	private void executeEvent(EventTypes event, int currentTime) {

		if (event == EventTypes.ClIENT_GROUP1_INFLUX) {
			eventHandler.ClientGroup1Influx(currentTime, this.timeLine);
			this.stampsState("Chegada", currentTime);
		} else if (event == EventTypes.ClIENT_GROUP2_INFLUX) {
			eventHandler.ClientGroup2Influx(currentTime, this.timeLine);
			this.stampsState("Chegada", currentTime);
		} else if (event == EventTypes.SERVICE_TERMINATION) {
			eventHandler.terminateService(currentTime, this.timeLine);
			this.stampsState("Saida", currentTime);
		}

		stampsCurrentCostumer();

	}

	private void stampsCurrentCostumer() {

		if (this.eventHandler.getSendoAtendido() != null) {
			OutputManager.getInstance()
					.generatesOutput("Elemento no servico: " + this.eventHandler.getSendoAtendido().toString());
			OutputManager.getInstance().generatesOutput("");
		} else {
			OutputManager.getInstance().generatesOutput("Elemento no servico: - ");
			OutputManager.getInstance().generatesOutput("");
		}

	}

	private void stampsState(String event, int state) {
		OutputManager.getInstance().generatesOutput("Tipo de evento: " + event + ", Momento do evento: " + state);
		eventHandler.generatesOutput();

	}

	public void simulates(int maxRuns) {
		int state = 0;
		while (state < maxRuns) {
			this.executesStateOfTime(state);
			state++;

		}
	}

	public void simulates(long timeMilliSecs) {
		long execTime = System.currentTimeMillis() + timeMilliSecs;
		
		int state = 0;
		while (System.currentTimeMillis() < execTime) {
			this.executesStateOfTime(state);
			state++;

		}

	}

}

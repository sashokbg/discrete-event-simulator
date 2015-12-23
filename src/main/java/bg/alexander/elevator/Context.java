package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.simulator.Event;
import bg.alexander.simulator.Simulator;

public class Context {
	private State state;
	private final Logger log = LogManager.getLogger(this.getClass());
	private Simulator simulator;
	
	public Context(Simulator simulator) {
		//initial state is stopped, doors closed
		this.state = new StateDoorsClosed();
		this.simulator = simulator;
	}

	public void addEvent(Event e){
		this.simulator.addEvent(e);
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		log.info(state);
		this.state = state;
	}
	
	public State pressButton(){
		log.info("Button pressed");
		return state.transitionToNext(this);
	}

	public int getCurrentTime() {
		return simulator.getCurrentTime();
	}
}

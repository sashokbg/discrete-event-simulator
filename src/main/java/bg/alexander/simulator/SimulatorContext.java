package bg.alexander.simulator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.Context;
import bg.alexander.State;
import bg.alexander.elevator.states.DoorsClosed;

public class SimulatorContext implements Context {
	private State state;
	private final Logger log = LogManager.getLogger(this.getClass());
	private Simulator simulator;
	
	public SimulatorContext(Simulator simulator) {
		//initial state is stopped, doors closed
		this.state = new DoorsClosed();
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

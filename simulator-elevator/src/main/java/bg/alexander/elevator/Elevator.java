package bg.alexander.elevator;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.Context;
import bg.alexander.State;
import bg.alexander.elevator.states.DoorsClosed;
import bg.alexander.simulator.Event;

public class Elevator implements Context{
	private Logger log = LogManager.getLogger(Elevator.class);
	private int capacity = 4;
	private State currentState;
	private Set<Passenger> passengers;
	private Context context;
	
	public Elevator(){
		passengers = new HashSet<Passenger>(capacity);
		currentState = new DoorsClosed();
	}
	
	public void pressButton(){
		currentState.transitionToNext(this);
	}

	@Override
	public void setState(State newState) {
		log.info(newState);
		currentState = newState;
	}

	@Override
	public State getState() {
		return this.currentState;
	}

	@Override
	public void addEvent(Event event) {
		context.addEvent(event);
	}

	/**
	 * Return the time from the parent context, since this context is not aware of it
	 */
	@Override
	public int getCurrentTime() {
		return context.getCurrentTime();
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}
}

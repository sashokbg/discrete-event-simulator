package bg.alexander.elevator.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.State;
import bg.alexander.TransitionState;
import bg.alexander.elevator.Context;
import bg.alexander.simulator.Event;

public class StateDoorsOpened implements State {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public State transitionToNext(Context context) {
		log.info("Door opened");
		TransitionState nextState = new StateDoorsClosing();
		
		Event doorsClosedEvent = new Event();
		doorsClosedEvent.setMessage("Doors closed");
		doorsClosedEvent.setAction((w)-> {
			context.setState(new StateDoorsClosed());
		});
		doorsClosedEvent.setTime(30);
		nextState.onTransition(doorsClosedEvent);
		
		context.addEvent(new Event(null,"Doors opening"));
		context.addEvent(doorsClosedEvent);
		
		context.setState(nextState);
		
		return nextState;
	}
	
	@Override
	public String toString(){
		return "State - Doors opened";
	}
}

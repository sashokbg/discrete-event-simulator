package bg.alexander.elevator.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.State;
import bg.alexander.TransitionState;
import bg.alexander.simulator.SimulatorContext;
import bg.alexander.simulator.Event;

public class DoorsOpened implements State {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public State transitionToNext(SimulatorContext context) {
		log.info("Door opened");
		TransitionState nextState = new DoorsClosing();
		
		Event doorsClosedEvent = new Event();
		doorsClosedEvent.setMessage("Doors closed");
		doorsClosedEvent.setAction((w)-> {
			context.setState(new DoorsClosed());
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

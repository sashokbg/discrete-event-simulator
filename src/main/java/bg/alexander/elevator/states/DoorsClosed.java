package bg.alexander.elevator.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.State;
import bg.alexander.TransitionState;
import bg.alexander.simulator.SimulatorContext;
import bg.alexander.simulator.Event;

public class DoorsClosed implements State{
	private final Logger log = LogManager.getLogger(this.getClass());
	
	/**
	 * <p>
	 * 	{@inheritDoc}
	 * </p>
	 * <p>
	 * 	Next state is StateDoorsOpening
	 * </p>
	 */
	@Override
	public State transitionToNext(SimulatorContext context) {
		TransitionState nextState = new DoorsOpening();
		context.setState(nextState);
		log.debug("Transitioning to "+nextState);
		
		Event doorsOpenedEvent = new Event();
		doorsOpenedEvent.setMessage("Doors opened");
		doorsOpenedEvent.setAction((w)-> {
			context.setState(new DoorsOpened());
		});
		doorsOpenedEvent.setTime(30);
		nextState.onTransition(doorsOpenedEvent);
		
		context.addEvent(new Event(null,"Doors opening"));
		context.addEvent(doorsOpenedEvent);
		
		return nextState;
	}
	
	@Override
	public String toString(){
		return "State - Doors closed";
	}
}

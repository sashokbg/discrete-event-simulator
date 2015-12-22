package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.simulator.Event;

public class StateStopped implements State{
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public State transition(Context context) {
		State newState = new StateDoorsOpening();
		context.setState(newState);
		
		Event doorsOpenedEvent = new Event();
		doorsOpenedEvent.setMessage("Doors opened");
		doorsOpenedEvent.setAction((w)-> {
			context.setState(new StateDoorsOpened());
		});
		doorsOpenedEvent.setTime(30);
		
		context.addEvent(doorsOpenedEvent);
		return newState;
	}
	
	@Override
	public String toString(){
		return "State - Stopped";
	}
}

package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.simulator.Event;

public class StateDoorsClosing implements TransitionState {
	private final Logger log = LogManager.getLogger(this.getClass());

	//the future doors closed event, since doors opening/closing are transitional
	private Event doorsClosedEvent;
	
	
	@Override
	public State transitionToNext(Context context) {
		log.info("Doors closing -> doors opening");
		State doorsOpeningState = new StateDoorsOpening();
		context.setState(doorsOpeningState);
		
		doorsClosedEvent.cancel();
		
		int elapsedTime = context.getCurrentTime() - doorsClosedEvent.getScheduledTime();
		
		Event doorsOpenedEvent = new Event();
		doorsOpenedEvent.setMessage("Doors closed");
		doorsOpenedEvent.setAction((w)-> {
			context.setState(new StateDoorsOpened());
		});
		doorsOpenedEvent.setTime(elapsedTime);
		
		context.addEvent(doorsOpenedEvent);
		context.addEvent(new Event(null,"Doors opening"));
		
		return doorsOpeningState;
	}
	
	@Override
	public String toString(){
		return "State - Doors closing";
	}

	public void onTransition(Event doorsClosedEvent) {
		this.doorsClosedEvent = doorsClosedEvent;
	}
}

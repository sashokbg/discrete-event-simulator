package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.simulator.Event;

public class StateDoorsOpening implements TransitionState{
	private final Logger log = LogManager.getLogger(this.getClass());
	
	//the future doors opened event, since doors opening is transitional
	private Event doorsOpenedEvent;
	
	@Override
	public State transitionToNext(Context context) {
		log.info("Doors opening -> doors closing");
		StateDoorsClosing nextState = new StateDoorsClosing();
		
		//cancel the opened event, since we are closing
		doorsOpenedEvent.cancel();
		
		int elapsedTime = context.getCurrentTime() - doorsOpenedEvent.getScheduledTime();
		
		Event doorsClosedEvent = new Event();
		doorsClosedEvent.setMessage("Doors closed");
		doorsClosedEvent.setAction((w)-> {
			context.setState(new StateDoorsClosed());
		});
		doorsClosedEvent.setTime(elapsedTime);
		nextState.onTransition(doorsClosedEvent);
		
		context.addEvent(doorsClosedEvent);
		context.addEvent(new Event(null,"Doors closing"));
		
		context.setState(nextState);
		
		return nextState;
	}
	
	public void onTransition(Event doorsOpenedEvent){
		this.doorsOpenedEvent=doorsOpenedEvent;
	}
	
	@Override
	public String toString(){
		return "State - Doors opening";
	}
}

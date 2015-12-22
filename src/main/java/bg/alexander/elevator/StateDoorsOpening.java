package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.simulator.SimpleEvent;

public class StateDoorsOpening implements State{
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public State transition(Context context) {
		log.info("Doors opening ..");
		State nextState = new StateDoorsClosing();
		context.addEvent(new SimpleEvent("Doors opening"));
		context.setState(nextState);
		return nextState;
	}
	
	@Override
	public String toString(){
		return "State - Doors opening";
	}
}

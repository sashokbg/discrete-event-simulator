package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateDoorsOpened implements State {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public State transition(Context context) {
		log.info("Door opened");
		
		State newState = new StateDoorsClosing();
		context.setState(newState);
		return newState;
	}
	
	@Override
	public String toString(){
		return "State - Doors opened";
	}
}

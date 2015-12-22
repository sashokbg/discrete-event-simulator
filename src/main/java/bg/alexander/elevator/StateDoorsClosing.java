package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateDoorsClosing implements State {
	private final Logger log = LogManager.getLogger(this.getClass());

	@Override
	public State transition(Context context) {
		log.info("Doors closing");
		State newState = new StateDoorsOpening();
		context.setState(newState);
		
		return newState;
	}

	@Override
	public String toString(){
		return "State - Doors closing";
	}
}

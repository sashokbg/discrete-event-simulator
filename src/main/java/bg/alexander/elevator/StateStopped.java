package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateStopped implements State{
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public void transition(Context context) {
		log.info("Elevator stopped");
		State newState = new StateDoorsOpening();
		context.setState(newState);
		newState.transition(context);
	}
}

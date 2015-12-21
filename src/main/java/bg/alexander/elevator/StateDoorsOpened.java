package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateDoorsOpened implements State {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Override
	public void transition(Context context) {
		log.info("Door opened");
	}
}

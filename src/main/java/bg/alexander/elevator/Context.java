package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Context {
	private State state;
	private final Logger log = LogManager.getLogger(this.getClass());
	
	public Context() {
		//initial state is stopped, doors closed
		this.state = new StateStopped();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void pressButton(){
		log.info("Button pressed");
		state.transition(this);
	}
}

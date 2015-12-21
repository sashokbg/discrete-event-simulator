package bg.alexander.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateDoorsOpening implements State{
	private final Logger log = LogManager.getLogger(this.getClass());
	private boolean inTransition;
	
	public StateDoorsOpening() {
		inTransition = false;
	}
	
	@Override
	public void transition(Context context) {
		log.info("Doors opening ..");
		State nextState = new StateDoorsOpened();
		if(inTransition){
			nextState = new StateDoorsClosing();
		}
		try {
			inTransition = true;
			Thread.sleep(3000);
			inTransition = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		context.setState(nextState);
		nextState.transition(context);
	}
}

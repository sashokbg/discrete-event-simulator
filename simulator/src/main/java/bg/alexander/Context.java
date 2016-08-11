package bg.alexander;

import bg.alexander.simulator.Event;
import bg.alexander.simulator.Simulator;

/**
 * A context is an abstract notion that represents the state, time and events <br/>
 * related to a given object
 * 
 * Every context should be able to change its state, receive and handle events <br/>
 * chaining them to parent context if necessary and provide the current time to its callers
 * 
 * <p>Most often the master context is the {@link Simulator} object</p>
 * 
 * @author Kirilov
 *
 */
public interface Context {
	void setState(State newState);
	State getState();
	void addEvent(Event event);
	int getCurrentTime();
	void setContext(Context context);
}

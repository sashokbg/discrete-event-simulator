package bg.alexander;

import bg.alexander.simulator.Event;

public interface TransitionState extends State {
	/**
	 * The event to be executed on transitioning 
	 * 
	 * @param event
	 */
	public void onTransition(Event event);
}

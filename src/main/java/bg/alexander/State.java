package bg.alexander;

import bg.alexander.elevator.Context;

public interface State {
	/**
	 * Transition to the next state
	 * 
	 * @param context
	 * @return
	 */
	public State transitionToNext(Context context);
}

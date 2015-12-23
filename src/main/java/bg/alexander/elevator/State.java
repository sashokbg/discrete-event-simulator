package bg.alexander.elevator;

public interface State {
	/**
	 * Transition to the next state
	 * 
	 * @param context
	 * @return
	 */
	public State transitionToNext(Context context);
}

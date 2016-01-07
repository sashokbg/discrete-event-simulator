package bg.alexander;

import bg.alexander.simulator.Event;

public interface Context {
	public void setState(State newState);
	public State getState();
	public void addEvent(Event event);
	public int getCurrentTime();
	public void setContext(Context context);
}

package bg.alexander.elevator;

public interface State {
	public State transition(Context context);
}

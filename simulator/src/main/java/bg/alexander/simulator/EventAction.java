package bg.alexander.simulator;

@FunctionalInterface
public interface EventAction {
	public void takePlace(int when);
}

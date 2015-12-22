package bg.alexander.simulator;

public class SimpleEvent extends Event{

	public SimpleEvent(String message) {
		super.setAction((w)->System.out.println("EVENT ["+w+"]: "+message));
	}
}

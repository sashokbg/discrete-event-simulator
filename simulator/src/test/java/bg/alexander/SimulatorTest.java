package bg.alexander;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bg.alexander.simulator.Event;
import bg.alexander.simulator.Simulator;

public class SimulatorTest {
	private Simulator simulator;

	@Before
	public void setUp() {
		simulator = new Simulator();
		simulator.setTickTime(100);
	}

	@Test
	public void testSimulator() throws InterruptedException {
		Event innerIvent = new Event(32);
		Event instantEvent = new Event();
		instantEvent.setAction((w) -> System.out.println("Instant event " + w));

		innerIvent.setAction((w) -> System.out.println("Inner event " + w));

		Event e = new Event(28);
		e.setAction((w) -> {
			simulator.addEvent(innerIvent);
			simulator.addEvent(instantEvent);
			System.out.print("New event "+w+" ");
		});
		simulator.addEvent(e);
		simulator.run();
	}
}

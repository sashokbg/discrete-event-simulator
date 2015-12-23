package bg.alexander;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bg.alexander.elevator.Context;
import bg.alexander.elevator.StateDoorsOpened;
import bg.alexander.elevator.StateDoorsClosed;
import bg.alexander.simulator.Event;
import bg.alexander.simulator.Simulator;

public class SimulatorTest {
	private Simulator simulator;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		simulator = new Simulator();
		simulator.setTickTime(1);
	}

	@Test
	public void testSimpleTransitionOpening() throws InterruptedException {
		Context ct = new Context(simulator);
		assertEquals(ct.getState().getClass(), StateDoorsClosed.class);
		simulator.addEvent(new Event((w) -> ct.pressButton(), "Press button"));

		simulator.run();
		assertEquals(ct.getState().getClass(), StateDoorsOpened.class);
	}
	
	@Test
	public void testSimpleTransitionClosing() throws InterruptedException {
		Context ct = new Context(simulator);
		ct.setState(new StateDoorsOpened());
		assertEquals(ct.getState().getClass(), StateDoorsOpened.class);
		simulator.addEvent(new Event((w) -> ct.pressButton(), "Press button"));

		simulator.run();
		assertEquals(ct.getState().getClass(), StateDoorsClosed.class);
	}

	@Test
	public void testReverseTransition() throws InterruptedException {
		Context ct = new Context(simulator);
		assertEquals(ct.getState().getClass(), StateDoorsClosed.class);
		simulator.addEvent(new Event((w) -> ct.pressButton(), "Press button"));

		Event pressButtonEvent = new Event();
		pressButtonEvent.setTime(5);
		pressButtonEvent.setMessage("Press button - again");
		pressButtonEvent.setAction((w) -> ct.pressButton());
		simulator.addEvent(pressButtonEvent);

		simulator.run();
		assertEquals(ct.getState().getClass(), StateDoorsClosed.class);
	}

	@Test
	public void testDoubleReverseTransition() throws InterruptedException {
		Context ct = new Context(simulator);
		assertEquals(ct.getState().getClass(), StateDoorsClosed.class);
		// pressButtonEvent.setAction();
		simulator.addEvent(new Event((w) -> ct.pressButton(), "Press button"));

		Event pressButtonEvent = new Event();
		pressButtonEvent.setTime(20);
		pressButtonEvent.setMessage("Press button - again");
		pressButtonEvent.setAction((w) -> ct.pressButton());
		simulator.addEvent(pressButtonEvent);

		pressButtonEvent = new Event();
		pressButtonEvent.setTime(25);
		pressButtonEvent.setMessage("Press button - again");
		pressButtonEvent.setAction((w) -> ct.pressButton());
		simulator.addEvent(pressButtonEvent);

		simulator.run();
		assertEquals(ct.getState().getClass(), StateDoorsOpened.class);
	}

	@Test
	public void testSimulator() throws InterruptedException {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

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
		
//		assertEquals("New event 29 Instant event 30 Inner event 62 ", outContent.toString());
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
}

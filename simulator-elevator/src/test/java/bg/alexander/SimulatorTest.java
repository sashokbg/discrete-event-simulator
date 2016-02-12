package bg.alexander;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bg.alexander.elevator.Elevator;
import bg.alexander.elevator.states.DoorsClosed;
import bg.alexander.elevator.states.DoorsOpened;
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
	public void testSimpleTransitionOpening() throws InterruptedException {
		Elevator elevator = new Elevator();
		assertEquals(elevator.getState().getClass(), DoorsClosed.class);
		elevator.setContext(simulator);
		
		simulator.addEvent(new Event((w) -> elevator.pressButton(), "Press button"));

		simulator.run();
		assertEquals(elevator.getState().getClass(), DoorsOpened.class);
	}
	
	@Test
	public void testSimpleTransitionClosing() throws InterruptedException {
		Elevator elevator = new Elevator();
		elevator.setContext(simulator);
		elevator.setState(new DoorsOpened());
		assertEquals(elevator.getState().getClass(), DoorsOpened.class);
		simulator.addEvent(new Event((w) -> elevator.pressButton(), "Press button"));

		simulator.run();
		assertEquals(elevator.getState().getClass(), DoorsClosed.class);
	}

	@Test
	public void testReverseTransition() throws InterruptedException {
		Elevator elevator = new Elevator();
		elevator.setContext(simulator);
		assertEquals(elevator.getState().getClass(), DoorsClosed.class);
		simulator.addEvent(new Event((w) -> elevator.pressButton(), "Press button"));

		Event pressButtonEvent = new Event();
		pressButtonEvent.setTime(5);
		pressButtonEvent.setMessage("Press button - again");
		pressButtonEvent.setAction((w) -> elevator.pressButton());
		simulator.addEvent(pressButtonEvent);

		simulator.run();
		assertEquals(elevator.getState().getClass(), DoorsClosed.class);
	}
	
	@Test
	public void testDoubleReverseTransition() throws InterruptedException {
		Elevator elevator = new Elevator();
		elevator.setContext(simulator);
		assertEquals(elevator.getState().getClass(), DoorsClosed.class);
		// pressButtonEvent.setAction();
		simulator.addEvent(new Event((w) -> elevator.pressButton(), "Press button"));

		Event pressButtonEvent = new Event();
		pressButtonEvent.setTime(20);
		pressButtonEvent.setMessage("Press button - again");
		pressButtonEvent.setAction((w) -> elevator.pressButton());
		simulator.addEvent(pressButtonEvent);

		pressButtonEvent = new Event();
		pressButtonEvent.setTime(25);
		pressButtonEvent.setMessage("Press button - again");
		pressButtonEvent.setAction((w) -> elevator.pressButton());
		simulator.addEvent(pressButtonEvent);

		simulator.run();
		assertEquals(elevator.getState().getClass(), DoorsOpened.class);
	}
}

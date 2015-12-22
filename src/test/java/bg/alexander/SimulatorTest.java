package bg.alexander;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bg.alexander.elevator.Context;
import bg.alexander.elevator.StateDoorsOpened;
import bg.alexander.elevator.StateStopped;
import bg.alexander.simulator.Event;
import bg.alexander.simulator.Simulator;

public class SimulatorTest{

	@Test
	public void testStateTransition() throws InterruptedException {
		Simulator simulator = new Simulator();
		simulator.setTickTime(10);
		Context ct = new Context(simulator);
		assertEquals(ct.getState().getClass(), StateStopped.class);
//		Event pressButtonEvent = new Event();
//		pressButtonEvent.setAction();
		simulator.addEvent(new Event((w)->ct.pressButton(),"Press button"));
		
//		pressButtonEvent = new Event();
//		pressButtonEvent.setTime(20);
//		pressButtonEvent.setAction((w)->ct.pressButton());
//		simulator.addEvent(pressButtonEvent);
		
		simulator.run();
		assertEquals(ct.getState().getClass(), StateDoorsOpened.class);
	}
	
	@Test
	public void testSimulator() throws InterruptedException {
		Simulator simulator = new Simulator();
		
//		Thread simulatorThread = new Thread(()->simulator.run());
//		simulatorThread.start();
		
		
//		Thread.sleep(1400);
		Event innerIvent = new Event(32);
		Event instantEvent = new Event();
		instantEvent.setAction((w)->System.out.println("Instant event "+w));
		
		innerIvent.setAction((w)->System.out.println("Inner event "+w));
		
		Event e = new Event(28);
		e.setAction((w)->{
			simulator.addEvent(innerIvent);
			simulator.addEvent(instantEvent);
			System.out.println("New event "+w);
		});
		simulator.addEvent(e);
		simulator.run();
	}
}

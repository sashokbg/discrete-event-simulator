package bg.alexander;

import org.junit.Test;

import bg.alexander.elevator.Context;

public class ElevatorTest{

	@Test
	public void testStateTransition() throws InterruptedException {
		Context ct = new Context();
		Thread th1 = new Thread(()->ct.pressButton());
		th1.start();
		Thread.sleep(300);
		ct.pressButton();
	}
}

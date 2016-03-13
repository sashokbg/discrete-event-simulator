package bg.alexander;

import org.junit.Before;
import org.junit.Test;

import bg.alexander.cars.Car;
import bg.alexander.cars.CarDirection;
import bg.alexander.cars.Field;
import bg.alexander.simulator.Simulator;

public class CarTest {
	private Simulator simulator;

	@Before
	public void setUp() {
		simulator = new Simulator();
		simulator.setTickTime(100);
	}
	
	@Test
	public void carTest(){
		Field field = new Field();
		field.setContext(simulator);
		field.addCar(new Car(6,1,"Car 1",CarDirection.RIGHT));
		field.race();
		simulator.run();
	}
}

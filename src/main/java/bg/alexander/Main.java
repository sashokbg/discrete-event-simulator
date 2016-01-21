package bg.alexander;

import bg.alexander.cars.Car;
import bg.alexander.cars.CarDirection;
import bg.alexander.cars.Field;
import bg.alexander.simulator.Simulator;

public class Main {
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		simulator.setTickTime(100);
		
		Field field = new Field();
		field.setContext(simulator);
		field.addCar(new Car(6,1,"Car 1",CarDirection.RIGHT));
		field.race();
		simulator.run();
	}
}

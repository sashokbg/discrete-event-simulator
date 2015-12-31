package bg.alexander.elevator;

import java.util.HashSet;
import java.util.Set;

import bg.alexander.Context;
import bg.alexander.State;
import bg.alexander.elevator.states.DoorsClosed;
import bg.alexander.passenger.Passenger;
import bg.alexander.simulator.SimulatorContext;

public class Elevator implements Context{
	private int capacity = 4;
	private State currentState;
	private Set<Passenger> passengers;
	
	public Elevator(){
		passengers = new HashSet<Passenger>(capacity);
		currentState = new DoorsClosed();
	}
	
	public void pressButton(SimulatorContext context){
		currentState.transitionToNext(this);
	}

	@Override
	public void setState(State newState) {
		newState = currentState;
	}
}

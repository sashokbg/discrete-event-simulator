package bg.alexander.simulator;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bg.alexander.Context;
import bg.alexander.State;
import bg.alexander.simulator.states.RunningState;

public class Simulator implements Context{
	private final Logger log = LogManager.getLogger(this.getClass());
	private int tickRate = 1;
	private int currentTime = 0;
	private int tickTime = 100;
	private State currentState;
	private volatile ArrayBlockingQueue<Event> events;
	
	public Simulator() {
		events = new ArrayBlockingQueue<Event>(99);
		currentState = new RunningState();
	}
	
	public void addEvent(Event event){
		//immediate event
		int scheduledTime = currentTime; 
		int executionTime = scheduledTime+1; 
		if(event.getTime()> 0){
			executionTime=currentTime+1+event.getTime();
		}
		event.setScheduledTime(scheduledTime);
		event.setExecutionTime(executionTime);
		events.add(event);
		log.debug("Adding event "+event+" scheduled for ["+executionTime+"]"); 
	}
	
	public void run(){
		for(currentTime = 0; currentTime< 100; currentTime += tickRate){
			events.stream().filter(
					(e)->e.getExecutionTime()==currentTime && !e.isCanceled())
			.forEach((e)->e.takePlace());
			
			log.trace("Tick + ["+currentTime+"]");
			try{
				Thread.sleep(tickTime);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		log.info("End for");
	}
	
	public int getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * How much real time one tick lasts
	 * @return the time for one tick in ms
	 */
	public int getTickTime() {
		return tickTime;
	}
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	@Override
	public void setState(State newState) {
		this.currentState = newState;
	}

	@Override
	public State getState() {
		return this.currentState;
	}

	@Override
	public void setContext(Context context) {
		//not implemented since this is the parent context
		throw new NotImplementedException();
	}
}

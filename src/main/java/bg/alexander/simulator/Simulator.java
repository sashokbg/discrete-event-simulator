package bg.alexander.simulator;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Simulator {
	private final Logger log = LogManager.getLogger(this.getClass());
	
	private int tickRate = 1;
	private int currentTime = 0;
	private int tickTime = 100;
	private volatile ArrayBlockingQueue<Event> events;
	
	public Simulator() {
		events = new ArrayBlockingQueue<Event>(99);
	}
	
	public void addEvent(Event event){
		log.debug("Adding event "+event); 
		//immediate event
		int scheduledTime = currentTime; 
		int executionTime = scheduledTime+1; 
		if(event.getTime()> 0){
			executionTime=currentTime+1+event.getTime();
		}
		event.setScheduledTime(scheduledTime);
		event.setExecutionTime(executionTime);
		events.add(event);
	}
	
	public void run(){
		for(currentTime = 0; currentTime< 100; currentTime += tickRate){
			events.stream().filter(
					(e)->e.getExecutionTime()==currentTime && !e.isCanceled())
			.forEach((e)->e.takePlace());
			
			log.trace("Tick");
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
	
	public int getTickTime() {
		return tickTime;
	}
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

}

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
		log.info("Adding event "+event);
		if(event.getTime()<= 0){
			event.setTime(currentTime+1);
		}
		else{
			event.setTime(currentTime+1+event.getTime());
		}
		events.add(event);
	}
	
	public void run(){
		for(currentTime = 0; currentTime< 100; currentTime += tickRate){
			events.stream().filter(
					(e)->e.getTime()==currentTime && !e.isCanceled())
			.forEach((e)->e.takePlace());
			
			log.debug("Tick");
			try{
				Thread.sleep(tickTime);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		log.info("End for");
	}
	
	public int getTickTime() {
		return tickTime;
	}
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
}

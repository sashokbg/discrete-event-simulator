package bg.alexander.simulator;

public class Event {
	private int time;
	private EventAction action;
	private boolean isCanceled;
	private String message;
	
	public Event() {
		time = 0;
		isCanceled = false;
	}
	
	public Event(EventAction action, String message){
		time = 0;
		isCanceled = false;
		
		this.message = message;
		this.action = action;
	}
	
	public boolean isCanceled(){
		return isCanceled;
	}
	
	public void cancel(){
		this.isCanceled = true;
	}
	
	public Event(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public EventAction getAction() {
		return action;
	}
	public void setAction(EventAction action) {
		this.action = action;
	}
	
	public void takePlace(){
		action.takePlace(time);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	@Override
	public String toString(){
		return "Event ["+time+"] "+message;
	}
}

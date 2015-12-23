package bg.alexander.simulator;

public class Event {
	private int time;
	private EventAction action;
	private boolean isCanceled;
	private String message;
	private int scheduledTime;
	private int executionTime;
	
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
	
	/**
	 * In what time should the event be executed </br>
	 * 
	 * 0 means immediately
	 * @return
	 */
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
		if(action!= null){
			action.takePlace(time);
		}
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

	public void setScheduledTime(int scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}
	
	public int getScheduledTime() {
		return scheduledTime;
	}

	public int getExecutionTime() {
		return executionTime;
	}
}

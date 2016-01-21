package bg.alexander.cars;

import bg.alexander.Context;
import bg.alexander.State;
import bg.alexander.simulator.Event;

public class Field implements Context{
	private Context context;
	private Car car;
	private int position = 0;
	private int size = 100;
	
	// start race
	public void race(){
		String positionStr = render();
		
		//crash
		if(position>=size){
			Event carAdvance = new Event();
			render();
			carAdvance.setMessage("Car CRASHED - "+positionStr+" speed: "+car.getCurrentSpeed());
			carAdvance.setAction((w)-> {
				position += car.getCurrentSpeed();
				car.setCrashed(true);
			});
			addEvent(carAdvance);
		}
		else{ //race
			Event carAdvance = new Event();
			carAdvance.setMessage("Car racing - "+positionStr+" speed: "+car.getCurrentSpeed());
			if(car.getCurrentSpeed() == car.getTopSpeed()){
				carAdvance.setMessage("TOP SPEED -  "+positionStr+" "+position);
			}
			carAdvance.setAction((w)-> {
				car.accelarate();
				position += car.getCurrentSpeed();
				race();
			});
			addEvent(carAdvance);
		}
	}
	
	private String render() {
		String result = "";
		for(int i =0; i< size; i++){
			if(i==position){
				result+=car.getGraphics();
			}else{
				result += "_";
			}
		}
		if(position >= size){
			result += car.getGraphics();
		}
		return result;
	}

	public void addCar(Car car){
		this.car = car;
	}
	
	@Override
	public void setState(State newState) {
		
	}

	@Override
	public State getState() {
		return null;
	}

	@Override
	public void addEvent(Event event) {
		context.addEvent(event);
	}

	@Override
	public int getCurrentTime() {
		return context.getCurrentTime();
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}
}

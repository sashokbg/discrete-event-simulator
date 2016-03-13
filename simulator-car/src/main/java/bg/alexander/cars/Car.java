package bg.alexander.cars;

public class Car {
	private int topSpeed;
	private String name;
	private CarDirection direction;
	private int accelaration;
	private int currentSpeed;
	private String graphics;
	private boolean isCrashed;
	
	public boolean isCrashed() {
		return isCrashed;
	}

	public void setCrashed(boolean isCrashed) {
		this.isCrashed = isCrashed;
	}

	public Car(int topSpeed,int accelaration, String name, CarDirection direction) {
		super();
		this.topSpeed = topSpeed;
		this.name = name;
		this.direction = direction;
		this.accelaration = accelaration;
		this.currentSpeed = 0;
		this.isCrashed = false;
		this.graphics = "o''o";
	}
	
	public String getGraphics(){
		if(isCrashed){
			return "***";
		}
		else
			return graphics;
	}
	
	public void accelarate(){
		this.currentSpeed += accelaration;
		if(currentSpeed > topSpeed){
			currentSpeed = topSpeed;
		}
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarDirection getDirection() {
		return direction;
	}

	public void setDirection(CarDirection direction) {
		this.direction = direction;
	}

	public int getAccelaration() {
		return accelaration;
	}

	public void setAccelaration(int accelaration) {
		this.accelaration = accelaration;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
}

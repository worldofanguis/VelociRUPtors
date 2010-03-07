package kicsijoe;

public abstract class Car {

	protected Directions plannedDirection;
	protected Road roadUnderMe;
	protected int startSpeed;
	protected int tickCount;

	public Car(){

	}

	public boolean canBeArrested(){
		return false;
	}

	public int getSpeed(){
		System.out.println("Car - getSpeed");
		return tickCount;
	}

	public void Move(){
		System.out.println("Car - Move");
	}

	public void MoveTo(Road road){
		System.out.println("Car - MoveTo");
	}

	public boolean Update(){
		System.out.println("Car - Update");
		return true;
	}

	public abstract void Interaction(StopSign sign);
	public abstract void Interaction(ExitSign sign);
	public abstract void Interaction(Bank bank);
	public abstract void Interaction(Hideout hideout);
	public abstract void Interaction(Lamp lamp);

}
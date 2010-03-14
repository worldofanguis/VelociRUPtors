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
		Output.methodStarts("Car - getSpeed");


		Output.methodEnds(String.format("Car - getSpeed - returns tickCount:%d", tickCount));
		return tickCount;
	}

	public void Move(){
		Output.methodStarts("Car - Move");

		Output.methodEnds("Car - Move");
	}

	public void MoveTo(Road road){
		Output.methodStarts("Car - MoveTo");

		Output.methodEnds("Car - MoveTo");
	}

	public boolean Update(){
		Output.methodStarts("Car - Update");

		// ExitCar //
		if(roadUnderMe.getNextRoads() == null){
			roadUnderMe.removeCar();
			Output.methodEnds("Car - Update");
			return false;
		}
		Output.methodEnds("Car - Update");
		return true;
	}

	public abstract void Interaction(StopSign sign);
	public abstract void Interaction(ExitSign sign);
	public abstract void Interaction(Bank bank);
	public abstract void Interaction(Hideout hideout);
	public abstract void Interaction(Lamp lamp);

}
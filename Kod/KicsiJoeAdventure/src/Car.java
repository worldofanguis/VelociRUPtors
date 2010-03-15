

public abstract class Car extends ClassID {

	protected Directions plannedDirection;
	protected Road roadUnderMe;
	protected int startSpeed;
	protected int tickCount;

	public Car(){

	}

	/*
	 * Function used for testing to set the roadUnderMe pointer
	 */
	public void setRoadUnderCar(Road road){
		roadUnderMe = road;
	}

	public boolean canBeArrested(){
                Output.methodStarts(ID, "canBeArrested()");
                Output.methodEnds(ID,"canBeArrested()","false");
		return false;
	}

	public int getSpeed(){
		Output.methodStarts(ID,"getSpeed()");


		Output.methodEnds(ID,"getSpeed()","tickCount");
		return tickCount;
	}

	public void Move(){
		Output.methodStarts(ID,"Move()");

		Output.methodEnds(ID,"Move()");
	}

	public void MoveTo(Road road){
		Output.methodStarts(toString(),"MoveTo()");

		Output.methodEnds(toString(),"MoveTo()");
	}

	public boolean Update(){
		Output.methodStarts(toString(),"Update()");

		// ExitCar //
		if(roadUnderMe.getNextRoads() == null){
			roadUnderMe.removeCar();
			Output.methodEnds(toString(),"Move()","false");
			return false;
		}
		Output.methodEnds(toString(),"Move()","true");
		return true;
	}

	public abstract void Interaction(StopSign sign);
	public abstract void Interaction(ExitSign sign);
	public abstract void Interaction(Bank bank);
	public abstract void Interaction(Hideout hideout);
	public abstract void Interaction(Lamp lamp);

}
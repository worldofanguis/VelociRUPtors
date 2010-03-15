
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public abstract class Car extends ClassID {

	protected int plannedDirection;
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
		Output.methodStarts(ID,"MoveTo("+road.toString()+")");
		roadUnderMe.removeCar();
		roadUnderMe = road;
		road.setCar(this);
		Output.methodEnds(ID,"MoveTo("+road.toString()+")");
	}

	public boolean Update(){
		Output.methodStarts(ID,"Update()");

		// Mozgatás //
			// plannedDirection beállítása //
		AvailableRoads ar = roadUnderMe.getNextRoads();
		if(moreThan1AR(ar)){	// több szabad irány van, választunk //
			System.out.println("Merre akarsz menni? lehetőségek:");
			if(ar.roads[0] != null) System.out.println("0 - balra");
			if(ar.roads[1] != null) System.out.println("1 - fel");
			if(ar.roads[2] != null) System.out.println("2 - jobbra");
			if(ar.roads[3] != null) System.out.println("3 - le");
			
			String line = null;
			try {
				 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
			} catch (IOException ex) {

			}
			 if(line.equals("0")) plannedDirection = Directions.LEFT.value;
		else if(line.equals("1")) plannedDirection = Directions.UP.value;
		else if(line.equals("2")) plannedDirection = Directions.RIGHT.value;
		else if(line.equals("3")) plannedDirection = Directions.DOWN.value;
		else System.out.println("ha-ha, de viccesnek tetszik lenni");
		} else {	// csak 1 irány szabad //
			for(int i=0;i<4;i++){
				if(ar.roads[i] != null){
					plannedDirection = i;
					break;
				}
			}
		}

			// Mozgás plannedDirection felé //
		MoveTo(ar.roads[plannedDirection]);
		// Mozgatás - vége //

		// ExitCar //
		if(roadUnderMe.getNextRoads() == null){
			roadUnderMe.removeCar();
			Output.methodEnds(ID,"Update()","false");
			return false;
		}
		Output.methodEnds(ID,"Update()","true");
		return true;
	}

	private boolean moreThan1AR(AvailableRoads ar){
		int arC = 0;
		for(int i=0;i<4;i++){
			if(ar.roads[i] != null)
				arC++;
		}
		return (arC > 1);
	}

	public abstract void Interaction(StopSign sign);
	public abstract void Interaction(ExitSign sign);
	public abstract void Interaction(Bank bank);
	public abstract void Interaction(Hideout hideout);
	public abstract void Interaction(Lamp lamp);

}
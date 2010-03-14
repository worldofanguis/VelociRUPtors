

public class Lamp implements TrafficController {

	private boolean state[] = new boolean[4];

	public Lamp(){

	}

	public boolean isGreen(Directions Diretion){
		Output.methodStarts("Lamp - isGreen");
		Output.methodEnds("Lamp - isGreen");
		return false;
	}

	public boolean Update(){
		Output.methodStarts("Lamp - Update");
		Output.methodEnds("Lamp - Update");
		return false;
	}

	public void whatSign(Car car){
		Output.methodStarts("Lamp - whatSign");
		car.Interaction(this);
		Output.methodEnds("Lamp - whatSign");
	}

}
package kicsijoe;

public class StopSign implements TrafficController {

	public StopSign(){

	}

	public void whatSign(Car car){
		Output.methodStarts("StopSign - whatSign");
		car.Interaction(this);
		Output.methodEnds("StopSign - whatSign");
	}

}
package kicsijoe;

public class ExitSign extends ClassID implements TrafficController {

	public ExitSign(){

	}

	public void whatSign(Car car){
		Output.methodStarts(ID, "whatSign("+car.toString()+")");
		car.Interaction(this);
		Output.methodEnds(ID, "whatSign("+car.toString()+")");
	}

}
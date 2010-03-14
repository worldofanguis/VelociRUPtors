

public class ExitSign implements TrafficController {

	public ExitSign(){

	}

	public void whatSign(Car car){
		Output.methodStarts("ExitSign - whatSign");
		car.Interaction(this);
		Output.methodEnds("ExitSign - whatSign");
	}

}
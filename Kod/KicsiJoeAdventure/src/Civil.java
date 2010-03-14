

public class Civil extends Car {

	public Civil(){

	}

	public void Interaction(StopSign sign){
		Output.methodStarts("Civil - Interaction - StopSign");

		Output.methodEnds("Civil - Interaction - StopSign");
	}

	public void Interaction(ExitSign sign){
		Output.methodStarts("Civil - Interaction - ExitSign");

		Output.methodEnds("Civil - Interaction - ExitSign");
	}

	public void Interaction(Bank bank){
		Output.methodStarts("Civil - Interaction - Bank");

		Output.methodEnds("Civil - Interaction - Bank");
	}

	public void Interaction(Hideout hideout){
		Output.methodStarts("Civil - Interaction - Hideout");

		Output.methodEnds("Civil - Interaction - Hideout");
	}

	public void Interaction(Lamp lamp){
		Output.methodStarts("Civil - Interaction - Lamp");
		
		Output.methodEnds("Civil - Interaction - Lamp");
	}

}
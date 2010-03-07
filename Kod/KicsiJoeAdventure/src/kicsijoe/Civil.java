package kicsijoe;

public class Civil extends Car {

	public Civil(){

	}

	public void Interaction(StopSign sign){
		System.out.println("Civil - Interaction - StopSign");
	}

	public void Interaction(ExitSign sign){
		System.out.println("Civil - Interaction - ExitSign");
	}

	public void Interaction(Bank bank){
		System.out.println("Civil - Interaction - Bank");
	}

	public void Interaction(Hideout hideout){
		System.out.println("Civil - Interaction - Hideout");
	}

	public void Interaction(Lamp lamp){
		System.out.println("Civil - Interaction - Lamp");
	}

}
package kicsijoe;

public class Police extends Car {

	private boolean policeModeActivated;

	public Police(){

	}

	public void Arrest(){
		System.out.println("Police - Arrest");
	}

	public void Interaction(StopSign sign){
		System.out.println("Police - Interaction - StopSign");
	}

	public void Interaction(ExitSign sign){
		System.out.println("Police - Interaction - ExitSign");
	}

	public void Interaction(Bank bank){
		System.out.println("Police - Interaction - Bank");
	}

	public void Interaction(Hideout hideout){
		System.out.println("Police - Interaction - Hideout");
	}

	public void Interaction(Lamp lamp){
		System.out.println("Police - Interaction - Lamp");
	}

	@Override
	public boolean Update(){
		System.out.println("Police - Update");
		return super.Update();
	}

}
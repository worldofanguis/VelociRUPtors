package kicsijoe;

public class Robber extends Car {

	public Robber(){

	}

	@Override
	public boolean canBeArrested(){
		return true;
	}

	public void Interaction(StopSign sign){
		System.out.println("Robber - Interaction - StopSign");
	}

	public void Interaction(ExitSign sign){
		System.out.println("Robber - Interaction - ExitSign");
	}

	public void Interaction(Bank bank){
		System.out.println("Robber - Interaction - Bank");
		bank.robBank();
	}

	public void Interaction(Hideout hideout){
		System.out.println("Robber - Interaction - Hideout");
		hideout.arrivedToHideout();
	}

	public void Interaction(Lamp lamp){
		System.out.println("Robber - Interaction - Lamp");
	}

	@Override
	public boolean Update(){
		System.out.println("Robber - Update");
		
		// Building interaction //
		Building building;
		if((building = roadUnderMe.hasBuilding()) != null)
			building.whatBuilding(this);

		return true;
	}
}
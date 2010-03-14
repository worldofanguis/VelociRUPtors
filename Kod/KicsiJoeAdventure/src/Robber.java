

public class Robber extends Car {

	public Robber(){

	}

	@Override
	public boolean canBeArrested(){
		return true;
	}

	public void Interaction(StopSign sign){
		Output.methodStarts("Robber - Interaction - StopSign");

		Output.methodEnds("Robber - Interaction - StopSign");
	}

	public void Interaction(ExitSign sign){
		Output.methodStarts("Robber - Interaction - ExitSign");

		Output.methodEnds("Robber - Interaction - ExitSign");
	}

	public void Interaction(Bank bank){
		Output.methodStarts("Robber - Interaction - Bank");
		bank.robBank();
		Output.methodEnds("Robber - Interaction - Bank");
	}

	public void Interaction(Hideout hideout){
		Output.methodStarts("Robber - Interaction - Hideout");
		hideout.arrivedToHideout();
		Output.methodEnds("Robber - Interaction - Hideout");
	}

	public void Interaction(Lamp lamp){
		Output.methodStarts("Robber - Interaction - Lamp");
		
		Output.methodEnds("Robber - Interaction - Lamp");
	}

	@Override
	public boolean Update(){
		Output.methodStarts("Robber - Update");
		
		// Building interaction //
		Building building;
		if((building = roadUnderMe.hasBuilding()) != null)
			building.whatBuilding(this);

		return true;
	}
}
package kicsijoe;

public class Police extends Car {

	private boolean policeModeActivated;

	public Police(){

	}

	public void Arrest(){
		Output.methodStarts("Police - Arrest");
		Main.game.GameOver(false);
		Output.methodEnds("Police - Arrest");
	}

	public void Interaction(StopSign sign){
		Output.methodStarts("Police - Interaction - StopSign");

		Output.methodEnds("Police - Interaction - StopSign");
	}

	public void Interaction(ExitSign sign){
		Output.methodStarts("Police - Interaction - ExitSign");

		Output.methodEnds("Police - Interaction - ExitSign");
	}

	public void Interaction(Bank bank){
		Output.methodStarts("Police - Interaction - Bank");

		Output.methodEnds("Police - Interaction - Bank");
	}

	public void Interaction(Hideout hideout){
		Output.methodStarts("Police - Interaction - Hideout");

		Output.methodEnds("Police - Interaction - Hideout");
	}

	public void Interaction(Lamp lamp){
		Output.methodStarts("Police - Interaction - Lamp");

		Output.methodEnds("Police - Interaction - Lamp");
	}

	@Override
	public boolean Update(){
		Output.methodStarts("Police - Update");
		if(!policeModeActivated && Main.game.isBankRobbed())
			policeModeActivated = true;

		if(policeModeActivated){
			Road nextRoad = null;
			Car nextCar;
			if((nextCar = nextRoad.hasCar()) != null){
				if(nextCar.canBeArrested())
					Arrest();
			}
		}
		Output.methodEnds("Police - Update");
		return super.Update();
	}

}
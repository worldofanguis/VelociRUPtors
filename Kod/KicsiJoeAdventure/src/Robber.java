

public class Robber extends Car {

	public Robber(){

	}

	@Override
	public boolean canBeArrested(){
                Output.methodStarts(ID, "canBeArrested()");
                Output.methodEnds(ID,"canBeArrested()","true");
		return true;
	}

	public void Interaction(StopSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

	public void Interaction(ExitSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

	public void Interaction(Bank bank){
		Output.methodStarts(ID,"Interaction("+bank.toString()+")");
		bank.robBank();
		Output.methodEnds(ID,"Interaction("+bank.toString()+")");
	}

	public void Interaction(Hideout hideout){
		Output.methodStarts(ID,"Interaction("+hideout.toString()+")");
		hideout.arrivedToHideout();
		Output.methodEnds(ID,"Interaction("+hideout.toString()+")");
	}

	public void Interaction(Lamp lamp){
		Output.methodStarts(ID,"Interaction("+lamp.toString()+")");
		
		Output.methodEnds(ID,"Interaction("+lamp.toString()+")");
	}

	@Override
	public boolean Update(){
		Output.methodStarts(ID,"Update()");
		
		// Building interaction //
		Building building;
		if((building = roadUnderMe.hasBuilding()) != null)
			building.whatBuilding(this);

		Output.methodEnds(ID,"Update()","true");
                return true;
	}
}
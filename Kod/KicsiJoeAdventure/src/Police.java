

public class Police extends Car {

	private boolean policeModeActivated;

	public Police(){

	}

	public void Arrest(){
		Output.methodStarts(ID,"Arrest()");
		Main.game.GameOver(false);
		Output.methodEnds(ID,"Arrest()");
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

		Output.methodEnds(ID,"Interaction("+bank.toString()+")");
	}

	public void Interaction(Hideout hideout){
		Output.methodStarts(ID,"Interaction("+hideout.toString()+")");

		Output.methodEnds(ID,"Interaction("+hideout.toString()+")");
	}

	public void Interaction(Lamp lamp){
		Output.methodStarts(ID,"Interaction("+lamp.toString()+")");

		Output.methodEnds(ID,"Interaction("+lamp.toString()+")");
	}

	@Override
	public boolean Update(){
		Output.methodStarts(ID,"Update()");
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
                String p = (super.Update()) ? new String("true") : new String("false");
		Output.methodEnds(ID,"Update()",p);
		return super.Update();
	}

}
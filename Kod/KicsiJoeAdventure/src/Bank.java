

public class Bank implements Building {

	public Bank(){

	}

	public void whatBuilding(Car car){
		Output.methodStarts("Bank - whatBuilding");
		car.Interaction(this);
		Output.methodEnds("Bank - whatBuilding");
	}

	public void robBank(){
		Output.methodStarts("Bank - robBank");
		Main.game.bankRobbed();
		Output.methodEnds("Bank - robBank");
	}

}


public class Bank extends ClassID implements Building {

	public Bank(){

	}

	public void whatBuilding(Car car){
		Output.methodStarts(ID,"whatBuilding(" + car.toString() + ")");
		car.Interaction(this);
		Output.methodEnds(ID,"whatBuilding(" + car.toString() + ")");
	}

	public void robBank(){
		Output.methodStarts(ID,"robBank()");
		Main.game.bankRobbed();
		Output.methodEnds(ID,"robBank()");
	}

}
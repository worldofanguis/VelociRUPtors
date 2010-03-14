package kicsijoe;

public class Hideout extends ClassID implements Building {

	public Hideout(){

	}

	public void whatBuilding(Car car){
		Output.methodStarts(ID,"whatBuilding(" + car.toString() + ")");
		car.Interaction(this);
		Output.methodEnds(ID,"whatBuilding(" + car.toString() + ")");
	}

	public void arrivedToHideout(){
		Output.methodStarts(ID,"arrivedToHideout()");
		if(Main.game.isBankRobbed())
			Main.game.GameOver(true);
		Output.methodEnds(ID,"arrivedToHideout()");
	}

}


public class Hideout implements Building {

	public Hideout(){

	}

	public void whatBuilding(Car car){
		Output.methodStarts("HideOut - whatBuilding");
		car.Interaction(this);
		Output.methodEnds("HideOut - whatBuilding");
	}

	public void arrivedToHideout(){
		Output.methodStarts("Hideout - arrivedToHideout");
		if(Main.game.isBankRobbed())
			Main.game.GameOver(true);
		Output.methodEnds("Hideout - arrivedToHideout");
	}

}
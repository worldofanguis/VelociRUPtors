package kicsijoe;

public class Hideout implements Building {

	public Hideout(){

	}

	public void whatBuilding(Car car){
		System.out.println("HideOut - whatBuilding");
		car.Interaction(this);
	}

	public void arrivedToHideout(){
		System.out.println("Hideout - arrivedToHideout");
		if(Main.game.isBankRobbed())
			Main.game.GameOver(true);
	}

}
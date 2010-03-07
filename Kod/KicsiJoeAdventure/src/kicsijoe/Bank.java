package kicsijoe;

public class Bank implements Building {

	public Bank(){

	}

	public void whatBuilding(Car car){
		System.out.println("Bank - whatBuilding");
		car.Interaction(this);
	}

	public void robBank(){
		System.out.println("Bank - robBank");
		Main.game.bankRobbed();
	}

}
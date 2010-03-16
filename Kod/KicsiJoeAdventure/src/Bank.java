/**
 * Class Bank:
 * A bankot reprezentáló osztály.
 */

public class Bank extends ClassID implements Building {

        /**
         * Konstruktor. Az inicializálásnál megjelenő objektumnév: bank.
         */
	public Bank(){
		setID("bank");
		Output.methodStarts(ID,"Bank()");
		Output.methodEnds(ID,"Bank()");
	}

        /**
         * Meghívja önmagával az őt meghívó Bank-interakciós függvényét.
         * @param car Az őt meghívó jármű.
         */
	public void whatBuilding(Car car){
		Output.methodStarts(ID,"whatBuilding(" + car.toString() + ")");
		car.Interaction(this);
		Output.methodEnds(ID,"whatBuilding(" + car.toString() + ")");
	}

        /**
         * Jelez a Game osztálynak, hogy kirabolták a bankot.
         */
	public void robBank(){
		Output.methodStarts(ID,"robBank()");
		Main.game.bankRobbed();
		Output.methodEnds(ID,"robBank()");
	}

}
/**
 * Class Bank:
 * A bankot reprezentáló osztály.
 */

public class Bank extends ClassID implements Building {

        /**
         * Konstruktor. Az inicializálásnál megjelenő objektumnév: bank.
         */
	public Bank(){
	}

        /**
         * Meghívja önmagával az őt meghívó Bank-interakciós függvényét.
         * @param car Az őt meghívó jármű.
         */
	public void whatBuilding(Car car){
		car.Interaction(this);
	}

        /**
         * Jelez a Game osztálynak, hogy kirabolták a bankot.
         */
	public void robBank(){
		Main.game.bankRobbed();
	}

	public char showMapChar() {
		return 'B';
	}

}
/**
 * A rejtekhelyet reprezentáló osztály.
 * (Innen indul a játékos, és ide kell visszaérnie)
 */

public class Hideout extends ClassID implements Building {

        /**
         * Konstruktor. Az inicializálásnál megjelenő objektumnév: hideout.
         */
	public Hideout(){
	}

        /**
         * Megszólítják, és válaszként meghívja az őt megszólító rejtekhely-
         * kezelő függvényét.
         * @param car Az őt megszólító autó.
         */
	public void whatBuilding(Car car){
		car.Interaction(this);
	}

        /**
         * A rablónak biztosított függvény, hogy jelezze, megérkezett.
         * Ez gondoskodik a játékállapot ellenőrzéséről.
         */
	public void arrivedToHideout(){
		if(Main.game.isBankRobbed())
			Main.game.GameOver(true);
	}

	public char showMapChar() {
		return 'H';
	}

}
/**
 * A rejtekhelyet reprezentáló osztály.
 * (Innen indul a játékos, és ide kell visszaérnie)
 */

public class Hideout extends ClassID implements Building {

        /**
         * Konstruktor. Az inicializálásnál megjelenő objektumnév: hideout.
         */
	public Hideout(){
            setID("hideout");
            Output.methodStarts(ID,"Hideout()");
            Output.methodEnds(ID,"Hideout()");
	}

        /**
         * Megszólítják, és válaszként meghívja az őt megszólító rejtekhely-
         * kezelő függvényét.
         * @param car Az őt megszólító autó.
         */
	public void whatBuilding(Car car){
		Output.methodStarts(ID,"whatBuilding(" + car.toString() + ")");
		car.Interaction(this);
		Output.methodEnds(ID,"whatBuilding(" + car.toString() + ")");
	}

        /**
         * A rablónak biztosított függvény, hogy jelezze, megérkezett.
         * Ez gondoskodik a játékállapot ellenőrzéséről.
         */
	public void arrivedToHideout(){
		Output.methodStarts(ID,"arrivedToHideout()");
		if(Main.game.isBankRobbed())
			Main.game.GameOver(true);
		Output.methodEnds(ID,"arrivedToHideout()");
	}

}
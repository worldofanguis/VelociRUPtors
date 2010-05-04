/**
 * A rejtekhelyet reprezentáló osztály.
 * (Innen indul a játékos, és ide kell visszaérnie)
 */

public class Hideout implements Building {
	private Road roadUnderMe;

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
        if(Controller.game.isBankRobbed()) {
            Controller.msg("Hideout reached in time");
            Controller.game.GameOver(true);
        }
    }

    /**
     *
     * @return H mint Hideout
     */
    public char showMapChar() {
        return 'H';
    }

    public void Draw() {
        Controller.view.Draw(this);
    }

	public void setRoad(Road r) {
		roadUnderMe = r;
	}

	public Road getRoadUnderMe() {
		return roadUnderMe;
	}

}
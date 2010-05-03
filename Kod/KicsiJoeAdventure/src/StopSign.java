/**
 * Class StopSign:
 * A STOP táblát reprezentáló osztály.
 */

public class StopSign implements TrafficController {
	private Road roadUnderMe;

    /**
     * Konstruktor
     */
    public StopSign(){

    }

    /**
     * Ezzel a metódussal tudják megszólítani a járművek. Meghívja az ő
     * stoptábla-kezelő függvényüket.
     * @param car Az őt megszólító autó.
     */
    public void whatSign(Car car){
            car.Interaction(this);
    }

    /**
     *
     * @return S mint STOP tábla
     */
    public char showMapChar() {
            return 'S';
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
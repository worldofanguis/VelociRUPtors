/**
 * EXIT táblát reprezentáló osztály.
 */

public class ExitSign implements TrafficController {

    /**
     * Az út amin van
     */
	private Road roadUnderMe;

    /**
     * Konstruktor
     */
    public ExitSign(){

    }

    /**
     * Mikor megszólítják, meghívja a hívó exit tábla interakciós függvényét magával.
     * @param car Az őt meghívó jármű.
     */
    public void whatSign(Car car){
        car.Interaction(this);
    }

    /**
     * Rajzoltat
     */
    public void Draw() {
        Controller.view.Draw(this);
    }

    /**
     * Beállítja alatta utat
     * @param r
     */
	public void setRoad(Road r) {
		roadUnderMe = r;
	}

    /**
     * Lekérdezze alatta utat
     * @return
     */
	public Road getRoadUnderMe() {
		return roadUnderMe;
	}

}
/**
 * EXIT táblát reprezentáló osztály.
 */

public class ExitSign implements TrafficController {
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
     *
     * @return E mint EXIT tábla
     */
    public char showMapChar() {
        return 'E';
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
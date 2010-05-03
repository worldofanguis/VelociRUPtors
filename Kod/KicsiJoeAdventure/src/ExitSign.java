/**
 * EXIT táblát reprezentáló osztály.
 */

public class ExitSign implements TrafficController {

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

}
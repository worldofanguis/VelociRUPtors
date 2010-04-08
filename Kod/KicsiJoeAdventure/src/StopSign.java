/**
 * Class StopSign:
 * A STOP táblát reprezentáló osztály.
 */

public class StopSign implements TrafficController {

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

}
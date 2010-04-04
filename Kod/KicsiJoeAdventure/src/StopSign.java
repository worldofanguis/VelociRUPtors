/**
 * Class StopSign:
 * A STOP táblát reprezentáló osztály.
 */

public class StopSign extends ClassID implements TrafficController {

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

	public char showMapChar() {
		return 'S';
	}

}
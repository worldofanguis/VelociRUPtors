/**
 * EXIT táblát reprezentáló osztály.
 */

public class ExitSign extends ClassID implements TrafficController {

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

	public char showMapChar() {
		return 'E';
	}

}
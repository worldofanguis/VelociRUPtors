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
		Output.methodStarts(ID, "whatSign("+car.toString()+")");
		car.Interaction(this);
		Output.methodEnds(ID, "whatSign("+car.toString()+")");
	}

}
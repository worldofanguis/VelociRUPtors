/**
 * Class StopSign:
 * A STOP táblát reprezentáló osztály.
 */

public class StopSign extends ClassID implements TrafficController {

	public StopSign(){

	}

        /**
         * Ezzel a metódussal tudják megszólítani a járművek. Meghívja az ő
         * stoptábla-kezelő függvényüket.
         * @param car Az őt megszólító autó.
         */
	public void whatSign(Car car){
		Output.methodStarts(ID, "whatSign("+car.toString()+")");
		car.Interaction(this);
		Output.methodEnds(ID, "whatSign("+car.toString()+")");
	}

}
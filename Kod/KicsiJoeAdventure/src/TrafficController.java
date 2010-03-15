/**
 * Interface TrafficController:
 * Interfész a forgalomirányító-készülékek együttes kezeléséhez.
 */

public interface TrafficController {

        /**
         * Egységes megszólító függvény.
         * @param car Aki megszólította, akinek reagálni kell.
         */
	public void whatSign(Car car);

}
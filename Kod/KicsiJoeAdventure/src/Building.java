/**
 * Interface Building:
 * Interfész osztály az épületek egységes kezeléséhez.
 */

public interface Building{

        /**
         * Az épület megszólítását segítő függvény.
         * @param car Aki megszólítja.
         */
	public void whatBuilding(Car car);
	public char showMapChar();
}
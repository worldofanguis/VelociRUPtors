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

    /**
     * A térkép megjelenítésekor az adott épület-típust
     * reprezentáló karakter eléréséhez szükséges függvény
     * @return A reprezentáló karakter
     */
    public char showMapChar();

	public void setRoad(Road r);
	public Road getRoadUnderMe();

    public void Draw();
}
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
     * Az épület alatti út beállítása
     * @param r beállítandó út
     */
	public void setRoad(Road r);

    /**
     * Az épület alatti út lekérdezése
     * @return az út
     */
	public Road getRoadUnderMe();

    /**
     * Kirajzolás
     */
    public void Draw();
}
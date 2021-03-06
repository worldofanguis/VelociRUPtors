/**
 * Interface Pickup:
 * A játék során felvehető bónuszokat
 * képviselő interfész.
 */
public interface Pickup {

    /**
     * Frissítő függvény.
     */
    public boolean Update();

     /**
     * Egységes megszólító függvény, amelyet az egyes típusok
     * úgy implementálnak, hogy meghívják a paraméterként megjelenő
     * autó megfelelő interakciós függvényét.
     * @param car Az autó amelyik felveszi a bónuszt
     */
    public void whatPickup(Car car);

    public void setTick(int tick);
    public void PickedUp();

	public void setRoad(Road r);
	public Road getRoadUnderMe();

    public void Draw();

}

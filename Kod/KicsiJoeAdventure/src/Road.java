/**
 * Class Road:
 * Egy útszakaszt megtestesítő osztály.
 */

public class Road extends ClassID {

    /**
     * Az út mellett található épület referenciája.
     */
    private Building buildingOnMe;

    /**
     * Az útszakaszon lévő jármű referenciája.
     */
    private Car carOnMe;

    /**
     * Az utszakaszon található felvehető elem.
     */
    private Pickup pickup;

    /**
     * Az útszakaszhoz kapcsolódó utak referenciái.
     */
    private Road next[];

    /**
     * Az útszakaszon található forgalomirányító-készülék referenciája.
     */
    private TrafficController trafficController;

    /**
     * Konstruktor. Az inicializáláshoz kapott objektumnév: r.
     */

    /**
     * A pályán történő abszolút elhelyzéshez szükséges
     * koordináták.
     */
    public int X,Y;

    /**
     * A pályageneráláskor szükséges változó,
     * melynek értéke false, ha még nem lett az útszakasz
     * pozícionálva.
     */
    public boolean Iterated;
	private boolean IncomingRoads[];

    /**
     * Konstruktor
     */
    public Road(){
        next = new Road[4];
		IncomingRoads = new boolean[4];
        buildingOnMe = null;
        carOnMe = null;
        trafficController = null;
        Iterated = false;

		// false ra állítjuk őket defaultként (már nem mintha alapból nem lennének azok ... //
		for(int i=0;i<4;i++)
			IncomingRoads[i] = false;
    }

    /**
     * AvailableRoads osztály generálása az adott útszakaszhoz.
     * @return A lehetséges útirányok egy AvailableRoads osztályban.
     */
    public AvailableRoads getNextRoads(){
            return new AvailableRoads(next);
    }

	public boolean[] getIncomingRoads(){
		return IncomingRoads;
	}

    /**
     * Van-e rajta autó?
     * @return A rajta lévő autó referenciája.
     */
    public Car hasCar(){
            return carOnMe;
    }

    /**
     * Van rajta forgalomirányító-készülék?
     * @return A rajta lévő forgalomirányító-készülék referenciája.
     */
    public TrafficController hasTrafficController(){
    return trafficController;
    }

    /**
     * Van rajta épület?
     * @return Az úton található épület referenciája.
     */
    public Building hasBuilding(){
        return buildingOnMe;
    }

    /**
     * Van rajta felvehető elem?
     * @return Az úton található felvehető elem referenciája.
     */
    public Pickup hasPickup(){
        return pickup;
    }

    /**
     * Épület beállítása (inicializáláshoz)
     * @param building Az út mellé helyezendő épület.
     */
    public void setBuilding(Building building){
        buildingOnMe = building;
		building.setRoad(this);
    }

    /**
     * Az útra helyezi az adott autót.
     * @param car Az útra helyezendő autó referenciája.
     */
    public void setCar(Car car){
        carOnMe = car;
        car.setRoad(this);
    }

    /**
     * Forgalomirányító-készülék helyezése az útra.
     * @param tc A forgalomirányító-készülök, amit az útra rakunk
     */
    public void setTrafficController(TrafficController tc){
        trafficController = tc;
		tc.setRoad(this);
    }

    /**
     * Felvehető elem helyezése az útra
     * @param p Az elem, amely az úton található
     */
    public void setPickup(Pickup p){
        pickup = p;
		p.setRoad(this);
    }

    /**
     * Eltávolítja az útról az aktuálisan ott lévő autót.
     */
    public void removeCar(){
        carOnMe = null;
    }

    /**
     * Az útszakaszhoz csatlakoztat további útszakaszt (inicializálás).
     * @param Direction Az adott irány, ahol csatlakozik
     * @param road A csatlakozó út
     */
    public void setRoad(Directions Direction,Road road){
        next[Direction.value] = road;
		IncomingRoads[Direction.value] = true;
    }

	public void Draw(){
		Controller.view.Draw(this);
	}
}
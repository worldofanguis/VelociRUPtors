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

    /**
     * Konstruktor
     */
    public Road(){
        next = new Road[4];
        buildingOnMe = null;
        carOnMe = null;
        trafficController = null;
        Iterated = false;
    }

    /**
     * AvailableRoads osztály generálása az adott útszakaszhoz.
     * @return A lehetséges útirányok egy AvailableRoads osztályban.
     */
    public AvailableRoads getNextRoads(){
            return new AvailableRoads(next);
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
    }

    /**
     * Az útra helyezi az adott autót.
     * @param car Az útra helyezendő autó referenciája.
     */
    public void setCar(Car car){
        carOnMe = car;
        car.setRoadUnderCar(this);
    }

    /**
     * Forgalomirányító-készülék helyezése az útra.
     * @param tc A forgalomirányító-készülök, amit az útra rakunk
     */
    public void setTrafficController(TrafficController tc){
        trafficController = tc;
    }

    /**
     * Felvehető elem helyezése az útra
     * @param p Az elem, amely az úton található
     */
    public void setPickup(Pickup p){
        pickup = p;
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
    }
}
/**
 * Class Bunny:
 * A húsvéti nyulat reprezentáló osztály.
 * A rablónak letartóztatás-mentességet jelent.
 */
public class Bunny implements Pickup{

    /**
     * Életbenmaradásig hátralévő óraciklusok száma.
     */
    private int TickLeft;

    /**
     * Alap életciklus
     */
    private int StartTick;
    
    /**
     * Fel van-e véve
     */
    private boolean isPicked = false;

    /**
     * Aktív-e
     */
    public boolean isActive = true;

    /**
     * Az út, amin leledzik.
     */
    private Road roadUnderMe;

    /**
     * Konstruktor, hozzáadja magát pickup listához és beállítja ticket.
     */
    public Bunny(){
        Controller.game.addPickup(this);
        TickLeft = StartTick = Controller.game.BunnyTick;
    }

    /**
     * Beállítja, hogy melyik útszakaszon van a nyúl.
     * @param r Az útszakasz, amire rakjuk.
     */
    public void setRoad(Road r){
        roadUnderMe = r;
    }

    /**
     * Lekérdezi melyik úton vagyunk
     * @return Az út amin vagyunk
     */
    public Road getRoadUnderMe(){
		return roadUnderMe;
    }

    /**
     * Ciklusidő beállítása
     * [azt hiszem nincs szükség már rá]
     * @param tick idő
     */
    public void setTick(int tick)
    {
        StartTick =TickLeft = tick;
    }


    /**
     * Meghívja a megfelelő interakciót.
     * @param car
     */
    public void whatPickup(Car car) {
        car.Interaction(this);
    }

    /**
     * Autó hívja meg, ha felvette.
     */
    public void PickedUp(){
        isPicked = true;
        TickLeft = StartTick;
        roadUnderMe = null;
    }

    /**
     * Minden ciklusban meghívódó függvény.
     * A visszatérési értékben jelez, hogy aktív-e.
     * @return false, ha már nem aktív
     */
    public boolean Update() {
        boolean ret = true;
        if(--TickLeft == 0){
            ret = false;
            isActive = false;
        }
        return ret;
    }

    /**
     * Kirajzoltatja magát
     */
    public void Draw() {
        Controller.view.Draw(this);
    }

}

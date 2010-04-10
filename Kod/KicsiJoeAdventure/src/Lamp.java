/**
 * A közlekedési lámpát reprezentáló osztály.
 */
public class Lamp extends ClassID implements TrafficController {

    /**
     * A lámpa állapota az egyes útszakaszokhoz. (zöld = true)
     */
    private boolean state[] = new boolean[4];

    /**
     * Lámpa váltási időközének alapértéke
     */
    private int startTick;

    /**
     * Aktuálisan hátralévő ciklusok száma a váltásig
     */
    private int currentTick;

    /**
     * Az út, amin van.
     */
    private Road roadUnderMe;

    public Lamp(){
        state[0] = state[1] = state[2] = state[3] = false;
        ID = Main.game.addLamp(this);
        startTick = 5;		// def values //
        currentTick = 5;
        Main.game.outputStream.println("ILAMP - ID:"+ID+" Tick:"+startTick);
    }

    /**
     * Beállítja, hogy melyik útszakaszon van a lámpa.
     * @param r Az útszakasz, amire rakjuk.
     */
    public void setRoad(Road r)
    {
        roadUnderMe = r;
    }

    /**
     * A lámpa állapotát lekérdező függvény.
     * @param Direction A kérdéses irány
     * @return true, ha zöld; false, ha piros.
     */
    public boolean isGreen(int Direction){
        return state[Direction]; //?
    }

    /**
     * A lámpa állapotát frissítő függvény.
     */
    public void Update(){
        if(--currentTick == 0){
            for (int i=0; i<4; i++)
                state[i] = !(state[i]);
            currentTick = startTick;
        }
        Main.game.outputStream.println("LAMP - ID:"+ID+" RoadID:"+roadUnderMe.ID+" IsRed:"+!(state[0])+" Tick:"+currentTick);
    }

    /**
     * Az egységes "forgalomirányító-készülék lekérdező" függvény.
     * Meghívja az őt megszólító lámpakezelő függvényét.
     * @param car Aki meghívta.
     */
    public void whatSign(Car car){
            car.Interaction(this);
    }

    /**
     *
     * @return L mint Lámpa
     */
    public char showMapChar() {
            return 'L';
    }

    /**
     * Lámpaszín beállítása.
     * @param Green true, ha zöld.
     */
    public void setColor(boolean Green){
        state[0] = Green;
        state[1] = Green;
        state[2] = Green;
        state[3] = Green;
    }

    /**
     * Beállítja a lámpa váltásáig hátralévő ciklusszámot.
     * Determinisztikus prototípus tesztelés.
     * @param Tick ciklusszám
     */
    public void setTick(int Tick){
        startTick = currentTick = Tick;
    }

}
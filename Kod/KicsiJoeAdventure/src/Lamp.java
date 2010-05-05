/**
 * A közlekedési lámpát reprezentáló osztály.
 */
public class Lamp implements TrafficController {

    /**
     * A lámpa állapota az egyes útszakaszokhoz. (zöld = true)
     */
    private boolean state[];

    /**
     * Aktív oldalak
     */
	private boolean activeSides[];

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
		state = new boolean[4];
		for(int i=0;i<4;i++)
			state[i] = false;

        Controller.game.addLamp(this);
        startTick = 50;		// def values //
        currentTick = 50;
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
     * Aktív oldalak lekérdezése
     * @return
     */
	public boolean[] getActiveSides(){
		return activeSides;
	}

    /**
     * A lámpa állapotát frissítő függvény.
     */
    public void Update(){
		// firssítjük a becsatlakozásokat... elég lenne csak 1 amikor már kész a pálya, de hát most mit tegyünk ... //
		activeSides = roadUnderMe.getIncomingRoads();
		// TODO: Ez befolyásolja a váltogatást ? //
        if(currentTick-- == 0){
            for (int i=0; i<4; i++)
                state[i] = !(state[i]);
            currentTick = startTick;
        }
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

    /**
     * Kirajzoltat
     */
    public void Draw(){
        Controller.view.Draw(this);
    }

    /**
     * Beállítja utat
     * @param r
     */
    public void setRoad(Road r) {
        roadUnderMe = r;
    }

    /**
     * Lekérdezi utat
     * @return
     */
	public Road getRoadUnderMe() {
		return roadUnderMe;
	}
}
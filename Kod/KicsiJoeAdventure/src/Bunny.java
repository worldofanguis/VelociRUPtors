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
    private int StartTick;
    
    private boolean isPicked = false;
    public boolean isActive = true;

    /**
     * Az út, amin leledzik.
     */
    private Road roadUnderMe;

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

    public Road getRoadUnderMe(){
		return roadUnderMe;
    }

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

    public void PickedUp(){
	isPicked = true;
	TickLeft = StartTick;
	roadUnderMe = null;
    }

    public char showMapChar() {
            return 'N';
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

    public void Draw() {
        Controller.view.Draw(this);
    }

}

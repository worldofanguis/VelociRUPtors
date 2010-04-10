/**
 * Class Bunny:
 * A húsvéti nyulat reprezentáló osztály.
 * A rablónak letartóztatás-mentességet jelent.
 */
public class Bunny extends ClassID implements Pickup{

    /**
     * Életbenmaradásig hátralévő óraciklusok száma.
     */
    private int TickLeft;

    /**
     * Az út, amin leledzik.
     */
    private Road roadUnderMe;

    public Bunny(){
        ID = Main.game.addPickup(this);
        TickLeft = 5; //Valami
        Main.game.outputStream.println("IBUNNY - ID"+ID+" TickLeft:"+TickLeft);
    }

    /**
     * Beállítja, hogy melyik útszakaszon van a nyúl.
     * @param r Az útszakasz, amire rakjuk.
     */
    public void setRoad(Road r)
    {
        roadUnderMe = r;
    }

    /**
     * Meghívja a megfelelő interakciót.
     * @param car
     */
    public void whatPickup(Car car) {
        car.Interaction(this);
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
        if(--TickLeft == 0)
            ret = false;
        Main.game.outputStream.println("BUNNY - ID:"+ID+" RoadID:"+roadUnderMe.ID+" TickLeft:"+TickLeft);
        return ret;
    }

}

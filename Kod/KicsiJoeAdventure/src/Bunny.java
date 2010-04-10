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

    public Bunny(){
        Main.game.addPickup(this);
        TickLeft = 5; //Valami
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
        if(--TickLeft == 0)
            return false;
        else
            return true;
    }

}

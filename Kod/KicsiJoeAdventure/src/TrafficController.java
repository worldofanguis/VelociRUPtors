/**
 * Interface TrafficController:
 * Interfész a forgalomirányító-készülékek együttes kezeléséhez.
 */

public interface TrafficController {

    /**
     * Egységes megszólító függvény.
     * @param car Aki megszólította, akinek reagálni kell.
     */
    public void whatSign(Car car);

    /**
     * A pályakirajzoláskor a megkülönböztetéshez szükséges karakter.
     * @return A készülékekre jellemző karakter
     */
    public char showMapChar();

    public void Draw();
}
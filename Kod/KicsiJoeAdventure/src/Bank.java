/**
 * Class Bank:
 * A bankot reprezentáló osztály.
 */

public class Bank implements Building {

    /**
     * Konstruktor.
     */
    public Bank(){
    }

    /**
     * Meghívja önmagával az őt meghívó Bank-interakciós függvényét.
     * @param car Az őt meghívó jármű.
     */
    public void whatBuilding(Car car){
        car.Interaction(this);
    }

    /**
     * Jelez a Game osztálynak, hogy kirabolták a bankot.
     */
    public void robBank(){
        Controller.game.bankRobbed();
    }

    public char showMapChar() {
        return 'B';
    }

    public void Draw() {
        Controller.view.Draw(this);
    }

}
/**
 * Class Bank:
 * A bankot reprezentáló osztály.
 */

public class Bank implements Building {

    /**
     * Az út, amelyen a bank van.
     */
	private Road roadUnderMe;

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
        Controller.msg("Bank robbed");
        Controller.bankChanged();
        Controller.game.bankRobbed();
    }

    /**
     * Ki van alatta?
     * @return Az alatta levő út referenciája
     */
	public Road getRoadUnderMe(){
		return roadUnderMe;
	}

    /**
     * Ráhelyezzük az adott útra
     * @param r Az adott út
     */
	public void setRoad(Road r){
        roadUnderMe = r;
    }

    /**
     * Kirajzoltatjuk a példányt.
     */
    public void Draw() {
        Controller.view.Draw(this);
    }

}
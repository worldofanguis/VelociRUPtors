package kicsijoe;

import java.util.List;

public class Game {

	private List<Car> cars;
	private List<Lamp> lamps;

	private boolean bankIsRobbed;
	private Car me;
	private Road roadStart;
	private int maxCarsOnMap;

	public Game(){
		bankIsRobbed = false;
	}

	public void bankRobbed(){
		System.out.println("Game - bankRobbed");
		bankIsRobbed = true;
	}

	public void GameOver(boolean Success){
		System.out.println("Game - GameOver");
	}

	public void Initialization(){
		System.out.println("Game - Inizialization");
		loadMapFromFile("map.txt");
	}

	public boolean isBankRobbed(){
		System.out.println("Game - isBankRobbed");
		return false;
	}

	public void loadMapFromFile(String Filename){
		System.out.println("Game - loadMapFromFile");
		
		// loop start //
		// new Road(); //
		populateRoad(null);
		// loop end //

		Road hideout = new Road();
		Road bank = new Road();

		hideout.setCar((me = new Robber()));
		bank.setCar(new Police());

	}

	public void populateRoad(Road road){
		System.out.println("Game - populateRoads");
	}

	public void removeActualCar(){
		System.out.println("Game - removeActualCar");
	}

	public void Update(){
		System.out.println("Game - Update");

		/* Car.Update() returns false
		 * removeActualCar();
		 */
	}
}
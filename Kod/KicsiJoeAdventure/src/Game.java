

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
		Output.methodStarts("Game - bankRobbed");
		bankIsRobbed = true;
		Output.methodEnds("Game - bankRobbed");
	}

	public void GameOver(boolean Success){
		Output.methodStarts("Game - GameOver");

		Output.methodEnds("Game - GameOver");
	}

	public void Initialization(){
		Output.methodStarts("Game - Inizialization");
		loadMapFromFile("map.txt");

		Output.methodEnds("Game - Inizialization");
	}

	public boolean isBankRobbed(){
		Output.methodStarts("Game - isBankRobbed");
		Output.methodEnds(String.format("Game - isBankRobbed - returns bankIsRobbed:%d", bankIsRobbed));
		return bankIsRobbed;
	}

	public void loadMapFromFile(String Filename){
		Output.methodStarts("Game - loadMapFromFile");
		
		// loop start //
		// new Road(); //
		populateRoad(null);
		// loop end //

		Road hideout = new Road();
		Road bank = new Road();

		hideout.setCar((me = new Robber()));
		bank.setCar(new Police());
		Output.methodEnds("Game - loadMapFromFile");
	}

	public void populateRoad(Road road){
		Output.methodStarts("Game - populateRoads");
		Output.methodEnds("Game - populateRoads");
	}

	public void removeActualCar(){
		Output.methodStarts("Game - removeActualCar");
		Output.methodEnds("Game - removeActualCar");
	}

	public void Update(){
		Output.methodStarts("Game - Update");

		/* Car.Update() returns false
		 * removeActualCar();
		 */
		 Output.methodEnds("Game - Update");
	}


		/* Test Maps */

	public void TestMap1(){
		Output.methodStarts("TestMap1");

		Output.methodEnds("TestMap1");
	}
	public void TestMap2(){
		Output.methodStarts("TestMap2");

		Output.methodEnds("TestMap2");
	}
	public void TestMap3(){
		Output.methodStarts("TestMap3");

		Output.methodEnds("TestMap3");
	}
	public void TestMap4(){
		Output.methodStarts("TestMap4");

		Output.methodEnds("TestMap4");
	}
	public void TestMap5(){
		Output.methodStarts("TestMap5");
		
		Output.methodEnds("TestMap5");
	}
}
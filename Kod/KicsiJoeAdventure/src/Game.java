

import java.util.List;

public class Game extends ClassID {

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
		Output.methodStarts(ID,"bankRobbed()");
		bankIsRobbed = true;
		Output.methodEnds(ID,"bankRobbed()");
	}

	public void GameOver(boolean Success){
                String p = (Success) ? new String("true") : new String("false");
		Output.methodStarts(ID,"bankRobbed(" + p +")");

		Output.methodEnds(ID,"bankRobbed(" + p +")");
	}

	public void Initialization(){
		Output.methodStarts(ID,"Initialization()");
		loadMapFromFile("map.txt");

		Output.methodEnds(ID,"Initialization()");
	}

	public boolean isBankRobbed(){
		Output.methodStarts(ID,"isBankRobbed()");
                String p = (bankIsRobbed) ? new String("true") : new String("false");
		Output.methodEnds(ID,"isBankRobbed()",p);
		return bankIsRobbed;
	}

	public void loadMapFromFile(String Filename){
		Output.methodStarts(ID,"loadMapFromFile(" + Filename + ")");
		
		// loop start //
		// new Road(); //
		populateRoad(null);
		// loop end //

		Road hideout = new Road();
		Road bank = new Road();

		hideout.setCar((me = new Robber()));
		bank.setCar(new Police());
		Output.methodEnds(ID,"loadMapFromFile(" + Filename + ")");
	}

	public void populateRoad(Road road){
		Output.methodStarts(ID,"populateRoad("+road.toString()+")");
		Output.methodEnds(ID,"populateRoad("+road.toString()+")");
	}

	public void removeActualCar(){
		Output.methodStarts(ID,"removeActualCar()");
		Output.methodEnds(ID,"removeActualCar()");
	}

	public void Update(){
		Output.methodStarts(ID,"Update()");

		/* Car.Update() returns false
		 * removeActualCar();
		 */
		 Output.methodEnds(ID,"Update()");
	}


		/* Test Maps */

	public void TestMap1(){
//		Output.methodStarts("TestMap1");
//
//		Output.methodEnds("TestMap1");
	}
	public void TestMap2(){
//		Output.methodStarts("TestMap2");
//
//		Output.methodEnds("TestMap2");
	}
	public void TestMap3(){
//		Output.methodStarts("TestMap3");
//
//		Output.methodEnds("TestMap3");
	}
	public void TestMap4(){
//		Output.methodStarts("TestMap4");
//
//		Output.methodEnds("TestMap4");
	}
	public void TestMap5(){
//		Output.methodStarts("TestMap5");
//
//		Output.methodEnds("TestMap5");
	}
}
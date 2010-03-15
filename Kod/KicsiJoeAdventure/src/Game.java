import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		Output.methodStarts(ID,"GameOver[Success](" + p +")");

		Output.methodEnds(ID,"GameOver[Success](" + p +")");
	}

	public void Initialization(){
		Output.methodStarts(ID,"Initialization()");
		loadMapFromFile("map.txt");
		Output.methodEnds(ID,"Initialization()");
	}

	public boolean isBankRobbed(){
		Output.methodStarts(ID,"isBankRobbed()");

		System.out.print("Is the bank robbed? y = yes, any other key = no: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("y")) bankIsRobbed = true; else bankIsRobbed = false;

		String p = (bankIsRobbed) ? new String("true") : new String("false");
		Output.methodEnds(ID,"isBankRobbed()",p);
		return bankIsRobbed;
	}

	public void loadMapFromFile(String Filename){
		Output.methodStarts(ID,"loadMapFromFile(" + Filename + ")");
		
		// loop start //
		// new Road(); //
		Road r = new Road();
		r.setID("road");
		populateRoad(r/*ide majd a loopban lévő út jön*/);
		// loop end //

		Road hideout = new Road();
		hideout.setID("hideout");
		Road bank = new Road();
		bank.setID("bank");

		me = new Robber();
		me.setID("robber");
		hideout.setCar(me);

		Police p = new Police();
		p.setID("police");
		bank.setCar(p);
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

	/*
	 * Mozgatás teszt
	 */
	public void TestMap3(){
		Output.methodStarts(ID,"TestMap3 - Mozgatás");
		// pálya felépítése //
		/*
		 *  r3-r2-r4
		 *     |
		 *     r1
		 *
		 */
		Output.ignore();
		Road r1 = new Road();
		r1.setID("ut-lent");
		Road r2 = new Road();
		r2.setID("keresztezodes");
		Road r3 = new Road();
		r3.setID("ut-balra");
		Road r4 = new Road();
		r4.setID("ut-jobbra");

		r1.setRoad(Directions.UP,r2);
		r2.setRoad(Directions.LEFT, r3);
		r2.setRoad(Directions.RIGHT, r4);

		Car c = new Civil();
		c.setID("civil");

		r1.setCar(c);
		c.setRoadUnderCar(r1);
		Output.resume();
		// pálya felépítése - vége //

		// 1. lépés //
		c.Update();
		// 2. lépés //
		c.Update();

		Output.methodEnds(ID,"TestMap3 - Mozgatás");
	}

	/*
	 * Building interaction tesztelése
	 */
	public void TestMap4(){
		Output.methodStarts(ID,"TestMap4 - Building interakcio");
		Output.ignore();
		Road r1 = new Road();
		r1.setID("roadWithBank");
		Robber t = new Robber();
		t.setID("robber");
		Bank b = new Bank();
		b.setID("bank");
		Road r2 = new Road();
		r2.setID("roadWithHideout");
		Hideout h = new Hideout();
		h.setID("hideout");
		r1.setBuilding(b);
		r2.setBuilding(h);
		Output.resume();

		System.out.print("h = hideout interaction ; b = bank interaction: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("b")){
			t.setRoadUnderCar(r1);
			t.Update();
		} else if(line.equals("h")){
			t.setRoadUnderCar(r2);
			t.Update();
		}

		Output.methodEnds(ID,"TestMap4 - Building interakcio");
	}
	public void TestMap5(){
//		Output.methodStarts("TestMap5");
//
//		Output.methodEnds("TestMap5");
	}
	public void TestMap6(){
//		Output.methodStarts("TestMap5");
//
//		Output.methodEnds("TestMap5");
	}

	/*
	 * Inicializáció tesztelése
	 */
	public void TestMap7(){
		Output.methodStarts(ID,"TestMap7 - Inicializalas");
		Initialization();
		Output.methodEnds(ID,"TestMap7");
	}
}
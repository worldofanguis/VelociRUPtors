/**
 * Class Game:
 * Az egész játék vezérléséért felelős osztály.
 * Szkeletonként itt találhatóak a tesztpályák is.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Game extends ClassID {

        /**
         * A pályán található autók referenciái.
         */
	private List<Car> cars;

        /**
         * A pályán található lámpák referenciái.
         */
	private List<Lamp> lamps;

	/**
         * Értéke true, ha már kirabolták a bankot, egyébként false.
         */
        private boolean bankIsRobbed;

        /**
         * A játékos autójának referenciája.
         */
	private Car me;

        /**
         * A pálya első útja, amelyből felépül (bejárható) az úthálózat.
         */
	private Road roadStart;

        /**
         * A pályán maximálisan tartózkodható autók száma.
         */
	private int maxCarsOnMap;

        /**
         * Konstruktor, alaphelyzet (nem rabolták ki a bankot)
         */
	public Game(){
		bankIsRobbed = false;
	}

        /**
         * Akkor hívódik meg, ha bankrablás történt, ennek megfelelően
         * változik a játékállapot.
         */
	public void bankRobbed(){
		Output.methodStarts(ID,"bankRobbed()");
		bankIsRobbed = true;
		Output.methodEnds(ID,"bankRobbed()");
	}

        /**
         * A játék befejezése a paramétertől függően.
         * @param Success Értéke true, ha a játékos nyert; false, ha a rendőr
         * elkapta a játékost.
         */
	public void GameOver(boolean Success){
            String p = (Success) ? new String("true") : new String("false");
            Output.methodStarts(ID,"GameOver[Success](" + p +")");

            Output.methodEnds(ID,"GameOver[Success](" + p +")");
	}

        /**
         * A játék kezdetkor a pálya elkészítése, az elemek elhelyezése.
         */
	public void Initialization(){
		Output.methodStarts(ID,"Initialization()");
		loadMapFromFile("map.txt");
		Output.methodEnds(ID,"Initialization()");
	}

        /**
         * A játékállapot lekérdezése.
         * (Megj.: Szkeletonról lévén szó, ezt most a felhasználó adja meg)
         * @return A játék aktuális állapota: true, ha már történt bankrablás;
         * false, ha még nem rabolták ki a bankot.
         */
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

        /**
         * A külső fájlban tárolt információk alapján a pálya felépítése.
         * (Megj.: Szkeletonban ez még nincs implementálva, csak imitáljuk
         * a működését)
         * @param Filename Azon .txt fájl elérési útvonala, amely alapján
         * felépítjük a pályát.
         */
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

        /**
         * Az úthoz kapcsolódó elemek (autó, épület) elhelyezése rá - inicalizáláskor.
         * @param road Az út amelyet módosítunk.
         */
	public void populateRoad(Road road){
		Output.methodStarts(ID,"populateRoad("+road.toString()+")");
		Output.methodEnds(ID,"populateRoad("+road.toString()+")");
	}

        /**
         * Eltávolítja az aktuális autót az autók listájáról.
         */
	public void removeActualCar(){
		Output.methodStarts(ID,"removeActualCar()");
		Output.methodEnds(ID,"removeActualCar()");
	}

        /**
         * A frissítő függvény, amely meghívja az összes autóra a frissítést,
         * így léptetve a játékot.
         */
	public void Update(){
		Output.methodStarts(ID,"Update()");

		/* Car.Update() returns false
		 * removeActualCar();
		 */
		 Output.methodEnds(ID,"Update()");
	}


	/* Test Maps */

        /**
         * Ütközések tesztpálya
         */
	public void TestMap1(){
//		Output.methodStarts("TestMap1");
//
//		Output.methodEnds("TestMap1");
	}

        /**
         * Civilgenerálás és eltüntetés tesztpálya
         */
	public void TestMap2(){
//		Output.methodStarts("TestMap2");
//
//		Output.methodEnds("TestMap2");
	}

<<<<<<< HEAD
        /**
         * Mozgatás
         */
=======
	/*
	 * Mozgatás teszt
	 */
>>>>>>> 540c4db1e1375b688418be47cb58912224fe6c06
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

        /**
         * Közlekedés / Stop és Piros tábla teszt
         */
	public void TestMap5(){
//		Output.methodStarts("TestMap5");
//
//		Output.methodEnds("TestMap5");
	}

        /**
         * Közlekedés / Exit tábla teszt
         */
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
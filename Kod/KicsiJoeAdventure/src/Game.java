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
         * A játékos pontszáma
         */
        private int points;

        /**
         * Konstruktor, alaphelyzet (nem rabolták ki a bankot)
         */
	public Game(){
		bankIsRobbed = false;
                points = 0;
	}

        /**
         * Pontszám módosítása.
         * @param add Az érték, amit hozzáadunk a pontszámhoz.
         */
        public void AddPoints(int add) {
            points += add;
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
		r.setID("r");
		populateRoad(r/*ide majd a loopban lévő út jön*/);
		// loop end //

		Road hideout = new Road();
		hideout.setID("hideout");
		Road bank = new Road();
		bank.setID("bank");

		me = new Robber();
		me.setID("me");
		hideout.setCar(me);

		Police p = new Police();
		p.setID("p");
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
            Output.methodStarts(ID,"Ütközések teszt");
            /* Pálya felépítése:
             * r3
             * r2
             * r1
             *
             * c1 autó r1-n, c2 autó r2-n
             */
            Output.ignore();
            Road r3 = new Road();
            r3.setID("r3");
            Road r2 = new Road();
            r2.setID("r2");
            Road r1 = new Road();
            r1.setID("r1");

            r1.setRoad(Directions.UP,r2);
            r2.setRoad(Directions.UP,r3);
            Car c1 = new Civil();
            Car c2 = new Civil();
            System.out.println("c1 auto nekiutkozik c2-nek. Melyik esetet teszteled? a: c1, c2 = Civil;\t b: c1= Rendor, c2=Rablo;\t c: c1=Rablo, c2=Civil");
            String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("a")){
                    c1 = new Civil();
                    c2 = new Civil();
		} else if(line.equals("b")){
                    c1 = new Police();
                    c2 = new Robber();
		} else if(line.equals("c")){
                    c1 = new Robber();
                    c2 = new Civil();
		}
            c1.setID("c1");
            c2.setID("c2");
            r1.setCar(c1);
            r2.setCar(c2);
            Output.resume();

            Output.methodEnds(ID,"Ütközések teszt");
	}

        /**
         * Civilgenerálás és eltüntetés tesztpálya
         */
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
		r1.setID("r1");
		Road r2 = new Road();
		r2.setID("r2");
		Road r3 = new Road();
		r3.setID("r3");
		Road r4 = new Road();
		r4.setID("r4");

		r1.setRoad(Directions.UP,r2);
		r2.setRoad(Directions.LEFT, r3);
		r2.setRoad(Directions.RIGHT, r4);

		Car c = new Civil();
		c.setID("c");

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
		Road roadWithBank = new Road();
		roadWithBank.setID("roadWithBank");
		Robber robber = new Robber();
		robber.setID("robber");
		Bank bank = new Bank();
		bank.setID("bank");
		Road roadWithHideout = new Road();
		roadWithHideout.setID("roadWithHideout");
		Hideout hideout = new Hideout();
		hideout.setID("hideout");
		roadWithBank.setBuilding(bank);
		roadWithHideout.setBuilding(hideout);
		Output.resume();

		System.out.print("h = hideout interaction ; b = bank interaction: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("b")){
			robber.setRoadUnderCar(roadWithBank);
			robber.Update();
		} else if(line.equals("h")){
			robber.setRoadUnderCar(roadWithHideout);
			robber.Update();
		}

		Output.methodEnds(ID,"TestMap4 - Building interakcio");
	}

        /**
         * Közlekedés / Stop és Piros tábla teszt
         */
	public void TestMap5(){
		Output.methodStarts(ID,"TestMap5 - Piros lámpa/Stoptábla interakció");
		Output.ignore();
		//Utak létrehozása
		Road r1 = new Road();
		r1.setID("startRoad");
		Road r2 = new Road();
		r2.setID("roadWithTC");
		Road r3 = new Road();
		r3.setID("thirdRoad");
		//Utak összekapcsolása
		r1.setRoad(Directions.UP,r2);
		r2.setRoad(Directions.UP, r3);
		//Rabló elhelyezése
		Car t = new Civil();
		t.setID("civil");
		t.setRoadUnderCar(r1);
		//TrafficController létrehozása
		Lamp l = new Lamp();
		l.setID("Lamp");
		StopSign s = new StopSign();
		s.setID("StopSign");
		Output.resume();

		//A felhasználó döntése szerinti TC lerakása
		System.out.print("p = piros lámpa interaction ; s = stoptábla interaction: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("p")){
			r2.setTrafficController(l);
		} else if(line.equals("s")){
			r2.setTrafficController(s);

		}

		//Végül kettőt lépni kell a kocsival
		t.Update();
		t.Update();
		t.Update();
		t.Update();
		t.Update();
		t.Update();
		Output.resume();



		Output.methodEnds(ID,"TestMap5 - Piros lámpa/Stoptábla interakció");
	}

        /**
         * Közlekedés / Exit tábla teszt
         */
	public void TestMap6(){
		Output.methodStarts(ID,"TestMap6 - Exit tábla interakció");
		Output.ignore();
		//Utak létrehozása
		Road r1 = new Road();
		r1.setID("startRoad");
		Road r2 = new Road();
		r2.setID("secondRoad");
		Road r3 = new Road();
		r3.setID("ExitRoad");
		Road r4 = new Road();
		r4.setID("FourthRoad");
		//Utak összekapcsolása
		r1.setRoad(Directions.UP,r2);
		r2.setRoad(Directions.RIGHT, r3);
		r2.setRoad(Directions.UP, r4);

		//Kocsi referencia létrehozása
		Car c;
			
		
		System.out.print("c = civil interaction ; r = rabló interaction; p = rendőr interakció: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("c")){
			c = new Civil();
			c.setID("civil");
			c.setRoadUnderCar(r1);
		} else if(line.equals("r")){
			c = new Robber();
			c.setID("robber");
			c.setRoadUnderCar(r1);
		}
		 else if(line.equals("p")){
			c = new Police();
			c.setID("police");
			c.setRoadUnderCar(r1);
		}
		else{return;}
		
		
		//TrafficController létrehozása
		ExitSign es = new ExitSign();
		es.setID("ExitSign");
		r3.setTrafficController(es);
		Output.resume();

		c.Update();
		c.Update();
		c.Update();
		c.Update();
		c.Update();
		
	    

		Output.methodEnds(ID,"TestMap6 - Exit tábla interakció");
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
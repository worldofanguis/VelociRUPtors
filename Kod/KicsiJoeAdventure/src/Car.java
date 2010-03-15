/**
 * Class Car:
 * Absztrakt osztály az összes járműre vonatkozó tulajdonságokkal
 * és metódusokkal.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Car extends ClassID {

        /**
         * A tervezett haladási irány (lsd. Directions osztály)
         */
	protected int plannedDirection;

        /**
         * Azon út referenciája, amelyiken található az autó.
         */
	protected Road roadUnderMe;

        /**
         * A kezdetben generált véletlenszerű sebesség
         */
	protected int startSpeed;

        /**
         * A léptetéshez szükséges visszaszámláló
         */
	protected int tickCount;
	protected AvailableRoads ar;

	public Car(){
            tickCount = 0;
	}

	/**
	 * Teszteléshez szükséges függvény, a pálya felépítésekor az autó
         * alá rakja az utat.
	 */
	public void setRoadUnderCar(Road road){
		roadUnderMe = road;
	}

        /**
         * Le lehet-e tartóztatni? (Rosszfiú?)
         * @return Alapértelmezésben (civil és rendőr miatt) false
         * értéket ad vissza.
         */
	public boolean canBeArrested(){
                Output.methodStarts(ID, "canBeArrested()");
                Output.methodEnds(ID,"canBeArrested()","false");
		return false;
	}

        /**
         * Az autó sebességét adja vissza.
         * @return Az autó aktuális sebessége (~mikor indul el újra).
         */
	public int getSpeed(){
		Output.methodStarts(ID,"getSpeed()");


		Output.methodEnds(ID,"getSpeed()","tickCount");
		return tickCount;
	}

        /**
         * Autó mozgatása.
         */
	public void Move(){
		Output.methodStarts(ID,"Move()");
<<<<<<< HEAD
=======

		Output.methodEnds(ID,"Move()");
	}

        /**
         * Az autót egy konkrét útra helyezi.
         * @param road Az út, amelyikre áthelyezzük.
         */
	public void MoveTo(Road road){
		Output.methodStarts(ID,"MoveTo("+road.toString()+")");
		roadUnderMe.removeCar();
		roadUnderMe = road;
		road.setCar(this);
		Output.methodEnds(ID,"MoveTo("+road.toString()+")");
	}

        /**
         * A frissítő/léptető függvény
         * @return A visszatérési érték true, ha az autó még a pályán van.
         */
	public boolean Update(){
		Output.methodStarts(ID,"Update()");

>>>>>>> 0602007fd027c5e3f26cc8c253dec11de2690cd7
		// Mozgatás //

		// plannedDirection beállítása //
		ar = roadUnderMe.getNextRoads();
		if(moreThan1AR(ar)){	// több szabad irány van, választunk //
			System.out.println("Merre akarsz menni? lehetosegek:");
			if(ar.roads[0] != null) System.out.println("0 - balra");
			if(ar.roads[1] != null) System.out.println("1 - fel");
			if(ar.roads[2] != null) System.out.println("2 - jobbra");
			if(ar.roads[3] != null) System.out.println("3 - le");

			String line = null;
			try {
				 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
			} catch (IOException ex) {

			}
			if(line.equals("0")) plannedDirection = Directions.LEFT.value;
			else if(line.equals("1")) plannedDirection = Directions.UP.value;
			else if(line.equals("2")) plannedDirection = Directions.RIGHT.value;
			else if(line.equals("3")) plannedDirection = Directions.DOWN.value;
			else System.out.println("ha-ha, de viccesnek tetszik lenni");
		} else {	// csak 1 irány szabad //
			for(int i=0;i<4;i++){
				if(ar.roads[i] != null){
					plannedDirection = i;
					break;
				}
			}
		}

		//Közlekedésirányító ellenőrzése a plannedDirection-ön//
		//Ez meghívja a megfelelő Interaction fv-t//
		//Ha egy előző interakció megállította a kocsit, akkor ne nézze meg ezt.
		if(tickCount == 0)
		    ar.roads[plannedDirection].hasTrafficController().whatSign(this);
		

		// Mozgás plannedDirection felé, ha nem kell várnunk //
		if(tickCount == 0)
		    MoveTo(ar.roads[plannedDirection]);
		// Mozgatás - vége //
		Output.methodEnds(ID,"Move()");
	}

	public void MoveTo(Road road){
		Output.methodStarts(ID,"MoveTo("+road.toString()+")");
		roadUnderMe.removeCar();
		roadUnderMe = road;
		road.setCar(this);
		Output.methodEnds(ID,"MoveTo("+road.toString()+")");
	}

	public boolean Update(){
		Output.methodStarts(ID,"Update()");

		Move();

				// ExitCar //
		if(roadUnderMe.getNextRoads() == null){
			roadUnderMe.removeCar();
			Output.methodEnds(ID,"Update()","false");
			return false;
		}
		Output.methodEnds(ID,"Update()","true");
		return true;
	}

        /**
         * A teszteléshez szükséges segédfüggvény (akkor kérdezzük meg a
         * felhasználót, hogy merre akar menni, ha több lehetséges útirány
         * van).
         * @param ar Lehetséges útirányok
         * @return true, ha több lehetséges továbbhaladási irány is van.
         */
	private boolean moreThan1AR(AvailableRoads ar){
		int arC = 0;
		for(int i=0;i<4;i++){
			if(ar.roads[i] != null)
				arC++;
		}
		return (arC > 1);
	}

	public abstract void Interaction(StopSign sign);
	public abstract void Interaction(ExitSign sign);
	public abstract void Interaction(Bank bank);
	public abstract void Interaction(Hideout hideout);
	public abstract void Interaction(Lamp lamp);

}
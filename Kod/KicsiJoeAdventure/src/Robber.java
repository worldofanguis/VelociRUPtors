
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class Robber:
 * A játékos járművét képviselő osztály.
 */

public class Robber extends Car {

	/**
         * Konstruktor. Az inicializálásnál megjelenő objektumnév: me.
         */
	Robber(int Speed) {
		super();
		tickCount = Speed;
	}

        /**
         * Ha megkérdezik, hogy rosszfiú-e, be kell ismernie.
         * @return Visszatérési értéke true, mivel le lehet tartóztatni.
         */
	@Override
	public boolean canBeArrested(){
  		return true;
	}

        /**
         * STOP táblával történő interakció (nincs hatással rá)
         * @param sign Azon tábla referenciája, amelyikbe "belefutott".
         */
	public void Interaction(StopSign sign){
	}

        /**
         * EXIT táblával történő interakció (nincs hatással rá)
         * @param sign Azon tábla referenciája, amelyikbe "belefutott".
         */
	public void Interaction(ExitSign sign){
		ar.roads[plannedDirection] = null;

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
		TrafficController tc;
		if(tickCount == 0)
		    if((tc=ar.roads[plannedDirection].hasTrafficController()) != null)
			tc.whatSign(this);
	}

        /**
         * A bankkal történő interakció (kirabolja).
         * @param bank Azon bank referenciája, amelyikhez érkezett.
         */
	public void Interaction(Bank bank){
		bank.robBank();
	}

        /**
         * A rejtekhellyel történő interakció (jeleznie kell neki)
         * @param hideout A rejtekhely referenciája, amely mellé ért.
         */
	public void Interaction(Hideout hideout){
		hideout.arrivedToHideout();
	}

        /**
         * Forgalomirányító lámpával történő interakció (nincs hatással rá)
         * @param lamp Azon lámpa referenciája, amelyikbe "belefutott".
         */
	public void Interaction(Lamp lamp){
	}

    /**
     * Interakció az autóval, amely azon az úton van, ahova menni szeretne.
     * (Pontlevonás és átveszi a sebességét, hogy ne menjen neki többször)
     */
     public void Interaction(Car car){
         Main.game.addPoints(-5);
         tickCount = car.getSpeed();
     }

	@Override
	public boolean Update(){

		// Mozgás plannedDirection felé //
		//Épület ellenőrzése - ez a legfontosabb, az ütközés ellenőrzés jöhet ez után
		Building building;
		if((building = roadUnderMe.hasBuilding()) != null)
			building.whatBuilding(this);


                /* Meghívja az ősosztály mozgatását.
                 * Ez a későbbiekben nem így lesz (játékos irányít),
                 * egyelőre teszteléshez így implementáltuk. */
		super.Update();
		return true;
	}

	@Override
	public char showMapChar() {
		return 'R';
	}
}

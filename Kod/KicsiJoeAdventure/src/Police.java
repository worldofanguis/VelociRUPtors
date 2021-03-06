
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class Police:
 * A rendőr járművét reprezentáló osztály.
 */

public class Police extends Car {

    /**
     * A rendőr állapota. Értéke true, ha riadó módban van.
     */
    private boolean policeModeActivated;

    /**
     * Konstruktor.
     * Determinisztikus teszteléskor paraméterként kapja meg a sebességét.
     */
    Police(int Speed) {
        super(Speed);
        policeModeActivated = false;
    }

        /**
         * A rabló letartóztatása megtörtént, jelez a Game osztálynak.
         */
	public void Arrest(){
		Controller.game.GameOver(false);
	}

	public void MoveTo(Road road){
        Car c = road.hasCar();
        if ( c != null ) {
            c.Interaction(this);
        } else {
            roadUnderMe.removeCar();
            road.setCar(this);
            selectedDirection = -1;
        }
		Pickup p = road.hasPickup();
		if( p != null ){
			p.whatPickup(this);
		}

		if(tickCount == 0)
			tickCount = startSpeed;
	}


        /**
         * STOP tábla interakció (riadó módban áthajthat, egyébként
         * meg kell állnia).
         * @param sign Az adott STOP tábla
         */
	public void Interaction(StopSign sign){
		if(!policeModeActivated){
		    tickCount+=Controller.game.StopTime;
		    MoveTo(ar.roads[plannedDirection]);

		}
	}

        /**
         * EXIT tábla interakció (soha nem hajthat az általa jelölt útra)
         * @param sign Az adott EXIT tábla
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
         * A bankkal történő interakció (nincs hatással rá).
         * @param bank Az adott bank.
         */
	public void Interaction(Bank bank){
	    ar.roads[plannedDirection] = null;
	    plannedDirection = getValidDirection();
	}

        /**
         * A rejtekhellyel történő interakció (nincs hatással rá).
         * @param hideout Az adott rejtekhely.
         */
	public void Interaction(Hideout hideout){
	    ar.roads[plannedDirection] = null;
	    plannedDirection = getValidDirection();
	}

        /**
         * A közlekedési lámpával történő interakció (riadó módban nincs
         * hatással rá, egyébként igen)
         * @param lamp Az adott lámpa.
         */
	public void Interaction(Lamp lamp){
		//A későbbieknek a belső változó alapján dönt, most
                //meghívja a függvényt ahol a tesztelő van megkérdezve
                if(!policeModeActivated){
			//Merről érkezünk a lámpához?//
		    int fromDirection=0;
		    switch (plannedDirection){
			case 0: fromDirection = 2; break;//ha balra megyünk, jobbról jövünk
			case 1: fromDirection = 3; break;
			case 2: fromDirection = 0; break;
			case 3: fromDirection = 1; break;
		    }
		    //Leelenőriozzük, hogy zöld-e//
		    if(lamp.isGreen(fromDirection)){
			return; //A lámpa nincs ránk hatással, az interakciónak vége.
		    }
		    else{
			//Csak eggyel növeljük
			tickCount++;
//			Output.methodEnds(ID,"Interaction("+lamp.toString()+") //A lampa piros volt.");
			return;
		    }
		}
		else{
//		   Output.methodEnds(ID,"Interaction("+lamp.toString()+") //Riado modban van.");
		}
	}

        /**
         * A frissítő/léptető függvény.
         * (A játékállapottól függően kell módosítani, hogy riadó módban
         * van-e).
         * @return true, amennyiben a pályán van.
         */
	@Override
	public boolean Update(){
		if(!policeModeActivated && Controller.game.isBankRobbed()){
			policeModeActivated = true;
			if(tickCount > Controller.game.MaxPoliceSpeed)
			    tickCount = Controller.game.MaxPoliceSpeed;
			startSpeed = Controller.game.MaxPoliceSpeed;
		}

		return super.Update();
	}

    /**
     * Interakció az autóval, amely azon az úton van, ahova menni szeretne.
     * (Ha riadó módban van, megpróbálja letartóztatni.
     * A szkeletonban a felhasználó dönti el, milyen módban van az autó,
     * ezért nem a belső változó, hanem az isBankRobbed() alapján döntünk.
     */
    public void Interaction(Civil civil){
		civil.tickCount = Controller.game.MinCivilSpeed;
    }

    public void Interaction(Robber robber){
         //A későbbieknek a belső változó alapján dönt, most
         //meghívja a függvényt ahol a tesztelő van megkérdezve
         if( Controller.game.isBankRobbed() ){
             if ( robber.canBeArrested() )
                 Arrest();
         } else
             robber.tickCount = Controller.game.MaxCivilSpeed;
     }

	public void Interaction(Police police){
		police.tickCount = Controller.game.MinCivilSpeed;
    }
     /**
      * Nem tudja felvenni a nyulat, békén hagyja.
      * @param bunny
      */
     public void Interaction(Bunny bunny)
     {
     }



    @Override
    public void Draw() {
        Controller.view.Draw(this);
    }

}
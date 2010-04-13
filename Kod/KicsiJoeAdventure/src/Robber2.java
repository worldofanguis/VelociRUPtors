

/**
 * Class Robber:
 * A játékos járművét képviselő osztály.
 */

public class Robber extends Car {

	private Bunny pickedupbunny;
        private int previousDirection;

    /**
     * Konstruktor.
     * Determinisztikus teszteléskor paraméterként kapja meg a sebességét.
     * @param Speed A kezdeti sebesség
     */
    Robber(int Speed) {
            super(Speed);
            previousDirection = plannedDirection;
    }

    /**
     * Ha megkérdezik, hogy rosszfiú-e, be kell ismernie (kivéve, ha van
     * nála nyúl).
     * @return Visszatérési értéke true, mivel le lehet tartóztatni.
     */
    @Override
    public boolean canBeArrested(){
            return true;
    }

	public void MoveTo(Road road){
        Car c = road.hasCar();
        if ( c != null ) {
            c.Interaction(this);
        } else {
            roadUnderMe.removeCar();
            roadUnderMe = road;
            road.setCar(this);
        }
		Pickup p = road.hasPickup();
		if( p != null ){
			p.whatPickup(this);
		}

		if(tickCount == 0)
			tickCount = startSpeed;
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

        //tervezett útirány beállítása
	plannedDirection = getValidDirection();
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
        if ( !Main.game.isBankRobbed() )  {
            bank.robBank();
            ar.roads[plannedDirection] = null;
            tickCount += 5;
        }
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
     public void Interaction(Civil civil){
		civil.tickCount = 1;
     }

     public void Interaction(Police police){
		if( Main.game.isBankRobbed() ){
             if ( canBeArrested() )
                 police.Arrest();
		}else
			police.tickCount = 1;
     }
	 
	 public void Interaction(Robber robber){

	 }

     /**
      * Nem tudja felvenni a nyulat, békén hagyja.
      * @param bunny
      */
     public void Interaction(Bunny bunny){
		 pickedupbunny = bunny;
		 bunny.getRoad().setPickup(null);
		 bunny.PickedUp();
     }

     /**
      * Frissítés
      * @return
      */
    @Override
    public boolean Update(){
        // Mozgás plannedDirection felé //
        //Épület ellenőrzése - ez a legfontosabb, az ütközés ellenőrzés jöhet ez után
	if(pickedupbunny!=null){
	    if(!pickedupbunny.isActive){
		//System.out.println("mekhalt");
	    }
	}

        if(tickCount > 0) tickCount--;
	else if(tickCount == 0){
           ar = roadUnderMe.getNextRoads();
           Building building;
           if ( ar.roads[plannedDirection] != null )
               if ( (building = ar.roads[plannedDirection].hasBuilding()) != null)
                   building.whatBuilding(this);
           Move();
        }

        Main.game.outputStream.println("CAR - ID:"+ID+" RoadID:"+roadUnderMe.ID+" Tick:"+tickCount);
        return true;
    }

    /**
     *
     * @return R mint rabló
     */
    @Override
    public char showMapChar() {
            return 'R';
    }

    public void setDirection(int i) {
        previousDirection = plannedDirection;
        plannedDirection = i;
    }

    public void Move(){
        int realDirection = -1;
        /*
         * plannedD ahova mennénk, previousD amerre eddig mentünk, ha egyik
         * se jó nem megy sehova.
         */
        if ( ar.roads[plannedDirection] != null )
            realDirection = plannedDirection;
        else if (ar.roads[previousDirection] != null)
            realDirection = previousDirection;

        if (realDirection != -1) {
            TrafficController tc;
            if(tickCount == 0)
                if((tc=ar.roads[realDirection].hasTrafficController()) != null)
                    tc.whatSign(this);
            if(tickCount == 0)
                MoveTo(ar.roads[realDirection]);
        }
    }
}

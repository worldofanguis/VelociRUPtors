

/**
 * Class Robber:
 * A játékos járművét képviselő osztály.
 */

public class Robber extends Car {

	private Bunny pickedupbunny;
        private int previousDirection;
        private int waitingTime;

    /**
     * Konstruktor.
     * Determinisztikus teszteléskor paraméterként kapja meg a sebességét.
     * @param Speed A kezdeti sebesség
     */
    Robber(int Speed) {
            super(Speed);
            previousDirection = plannedDirection;
            waitingTime = 5;
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
        Building building;
        if((building = ar.roads[plannedDirection].hasBuilding()) != null)
                building.whatBuilding(this);


        if(tickCount == 0) {
           ar = roadUnderMe.getNextRoads();
        }

	if(pickedupbunny!=null){
	    if(!pickedupbunny.isActive){
		//System.out.println("mekhalt");
	    }
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

    /**
     *
     */
    @Override
    public void Move(){
        Building building;
        if ( ar.roads[plannedDirection] != null )
            if((building = ar.roads[plannedDirection].hasBuilding()) != null){
                building.whatBuilding(this);
            }

        /* ha a játékos olyan irányban akar tovább menni, ahova nem mehet,
         * akkor a korábbi útirányon halad és amint lehet követi az újat.
         */
        // Mozgás plannedDirection felé, ha nem kell várnunk //
        if(tickCount == 0)
            if (ar.roads[plannedDirection] != null)
                MoveTo(ar.roads[plannedDirection]);
            else if (previousDirection != -1)
                MoveTo(ar.roads[previousDirection]);

        // Mozgatás - vége //
    }

    /**
     *
     */
    @Override
    public void setDirection(int newDirection){
        previousDirection = plannedDirection; //indexelés miatt
        plannedDirection = newDirection;
    }
}

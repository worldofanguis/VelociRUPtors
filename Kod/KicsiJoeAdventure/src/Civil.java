/**
 * Class Civil:
 * A civil autókat képviselő osztály.
 */

public class Civil extends Car {
    /**
     * Konstruktor.
     * A determinisztikus teszteseknél létrehozáskor kap sebességet.
     */
    Civil(int Speed) {
        super(Speed);
    }

	public void MoveTo(Road road){
        Car c = road.hasCar();
        if ( c != null ) {
            c.Interaction(this);
        } else {
            roadUnderMe.removeCar();
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
     * STOP táblával történő interakció (meg kell állnia előtte).
     * @param sign Az adott STOP tábla.
     */
    public void Interaction(StopSign sign){
        tickCount+=5;
        MoveTo(ar.roads[plannedDirection]);
    }

    /**
     * EXIT táblával történő interakció (nincs hatással rá).
     * @param sign Az adott EXIT tábla.
     */
    public void Interaction(ExitSign sign){
    }

    /**
     * Bankkal történő interakció (nincs hatással rá).
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
     * Közlekedési lámpával interakció (ha piros, nem haladhat tovább).
     * @param lamp Az adott lámpa.
     */
    public void Interaction(Lamp lamp){
        //Merről érkezünk a lámpához?//
        int fromDirection=0;

        switch (plannedDirection){
            case 0: fromDirection = 2; //ha balra megyünk, jobbról jövünk
            case 1: fromDirection = 3;
            case 2: fromDirection = 0;
            case 3: fromDirection = 1;
        }

        //Leelenőriozzük, hogy zöld-e//
        if(lamp.isGreen(fromDirection)){
            return; //A lámpa nincs ránk hatással, az interakciónak vége.
        }
        else{
            //Csak eggyel növeljük
            tickCount++;
            return;
        }
    }

	 /**
     * Interakció az autóval, amely azon az úton van, ahova menni szeretne.
     * (várakozik arra hogy elmenjen)
     */
    public void Interaction(Civil civil){
         civil.tickCount = 1;
    }

	public void Interaction(Robber robber){
		Controller.game.addPoints(-5);
		robber.tickCount = 5;
	}

	public void Interaction(Police police){
		police.tickCount = 1;
	}
     /**
      * Nem tudja felvenni a nyulat, békén hagyja.
      * @param bunny
      */
     public void Interaction(Bunny bunny)
     {
     }

     /**
      *
      * @return C mint Civil
      */
    @Override
    public char showMapChar() {
        return 'C';
    }

    @Override
    public void Draw() {
        Controller.view.Draw(this);
    }
}
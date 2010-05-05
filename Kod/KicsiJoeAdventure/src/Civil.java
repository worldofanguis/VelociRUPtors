/**
 * Class Civil:
 * A civil autókat képviselő osztály.
 */

public class Civil extends Car {
    /**
     * Konstruktor.
     * Létrehozáskor kap sebességet.
     */
    Civil(int Speed) {
        super(Speed);
    }

    /**
     * Útszakaszra mozgat, ha ne áll ott autó
     * @param road ahova menne
     */
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
     * STOP táblával történő interakció (meg kell állnia előtte).
     * @param sign Az adott STOP tábla.
     */
    public void Interaction(StopSign sign){
        tickCount+=Controller.game.StopTime;
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
            case 0: fromDirection = 2; break; //ha balra megyünk, jobbról jövünk
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

    /**
     * Interakció rablóval, pontlevonás
     * @param robber Tőle vonunk le pontot.
     */
	public void Interaction(Robber robber){
		Controller.game.addPoints(-50);
		robber.tickCount = Controller.game.MaxCivilSpeed;
	}

    /**
     * Rendőr ütközés.
     * @param police
     */
	public void Interaction(Police police){
		police.tickCount = Controller.game.MaxPoliceSpeed;
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
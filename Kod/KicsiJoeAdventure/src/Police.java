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
         * Konstruktor. Az inicializálásban megjelenő objektumnév: p.
         */
	public Police(){
            policeModeActivated = false;
            setID("p");
            Output.methodStarts(ID,"Police()");
            Output.methodEnds(ID,"Police()");
	}

        /**
         * A rabló letartóztatása megtörtént, jelez a Game osztálynak.
         */
	public void Arrest(){
		Output.methodStarts(ID,"Arrest()");
		Main.game.GameOver(false);
		Output.methodEnds(ID,"Arrest()");
	}

        /**
         * STOP tábla interakció (riadó módban áthajthat, egyébként
         * meg kell állnia).
         * @param sign Az adott STOP tábla
         */
	public void Interaction(StopSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		if(policeModeActivated){
		    tickCount+=5;
		    MoveTo(ar.roads[plannedDirection]);

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
		}
	}

        /**
         * EXIT tábla interakció (soha nem hajthat az általa jelölt útra)
         * @param sign Az adott EXIT tábla
         */
	public void Interaction(ExitSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

        /**
         * A bankkal történő interakció (nincs hatással rá).
         * @param bank Az adott bank.
         */
	public void Interaction(Bank bank){
		Output.methodStarts(ID,"Interaction("+bank.toString()+")");

		Output.methodEnds(ID,"Interaction("+bank.toString()+")");
	}

        /**
         * A rejtekhellyel történő interakció (nincs hatással rá).
         * @param hideout Az adott rejtekhely.
         */
	public void Interaction(Hideout hideout){
		Output.methodStarts(ID,"Interaction("+hideout.toString()+")");

		Output.methodEnds(ID,"Interaction("+hideout.toString()+")");
	}

        /**
         * A közlekedési lámpával történő interakció (riadó módban nincs
         * hatással rá, egyébként igen)
         * @param lamp Az adott lámpa.
         */
	public void Interaction(Lamp lamp){
		Output.methodStarts(ID,"Interaction("+lamp.toString()+")");
		//A későbbieknek a belső változó alapján dönt, most
                //meghívja a függvényt ahol a tesztelő van megkérdezve
                if(Main.game.isBankRobbed()){
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
			Output.methodEnds(ID,"Interaction("+lamp.toString()+") //A lampa zold.");
			return; //A lámpa nincs ránk hatással, az interakciónak vége.
		    }
		    else{
			//Csak eggyel növeljük
			tickCount++;
			Output.methodEnds(ID,"Interaction("+lamp.toString()+") //A lampa piros volt.");
			return;
		    }
		}
		else{
		   Output.methodEnds(ID,"Interaction("+lamp.toString()+") //Riado modban van.");
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
		Output.methodStarts(ID,"Update()");
		if(!policeModeActivated && Main.game.isBankRobbed())
			policeModeActivated = true;

		String p = (super.Update()) ? new String("true") : new String("false");
		Output.methodEnds(ID,"Update()",p);
		return super.Update();
	}

    /**
     * Interakció az autóval, amely azon az úton van, ahova menni szeretne.
     * (Ha riadó módban van, megpróbálja letartóztatni.
     * A szkeletonban a felhasználó dönti el, milyen módban van az autó,
     * ezért nem a belső változó, hanem az isBankRobbed() alapján döntünk.
     */
     public void Interaction(Car car){
         Output.methodStarts(ID, "Interaction(" + car + ")");
         //A későbbieknek a belső változó alapján dönt, most
         //meghívja a függvényt ahol a tesztelő van megkérdezve
         if( Main.game.isBankRobbed() ){
             if ( car.canBeArrested() )
                 Arrest();
         } else
             tickCount = car.getSpeed();
         Output.methodEnds(ID, "Interaction(" + car + ")");
     }

}
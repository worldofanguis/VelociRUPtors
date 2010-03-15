/**
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
            setID("civil");
            Output.methodStarts(ID,"Civil()");
            Output.methodEnds(ID,"Civil()");
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

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
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

		Output.methodEnds(ID,"Interaction("+lamp.toString()+")");
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

		if(policeModeActivated){
			Road nextRoad = null;
			Car nextCar;
			if((nextCar = nextRoad.hasCar()) != null){
				if(nextCar.canBeArrested())
					Arrest();
			}
		}
                String p = (super.Update()) ? new String("true") : new String("false");
		Output.methodEnds(ID,"Update()",p);
		return super.Update();
	}

}
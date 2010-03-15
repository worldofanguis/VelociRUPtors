/**
 * Class Civil:
 * A civil autókat képviselő osztály.
 */

public class Civil extends Car {

        /**
         * Konstruktor. Az inicializáláshoz kapott objektumnév: civil.
         */
	public Civil(){
            setID("civil");
            Output.methodStarts(ID,"Civil()");
            Output.methodEnds(ID,"Civil()");
	}

        /**
         * STOP táblával történő interakció (meg kell állnia előtte).
         * @param sign Az adott STOP tábla.
         */
	public void Interaction(StopSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		tickCount+=5;
		MoveTo(ar.roads[plannedDirection]);

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

        /**
         * EXIT táblával történő interakció (nincs hatással rá).
         * @param sign Az adott EXIT tábla.
         */
	public void Interaction(ExitSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

        /**
         * Bankkal történő interakció (nincs hatással rá).
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
         * Közlekedési lámpával interakció (ha piros, nem haladhat tovább).
         * @param lamp Az adott lámpa.
         */
	public void Interaction(Lamp lamp){
		Output.methodStarts(ID,"Interaction("+lamp.toString()+")");

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
		    Output.methodEnds(ID,"Interaction("+lamp.toString()+"):the lamp was Green.");
		    return; //A lámpa nincs ránk hatással, az interakciónak vége.
		}
		else{
		    //Csak eggyel növeljük 
		    tickCount++;
		    Output.methodEnds(ID,"Interaction("+lamp.toString()+"):the lamp was Red.");
		    return;
		}


		
	}

}
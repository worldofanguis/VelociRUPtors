/**
 * Class Robber:
 * A játékos járművét képviselő osztály.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Robber extends Car {

	/**
         * Konstruktor. Az inicializálásnál megjelenő objektumnév: me.
         */
        public Robber(){
            setID("me");
            Output.methodStarts(ID,"Robber()");
            Output.methodEnds(ID,"Robber()");
	}

        /**
         * Ha megkérdezik, hogy rosszfiú-e, be kell ismernie.
         * @return Visszatérési értéke true, mivel le lehet tartóztatni.
         */
	@Override
	public boolean canBeArrested(){
                Output.methodStarts(ID, "canBeArrested()");
                Output.methodEnds(ID,"canBeArrested()","true");
		return true;
	}

        /**
         * STOP táblával történő interakció (nincs hatással rá)
         * @param sign Azon tábla referenciája, amelyikbe "belefutott".
         */
	public void Interaction(StopSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

        /**
         * EXIT táblával történő interakció (nincs hatással rá)
         * @param sign Azon tábla referenciája, amelyikbe "belefutott".
         */
	public void Interaction(ExitSign sign){
		Output.methodStarts(ID,"Interaction("+sign.toString()+")");

		Output.methodEnds(ID,"Interaction("+sign.toString()+")");
	}

        /**
         * A bankkal történő interakció (kirabolja).
         * @param bank Azon bank referenciája, amelyikhez érkezett.
         */
	public void Interaction(Bank bank){
		Output.methodStarts(ID,"Interaction("+bank.toString()+")");
		bank.robBank();
		Output.methodEnds(ID,"Interaction("+bank.toString()+")");
	}

        /**
         * A rejtekhellyel történő interakció (jeleznie kell neki)
         * @param hideout A rejtekhely referenciája, amely mellé ért.
         */
	public void Interaction(Hideout hideout){
		Output.methodStarts(ID,"Interaction("+hideout.toString()+")");
		hideout.arrivedToHideout();
		Output.methodEnds(ID,"Interaction("+hideout.toString()+")");
	}

        /**
         * Forgalomirányító lámpával történő interakció (nincs hatással rá)
         * @param lamp Azon lámpa referenciája, amelyikbe "belefutott".
         */
	public void Interaction(Lamp lamp){
		Output.methodStarts(ID,"Interaction("+lamp.toString()+")");
		
		Output.methodEnds(ID,"Interaction("+lamp.toString()+")");
	}

	@Override
	public boolean Update(){
	    Output.methodStarts(ID,"Update()");

	    if(tickCount > 0) tickCount--;


		// Mozgás plannedDirection felé //
		//Épület ellenőrzése - ez a legfontosabb, az ütközés ellenőrzés jöhet ez után
		Building building;
		if((building = roadUnderMe.hasBuilding()) != null)
			building.whatBuilding(this);

		if(tickCount == 0)
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
}

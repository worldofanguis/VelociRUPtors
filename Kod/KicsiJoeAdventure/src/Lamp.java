/**
 * A közlekedési lámpát reprezentáló osztály.
 */

public class Lamp extends ClassID implements TrafficController {

        /**
         * A lámpa állapota az egyes útszakaszokhoz. (zöld = true)
         */
	private boolean state[] = new boolean[4];

	public Lamp(){

	}

        /**
         * A lámpa állapotát lekérdező függvény.
         * @param Direction A kérdéses irány
         * @return true, ha zöld; false, ha piros.
         */
	public boolean isGreen(Directions Direction){
		String p = new String();
        if(Direction == Directions.LEFT){ p = "LEFT"; }
		else if(Direction == Directions.UP){ p = "UP"; }
		else if(Direction == Directions.RIGHT){ p = "RIGHT"; }
        else if(Direction == Directions.DOWN){ p = "DOWN"; }
		Output.methodStarts(ID,"isGreen(" + p + ")");

                //...valamivel visszatér
                boolean ret = false;
                String p2 = (ret) ? new String("true") : new String("false");

		Output.methodEnds(ID,"isGreen(" + p + ")",p2);
		return ret;
	}

        /**
         * A lámpa állapotát frissítő függvény.
         */
	public void Update(){
		Output.methodStarts(ID,"Update()");

		Output.methodEnds(ID,"Update()");
	}

	/**
         * Az egységes "forgalomirányító-készülék lekérdező" függvény.
         * Meghívja az őt megszólító lámpakezelő függvényét.
         * @param car Aki meghívta.
         */
        public void whatSign(Car car){
		Output.methodStarts(ID, "whatSign("+car.toString()+")");
		car.Interaction(this);
		Output.methodEnds(ID, "whatSign("+car.toString()+")");
	}

}
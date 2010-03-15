<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lamp extends ClassID implements TrafficController {
	//A zöldek helyén igazat ad//
=======
/**
 * A közlekedési lámpát reprezentáló osztály.
 */

public class Lamp extends ClassID implements TrafficController {

        /**
         * A lámpa állapota az egyes útszakaszokhoz. (zöld = true)
         */
>>>>>>> 0602007fd027c5e3f26cc8c253dec11de2690cd7
	private boolean state[] = new boolean[4];

	public Lamp(){

	}

<<<<<<< HEAD
	public boolean isGreen(int Direction){
=======
        /**
         * A lámpa állapotát lekérdező függvény.
         * @param Direction A kérdéses irány
         * @return true, ha zöld; false, ha piros.
         */
	public boolean isGreen(Directions Direction){
>>>>>>> 0602007fd027c5e3f26cc8c253dec11de2690cd7
		String p = new String();

		if(Direction == 0){ p = "LEFT"; }
		else if(Direction == 1){ p = "UP"; }
		else if(Direction == 2){ p = "RIGHT"; }
		else if(Direction == 3){ p = "DOWN"; }

		Output.methodStarts(ID,"isGreen(" + p + ")");

                //...valamivel visszatér
                boolean ret;
		//ret = state[Direction];

		System.out.print("Is the lamp green? y = yes, any other key = no: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("y")) ret = true; else ret = false;

		String s = (ret) ? new String("true") : new String("false");

		Output.methodEnds(ID,"isGreen(" + p + ")",s);
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
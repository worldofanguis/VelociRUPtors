import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
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
	public boolean isGreen(int Direction){
		String p = new String();
        if(Direction == 0){ p = "LEFT"; }
		else if(Direction == 1){ p = "UP"; }
		else if(Direction == 2){ p = "RIGHT"; }
        else if(Direction == 3){ p = "DOWN"; }
		Output.methodStarts(ID,"isGreen(" + p + ")");

		//visszatérési érték
                boolean ret;

		//ret = state[Direction];

		System.out.print("Is the lamp greem? y = yes, any other key = no: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("y")) ret = true; else ret = false;




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
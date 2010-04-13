
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Összehasonlító program. Parancssori argumentumokként
 * kapja meg az összehasonlítandó fájlok elérési címét,
 * amennyiben egyeznek, kiírja, hogy OK, egyébként pedig kiírja
 * az első hibás sort, amit talált.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String kapott;
        String elvart;
        try {
            kapott = args[0];
            elvart = args[1];
            
            BufferedReader result = new BufferedReader(new FileReader(new File(kapott)));
            BufferedReader expected = new BufferedReader(new FileReader(new File(elvart)));

            String lineR, lineE;
            boolean good = true;
            while ( ( ( lineR = result.readLine() ) != null ) && ( ( lineE = expected.readLine() ) != null ) )
            {
                while ( lineR.startsWith("#") || lineR.isEmpty() )
                    lineR = result.readLine();
                while ( lineE.startsWith("#") || lineE.isEmpty() )
                    lineE = expected.readLine();

                if ( lineR.compareTo(lineE) == 0 )
                    continue;
                else {
                    System.out.println("Kapott: " + lineR );
                    System.out.println("Vart: " + lineE );
                    good = false;
                    //System.exit(-1);
                }
            }
            if (good) System.out.println("OK");
            else System.out.println("HIBA");
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Tul keves argumentum");
            System.exit(-1);
        } catch (FileNotFoundException e)
        {
            System.out.println("Nem talalhato fajl");
            System.exit(-1);
        } catch (Exception e)
        {
            System.out.println("Hibbba.");
            System.exit(-1);
        }
        
        //Ize a vegere hogy ne menekuljon el
//        try {
//            int i = System.in.read();
//        } catch (Exception e) {
//            System.out.println("well, duh");
//        }
    }
}

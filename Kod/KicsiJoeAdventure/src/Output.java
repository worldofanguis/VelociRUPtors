
import java.io.PrintStream;

/**
 * Class Output:
 * A terminálon történő megjelenítést kezelő osztály.
 */
public class Output{

    private static PrintStream stream;

    Output() {
        stream = System.out;
    }

    public static void setStream(PrintStream s) {
        stream = s;
    }

    public static void printMap(char[][] Map, int size) {
        for(int ii=0;ii<(size+1)*3;ii++)
            stream.println(Map[ii]);
    }

    public static void print(String s)
    {
        stream.println(s);
    }

}


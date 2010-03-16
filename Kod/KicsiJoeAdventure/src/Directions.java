/**
 * Class Directions:
 * Irányok statikus tárolása és egységes kezelésére szolgáló osztály.
 */
public final class Directions {
        /**
         * Konstans tagváltozó, létrehozás után nem változik.
         */
	public final int value;

        /**
         * Konstruktor
         * @param i Útirány azonosító
         */
	private Directions(int i) {value = i;}

        //A lehetséges útirányok
	public static Directions LEFT = new Directions(0);
	public static Directions UP = new Directions(1);
	public static Directions RIGHT = new Directions(2);
	public static Directions DOWN = new Directions(3);
}
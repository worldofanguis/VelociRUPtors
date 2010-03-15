<<<<<<< HEAD
/**
 * Class Directions:
 * Irányok statikus tárolása és egységes kezelésére szolgáló osztály.
 */
final class Directions {
	public static final int LEFT = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
=======


public final class Directions {
	public final int value;

	private Directions(int i) {value = i;}

	public static Directions LEFT = new Directions(0);
	public static Directions UP = new Directions(1);
	public static Directions RIGHT = new Directions(2);
	public static Directions DOWN = new Directions(3);
>>>>>>> 540c4db1e1375b688418be47cb58912224fe6c06
}
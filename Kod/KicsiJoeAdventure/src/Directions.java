/**
 * Class Directions:
 * Irányok statikus tárolása és egységes kezelésére szolgáló osztály.
 */
public final class Directions {
	public final int value;

	private Directions(int i) {value = i;}

	public static Directions LEFT = new Directions(0);
	public static Directions UP = new Directions(1);
	public static Directions RIGHT = new Directions(2);
	public static Directions DOWN = new Directions(3);
}
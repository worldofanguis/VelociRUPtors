/**
 * Class AvailableRoads:
 * A lehetséges útirányokat tároló osztály.
 */

public class AvailableRoads{

        /**
         * Tömb az utak referenciáinak.
         */
	public Road roads[];

        /**
         * Konstruktor, az adott úthoz hozza létre a lehetséges irányokat.
         * @param road A kapcsolódó utak.
         */
	public AvailableRoads(Road[] road){
		roads = new Road[4];

		roads[0] = road[0];
		roads[1] = road[1];
		roads[2] = road[2];
		roads[3] = road[3];
	}
}
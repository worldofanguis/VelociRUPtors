/**
 * Class AvailableRoads:
 * A lehetséges útirányokat tároló osztály.
 */

public class AvailableRoads extends ClassID {

        /**
         * Tömb az utak referenciának.
         */
	public Road roads[];

        /**
         * Konstruktor, az adott úthoz hozza létre a lehetséges irányokat.
         * @param road A kapcsolódó utak.
         */
	public AvailableRoads(Road[] road){
		setID("AvailableRoads");
		Output.methodStarts(ID,"AvailableRoads({"+road[0]+","+road[1]+","+road[2]+","+road[3]+"})");
		roads = new Road[4];

		roads[0] = road[0];
		roads[1] = road[1];
		roads[2] = road[2];
		roads[3] = road[3];

		Output.methodEnds(ID,"AvailableRoads({"+road[0]+","+road[1]+","+road[2]+","+road[3]+"})");
	}
}
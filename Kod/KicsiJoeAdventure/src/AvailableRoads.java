/**
 * Class AvailableRoads:
 * A lehets�ges �tir�nyokat t�rol� oszt�ly.
 */

public class AvailableRoads extends ClassID {

        /**
         * T�mb az utak referenci�nak.
         */
	public Road roads[];

        /**
         * Konstruktor, az adott �thoz hozza l�tre a lehets�ges ir�nyokat.
         * @param road A kapcsol�d� utak.
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
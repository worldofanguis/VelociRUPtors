

public class AvailableRoads extends ClassID {

	public Road roads[];

	public AvailableRoads(Road[] road){
		setID("AvailableRoads");
		Output.methodStarts(ID,"AvailableRoads(r0:"+road[0]+"r1:"+road[1]+"r2:"+road[2]+"r3:"+road[3]+")");
		roads = new Road[4];

		roads[0] = road[0];
		roads[1] = road[1];
		roads[2] = road[2];
		roads[3] = road[3];

		Output.methodEnds(ID,"AvailableRoads(r0:"+road[0]+"r1:"+road[1]+"r2:"+road[2]+"r3:"+road[3]+")");
	}
}
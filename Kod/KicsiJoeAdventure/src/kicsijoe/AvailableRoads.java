package kicsijoe;

public class AvailableRoads extends ClassID {

	public Road roads[];

	public AvailableRoads(Road[] road){
		Output.methodStarts(ID,"AvailableRoads(" + road.toString() + ")");
		roads = new Road[4];

		roads[0] = road[0];
		roads[1] = road[1];
		roads[2] = road[2];
		roads[3] = road[3];

		Output.methodEnds(ID,"AvailableRoads(" + road.toString() + ")");
	}
}
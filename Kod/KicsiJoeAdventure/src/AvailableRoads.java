

public class AvailableRoads {

	public Road roads[];

	public AvailableRoads(Road[] road){
		Output.methodStarts("AvailableRoads constructor");
		roads = new Road[4];

		roads[0] = road[0];
		roads[1] = road[1];
		roads[2] = road[2];
		roads[3] = road[3];

		Output.methodEnds("AvailableRoads constructor");
	}
}
package kicsijoe;

public class AvailableRoads {

	public Road roads[];

	public AvailableRoads(Road[] road){
		roads = new Road[4];

		roads[0] = road[0];
		roads[1] = road[1];
		roads[2] = road[2];
		roads[3] = road[3];

		System.out.println("AvailableRoads created");
	}
}